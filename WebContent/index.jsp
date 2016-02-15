<%@ include file="/WEB-INF/jspf/head.jspf"%>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/date.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker3.css" />
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form action="controller" method="get" role="form">
				<input type="hidden" name="command" value="findTrains" />
				<legend>
					<fmt:message key="label.find_tickets" />
				</legend>
				<div class="form-group">
					<label class="control-label" for="stationFrom"> <fmt:message
							key="label.from" />:
					</label> <input class="form-control" id="stationFrom" name="stationFrom"
						onkeyup="getStations(this)" value="${stationFrom}" />
					<p class="text-warning" id="stationFromError">${stationFromError}</p>
				</div>
				<div class="text-center">
					<a href="#" class="btn btn-default" id="swapStations"><span class="glyphicon glyphicon-sort"/></a>
				</div>
				<div class="form-group">
					<label class="control-label" for="stationTo"> <fmt:message
							key="label.to" />:
					</label><input class="form-control" id="stationTo" name="stationTo"
						onkeyup="getStations(this)" value="${stationTo}">
					<p class="text-warning" id="stationToError">${stationToError}</p>
				</div>
				<div class="form-group">
					<label class="control-label" for="date"> <fmt:message
							key="label.date" />:
					</label> <input class="form-control datepicker" id="date" name="date"
						value="${requestScope.date}" data-provide="datepicker-inline" />
					<p class="text-warning" id="date">${dateError}</p>
				</div>
				<button type="submit" class="btn btn-default">
					<fmt:message key="action.search" />
				</button>
			</form>
		</div>
	</div>
</div>
<hr />
<div class="container">
	<c:choose>
		<%-- Make it a list --%>
		<c:when test="${not empty sessionScope.trainBeans}">
			<table class="table table-bordered">
				<tr>
					<th><fmt:message key="label.train_tag" /></th>
					<th><fmt:message key="label.from" /></th>
					<th><fmt:message key="label.to" /></th>
					<th><fmt:message key="label.departure" /></th>
					<th><fmt:message key="label.arrival" /></th>
					<th><fmt:message key="label.duration" /></th>
				</tr>
				<c:forEach items="${trainBeans}" var="t">
					<tr>
						<td><a
							href="controller?command=showRouteInfo&trainId=${t.trainId}"
							data-toggle="popover" data-trigger="hover" data-placement="left"
							title="Popover Header"
							data-content="Some content inside the popover"
							class="btn btn-link">${t.trainTag}</a></td>
						<td>${t.stationFrom}</td>
						<td>${t.stationTo}</td>
						<td><fmt:formatDate value="${t.depDate}" type="both"
								timeStyle="short" dateStyle="long" /></td>
						<td><fmt:formatDate value="${t.arrDate}" type="both"
								timeStyle="short" dateStyle="long" /></td>
						<td><fmt:formatDate value="${t.duration}" pattern="hh:mm" /></td>
						<td><a
							href="controller?command=getFreeSeats&routeId=${t.routeId}"><fmt:message
									key="action.view_seats" /></a></td>
					</tr>
				</c:forEach>

			</table>
			<div class="well">	<fmt:message key="message.time_local" /></div>
		</c:when>
		<c:otherwise>
			<div class="well well-lg">
				<fmt:message key="message.nothing_found" />
			</div>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>