<%-- 
    Document   : listAppointments
    Created on : 18 ene 2024, 18:44:36
    Author     : jeiny
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.appointmentapp.logic.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="all" href="../CSS/style.css" />
        <title>SGE</title>
    </head>
    <body>
        <div class="container-wrapper">
            <div class="feedback-card">
                <div class="feedback-header">
                    <h1>FILTER LIST</h1>
                </div>
                <form class="feedback-body" action="../SvFilterList" method="GET">
                    <div> 
                        <label>DATE</label>
                        <input type="date" id="dateFilter" name="dateFilter" required>
                    </div>
                    </br>
                    <div> 
                        <input type="radio" value="Waiting" name="status">
                        <label>Waiting</label>
                    </div>
                    <div> 
                        <input type="radio" value="Attended" name="status">
                        <label>Attended</label> 
                    </div>
                    <button type="submit" class="">FILTER</button>
                </form>
            </div>
            <div class="feedback-card">
                <div class="feedback-header">              
                    <h1>LIST OF APPOINTMENTS</h1>
                </div>
                <table id="appointmentsList" border="1">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>USER</th>
                            <th>DATE</th>
                            <th>STATUS</th>
                            <th>PAPERWORK</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Appointment> appointments = (List<Appointment>) session.getAttribute("appointments");
                        %>
                        <% if (appointments != null) {%>
                        <%for (Appointment appointment : appointments) {%>
                        <tr>
                            <td><%=appointment.getId()%></td>
                            <td><%=appointment.getUser().getName()%></td>
                            <td><%=appointment.getDate()%></td>
                            <td><%=appointment.isStatus()%></td>
                            <td><%=appointment.getPaperwork().getName()%></td>  
                        <tr>
                            <%}%>
                            <%} else {%>
                    <h2>There are no appointments yet</h2>
                    <%}%>
                    </tbody>  
                </table>
            </div>
        </div>
        </br>
        </br>
        <div class="feedback-card">
            <form class="feedback-body" action="../SvKillSession" method="POST">
                <input type="submit" value="RETURN">
                <label></label>
            </form>
        </div>
    </body>
</html>
