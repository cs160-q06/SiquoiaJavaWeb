<%-- 
    Document   : quiz1
    Created on : Dec 3, 2013, 10:45:21 AM
    Author     : mr.nam
--%>
<%@page import="DataOOD.Quiz"%>
<%
    //initial
    session.setAttribute("select", null);
    session.setAttribute("error", null);
    //
    if (request.getParameter("answer") != null) {
        
        if (request.getParameter("next") != null) {
            session.setAttribute("next", "next");
            Quiz quiz = (Quiz) session.getAttribute("quiz");
            String s = (String) request.getParameter("answer");
            if(quiz.isCurrentCorrect(s))
                session.setAttribute("select", "correct");
            else
                session.setAttribute("select", "incorrect");
        }
    }
    else
        session.setAttribute("error", "no answer was chosen");


%>

<%    String strViewPage = "quiz.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>