<%-- 
    Document   : brandedQuizReport
    Created on : Dec 15, 2013, 2:08:40 PM
    Author     : nam
--%>


<%@page import="Miscellanea.EnumValue"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="controller.jsp" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />
        <script type="text/javascript">
            window.history.forward(1);

        </script>
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
                                <h2>Branded Quiz Report</h2>
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
            <div class="well well-small">
                <div align="center">
                    <b>Report</b>
                    <hr />
                    <%                    if (session.getAttribute("topicReport") != null
                                && session.getAttribute("correct") != null) {
                            String topic = (String) session.getAttribute("topicReport");
                            int correct = (Integer) session.getAttribute("correct");
                            int total = (Integer) session.getAttribute("total");
                            int totalPoint = Controller.getLoginUser().getPoint();
                            int previousPoint = totalPoint - correct;

                    %>
                    <table border="0" style="width: 100%" class="table-striped table-condensed">
                        <tr>
                            <td><b>Topic</b></td>
                            <td><%=topic%></td>
                        </tr>
                        <tr>
                            <td><b>Total Question</b></td>
                            <td><%=total%></td>
                        </tr>
                        <tr>
                            <td><b>Correct Answer</b></td>
                            <td><%=correct%></td>
                        </tr>
                        <tr>
                            <td><b>Your previous points</b></td>
                            <td><%=previousPoint%> point(s)</td>
                        </tr>
                        <tr>
                            <td><b>You are rewarded</b></td>
                            <td><%=correct%> point(s)</td>
                        </tr>
                        
                    </table>
                    <hr />
                    <table border="0" style="width: 100%" class="table-striped table-condensed">
                        <tr>
                            <td><b>Your total points</b></td>
                            <td><%=totalPoint%> point(s)</td>
                        </tr>
                    </table>
                    <%}%>
                </div>
            </div>
            <div align="center">
                <a href="account.jsp"><button type="submit" class="btn btn-warning" >Back Home</button>
                </a>
            </div>
        </div>
        <hr />
        <div>
            <small>© Copyright 2013, SQ06 Sequoia Inc.</small>
        </div>
    </body>
</html>

