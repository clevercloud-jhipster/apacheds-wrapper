package com.clevercloud.apacheds;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ping"}, loadOnStartup = 1)
public class RootServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/WEB-INF/index.html").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
