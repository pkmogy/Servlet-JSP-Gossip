package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebFilter("/*")
public class PerformanceFilter extends HttpFilter {
    @Override
    protected void doFilter(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long begin = System.currentTimeMillis();
        chain.doFilter(request, response);
        getServletContext().log("Request process in " +
                (System.currentTimeMillis() - begin) + " milliseconds");
    }
}