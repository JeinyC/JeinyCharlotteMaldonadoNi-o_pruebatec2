package com.mycompany.appointmentapp.servlets;

import com.mycompany.appointmentapp.logic.LogicController;
import com.mycompany.appointmentapp.logic.SecretUser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeiny
 */
@WebServlet(name = "SvSigUp", urlPatterns = {"/SvSigUp"})
public class SvSignUp extends HttpServlet {

    LogicController controller = new LogicController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<SecretUser> userList = controller.getUserList();

        HttpSession session = request.getSession();
        session.setAttribute("secretUserInfo", userList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int secretNumber = Integer.parseInt(request.getParameter("secretNumber"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        SecretUser signUser = controller.getUser(secretNumber);
        

        HttpSession existingSession = request.getSession(false);
        if (existingSession != null) {
            existingSession.invalidate();
        }

        if (signUser == null) {
            HttpSession sessionUser = request.getSession(true);
            sessionUser.setAttribute("secretNumber", secretNumber);
            controller.addUser(secretNumber, name, age);
            response.sendRedirect(request.getContextPath() + "/JSP/newAppointment.jsp");
        } else {
            HttpSession sessionAlert = request.getSession(true);
            sessionAlert.setAttribute("notSecret", "WE HAVE SEEN YOU ON CAMERA AND YOU ARE NOT ENOUGH FOR THIS SECRET PROGRAM");
            response.sendRedirect(request.getContextPath() + "/JSP/loginMenu.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
