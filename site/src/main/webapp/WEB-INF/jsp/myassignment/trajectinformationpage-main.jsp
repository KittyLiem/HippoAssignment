<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<%--@elvariable id="document" type="com.finalist.beans.Trajectinformation"--%>

<div class="yui-main">
	<div id="content" class="yui-b left-and-right ">	
		<hst:link var="link" hippobean="${document}"/>
		
		<div id="article">
			<h3>${document.trajectId}</h3>
			
			<c:if test="${not empty document.trajectName}">
				<p>Name: <a href="<hst:link path="/resttrajectapi/Trajectinformation/${document.trajectName}/" />"
						target="_blank"
						title="Click the link to see the traject's average velocity and traveltime for the past hour"><c:out value="${document.trajectName}"/></a>
				</p>
			</c:if>
			<c:if test="${not empty document.trajectLength}">
			    <p><c:out value="${document.trajectLength}"/></p>
			</c:if>
			<c:if test="${hst:isReadable(document, 'trajectMeasurementDate.time')}">
				<span class="date">Datum: <fmt:formatDate value="${document.trajectMeasurementDate.time}" 
			      				type="both" dateStyle="medium" timeStyle="short"/> </span>
			</c:if>
			<p>Snelheid: ${document.trajectMeasurementVelocity}</p>
			<p>Reistijd: ${document.trajectMeasurementTraveltime}</p>
		</div>
	</div>
</div>
