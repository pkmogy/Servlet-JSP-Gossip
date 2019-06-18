package cc.openhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Upload",urlPatterns = {"/upload"})
public class Upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        byte[] content = bodyContent(request);
        String contentAsTxt=new String(content,"ISO-8859-1");

        String filename=filename(contentAsTxt);
        Range fileRange=fileRange(contentAsTxt,request.getContentType());

        write(
                content,
                contentAsTxt.substring(0,fileRange.start)
                .getBytes("ISO-8859-1")
                .length,
                contentAsTxt.substring(0,fileRange.end)
                .getBytes("ISO-8859-1")
                .length,
                "c:/workspace/"+filename
        );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private byte[] bodyContent(HttpServletRequest request) throws IOException{
        //暫存byte陣列
        try(ByteArrayOutputStream out =new ByteArrayOutputStream()){
            InputStream in =request.getInputStream();
            //緩衝
            byte[] buffer= new byte[1024];
            int length =-1;
            while ((length=in.read(buffer))!=-1){
                out.write(buffer,0,length);
            }
            //轉成位元陣列
            return out.toByteArray();
        }
    }

    private String filename(String contentTxt) throws UnsupportedEncodingException{
        //正規表示法
        //設置條件
        Pattern pattern=Pattern.compile("filename=\"(.*)\"");
        //設置範圍
        Matcher matcher=pattern.matcher(contentTxt);
        //執行
        matcher.find();
        //取得檔名
        return matcher.group(1);
    }

    private static class Range{
        final int start;
        final int end;
        public Range(int start,int end){
            this.start=start;
            this.end=end;
        }
    }

    private Range fileRange(String content,String contentType){
        Pattern pattern = Pattern.compile("filename=\".*\"\\r\\n.*\\r\\n\\r\\n(.*+)");
        Matcher matcher = pattern.matcher(content);
        matcher.find();
        //索引
        int start=matcher.start(1);
        //lastIndexOf取得=的索引
        //substring抓=後面的字串
        String boundary=contentType.substring(
                contentType.lastIndexOf("=")+1,contentType.length());
        //indexOf(查詢字串,查詢起始位置)
        int end=content.indexOf(boundary,start)-4;

        return new Range(start,end);
    }

    private void write(byte[] content,int start,int end,String file) throws IOException{
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content, start, (end - start));
        }
    }
}
