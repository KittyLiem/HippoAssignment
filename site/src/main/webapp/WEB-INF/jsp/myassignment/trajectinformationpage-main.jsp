<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<%--@elvariable id="document" type="com.finalist.beans.TrajectInformation"--%>

<hst:link var="link" hippobean="${document}"/>

  <h3><a href="${link}"><c:out value="${document.trajectId}"/></a></h3>
 
  <c:if test="${not empty document.trajectName}">
    <p><c:out value="${document.trajectName}"/></p>
  </c:if>
  <c:if test="${not empty document.trajectLength}">
    <p><c:out value="${document.trajectLength}"/></p>
  </c:if>


<!--  <c:if test="${hst:isReadable(document, 'image.original')}">
    <hst:link var="img" hippobean="${document.image.original}"/>
    <figure>
      <img src="${img}" title="${fn:escapeXml(document.image.fileName)}"
           alt="${fn:escapeXml(document.image.fileName)}"/>
      <figcaption>${fn:escapeXml(document.image.description)}</figcaption>
    </figure>
  </c:if>

  <hst:html hippohtml="${document.content}"/>
-->
