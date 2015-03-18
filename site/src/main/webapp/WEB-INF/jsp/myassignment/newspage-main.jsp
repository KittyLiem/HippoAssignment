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
<div class="yui-main">
	<div id="content" class="yui-b left-and-right ">
		<hst:link var="link" hippobean="${document}"/>
		<div id="article">
			<article class="has-edit-button">
			<hst:cmseditlink hippobean="${document}"/>
			                
			<h2> <a href="${link}"><c:out value="${document.title}"/></a></h2>	
					
				<c:if test="${hst:isReadable(document, 'image.large')}">
				    <hst:link var="img" hippobean="${document.image.large}"/>
				      <img class="image" src="${img}" title="${fn:escapeXml(document.image.fileName)}"
				           alt="${fn:escapeXml(document.image.fileName)}"/>
				</c:if>

				<c:if test="${hst:isReadable(document, 'date.time')}">
				    <span class="date">
				      <fmt:formatDate value="${document.date.time}" type="both" dateStyle="medium" timeStyle="short"/>
				    </span>
				</c:if>
				
		<!-- 		  <c:if test="${not empty document.author}">
				    <p><c:out value="${document.author}"/></p>
				  </c:if>
				  <c:if test="${not empty document.source}">
				    <p><c:out value="${document.source}"/></p>
				  </c:if>
				  <c:if test="${not empty document.location}">
				    <p><c:out value="${document.location}"/></p>
				  </c:if>
		-->
		          <div id="editable_cont" class="inline-editor-editable-container">
					  <c:if test="${not empty document.introduction}">
					    <li><c:out value="${document.introduction}"/></li>
					  </c:if>
					  <div class="yui-cssbase body">
				  	<span class=" inline" id="myassignment:description">
				
			  		<hst:html hippohtml="${document.content}"/>
			  		</span>
			  		</div>
				  </div>	
				</article>
		</div>
	</div>
</div>