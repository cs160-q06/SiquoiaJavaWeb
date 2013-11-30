<%@page import="java.util.*" %>

<%
	String username, password;
	if(request.getParameter("txtUserName") == null)
		username = "";
	else
		username = request.getParameter("txtUserName");
	
	if(request.getParameter("txtPassword") == null)
		password = "";
	else
		password = request.getParameter("txtPassword");

	request.setAttribute("UName", username);
%>

<%
	String strViewPage="GetAttributeMethod1.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
	if (dispatcher != null){
		dispatcher.forward(request, response);
	}
%>