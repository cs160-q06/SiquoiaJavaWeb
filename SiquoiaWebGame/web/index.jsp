<%-- 
    Document   : index.jsp
    Created on : Dec 6, 2013, 2:30:40 PM
    Author     : mr.nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />
        
    </head>
    <body>
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <ul class="nav pull-left">
                        <li>
                            <img class="centre" id="topBar" border="0" src="img/SiQuoia logo.jpg">
                        </li>
                        <li class="divider-vertical"> </li>

                    </ul>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-center">
                            <li>
                                <h2>Registration</h2>
                            </li>
                        </ul>
                        <ul class="nav pull-right">
                            <li><a href="home.html"><i class="icon-white icon-lock"></i> Logout</a>
                            </li></ul>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="login">

                <div class="small-link">
                    <a href="#">Create account</a>
                </div>

                <form class="loginfield" action="login1.jsp" method="post">
                    <div>
                        <%= (session.getAttribute("error") != null
                                ? (String) session.getAttribute("error") : "")%>
                    </div>   
                    <p>
                        <label for="username">Username:</label>
                        <label id="error"> <%= (session.getAttribute("username_error") != null
                                ? (String) session.getAttribute("username_error") : "")%>
                        </label>
                        <input class="text" type="text" id="username" name="username" />


                        <label for="password">Password:</label>
                        <label id="error">
                            <%= (session.getAttribute("password_error") != null
                                ? (String) session.getAttribute("password_error") : "")%>
                        </label>
                        <input class="text" type="text" id="password" name="password"/>

                    </p>

                    <input id="button" type="submit" value="Sign in" name="login" />

                    <div class="small-link red">
                        <a href="#">Guest sign-in</a>
                    </div>
                </form>

            </div> <!-- END login -->
        
        <footer style="text-align: right">
            <small >© Copyright 2013, SQ06 Sequoia Inc.</small>
        </footer>
    </body>
</html>
