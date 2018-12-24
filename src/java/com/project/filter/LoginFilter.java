/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.filter;

import com.project.bean.LoginController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author test
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        LoginController session = (LoginController) req.getSession().getAttribute("loginController");

        String url = req.getRequestURI();

        if (session == null || !session.isLogged) {
            // Giris yapilmamissa ve forum.xhtml ya da logout.xhtml request'i gerceklesirse
            if (url.contains("forum.xhtml") || url.contains("logout.xhtml") || url.equals(req.getContextPath() + "/") || url.contains("dashboard.xhtml")) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            }
        } else {
            // Giris yapilmissa ve register.xhtml ya da login.xhtml request'i gerceklesirse
            if (url.contains("register.xhtml") || url.contains("login.xhtml")) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/forum.xhtml");
            } // Giris yapilmissa ve logout.xhtml request'i gerceklesirse
            else if (url.contains("logout.xhtml")) {
                req.getSession().removeAttribute("loginController");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            }
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
