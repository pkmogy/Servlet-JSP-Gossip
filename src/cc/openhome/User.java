package cc.openhome;

import java.io.*;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "User",urlPatterns = {"/user"})
public class User extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得cookie，並判斷是否為空值
        /*
        Optional<Cookie> userCookic=Optional.ofNullable(request.getCookies())
                                            .flatMap(this::userCookie);
        if(userCookic.isPresent()){
            Cookie cookie=userCookic.get();
            request.setAttribute(cookie.getName(),cookie.getValue());
            userHtml(request,response);
        }else{
            response.sendRedirect("login.jsp");
        }*/
        HttpSession session = request.getSession();
        Optional<Object> token = Optional.ofNullable(session.getAttribute("login"));

        if(token.isPresent()) {
            userHtml(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    private Optional<Cookie> userCookie(Cookie[] cookies){
        return Stream.of(cookies)
                .filter(cookie -> "user".equals(cookie.getName()) && "leelo".equals((cookie.getValue())))
                .findFirst();
    }

    private void userHtml(HttpServletRequest request,HttpServletResponse response) throws SecurityException,IOException{
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + request.getSession().getAttribute("login") + "已登入</h1>");
        out.println("<a href='logout'>登出</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
