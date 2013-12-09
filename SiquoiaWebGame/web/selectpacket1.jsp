<%-- 
    Document   : selectpacket1
    Created on : Dec 8, 2013, 6:39:40 PM
    Author     : mr.nam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DataOOD.Topic"%>
<%@page import="Controller.Controller"%>


<%    String name, expand;
    session.setAttribute("error", null);
    //if ("POST".equalsIgnoreCase(request.getMethod())) {
        if (request.getParameter("topic") != null) {
            name = request.getParameter("topic");
            session.setAttribute("select", name);

        }

        if (request.getParameter("expand") != null) {
            expand = (String) session.getAttribute("select");
            if (expand != null && !expand.isEmpty()) {
                List<Topic> list = Controller.getSubTopicByName(expand);
                if (!list.isEmpty()) {
                    session.setAttribute("entire", expand);
                } else {
                    session.setAttribute("error", expand + " does not have sub topic");
                }

            }

        }
        if (request.getParameter("back") != null) {
            name = (String) session.getAttribute("entire");
            if (name == null) {
                session.setAttribute("error", "No more Up Topic");

            } else {
                Topic parent = Controller.getTopicParentByName(name);
                if (parent != null) {
                    session.setAttribute("entire", parent.getDescription());
                    session.setAttribute("select", null);
                } else {
                    session.setAttribute("entire", null);
                    session.setAttribute("select", null);
                }
            }

        }
  //  }


%>

<%    String strViewPage = "selectpacket.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>