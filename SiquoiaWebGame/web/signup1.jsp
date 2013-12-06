<%-- 
    Document   : signup1
    Created on : Dec 5, 2013, 9:57:49 PM
    Author     : mr.nam
--%>

<%
    session.setAttribute("username_error", null);
    session.setAttribute("password_error", null);
    session.setAttribute("re-password_error", null);
    session.setAttribute("email_error", null);
    session.setAttribute("error", null);
    //
    if (request.getParameter("create") != null) {
        boolean checkInput = true;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("re-password");
        String email = request.getParameter("email");
        //check null
        if (username == null || username.isEmpty()) {
            session.setAttribute("username_error", " cannot be blank!");
            checkInput = false;
        }
        if (password == null || password.isEmpty()) {
            session.setAttribute("password_error", " cannot be blank!");
            checkInput = false;
        }
        if (repassword == null || repassword.isEmpty()) {
            session.setAttribute("re-password_error", " cannot be blank!");
            checkInput = false;
        }
        if (email == null || email.isEmpty()) {
            session.setAttribute("email_error", " cannot be blank!");
            checkInput = false;
        }
        if (password != null && !password.isEmpty()
                && repassword != null && !repassword.isEmpty()) // 2 passwords are not the same
        {
            if (!password.equals(repassword)) {
                session.setAttribute("password_error", " re-password is different");
                session.setAttribute("re-password_error", " re-password is different");
                checkInput = false;
            }
        }
        //if both not null
        if (checkInput) {
            {

                session.setAttribute("checkInput", true);
                //response.sendRedirect("signsignup_success.html");

            }

        }
    }
%>

<%
    String strViewPage = "signup.jsp";
    if (session.getAttribute("checkInput")!=null) {
        strViewPage = "signsignup_success.jsp";
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
    if (dispatcher != null) {
        dispatcher.forward(request, response);
    }
%>