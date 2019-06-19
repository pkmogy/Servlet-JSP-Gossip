package cc.openhome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet("/upload2")
public class Upload2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getParts()
                .stream()
                .filter(part2 -> !"upload".equals(part2.getName()))
                .forEach(this::write);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void write(Part part){
        String filename=part.getSubmittedFileName();

        try(InputStream in=part.getInputStream();
            OutputStream out=new FileOutputStream("c:/workspace/" + filename)){
            byte[] buffer=new byte[1024];
            int length=-1;
            while ((length=in.read(buffer))!=-1){
                out.write(buffer,0,length);
            }
        }catch(IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
