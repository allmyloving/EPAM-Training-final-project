<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ attribute name="orderBy" required="true" type="java.lang.String"%>
<c:url var="myUrl" value="">
	<c:forEach items="${param}" var="entry">
		<c:if test="${entry.key ne 'orderby' and entry.key ne 'o'}">
			<c:param name="${entry.key}" value="${entry.value}" />
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${param['o'] eq 'asc'}">
			<c:param name="o" value="desc" />
		</c:when>
		<c:otherwise>
			<c:param name="o" value="asc" />
		</c:otherwise>
	</c:choose>
	<c:param name="orderby" value="${orderBy}" />
</c:url>
<a href="${myUrl}">