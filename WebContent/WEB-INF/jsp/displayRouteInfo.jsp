<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<c:choose>
			<%-- Make it a list --%>
			<%-- not sure how that would work --%>
			<c:when test="${not empty routeInfoBeans}">
				<table class="table table-bordered">
					<tr>
						<th><fmt:message key="label.station" /></th>
						<th><fmt:message key="label.departure" /></th>
						<th><fmt:message key="label.arrival" /></th>
					</tr>
					<c:forEach items="${routeInfoBeans}" var="r">
						<tr>
							<td>${r.stationName}</td>
							<td><fmt:formatDate value="${r.depTime}" type="time"
									timeStyle="medium" /></td>
							<td><fmt:formatDate value="${r.arrTime}" type="time"
									timeStyle="medium" /></td>
						</tr>
					</c:forEach>

				</table>
				<div class="well">Time is local.</div>
			</c:when>
			<c:otherwise>
				<div class="well well-lg">
					<fmt:message key="message.nothing_found" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="container">
		<ul class="pager">
			<li><a href="javascript:history.go(-1)">Go back</a></li>
		</ul>
	</div>

</body>
</html>