<%-- 
    Document   : login1
    Created on : Dec 4, 2013, 11:28:30 PM
    Author     : mr.nam
--%>

<%
        session.setAttribute("username_error", null);
        session.setAttribute("password_error", null);
        session.setAttribute("error", null);
    if (request.getParameter("login") != null) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //check null
        if (username == null || username.isEmpty()) {
            session.setAttribute("username_error", " cannot be blank!");
        } 
        if (password == null || password.isEmpty()) {
            session.setAttribute("password_error", " cannot be blank!");
        } 
        //if both not null
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty() ) {
            {
                //check user
                if(!Controller.Controller.isLogin(username, password, 11))
                    session.setAttribute("error", "Username or password is incorrect!");
                else
                {
                    session.setAttribute("error", "Username and password are correct!");

                }
            }

        }
    }
%>

<%
    String strViewPage = "login.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>

