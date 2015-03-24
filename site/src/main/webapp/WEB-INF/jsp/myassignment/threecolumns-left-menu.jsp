<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>

<div id="left" class="yui-b">

     <!-- leftmenu -->

	<c:if test="${not empty menu}">
      <ul id="left-nav">
    	<c:forEach var="item" items="${menu.siteMenuItems}">
    	    <c:choose>
        		<c:when test="${item.selected or item.expanded}">
          			<li class="active"><a href="<hst:link link="${item.hstLink}"/>">${item.name}</a></li>
        		</c:when>
        		<c:otherwise>
         			 <li><a href="<hst:link link="${item.hstLink}"/>">${item.name}</a></li>
        		</c:otherwise>
      		</c:choose>
    	</c:forEach>
  	  </ul>
	</c:if>
 </div>