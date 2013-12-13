<%-- 
    Document   : tabele
    Created on : 2013-06-26, 19:20:21
    Author     : artur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div><h2>Dodawanie strony do Książki</h2></div>
<div>
    <p>Dostępne tabele i kolumny</p>
    <select multiple="multiple" style="width: 200px;">
<c:forEach var="tabela" items="${tabele}">
    <option>${tabela}</option>
</c:forEach>
    </select>
    
    <select multiple="multiple" style="width: 200px;">
<c:forEach var="kolumna" items="${kolumny}">
    <option>${kolumna.nazwa}</option>
</c:forEach>
    </select>
    <br />
    
    <%--   REPEAT zastąpić javaScriptem --%>
    <select multiple="multiple" style="width: 200px;">
<c:forEach var="tabela" items="${tabele}">
    <option>${tabela}</option>
</c:forEach>
    </select>
    
    <select multiple="multiple" style="width: 200px;">
<c:forEach var="kolumna" items="${kolumny}">
    <option>${kolumna.nazwa}</option>
</c:forEach>
    </select>
    <br />
    <%--   REPEAT zastąpić javaScriptem --%>
    
    <button>Dodaj tabele +</button> <span style="font-size: smaller">działa jak dodawanie rekordów w mysql'u</span>
</div>