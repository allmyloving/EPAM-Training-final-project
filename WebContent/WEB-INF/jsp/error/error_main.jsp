<%@ page isErrorPage="true"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<c:set var="title" value="Oops" scope="page" />

<div class="jumbotron">
	<div class="container">
		<h1>Oops!</h1>
		<br />
		<p>It seems like some error occurred. We're sorry.</p>
		<div class="list-group">
			<ul>
				<c:forEach items="${errors}" var="error">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
		<p>If you are not able to fix those on your own, please, contact
			your system administrator.</p>
		<p>
			<a class="btn btn-success btn-lg" href="javascript: history.go(-1)">Go
				back</a>
		</p>
	</div>
</div>
</body>
</html>