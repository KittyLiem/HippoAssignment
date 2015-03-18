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

<%--@elvariable id="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable"--%>
<c:forEach var="item" items="${pageable.items}">
  <c:if test="${hst:isReadable(item, 'title')}">
    <hst:link var="link" hippobean="${item}"/>
    <article class="has-edit-button">
      <hst:cmseditlink hippobean="${item}"/>
      <h3><a href="${link}"><c:out value="${item.title}"/></a></h3>
      <c:if test="${hst:isReadable(item, 'introduction')}">
        <p><c:out value="${item.introduction}"/></p>
      </c:if>
    </article>
  </c:if>
</c:forEach>
<c:if test="${pageable.showPagination}">
  <%@ include file="/WEB-INF/jsp/include/pagination.jsp" %>
</c:if>
<%--@elvariable id="editMode" type="java.lang.Boolean"--%>
<c:if test="${editMode and empty pageable}">
  <img src="<hst:link path='/images/essentials/catalog-component-icons/generic-list.png'/>"> Click to edit Generic List
</c:if>