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
        <link rel="stylesheet" href="style.css" type="text/css" media="handheld,all" />
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
                    Create account
                </div>

                <form class="loginfield" action="signup1.jsp" method="post">
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
                        
                        <label for="re-password">Re-password:</label>
                        <label id="error">
                            <%= (session.getAttribute("re-password_error") != null
                                ? (String) session.getAttribute("re-password_error") : "")%>
                        </label>
                        <input class="text" type="text" id="re-password" name="re-password"/>
                        
                        <label for="email">Email:</label>
                        <label id="error">
                            <%= (session.getAttribute("email_error") != null
                                ? (String) session.getAttribute("email_error") : "")%>
                        </label>
                        <input class="text" type="text" id="email" name="email"/>

                    </p>

                    <input id="button" type="submit" value="Create account" name="create" />
                    
                </form>

            </div> <!-- END login -->

        </div> <!-- END wallpaper -->
    </body>
</html>
