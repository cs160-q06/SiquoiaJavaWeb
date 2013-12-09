<%-- 
    Document   : newQuiz
    Created on : Dec 8, 2013, 9:25:03 PM
    Author     : mr.nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Miscellanea.EnumString"%>
<%@page import="DataOOD.Media"%>
<%@page import="DataOOD.Node"%>
<%@page import="DataOOD.Quiz"%>
<%@page import="DataOOD.Question"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="Controller.Controller"%>
<%@page import="DataOOD.Topic"%>
<%@page import="java.util.List"%>
<%@ include file="controller.jsp" %>
<!DOCTYPE html>
<html>
    <%!
        private List<Topic> list;
        private Quiz quiz;

        private Quiz generateQuiz(String topic) {
            Node<Topic> root = Controller.generateNodeTopic();
            Quiz quiz = Controller.generateQuizFromTopic(Controller.getTopicByName(topic));
            return quiz;
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />

    </head>
     <body>

        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <ul class="nav pull-left">
                        <li>
                            <a href="index.jsp">
                                <img class="centre" id="topBar" src="img/SiQuoia logo.jpg">
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
        <form action="newQuiz1.jsp" class="loginfield" method="post">
                <div id="sink">
                    <%
                        if (session.getAttribute("quiz") == null) {//generate quiz in beginning
                            String t = (String)session.getAttribute("topic");
//                            quiz = generateQuiz();
                            quiz = generateQuiz(t);
                            session.setAttribute("userPoint", 0);
                        }
                        if (session.getAttribute("start") == null) {
                    %>

                    <div class="well well-small">
                        <label><b>Instructions</b></label>
                        <ul>
                            <li>Read the question carefully.</li>
                            <li>Select an answer and click next.</li>
                            <li>Click Submit at anytime to submit the quiz</li>
                        </ul>
                    </div>
                    <%
                    } else {
                        if (session.getAttribute("next") != null) {
                            quiz.next();
                            session.setAttribute("next", null);
                        }
                        int count = quiz.getCurrentNumber();
                        int total = quiz.getTotal();
                        Question q = quiz.getCurrentQuestion();
                        String des = q.getQuestion();
                        String a1 = q.getAnswer1();
                        String a2 = q.getAnswer2();
                        String a3 = q.getAnswer3();
                        String a4 = q.getCorrectAnswer();
                        int mediaID = q.getMedia();
                        session.setAttribute("quiz", quiz);
                    %>
                    <div class="well well-small">
                        <label style="text-align: center">Question #<%=count%>/<%=total%></label> 
                    </div>
                    <%
                        if (mediaID > 0) {
                    %>


                    <div class="well well-small">
                        <%
                            Media media = Controller.getMediaByID(mediaID);
                            String type = media.getType();
                            String mediaDes = media.getDescription();
                            if (type.equals(EnumString.IMAGE.getValue())) {
                        %>
                        <img src="<%=mediaDes%>" alt="Pulpit rock" width="100%"/>
                        <%
                        } else if (type.equals(EnumString.AUDIO.getValue())) {
                        %>
                        <embed  width="100%" src="<%=mediaDes%>" />
                        <%
                        } else if (type.equals(EnumString.VIDEO.getValue())) {
                        %>
                        <video width="100%" controls="" autoplay="" src="<%=mediaDes%>"></video>

                        <%
                            }
                        %>
                    </div>

                    <%
                        }
                    %>



                    <div class="well well-small">
                        <label><b><%=des%></b></label>
                        <hr />
                        <p>
                            <input type="radio" name="answer" id="1" value="<%=a1%>" /><%=a1%><br /><br />
                            <input type="radio" name="answer" id="2" value="<%=a2%>" /><%=a2%><br /><br />
                            <input type="radio" name="answer" id="3" value="<%=a3%>" /><%=a3%><br /><br />
                            <input type="radio" name="answer" id="4" value="<%=a4%>" /><%=a4%><br /><br />
                        </p>
                    </div>



                    <%
                        }
                    %>
                    <div class="well well-small" align=""center>
                        Total Correct: <%= (session.getAttribute("userPoint") != null
                                ? (Integer) session.getAttribute("userPoint") : 0)%>
                        <br />  
                        <%= (session.getAttribute("select") != null
                                ? (String) session.getAttribute("select") : "")%>
                        <br />
                        <%= (session.getAttribute("error") != null
                                ? "Error: " + (String) session.getAttribute("error") : "")%>
                    </div>
                    <div align=""center>   

                        <button type="submit" class="btn btn-large" value="Next" name="next">Next</button>
                    </div>
                </div>

            </form>
    </body>
</html>