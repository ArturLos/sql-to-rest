<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div><h2>Strona główna CMS</h2></div>

<div id="menu">
    Menu przyciskowe akcji globalnych usługi.
</div>

<div id="listaKonfiguracji" style="width: 300px; height: 400px; float: left; border: #6666ff medium solid; font-size: small;">
    Akcje lokalne:<br />
    <a href="<c:url value="/struktura/dodaj"/>">Dodaj strukture</a>
    <ul>
        <c:forEach var="struktura" items="${struktury}">
            <c:url var="url" value="/struktura/${fn:escapeXml(struktura.nazwa)}"></c:url>
            <li>
                <a href="${url}">${fn:escapeXml(struktura.nazwa)}</a>
                <% /* trzeba z tego zrobić .tld i custom tag java.net.URLEncoder.encode("ZEB<S23#2013", "UTF-8")*/ %>
                <spring:url var="url" value="/struktura/${struktura.nazwa}/del" htmlEscape="true"></spring:url>
                <a href="${url}" title="Usuń" style="padding-left: 15px;">X</a>
            </li>
        </c:forEach>
    </ul>
</div>

<div style="width: 300px; height: 400px; border: #6666ff medium solid; float: left; font-size: small;">
    Akcje lokalne:<br />
    <c:if test="${curStr !=null}">
        <a href="<c:url value="/struktura/${curStr.nazwa}/dodajTabela"/>">Dodaj tabele</a>
        <p>Wybrano konfigurację <c:out value="${curStr.nazwa}" escapeXml="true" /></p>
        <p>Lista wybranych tabel</p>

        <ul>
            <c:forEach var="element" items="${curStr.tabele}">
                <c:url var="adresTabDel" value="/struktura/${curStr.nazwa}/tabela/${element.nazwa}/del" />
                <c:url var="adresTabEdit" value="/struktura/${curStr.nazwa}/tabela/${element.nazwa}" />
                <li><div>
                        <div>
                            <a href="${adresTabEdit}" title="Edytuj">${element.nazwa}</a>
                            <a href="${adresTabDel}" title="Usuń" style="padding-left: 15px;">X</a></div>
                        <div>elementy:
                            <c:forEach var="kolumna" items="${element.kolumny}">
                                ${kolumna.nazwa}&nbsp;
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>
