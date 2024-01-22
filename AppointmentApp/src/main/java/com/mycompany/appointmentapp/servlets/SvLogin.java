package com.mycompany.appointmentapp.servlets;

import com.mycompany.appointmentapp.logic.Appointment;
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
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    LogicController controller = new LogicController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        final int ADMIN_SECRET_NUMBER = 321;

        int secretNumber = Integer.parseInt(request.getParameter("secretNumber"));
        SecretUser loggedUser = controller.getLoggedUser(secretNumber);
        
        HttpSession existingSession = request.getSession(false);
        if (existingSession != null) {
            existingSession.invalidate();
        }

        if (loggedUser != null) {
            if (loggedUser.getSecretNumber() == ADMIN_SECRET_NUMBER) {
                HttpSession sessionAdmin = request.getSession(true);
                List<Appointment> appointments = controller.getAppointmentList();
                sessionAdmin.setAttribute("appointments", appointments);
                response.sendRedirect(request.getContextPath() + "/JSP/appointmentListAdmin.jsp");
            } else {
                HttpSession sessionUser = request.getSession();
                sessionUser.setAttribute("secretNumber", secretNumber);
                response.sendRedirect(request.getContextPath() + "/JSP/newAppointment.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/JSP/notExist.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
