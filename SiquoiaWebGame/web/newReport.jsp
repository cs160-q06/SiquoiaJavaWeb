<%-- 
    Document   : newReport
    Created on : Dec 8, 2013, 10:47:13 PM
    Author     : mr.nam
	
	Information for quiz packet report, displayed at the end of a quiz.
--%>
<%@page import="Miscellanea.EnumValue"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="controller.jsp" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Report</title>
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
                                <h2>Quiz Report</h2>
                            </li>
                        </ul>
                        <ul class="nav pull-right">
                            
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
                <%
                    if (session.getAttribute("topicReport") != null && 
                            session.getAttribute("correct") != null)
                    {
                        String topic = (String) session.getAttribute("topicReport");
                        int correct = (Integer) session.getAttribute("correct");
                        int total = (Integer) session.getAttribute("total");
                        int totalPoint = Controller.getLoginUser().getPoint();
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
                            <td><b>You are reward</b></td>
                            <td><%=correct%> point(s)</td>
                        </tr>
                        <hr />
                        <tr>
                            <td><b>Your total points</b></td>
                            <td><%=totalPoint%> point(s)</td>
                        </tr>
                    </table>
                <%}%>
                </div>
            </div>
            <div align="center">
                <a href="account.jsp"><button type="" class="btn btn-warning" >Play again</button>
                </a>
            </div>
        </div>
        <footer>
            <small>© Copyright 2013, SQ06 Sequoia Inc.</small>
        </footer>
    </body>
</html>
