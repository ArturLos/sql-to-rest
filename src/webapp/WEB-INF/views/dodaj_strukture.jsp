<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div><h2>Dodawanie struktury</h2></div>
<form:form modelAttribute="structure">
<form:errors path="*" cssClass="error" delimiter="<br />"/>
Nazwa: 
<form:input path="nazwa" /><br />
<input type="submit" value="Zapisz" />
</form:form>