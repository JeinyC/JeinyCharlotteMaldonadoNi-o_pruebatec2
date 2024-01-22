package com.mycompany.appointmentapp.servlets;

import com.mycompany.appointmentapp.logic.Appointment;
import com.mycompany.appointmentapp.logic.LogicController;
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
@WebServlet(name = "SvAppointment", urlPatterns = {"/SvAppointment"})
public class SvAppointment extends HttpServlet {

    LogicController controller = new LogicController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession existingSession = request.getSession(false);
        if (existingSession != null) {
            existingSession.invalidate();
        }
        
        HttpSession session = request.getSession(true);
        List<Appointment> appointments = controller.getAppointmentList();
        session.setAttribute("appointments", appointments);
        response.sendRedirect(request.getContextPath() + "/JSP/appointmentsList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();

        int idPaperwork = Integer.parseInt(request.getParameter("paperWork"));
        int userSecretNumber = (int) session.getAttribute("secretNumber");
        controller.addAppointment(idPaperwork, userSecretNumber);

        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
