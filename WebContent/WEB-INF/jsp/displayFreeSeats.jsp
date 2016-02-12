<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<div class="row">
			<c:forEach items="${carriageList.types}" var="t">
				<div class="col-lg-6">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								<fmt:message key="${t}" />
							</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<a class="list-group-item"><fmt:message
										key="label.carriage_tag" /><span class="pull-right"><fmt:message
											key="label.available_seats" /></span></a>
								<c:forEach items="${carriageList.carriages}" var="c">
									<c:if test="${c.type.name==t}">
										<c:set scope="page" value="${fn:length(c.seatsTaken)}"
											var="seatsTaken" />
										<a href="#" class="list-group-item">${c.tag}<span
											class="badge">${c.type.seatNum - seatsTaken}</span></a>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="well">
							<fmt:message key="message.click_row" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>