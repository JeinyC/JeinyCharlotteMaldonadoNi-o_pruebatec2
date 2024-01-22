package com.mycompany.appointmentapp.servlets;

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
@WebServlet(name = "SvInit", urlPatterns = {"/SvInit"})
public class SvInit extends HttpServlet {
    
    LogicController logicController = new LogicController();

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

        if (!logicController.paperworkExists(1)) {
            logicController.initPaperwork("Project Hidden Cipher");
            logicController.initPaperwork("Oracle Program");
            logicController.initPaperwork("Operation Silence");
        }
        if (logicController.getLoggedUser(ADMIN_SECRET_NUMBER) == null){
            logicController.addAdmin(ADMIN_SECRET_NUMBER);
        }
        response.sendRedirect(request.getContextPath() + "/JSP/loginMenu.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
