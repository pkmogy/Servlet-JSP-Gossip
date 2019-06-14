package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/*")
public class path extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletMapping mapping =request.getHttpServletMapping();

        PrintWriter out =response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>Path Servlet</title>");
        out.println("    </head>");
        out.println("    <body>");
        //取得整個路徑
        out.println(request.getRequestURI() + "<br>");
        //取得環境路徑
        out.println(request.getContextPath() + "<br>");
        //取得servlet路徑
        out.println(request.getServletPath() + "<br>");
        //取得classes路徑
        out.println(request.getPathInfo()+ "<br>");
        //取得列舉值
        out.println(mapping.getMappingMatch() + "<br>");
        //取得實際符合對比值
        out.println(mapping.getMatchValue() + "<br>");
        //取得servlet設定值
        out.println(mapping.getPattern());
        out.println("    </body>");
        out.println("</html>");
    }
}
