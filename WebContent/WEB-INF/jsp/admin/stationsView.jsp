<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<h2>Stations</h2>

		<table class="table table-hover">
			<c:forEach items="${stations}" var="s">
				<tr>
					<td>${s.name}</td>
					<td><a href="#">Edit</a>
				</tr>
			</c:forEach>
		</table>
		<a href="controller?command=addStation">Add new station</a>
	</div>

</body>
</html>