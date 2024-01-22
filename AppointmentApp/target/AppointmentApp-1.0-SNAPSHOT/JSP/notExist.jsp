<%-- 
    Document   : notExist
    Created on : 21 ene 2024, 6:24:51
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
        <div class="alert alert-danger">
            <h1>YOU DO NOT EXIST</h1>
            <form action="../SvKillSession" method="POST">
                <input type="submit" value="EXIT">
                <label></label>
            </form>
        </div>
    </body>
</html>
