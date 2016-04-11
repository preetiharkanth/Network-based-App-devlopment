/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBeans.recommend;
import JavaBeans.study;
import JavaBeans.tempuser;
import JavaBeans.user;
import Utility.EmailUtility;
import Utility.TempUserDB;
import Utility.generatePass;
import Utility.studyDB;
import Utility.userDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.apache.poi.util.IOUtils;

/**
 *
 * @author Nitin
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        String resultMessage = "";
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            HttpSession session = request.getSession(true);

            if (action.equals("logout")) {
                session.setAttribute("theUser", null);
                session.removeAttribute("coins");
                session.removeAttribute("studies_1");
                session.removeAttribute("participation");
                RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
            } else {

                if (action.equals("login")) {
                    String email = request.getParameter("username");
                    String pwd = request.getParameter("password");
                    user theUser = userDB.validateUser(email, pwd);

                    if (theUser != null) {
                        session.setAttribute("theUser", theUser);
                        session.setAttribute("uname", theUser.getUName());
                        session.setAttribute("coins", theUser.getCoins());
                        session.setAttribute("studies_1", theUser.getStudies());
                        session.setAttribute("participation", theUser.getParticipation());
                        RequestDispatcher rd1 = request.getRequestDispatcher("main.jsp");
                        rd1.forward(request, response);
                    } else {
                        request.setAttribute("msg", "Email/Password do not macth.");
                        RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
                        rd1.forward(request, response);
                    }

                } else if (action.equals("signup")) {
                    String pwd = request.getParameter("password");
                    String cpwd = request.getParameter("confirmpassword");
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");

                    //user newUser = new user();
                    tempuser newTempUser = new tempuser();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    Calendar calobj = Calendar.getInstance();

                    if (pwd != "" || cpwd != "" || name != "" || email != "") {

                        if (pwd.equals(cpwd)) {

                            if (userDB.emailExists(email)) {
                                request.setAttribute("msg", "Email Already Exits.");
                                RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");
                                rd.forward(request, response);

                            } else {

                                recommend rUser = userDB.getRUser(email);
                                if (rUser != null) {
                                    user u = userDB.getUser(rUser.getExistingUserEmail());
                                    u.setCoins((u.getCoins() + 2));
                                    userDB.update(u);
                                    userDB.rdelete(rUser);
                                }
                                newTempUser.setUName(name);
                                newTempUser.settempuserEmail(email);
                                newTempUser.setPassword(cpwd);
                                newTempUser.setIssueDate(dateFormat.format(date));
                                SecureRandom random = new SecureRandom();
                                byte bytes[] = new byte[20];
                                random.nextBytes(bytes);
                                String token = bytes.toString();
                                newTempUser.setToken(token);
                                TempUserDB.addTempUser(newTempUser);
                                String acturl = "http://assignment2-pharkant.rhcloud.com/Project/UserController?action=activate&email=" + email + "&token=" + token;
                                try {
                                    EmailUtility.sendEmail(host, port, user, pass, email, "Activation Mail", acturl);
                                    url = "/login.jsp";

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    resultMessage = "There were an error: " + ex.getMessage();
                                } finally {
                                    //request.setAttribute("Message", resultMessage);
                                    getServletContext().getRequestDispatcher(url).forward(request, response);
                                }
                            }

//                                
                        } else {
                            request.setAttribute("msg", "Passwords doesn't match.");
                            RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("msg", "Enter required data.");
                        RequestDispatcher rd = request.getRequestDispatcher("Signup.jsp");
                        rd.forward(request, response);
                    }

                } else if (action.equals("recommend")) {
                    String newUser = request.getParameter("recipient");
                    String existUser = request.getParameter("useremail");
                    if (!userDB.emailExists(newUser)) {
                        recommend ru = new recommend();
                        ru.setExistingUserEmail(existUser);
                        ru.setNewUserEmail(newUser);
                        userDB.radd(ru);
                        String acturl = "http://assignment2-pharkant.rhcloud.com/Project/Signup.jsp";
                        try {
                            EmailUtility.sendEmail(host, port, user, pass, newUser, "Activation Mail", acturl);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            resultMessage = "There were an error: " + ex.getMessage();
                        } finally {
                            //request.setAttribute("Message", resultMessage);
                            RequestDispatcher rd = request.getRequestDispatcher("confirmr.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("msg", "User Exist.");
                        RequestDispatcher rd = request.getRequestDispatcher("recommend.jsp");
                        rd.forward(request, response);
                    }

                } else if (action.equals("activate")) {
                    user newUser = new user();
                    String token = request.getParameter("token");
                    String email = request.getParameter("email");
                    tempuser tempU = TempUserDB.getUser(token, email);
                    if (tempU != null) {
                        newUser.setUName(tempU.getUName());
                        newUser.setEmail(tempU.gettempuserEmail());
                        try {
                            newUser.setPassword(generatePass.createHash(tempU.getPassword()));
                            //    newUser.setSalt(salt1);
                        } catch (Exception e) {
                        }
                        newUser.setCoins(0);
                        newUser.setParticipation(0);
                        newUser.setStudies(0);
                        session.setAttribute("theUser", newUser);
                        session.setAttribute("coins", newUser.getCoins());
                        session.setAttribute("participation", newUser.getParticipation());
                        session.setAttribute("studies_1", 0);
                        session.setAttribute("uname", newUser.getUName());
                        userDB.addUser(newUser);
                        TempUserDB.delete(tempU);
                        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                        rd.forward(request, response);

                    }

                } else if (action.equals("reset")) {
                    String email = request.getParameter("email");
                    String reurl = null;
                    String reresultMessage = null;
                    if (userDB.emailExists(email)) {
                        String Acturl = "http://assignment2-pharkant.rhcloud.com/Project/resetpwd.jsp?&email=" + email;
                        try {
                            EmailUtility.sendEmail(host, port, user, pass, email, "Password set", Acturl);
                            url = "/emailSent.jsp";
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            resultMessage = "There were an error: " + ex.getMessage();
                        } finally {
                            request.setAttribute("Message", resultMessage);
                            getServletContext().getRequestDispatcher(url).forward(
                                    request, response);
                        }

                    }

                } else if (action.equals("newpassword")) {
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String confirmpassword = request.getParameter("confirmpassword");
                    if (password.equals(confirmpassword)) {
                        user u = userDB.getUser(email);
                        if (u != null) {
                            u.setPassword(password);
                        } else {
                            request.setAttribute("msg", "No user found");
                        }
                        userDB.update(u);
                        getServletContext().getRequestDispatcher("/login.jsp").forward(
                                request, response);

                    } else {

                        request.setAttribute("msg", "Password doesn't match");

                        getServletContext().getRequestDispatcher("/email.jsp").forward(
                                request, response);
                    }
                } else if (action.equals("how")) {
                    if (session.getAttribute("theUser") == null) {
                        RequestDispatcher rd = request.getRequestDispatcher("how.jsp");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                        rd.forward(request, response);
                    }
                } else if (action.equals("about")) {
                    if (session.getAttribute("theUser") == null) {
                        RequestDispatcher rd = request.getRequestDispatcher("about.jsp");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("aboutl.jsp");
                        rd.forward(request, response);
                    }
                } else if (action.equals("home")) {
                    if (session.getAttribute("theUser") == null) {
                        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                        rd.forward(request, response);
                    }
                } else if (action.equals("main")) {
                    if (session.getAttribute("theUser") == null) {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                        rd.forward(request, response);
                    }
                }

            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
