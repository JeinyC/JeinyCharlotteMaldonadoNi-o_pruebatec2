<%-- 
    Document   : newPaperwork
    Created on : 18 ene 2024, 2:54:26
    Author     : jeiny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="all" href="../CSS/style.css" />
        <title>SGE</title>
    </head>
    <body>
        <div class="feedback-card">
            <div class="feedback-header">
                <h1>SELECT A PAPERWORK</h1>
            </div>
            <form class="feedback-body" action="../SvAppointment"  method="POST">
                
                <div class="feedback-body__item">
                    <label class="feedback-body__message">Project Hidden Cipher</label>
                    <input type="radio" value="1" name="paperWork" class="radio-input">
                </div>

                <div class="feedback-body__item">
                    <label class="feedback-body__message">Oracle Program</label>
                    <input type="radio" value="2" name="paperWork" class="radio-input">
                </div>

                <div class="feedback-body__item">
                    <label class="feedback-body__message">Operation Silence</label>
                    <input type="radio" value="3" name="paperWork" class="radio-input">
                </div>
                
                <button type="submit" class="feedback-body_button">SEND</button>
            </form>
        </div>
    </body>
</html>
