/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBeans.answer;
import JavaBeans.study;
import JavaBeans.user;
import Utility.answerDB;
import Utility.studyDB;
import Utility.userDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Nitin
 */
@WebServlet(name = "StudyController", urlPatterns = {"/StudyController"})
@MultipartConfig
public class StudyController extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(StudyController.class.getCanonicalName());

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
        try (PrintWriter out = response.getWriter()) {
//            studyDB.setStuies();
            String action = request.getParameter("action");
            HttpSession session = request.getSession(true);
            int BUFFER_LENGTH = 4096;
            user theUser = (user) session.getAttribute("theUser");

            String StudyCode = null;
            String url = null;
            if (action.equals("")) {
                if (theUser == null) {
                    url = "home.jsp";
                } else {
                    url = "main.jsp";
                }
            } else if (action.equals("add")) {
                if (theUser != null) {
                    String studyName = request.getParameter("name");
                    String question = request.getParameter("question");
                    String imageURL = request.getParameter("image");
//                    out.println(imageURL.toString());
                    String noPart = request.getParameter("noPart");
                    String description = request.getParameter("description");
                    int noPa = Integer.parseInt(noPart);
                    if (studyName != "" && question != "" && imageURL != "" && noPart != "" && description != "") {
                        List<study> totalstudies = studyDB.getStudies("All");
                        // out.println(totalstudies.toString());
                        String code = "S" + (totalstudies.size() + 1);
//                        out.print(totalstudies.toString());
                        study Study = new study();
                        Study.setSName(studyName);
                        Study.setSCode(code);
                        Study.setEmail(theUser.getEmail());
                        Study.setDescription(description);
                        Study.setQuestion(question);
                        //Study.setsUrl(imageURL);
                        Study.setReqParticipants(noPa);
                        Study.setActParticipants(0);
                        Study.setDateCreated(new Date().toString());
                        Study.setSStatus("Start");
                        // out.println("asd");
                        

                        for (Part part : request.getParts()) {
                            if (part.getName() != null) {

                                InputStream is = request.getPart(part.getName()).getInputStream();
                                String fileName = getFileName(part);
                                if (fileName != null && !fileName.equalsIgnoreCase("null") && !fileName.equalsIgnoreCase("")) {
                                    fileName = fileName.trim();
                                    fileName = code + fileName.substring(fileName.lastIndexOf("."));

                                    FileOutputStream os = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR") + fileName);
                                    byte[] bytes = new byte[BUFFER_LENGTH];
                                    int read = 0;
                                    while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
                                        os.write(bytes, 0, read);
                                    }
                                    os.flush();
                                    is.close();
                                    os.close();

                                    Study.setImageURL(fileName);

                                }
                            }
                        }

                        //out.println(studyDB.studies.toString());
                        studyDB.addStudty(Study);
                        theUser.setStudies((theUser.getStudies()+1));
                        session.setAttribute("studies_1",theUser.getStudies());
                        userDB.update(theUser);
                        List<study> studies = studyDB.getStudiesFor(theUser.getEmail());
                        request.setAttribute("studies", studies);
                        // out.println(studies.toString());
                        url = "studies.jsp";
                    } else {
                        request.setAttribute("msg", "Enter all required data.");
                        url = "newstudy.jsp";
                    }
                } else {
                    out.println("else");
                }
            } else if (action.equals(
                    "Start")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);
                        out.println(s.toString());
                        s.setSStatus("Stop");
                        studyDB.updateStudy(s);
                        List<study> studies = studyDB.getStudiesFor(theUser.getEmail());
                        request.setAttribute("studies", studies);
                        url = "studies.jsp";
                    } else {
                        out.println("else");
                    }
                } else {
                    url = "login.jsp";
                }
            } else if (action.equals(
                    "Stop")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);
                        s.setSStatus("Start");
                        studyDB.updateStudy(s);
                        List<study> studies = studyDB.getStudiesFor(theUser.getEmail());
                        request.setAttribute("studies", studies);
                        url = "studies.jsp";
                    }
                } else {
                    url = "login.jsp";
                }
            } else if (action.equals(
                    "answer")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);


                        String rating = request.getParameter("rating");
                        answer Answer = new answer();
                        Answer.setSCode(StudyCode);
                        Answer.setChoice(Integer.parseInt(rating));
                        Answer.setEmail(theUser.getEmail());
                        Answer.setDateSubmitted(new Date().toString());
                        answerDB.updateAnswer(Answer);

                       
                        theUser.setCoins((theUser.getCoins() + 1));
                        theUser.setParticipation((theUser.getParticipation() + 1));
                        userDB.update(theUser);
                        session.setAttribute("coins", theUser.getCoins());
                        session.setAttribute("participation", theUser.getParticipation());
                        session.setAttribute("studies_1", theUser.getStudies());
                            
                        List<study> studies = studyDB.getStudies("Open");
                        request.setAttribute("studies", studies);
                        url = "participate.jsp";

                    } else {
                        out.println("else//");
                    }
                } else {
                    url = "login.jsp";
                }
            } else if (action.equals(
                    "update")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);
                        String studyName = request.getParameter("name");
                        String question = request.getParameter("question");
                        String imageURL = request.getParameter("image");
                        String noPart = request.getParameter("noPart");
                        String description = request.getParameter("description");
                        int noPa = Integer.parseInt(noPart);
                        if (studyName == "" || question == "" || imageURL == "" || noPart == "" || description == "") {
                            request.setAttribute("msg", "Enter all required data.");
                            url = "studies.jsp/StudyController?action=edit";
                        } else {
                            s.setSName(studyName);
                            s.setEmail(theUser.getEmail());
                            s.setDescription(description);
                            s.setQuestion(question);
                            for (Part part : request.getParts()) {
                                if (part.getName() != null) {

                                    InputStream is = request.getPart(part.getName()).getInputStream();
                                    String fileName = getFileName(part);
                                    if (fileName != null && !fileName.equalsIgnoreCase("null") && !fileName.equalsIgnoreCase("")) {
                                        fileName = fileName.trim();
                                        fileName = StudyCode + fileName.substring(fileName.lastIndexOf("."));

                                        FileOutputStream os = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR") + fileName);
                                        byte[] bytes = new byte[BUFFER_LENGTH];
                                        int read = 0;
                                        while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
                                            os.write(bytes, 0, read);
                                        }
                                        os.flush();
                                        is.close();
                                        os.close();

                                        s.setImageURL(fileName);

                                    }
                                }
                            }
                            s.setReqParticipants(noPa);
                            s.setDateCreated(new Date().toString());
                            s.setSStatus("Start");
                            studyDB.updateStudy(s);
                            List<study> studies = studyDB.getStudiesFor(theUser.getEmail());
                            request.setAttribute("studies", studies);
                            url = "studies.jsp";
                        }
                    } else {
                        out.println("else");
                    }
                } else {
                    url = "login.jsp";
                }
            } else if (action.equals(
                    "edit")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);
                        request.setAttribute("name", s.getSName());
                        request.setAttribute("question", s.getQuestion());
                        request.setAttribute("description", s.getDescription());
                        request.setAttribute("url", s.getImageURL());
                        request.setAttribute("code", StudyCode);
                        request.setAttribute("noPart", s.getReqParticipants());
                        url = "editstudy.jsp";
                    }
                } else {
                    url = "login.jsp";
                }

            } else if (action.equals(
                    "participate")) {
                if (theUser != null) {
                    StudyCode = request.getParameter("code");
                    if (StudyCode != null) {
                        study s = studyDB.getStudy(StudyCode);
                        request.setAttribute("code", StudyCode);
                        request.setAttribute("question", s.getQuestion());
                        request.setAttribute("url", s.getImageURL());
                        s.setActParticipants(s.getActParticipants()+1);
                        studyDB.updateStudy(s);
                        url = "question.jsp";
                    } else {
                        List<study> studies = studyDB.getStudies("Open");
                        request.setAttribute("studies", studies);
                        out.println(studies.toString());
                        url = "participate.jsp";

                    }
                } else {
                    url = "login.jsp";
                }
            } else if (action.equals(
                    "view")) {
                if (theUser != null) {
                    List<study> studies = studyDB.getStudiesFor(theUser.getEmail());
                    request.setAttribute("studies", studies);
                    url = "studies.jsp";
                } else {
                    url = "login.jsp";
                }
            }

            request.getRequestDispatcher(url)
                    .forward(request, response);
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

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
