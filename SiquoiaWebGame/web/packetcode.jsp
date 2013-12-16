<%-- 
    Document   : packetcode
    Created on : Dec 6, 2013, 7:21:15 PM
    Author     : mr.nam
	
	Enter the branded quiz packet code (unique token) to access a particular branded packet.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Packet Code</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="handheld,all" />
    </head>
    <body>
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <ul class="nav pull-left">
                        <li>
                            <img class="centre" id="topBar" border="0" src="img/SiQuoia logo.jpg">
                        </li>
                        <li class="divider-vertical"> </li>

                    </ul>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-center">
                            <li>
                                <h2>Packet Code</h2>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <!-- -->
        <div id="login">
            <form >
                <div class="well well-small" align="center">
                    <label class="control-label" for="inputEmail">Packet Code</label>
                    
                    <input type="text" placeholder="Enter the 4-digit code" style="alignment-adjust: central">
                    
                </div>

                <div align="center">
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="submit" class="btn btn-danger">Cancel</button>
                </div>
            </form>
        </div>
        <!-- -->
        <hr />
        <div>
            <small>© Copyright 2013, SQ06 Sequoia Inc.</small>
        </div>
    </body>
</html>
