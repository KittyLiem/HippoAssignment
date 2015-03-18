<%@ include file="/WEB-INF/jsp/include/imports.jsp" %>
<%--
  Copyright 2014 Hippo B.V. (http://www.onehippo.com)

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  --%>

<%--@elvariable id="document" type="com.finalist.beans.NewsDocument"--%>
<%--@elvariable id="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable"--%>


<div class="yui-main">
	<div id="content" class="yui-b left-and-right">

    	<h2>News Overview</h2>
		<div id="news">
			<c:forEach var="item" items="${pageable.items}" varStatus="status">

			  	<hst:link var="link" hippobean="${item}"/>
			  	<article class="has-edit-button">
			  	<ul class="news-item">	
			    	<hst:cmseditlink hippobean="${item}"/>
			          <c:if test="${not empty item.image.small}">
			          	<li class="image"><a href="${item}">
			                    <hst:link var="img" hippobean="${item.image.small }"/>
			                    <img  src="${img}" alt="${link}" /></a></li>
                      </c:if>
			    	<li class="title">
			    		<a href="${link}"><c:out value="${item.title}"/></a>
			    	</li>
			    	<li class="date">
			    		<c:if test="${hst:isReadable(item, 'date.time')}">
			        		<fmt:formatDate value="${item.date.time}" type="both" dateStyle="medium" timeStyle="short"/>
			   			</c:if>
			   		</li>
			   		<li>
			    		<c:out value="${item.introduction}"/>
			    	</li>
			    </ul>
			  </article>

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
