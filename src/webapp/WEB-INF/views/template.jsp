<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pl">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1{
                background-color: #ff9900;
            }
            #content{
                background-color: #ffff99;
            }
            ul.login{
                list-style: none;
                display: block;
                width: 200px;
                margin: 0 0 0 0;
                padding: 0 0 0 0;
            }
            ul.login li{
                background-color: #6666ff;
                margin: 2px 0px 2px 0px;
                padding: 1px 5px 1px 20px;
            }
            ul.login li:hover{
                background-color: #6699ff;
            }
            .error{
                background-color: #cc0000;
                display: block;
                padding: 2px 2px;
                font-size: small;
                color: white;
            }
        </style>
    </head>
    <body>
        <c:url var="homepage" value="/struktura"/>
        <h1><a href="${homepage}">CMS WebService</a> - zarządzanie usługą</h1>
        <div id="content">
            <tiles:insertAttribute name="content" />
        </div>
    </body>
</html>
