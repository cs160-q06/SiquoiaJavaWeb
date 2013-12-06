<%-- 
    Document   : shop
    Created on : Oct 20, 2013, 2:42:09 AM
    Author     : mr.nam
--%>

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
        <title>Siquoia Shop</title>
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
                
                <div class="green glowCenter" style="font-size: 30px">Shop</div>
                
                <form action="shop1.jsp" class="loginfield" action="#" method="post">
                    <div class="blue glow">Point: <%= (session.getAttribute("userPoint")!=null
                            ?(String)session.getAttribute("userPoint"):(100+""))%></div>
                    <p>
                        <div class="text" style="background-color: white;height: 200px">
                            <%
                                String entire = (String) session.getAttribute("entire");
                                if (entire == null) {
                                    list = Controller.getSubTopicByID(0);
                                } else {
                                    list = Controller.getSubTopicByName(entire);
                                }
                                for (Topic t : list) {
                                    String name = t.getDescription();
                                    

                            %>
                            <input id="button" type="submit" name="topic" value="<%=name%>"  />
                            <%                    }%>                            
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
                    <input id="button" type="submit" value="Expand" name="expand" />
                    <input id="button" type="submit" value="Back" name="back" />                    
                    <input id="button" type="submit" value="Buy" name="" />    
                </form>


            </div> <!-- END login -->

        </div> <!-- END wallpaper -->
    </body>


</html>