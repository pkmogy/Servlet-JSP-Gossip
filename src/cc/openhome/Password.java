package cc.openhome;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/password")
public class Password extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        //隨機生成數字
        String passwd=new Random().ints(0,10)
                                  .limit(4)
                                  .mapToObj(String::valueOf)
                                  .collect(Collectors.joining());
        //實際場合必須將產生的驗證碼存在某個地方，以供之後比對
        ImageIO.write(
                passwordImage(passwd),
                "JPG",
                response.getOutputStream()
        );
    }

    private BufferedImage passwordImage(String password) throws IOException{
        //建立圖片緩存
        BufferedImage bufferedImage=new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
        //建立畫布
        Graphics g=bufferedImage.getGraphics();
        //設定顏色
        g.setColor(Color.WHITE);
        //設定字型樣式
        g.setFont(new Font("標楷體",Font.BOLD,16));
        //寫入(畫)字串
        g.drawString(password,10,15);
        return bufferedImage;
    }
}
