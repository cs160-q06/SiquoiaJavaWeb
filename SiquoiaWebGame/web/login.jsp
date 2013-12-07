<%-- 
    Document   : login.jsp
    Created on : Dec 4, 2013, 11:26:59 PM
    Author     : mr.nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Siquoia login mockup</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />
    </head>

    <body>
        
        <div id="wallpaper">
            
            <div id="logo">
                <span class="green glow">S</span>
                <span class="blue glow">i</span>
                <span class="red glow">q</span>
                <span class="orange glow">u</span>
                <span class="red glow">o</span>
                <span class="blue glow">i</span>
                <span class="green glow">a</span>
            </div> <!-- END logo -->

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

        </div> <!-- END wallpaper -->
    </body>

</html>
