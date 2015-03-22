<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<%--@elvariable id="document" type="com.finalist.beans.Trajectinformationt"--%>
<%--@elvariable id="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable"--%>

<div class="yui-main">
	<div id="content" class="yui-b left-and-right ">
		<h2>Trajectinformatie</h2>

			<div id="trajecten">	 
				<c:forEach var="item" items="${pageable.items}" varStatus="status">  
					<hst:link var="link" hippobean="${document}"/>
					<ul class="product-item">
						<li class="title"><a href="${link}"> ${item.trajectId}</a></li>
						<li><a href="${link}"> ${item.trajectName}</a></li>
						<li>${item.trajectLength}</li>
						<!-- measurements -->
						<c:forEach var="measure" items="${item.trajectMeasurement}" >  
							<c:if test="${hst:isReadable(measure, 'date.time')}">
							    <span class="date">
							      <fmt:formatDate value="${measure.date.time}" type="both" dateStyle="medium" timeStyle="short"/>
							    </span>
							</c:if>
							<li>Snelheid: ${measure.trajectMeasurementVelocity}</li>
							<li>Reistijd: ${measure.trajectMeasurementTraveltime}</li>
						</c:forEach>
		
					</ul>             
				</c:forEach>

			</div>
			
	</div>
</div>
<c:if test="${pageable.showPagination}">
  <%@ include file="/WEB-INF/jsp/include/pagination.jsp" %>
</c:if>

