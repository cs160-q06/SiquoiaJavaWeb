<%-- 
    Document   : enterTokenNoLogIn1
    Created on : Dec 14, 2013, 7:05:00 PM
    Author     : mr.nam
--%>

<%
    String strViewPage = "enterTokenNoLogIn.jsp";
    //initial
    session.setAttribute("error", null);
    //
    
%>
<%
    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>

