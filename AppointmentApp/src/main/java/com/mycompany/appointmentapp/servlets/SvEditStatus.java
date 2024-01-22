package com.mycompany.appointmentapp.servlets;

import com.mycompany.appointmentapp.logic.Appointment;
import com.mycompany.appointmentapp.logic.LogicController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeiny
 */
@WebServlet(name = "SvEditStatus", urlPatterns = {"/SvEditStatus"})
public class SvEditStatus extends HttpServlet {

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

        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        Appointment Appointment = controller.getAppointmentById(appointmentId);

        controller.setAppointmentState(Appointment);
        response.sendRedirect(request.getContextPath() + "/JSP/appointmentListAdmin.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
