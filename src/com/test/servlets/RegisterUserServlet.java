package com.test.servlets;

import com.test.beans.User;
import com.test.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String activity = req.getParameter("activity");
        int age = Integer.parseInt(req.getParameter("age"));

        User user = new User(username, password, firstName, lastName, age, activity);

        ApplicationDao dao = new ApplicationDao();
        int rows = dao.registerUser(user);

        String infoMessage = null;
        if (rows == 0){
            infoMessage = "Sorry, an error occurred!";
        }else {
            infoMessage = "User registered successfully";
        }

        String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), infoMessage);
        res.getWriter().write(page);
    }

    public String getHTMLString(String filePath, String message) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line= reader.readLine()) != null){
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, message);

        return page;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), "");
        resp.getWriter().write(page);
    }
}
