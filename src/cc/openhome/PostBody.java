package cc.openhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/postbody")
public class PostBody extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println(bodyContent(request.getReader()));
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String bodyContent(BufferedReader reader) throws IOException{
        String input =null;
        StringBuffer requestBody=new StringBuffer();
        while ((input=reader.readLine()) != null){
            requestBody.append(input)
                       .append("<br>");
        }
        return requestBody.toString();
    }
}
