<%-- 
    Document   : login
    Created on : 20 ene 2024, 23:53:16
    Author     : jeiny
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>SGE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="all" href="../CSS/style.css" />
    </head>
    <body>
        <header>
            <%
                String notSecretMessage = (String) session.getAttribute("notSecret");
                if (notSecretMessage != null) {
            %>
            <div class="alert alert-danger">
                <%= notSecretMessage%>
            </div>
            <%
                    session.removeAttribute("notSecret");
                }
            %>
        </header>
        <div class="container-wrapper">
            <div class="feedback-card">
                <div class="feedback-header">
                    <h1>LOGIN</h1>
                </div>
                <form id="login" class="feedback-body" action="../SvLogin"  method="POST">

                    <div class="containerUser">
                        <label id="sn" class="feedback-body__message">Secret Number</label>
                    </div>
                    <input id="sn" type="number" name="secretNumber" required>

                    <button type="submit" class="feedback-body_button">SEND</button>
                </form>
            </div>
            <div class="feedback-card">
                <div class="feedback-header">
                    <h1>SIGN UP</h1>
                </div>
                <form id="signup" class="feedback-body" action="../SvSigUp"  method="POST">

                    <div class="containerUser">
                        <label id="sn" class="feedback-body__message">Secret Number</label>
                    </div>
                    <input id="sn" type="number" name="secretNumber" required>

                    <div class="containerUser">
                        <label id="name" class="feedback-body__message">Name</label>
                    </div>
                    <input id="name" type="text" name="name" required>

                    <div class="containerUser">
                        <label id="age" class="feedback-body__message">Age</label>
                    </div>
                    <input id="age" type="number" name="age" required>

                    <button type="submit" class="feedback-body_button">SEND</button>
                </form>
            </div>
        </div>
        <div class="feedback-card">
            <form class="feedback-body" action="../SvKillSession" method="POST">
                <input type="submit" value="RETURN">
                <label></label>
            </form>
        </div>
    </body>
</html>
