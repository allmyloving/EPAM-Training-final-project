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
								<fmt:message key="${t.name}" />
								<span class="pull-right"> &#8372;${t.price}</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<a class="list-group-item"><fmt:message
										key="label.carriage_tag" /><span class="pull-right"><fmt:message
											key="label.available_seats" /></span></a>
								<c:forEach items="${carriageList.carriages}" var="c">
									<c:if test="${c.type==t}">
										<c:set scope="page" value="${fn:length(c.seatsTaken)}"
											var="seatsTaken" />
										<a href="#" class="list-group-item carriage-item" id="${c.id}"><b>${c.tag}</b><span
											class="badge">${c.type.seatNum - seatsTaken}</span></a>
										<div id="${c.id}" class="seat-container">
											<c:forEach begin="1" end="${c.type.seatNum}" step="1"
												var="seat">
												<c:choose>
													<c:when test="${empty c.seats[seat]}">
														<a href="#" class="btn btn-warning">
													</c:when>
													<c:otherwise>
														<a href="#" class="btn btn-warning disabled">
													</c:otherwise>
												</c:choose>
												${seat }</a>
											</c:forEach>
										</div>
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