/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Utility.EmailUtility;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String recipient = request.getParameter("recipient");
        String subject = "System Genrated Email";
        String content = "Mail from " + request.getParameter("user") + "(" + request.getParameter("useremail") + ")" + "\n" + request.getParameter("content") +"\n Click on link to Sign Up: http://localhost:8084/Project/singip.jsp";

        String resultMessage = "";
        String url = null;

        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            
            if (request.getParameter("useremail").equals("")) {
                url = "/confirmc.jsp";
            } else {
                url = "http://localhost:8084/Project/UserController?action=recommend&useremail=" + request.getParameter("useremail") + "&recipient=" + recipient;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher(url).forward(
                    request, response);
        }
    }
}
