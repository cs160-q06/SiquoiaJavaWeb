<%-- 
    Document   : demoQuiz1
    Created on : Dec 7, 2013, 1:59:10 AM
    Author     : mr.nam
--%>

<%@page import="DataOOD.Quiz"%>
<%

    String strViewPage = "demoQuiz.jsp";
    //initial
    session.setAttribute("select", null);
    session.setAttribute("error", null);
    //
    if ("POST".equalsIgnoreCase(request.getMethod())) {

        if (session.getAttribute("start") == null) {//introduction
            session.setAttribute("start", "start");
        } else {
            //start the quiz
            if (request.getParameter("answer") != null) {
                if (request.getParameter("next") != null) {
                    session.setAttribute("next", "next");
                    Quiz quiz = (Quiz) session.getAttribute("quiz");
                    String s = (String) request.getParameter("answer");
                    if (quiz.isCurrentCorrect(s)) {
                        session.setAttribute("select", "correct");
                    } else {
                        session.setAttribute("select", "incorrect");
                    }
                }
            } else {
                session.setAttribute("error", "no answer was chosen");
            }
        }
    }

%>

<%    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>