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
    %>
    <head>
        <title>Siquoia login mockup</title>
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
                
                <div class="green glowCenter" style="font-size: 30px">Quiz</div>
                
                    <div class="blue glow">Point: <%= (session.getAttribute("userPoint")!=null
                            ?(String)session.getAttribute("userPoint"):(100+""))%></div>
                    <p>
                        <div class="text" style="background-color: white;height: 200px">
                            <%
                                Node<Topic> root = Controller.generateNodeTopic();
                                Quiz quiz = Controller.generateQuizFromTopic(root.getChildren().get(0).getData());
                                String entire = (String) session.getAttribute("entire");
                                if (entire == null) {
                                    list = Controller.getSubTopicByID(0);
                                } else {
                                    list = Controller.getSubTopicByName(entire);
                                }
                                   
                                quiz.next();
                                Question q = quiz.getCurrentQuestionRandomShuffle();
                                String des = q.getQuestion();
                                String a1 = q.getAnswer1();
                                String a2 = q.getAnswer2();
                                String a3 = q.getAnswer3();
                                String a4 = q.getCorrectAnswer();
                            %>
                            <label><%=des%></label><br />
                            <form action="">
                            <input type="radio" name="1" id="1" value="1"><%=a1%><br />
                            <input type="radio" name="1" id="2" value="2"><%=a2%><br />
                            <input type="radio" name="1" id="3" value="3"><%=a3%><br />
                            <input type="radio" name="1" id="4" value="4"><%=a4%><br />
                                    </form>
                        </div>
                        <div>
                            Selected: <%= (session.getAttribute("select") != null
                                    ? (String) session.getAttribute("select") : "")%>
                        </div>   
                        <div>
                            <%= (session.getAttribute("error") != null
                                    ? "Error: " + (String) session.getAttribute("error") : "")%>
                        </div>   
                    </p>
            <form action="quiz1.jsp" class="loginfield" action="#" method="post">

                    <input id="button" type="submit" value="Expand" name="expand" />
                    <input id="button" type="submit" value="Back" name="back" />                    
                    <input id="button" type="submit" value="Buy" name="" />    
                </form>


            </div> <!-- END login -->

        </div> <!-- END wallpaper -->
    </body>


</html>