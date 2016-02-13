<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
<c:set var="title" value="${signUpLoc}" scope="page" />
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form role="form" action="controller" method="post" id="ticketOrderForm">
				<input type="hidden" value="orderTicket" name="command" />
				<fieldset>
					<legend>
						<fmt:message key="label.ticket_order" />
					</legend>
					<div class="alert alert-dismissible alert-warning" id="errorDiv">
					</div>
					<div class="form-group">
						<label class="control-label" for="firstName"><fmt:message
								key="label.first_name" />:</label> <input class="form-control" id="firstName"
							name="firstName" value="${requestScope.email}">
						<p class="text-warning" id="firstNameError"></p>
					</div>
					<div class="form-group">
						<label class="control-label" for="lastName"><fmt:message
								key="label.last_name" />:</label> <input class="form-control" id="lastName"
							name="lastName" value="${requestScope.email}">
						<p class="text-warning" id="lastNameError"></p>
					</div>
					<div class="form-group">
						<label class="control-label" for="repPassword"> <fmt:message
								key="label.repeat_password" />:
						</label> <input type="password" class="form-control" id="repPassword"
							name="repPassword">
						<p class="text-warning" id="repPasswordError"></p>
					</div>
					<button type="submit" class="btn btn-default btn-block"
						id="signUpSubmit" onclick="validateSignUp()"><fmt:message key="label.proceed"/></button>
				</fieldset>
			</form>
		</div>
	</div>
</div>
</body>
</html>