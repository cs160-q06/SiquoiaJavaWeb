<%-- 
    Document   : enterTokenNoLogIn1
    Created on : Dec 14, 2013, 7:05:00 PM
    Author     : mr.nam
--%>

<%@page import="DataOOD.Token"%>
<%@page import="Controller.Controller"%>
<%
    String strViewPage = "enterTokenNoLogIn.jsp";
    //initial
    session.setAttribute("error", null);
    //
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        if (session.getAttribute("submit") == null) {//introduction
            String code = (String) session.getAttribute("code");
            if (code == null || code.isEmpty()) {
                session.setAttribute("error", "CODE cannot be blank!");
            } else {
                if (!Controller.isExistedToken(code)) {//not exist
                    session.setAttribute("error", "This CODE does not exist!");
                } else if (!Controller.isUsedToken(code)) {// already used
                    session.setAttribute("error", "This CODE is already used!");
                } else {
                    strViewPage = "enterTokenNoLogIn1.jsp";
                    session.invalidate();
                    session = request.getSession();
                    Token token = Controller.getTokenByCode(code);
                    token.setCode(code);
                    //Controller.
                }
            }
        }

    }

%>
<%    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>

