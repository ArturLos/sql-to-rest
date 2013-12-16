<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page trimDirectiveWhitespaces="true" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div><h2>Dodawanie tabeli do struktury</h2></div>
<script type="text/javascript">
    function zmianaTabeli(newVal){
        document.location.href = "<c:url value="/struktura/${idStructure}/dodajTabela/" />"+newVal;
    }
</script>
<form:form modelAttribute="tab">
<form:select path="nazwa" onchange="zmianaTabeli(this.value)">
    <form:option value="">wybierz</form:option>
    <form:options items="${tabelaList}" itemLabel="nazwa" itemValue="nazwa" />
</form:select>

<c:if test="${not(kolumnaList == null)}">
    <form:select path="columns" multiple="true">
        <form:options items="${kolumnaList}" itemLabel="nazwa" itemValue="nazwa"/>
    </form:select>
    <input type="submit" value="Dodaj" />
</c:if>
</form:form>