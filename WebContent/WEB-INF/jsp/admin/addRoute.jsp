<%@ include file="/WEB-INF/jspf/head.jspf"%>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/date.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker3.css" />
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="ru">
	<div class="container">
		<legend>
			<fmt:message key="label.add_route" />
		</legend>
		<div class='row'>
			<div class='col-sm-6'>
				<div class='form-group'>
					<label for="trainSelect"><fmt:message key="label.train" /></label>
					<select id="trainSelect" class="form-control">
						<c:forEach items="${trainBeans}" var="t">
							<option value="${t.trainId}">${t.trainTag}
								&nbsp;&#124;&nbsp;&nbsp;${t.stationFrom} &mdash; ${t.stationTo}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class='col-sm-6'>
				<div class='form-group'>
					<label for="date"><fmt:message key="label.date" /></label> <input
						class="form-control datepicker" id="date" name="date"
						data-provide="datepicker-inline" />
					<p class="text-warning" id="date">${dateError}</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class='col-sm-6 col-sm-offset-4'>
				<button type="submit" class="btn btn-default btn-block"
					onclick="addRoute()">
					<fmt:message key="action.add" />
				</button>
			</div>
		</div>

	</div>
</body>
</html>