<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ taglib prefix="st4" uri="/WEB-INF/myTags.tld"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="${currentLocaleLocale}">
	<div class="container">
		<form role="form" action="controller" class="input-not-empty" method="post">
			<input type="hidden" name="command" value="addCarriages" />
			<input type="hidden" name="trainId" value="${trainId}" />
			<legend>
				<fmt:message key="label.select_stations" />
			</legend>
			<div id="carriageContainer">
				<div id="selectCarriage">
					<div class='row'>
						<div class='col-sm-4'>
							<div class='form-group'>
								<label class="control-label" for="carTag"><fmt:message
										key="label.carriage_tag" /></label> <input class="form-control"
									id="carTag" name="carTag">
								<p class="text-warning" id="carTagError"></p>
							</div>
						</div>
						<div class='col-sm-4'>
							<div class='form-group'>
								<label for="carTypeSelect"><fmt:message
										key="label.carriage_type" /></label> <select id="carTypeSelect"
									name="carTypeSelect" class="form-control">
									<c:forEach items="${carTypes}" var="s">
										<option value="${s.id}"><fmt:message key="${s.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-default" onclick="addCarriageSelect(event)">
					<span class="glyphicon glyphicon-plus" />
				</button>
			</div>
			<div class="form-group">
				<button class="btn btn-default" type="submit">
					<fmt:message key="action.submit" />
				</button>
			</div>
		</form>
	</div>
</body>
</html>