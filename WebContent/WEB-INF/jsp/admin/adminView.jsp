<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<p>
		<h2>Welcome to admin page!</h2>
		</p>
		<br />
		<div class="btn-group-justified btn-group-lg">
			<a class="btn btn-default"
				href="controller?command=stationView">Manage stations</a> <a
				class="btn btn-default"
				href="controller?command=routeView">Manage routes</a> <a
				class="btn btn-default"
				href="controller?command=trainView">Manage trains</a>
		</div>
	</div>
</body>
</html>