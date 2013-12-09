<%-- 
    Document   : payment1
    Created on : Dec 8, 2013, 8:43:53 PM
    Author     : mr.nam
--%>

<%@page import="Controller.Controller"%>
<%

    String strViewPage = "payment.jsp";
    //initial
    //
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        if (request.getParameter("next") != null) {
            
                session.setAttribute("payMethod", (String) request.getParameter("pay"));
           if ( request.getParameter("check") !=null) {
                //Controller.
                String topic = (String) session.getAttribute("topic");
                session.invalidate();
                session = request.getSession();
                session.setAttribute("topic", topic);
                strViewPage = "newQuiz.jsp";
                
            }
        }
    }

%>

<%    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>