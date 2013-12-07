<%-- 
    Document   : quiz
    Created on : Dec 3, 2013, 10:43:50 AM
    Author     : mr.nam
--%>

<%@page import="DataOOD.Node"%>
<%@page import="DataOOD.Quiz"%>
<%@page import="DataOOD.Question"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="Controller.Controller"%>
<%@page import="DataOOD.Topic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
    <%!
        private List<Topic> list;
        private Quiz quiz = generateQuiz();

        private Quiz generateQuiz() {
            Node<Topic> root = Controller.generateNodeTopic();
            Quiz quiz = Controller.generateQuizFromTopic(root.getChildren().get(0).getData());
            return quiz;
        }

    %>
    <head>
        <title>Quiz</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />
        <style>
            #sink {
                position: relative;
                margin: 0px auto;
                width: 640px;
                height: 320px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: #000 0 0 4px inset;
                transition: all .25s ease-in;
                padding: 10px;

            }
            #sink:hover {
                background-color: #FEFEFE;
                box-shadow: #000 0 0 8px inset;
                transition: all .25s ease-out;
            }
        </style>
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
                                <h2>Quiz</h2>
                            </li>
                        </ul>
                        <ul class="nav pull-right">
                            <li><a href="index.jsp"><i class="icon-white icon-lock"></i> Logout</a>
                            </li></ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="wallpaper">

            <form action="quiz1.jsp" class="loginfield" action="#" method="post">

                <%

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

                    session.setAttribute("quiz", quiz);
                %>

                <div id="sink">
                    <label style="text-align: center">Question #<%=count%>/<%=total%></label><br />                            
                    <label><%=des%></label><br />
                    <input type="radio" name="answer" id="1" value="<%=a1%>" /><%=a1%><br />
                    <input type="radio" name="answer" id="2" value="<%=a2%>" /><%=a2%><br />
                    <input type="radio" name="answer" id="3" value="<%=a3%>" /><%=a3%><br />
                    <input type="radio" name="answer" id="4" value="<%=a4%>" /><%=a4%><br />
                </div>


                <div align="center">
                    <div class="blue glow">
                        Point: <%= (session.getAttribute("userPoint") != null
                                ? (String) session.getAttribute("userPoint") : (100 + ""))%>

                    </div>
                    <div style="text-align: center">
                        <%= (session.getAttribute("select") != null
                                ? (String) session.getAttribute("select") : "")%>
                        <br />

                        <%= (session.getAttribute("error") != null
                                ? "Error: " + (String) session.getAttribute("error") : "")%>
                        <br />
                        <br />
                    </div>   
                    <div>   

                        <button type="submit" class="btn btn-large" value="Next" name="next">Next</button>
                    </div>
                </div>
            </form>



        </div> <!-- END wallpaper -->
    </body>


</html>