package cc.openhome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.io.*;

@WebServlet("/headers")
public class Headers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ShowHeader</title>");
        out.println("</head>");
        out.println("<body>");
        //將標頭存成list,使用foreach讀出來
        Collections.list(request.getHeaderNames())
                .forEach(name -> {
                    out.printf("%s: %s<br>%n", name, request.getHeader(name));
                });

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
