package com.sda.twitter.filters;

import com.sda.twitter.persistance.entities.TbUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/add-article.jsp")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        TbUser currentUser = (TbUser) session.getAttribute("currentUser");
        if (currentUser == null) {

            final String requestURI = request.getRequestURI();
            request.setAttribute("cuccrentURI", requestURI);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, response);
        }
        chain.doFilter(req, response);
    }
}
