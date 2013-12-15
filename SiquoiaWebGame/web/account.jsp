<%-- 
    Document   : account
    Created on : Dec 8, 2013, 4:34:05 PM
    Author     : mr.nam
	
	Homepage for gamer when logged in as a registered user. Access game functions here.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="controller.jsp" %> 
<%@ include file="account1.jsp" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />

    </head>
    <body>
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <ul class="nav pull-left">
                        <li>
                            <a href="index.jsp">
                                <img class="centre" id="topBar" border="0" src="img/SiQuoia logo.jpg">
                            </a>
                        </li>
                        <li class="divider-vertical"> </li>

                    </ul>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-center">
                            <li>
                                <h2>Account</h2>
                            </li>
                        </ul>
                        <ul class="nav pull-right">
                            <li>Welcome <b> <%=usernameLogin%></b>
                                <form action="controller.jsp" method="post">
                                    <i class="icon-white icon-lock"></i> 
                                    <button type="submit" name="logout">Logout</button>
                                </form>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <div id="sink">

            <div class="button" style="left:0;">
                <a href="selectpacket.jsp"><img src="img/button_take_quiz.png" alt="take quiz"
                                                style="width: 100px; height: auto;">Take a Quiz</a>
            </div>

            <div class="button" style="left: 200px">
                <a href="#"><img src="img/button_quiz_packets.png" alt="quiz pakets"
                                 style="width: 100px; height: auto;">Submit a Quiz Packet</a>
            </div>

            <div class="button" style="right:0;">
                <a href="#"><img src="img/button_settings.png" alt="settings"
                                 style="width: 100px; height: auto;">User Settings</a>
            </div>
            <br /><br /><br /><br /><br /><br /><br /><br />

            <div class="well well-small" style="">
                <label style="text-align: center"><b>Account Summary</b></label> 
                <hr />
                <div>
                    <table border="0" style="width: 100%" class="table-striped table-condensed">
                        <tr>
                            <td><b>Points</b></td>
                            <td><%=point%> </td>
                            <td align="middle"><a href="payment.jsp"><button type="submit" name="next" class="btn btn-mini">Buy More</button></a>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Amount of purchased Packets</b></td>
                            <td><%=amountPurchasePacket%></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><b>Amount of earned Points</b></td>
                            <td><%=amountEarnedPoint%></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><b>Amount of used Points</b></td>
                            <td><%=amountUsedPoint%></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <footer>
            <small>© Copyright 2013, SQ06 Sequoia Inc.</small>
        </footer>
    </body>
</html>
