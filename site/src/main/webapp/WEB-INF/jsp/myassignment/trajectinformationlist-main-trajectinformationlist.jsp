<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<%--@elvariable id="document" type="com.finalist.beans.Trajectinformationt"--%>
<%--@elvariable id="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable"--%>

<div class="yui-main">
	<div id="content" class="yui-b left-and-right ">
		<h2>Trajectinformatie</h2>

			<div id="trajecten">	 
				<c:forEach var="item" items="${pageable.items}" varStatus="status">  
					<hst:link var="link" hippobean="${item}"/>
					<ul class="product-item">
						<li class="title"><a href="${link}"> ${item.trajectId}</a></li>
						<li>${item.trajectName}, lengte: ${item.trajectLength}</li>
						<li>Meting datum: 
							<c:if test="${hst:isReadable(item, 'trajectMeasurementDate.time')}">
			        			<fmt:formatDate value="${item.trajectMeasurementDate.time}" 
			        				type="both" dateStyle="medium" timeStyle="short"/>
			   				</c:if>
						</li> 
						<li>Snelheid: ${item.trajectMeasurementVelocity}</li>
						<li>Reistijd: ${item.trajectMeasurementTraveltime}</li>
					</ul>             
				</c:forEach>

			</div>
			
	</div>

<c:if test="${pageable.showPagination}">
  <%@ include file="/WEB-INF/jsp/include/pagination.jsp" %>
</c:if>
</div>
<%--@elvariable id="editMode" type="java.lang.Boolean"--%>
<c:if test="${editMode and empty pageable}">
  <img src="<hst:link path='/images/essentials/catalog-component-icons/news-list.png'/>"> Click to edit News List
</c:if>

