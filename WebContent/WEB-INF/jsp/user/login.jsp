<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<fmt:message key="label.login" var="loginLoc"/>
<c:set var="title" value="${loginLoc}" scope="page" />
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form role="form" action="controller" method="post" id="loginForm">
				<input type="hidden" value="login" name="command" />
				<legend>${loginLoc}</legend>
				<fieldset>
				<div class="form-group">
					<label for="email"><fmt:message key="label.email"/>:</label> <input type="email"
						class="form-control" id="email" name="email">
				</div>
				<div class="form-group">
					<label for="password"><fmt:message key="label.password"/>:</label> <input type="password"
						class="form-control" id="password" name="password"> <span class="help-block">This
						is some help text that breaks onto a new line and may extend more
						than one line.</span>
				</div>
				<div class="checkbox">
					<label><input type="checkbox"> <fmt:message key="label.remember_me"/></label>
				</div>
				<button type="submit" class="btn btn-primary btn-block"><fmt:message key="label.login"/></button>
				</fieldset>
			</form>
		</div>
	</div>
</div>
</body>
</html>