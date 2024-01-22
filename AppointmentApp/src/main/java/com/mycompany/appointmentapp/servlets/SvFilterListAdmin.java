package com.mycompany.appointmentapp.servlets;

import com.mycompany.appointmentapp.logic.Appointment;
import com.mycompany.appointmentapp.logic.LogicController;
import com.mycompany.appointmentapp.utils.DateUtil;
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
@WebServlet(name = "SvFilterListAdmin", urlPatterns = {"/SvFilterListAdmin"})
public class SvFilterListAdmin extends HttpServlet {

    LogicController controller = new LogicController();
    DateUtil dateUtil = new DateUtil();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String dateFilter = request.getParameter("dateFilter");
        String status = request.getParameter("status");
        String dateFilterNewFormat = dateUtil.getFormattedDateFromString(dateFilter);

        HttpSession session = request.getSession();
        List<Appointment> appointmentsFilterList = controller.getAppointmentFilterAdminView(dateFilterNewFormat, status);
        session.setAttribute("appointments", appointmentsFilterList);
        response.sendRedirect(request.getContextPath() + "/JSP/appointmentListAdmin.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
