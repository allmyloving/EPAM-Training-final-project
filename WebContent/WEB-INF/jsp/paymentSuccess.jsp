<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="jumbotron">
		<div class="container">
			<p>
				<fmt:message key="message.payment_success" />
			</p>
			<a href="print"><fmt:message key="action.print" /></a> <a
				href="controller?command=userProfileView"><fmt:message
					key="label.my_profile" /></a>
		</div>
	</div>

</body>
</html>