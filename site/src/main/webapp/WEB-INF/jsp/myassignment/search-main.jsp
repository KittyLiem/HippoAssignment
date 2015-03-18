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

<%--@elvariable id="item" type="org.example.beans.NewsDocument"--%>
<%--@elvariable id="query" type="java.lang.String"--%>
<%--@elvariable id="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable"--%>
<div class="yui-main">
	<div id="content" class="yui-b right more-padding">
		<h2>Search results</h2>
		<c:choose>
		  <c:when test="${not empty pageable}">
		    <c:choose>
		      <c:when test="${pageable.total == 0}">
		        <p id="results">No results for: <c:out value="${query}"/></p>
		      </c:when>
		      <c:otherwise>
		      <div id="search-results">
		        <c:forEach var="item" items="${pageable.items}" varStatus="status">
		          <hst:link var="link" hippobean="${item}"/>
		          <article>
		            <hst:cmseditlink hippobean="${item}"/>
		            <ul class="search-result">
		            	<li class="title"><a href="${link}"><c:out value="${item.title}"/></a></li>
		            	<li class="text">${item.introduction}</li>
		            </ul>
		          </article>
		        </c:forEach>
		        </div>
		        <c:if test="${pageable.showPagination}">
		          <%@ include file="/WEB-INF/jsp/include/pagination.jsp" %>
		        </c:if>
		      </c:otherwise>
		    </c:choose>
		  </c:when>
		  <c:otherwise>
		    <h3>Please fill in a search term.</h3>
		  </c:otherwise>
		</c:choose>
	</div>
</div>
