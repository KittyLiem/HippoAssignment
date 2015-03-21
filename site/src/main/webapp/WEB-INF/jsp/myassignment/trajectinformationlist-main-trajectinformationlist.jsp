<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<%--@elvariable id="document" type="com.finalist.beans.TrajectInformationt"--%>
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
						<!--TODO add measurements -->
					</ul>             
				</c:forEach>
	<!-- Later for measurements			<c:if test="${hst:isReadable(document, 'date.time')}">
				    <span class="date">
				      <fmt:formatDate value="${document.date.time}" type="both" dateStyle="medium" timeStyle="short"/>
				    </span>
				</c:if>
				
		-->
				 </div>
			
	</div>
</div>
<c:if test="${pageable.showPagination}">
  <%@ include file="/WEB-INF/jsp/include/pagination.jsp" %>
</c:if>

