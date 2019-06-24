package cc.openhome;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns ={"/login"},
            initParams = {
                @WebInitParam(name="success",value = "user"),
                @WebInitParam(name = "error",value = "login.jsp")
            })
public class Login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException{
        super.init();
        SUCCESS_VIEW=this.getInitParameter("success");
        ERROR_VIEW=this.getInitParameter("error");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String passwd=request.getParameter("passwd");
        if ("leelo".equals(name)&&"123456".equals(passwd)){
            //實作cookie
            //Cookie cookie=new Cookie("user",name);
            //cookie.setMaxAge(7*24*60*60);
            //response.addCookie(cookie);
            //response.sendRedirect("user");
            //實作Session
            if(request.getSession(false) != null) {
                changeSessionId(request);
            }
            request.getSession().setAttribute("login", name);
            response.sendRedirect(SUCCESS_VIEW);
        }
        else{
            response.sendRedirect(ERROR_VIEW);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void changeSessionId(HttpServletRequest request) {
        HttpSession oldSession = request.getSession();

        Map<String, Object> attrs = new HashMap<>();
        for(String name : Collections.list(oldSession.getAttributeNames())) {
            attrs.put(name, oldSession.getAttribute(name));
            oldSession.removeAttribute(name);
        }
        oldSession.invalidate(); // 令目前 Session 失效

        HttpSession newSession = request.getSession();
        for(String name : attrs.keySet()) {
            newSession.setAttribute(name, attrs.get(name));
        }
    }
}
