<%-- 
    Document   : signup
    Created on : Dec 5, 2013, 9:57:37 PM
    Author     : mr.nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Siquoia Sign Up</title>
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
        <div id="wallpaper">

           
            <div id="login">

                <form class="loginfield" action="signup1.jsp" method="post">
                    <div>
                        <%= (session.getAttribute("error") != null
                                ? (String) session.getAttribute("error") : "")%>
                    </div>   
                    <p>
                        <label for="username" >Username:</label>
                        <label id="error"> <%= (session.getAttribute("username_error") != null
                                ? (String) session.getAttribute("username_error") : "")%>
                        </label>
                        <input class="text" type="text" id="username" placeholder="username" name="username" />


                        <label for="password" >Password:</label>
                        <label id="error">
                            <%= (session.getAttribute("password_error") != null
                                    ? (String) session.getAttribute("password_error") : "")%>
                        </label>
                        <input class="text" type="password" id="password" placeholder="password" name="password"/>

                        <label for="re-password" >Re-password:</label>
                        <label id="error">
                            <%= (session.getAttribute("re-password_error") != null
                                    ? (String) session.getAttribute("re-password_error") : "")%>
                        </label>
                        <input class="text" type="password" id="re-password" placeholder="repassword" name="re-password"/>

                        <label for="email">Email:</label>
                        <label id="error">
                            <%= (session.getAttribute("email_error") != null
                                    ? (String) session.getAttribute("email_error") : "")%>
                        </label>
                        <input class="text" type="email" id="email" placeholder="email" name="email"/>

                    </p>

                    <input id="button" type="submit" value="registration" name="registration" />

                </form>

            </div> <!-- END login -->

        </div> <!-- END wallpaper -->
        <footer style="text-align: right">
            <small >© Copyright 2013, SQ06 Sequoia Inc.</small>
        </footer>
    </body>
</html>
