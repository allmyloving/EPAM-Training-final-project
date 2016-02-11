<%@page isErrorPage="true"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<c:set var="title" value="Oops" scope="page" />
<div class="jumbotron">
	<div class="container">
		<h1>Oops!</h1>
		<br />
		<h2>404 Not Found</h2>
		<p>It seems like the page you requested is not available.</p>
		<p>
			<a class="btn btn-success btn-lg" href="index.jsp">Go home</a>
		</p>
	</div>
</div>
</body>
</html>