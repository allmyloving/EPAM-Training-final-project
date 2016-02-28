<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<div class="jumbotron">
	<div class="container">
		<p>Hi!</p>
		<c:choose>
			<c:when test="${not empty tickets }">
				<p>
					<fmt:message key="message.ordered_tickets" />
				</p>
		`
		<c:forEach items="${tickets}" var="t">
					<div class="panel panel-info">
						<div class="panel-body">
							<st4:ticket bean="${t}" display="short" />
							<span class="pull-right"> <a
								class="btn btn-success btn-lg" href="print?id=${t.ticketId}"><fmt:message
										key="action.print" /></a></span>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<fmt:message key="message.no_tickets" />
			</c:otherwise>
		</c:choose>
	</div>
</div>
<br />
</div>
</div>

</body>
</html>