<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<fmt:setLocale value="${sessionScope.currentLocaleLocale}" />
<fmt:setBundle basename="${applicationScope.bundleBasename}" />
<%@ attribute name="bean" required="true"
	type="ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean"%>
<%@ attribute name="display" required="true" type="java.lang.String"%>

<div class="list-group">
	<a class="list-group-item"><b><fmt:message
				key="label.train_tag" /></b><span class="pull-right">${bean.trainBean.trainTag}</span></a>
	<a class="list-group-item"><b><fmt:message
				key="label.departure" /></b><span class="pull-right"> <fmt:formatDate
				value="${bean.trainBean.depDate}" type="both" timeStyle="short"
				dateStyle="long" /> <b>${bean.stationFrom.name}</b></span></a> <a
		class="list-group-item"><b><fmt:message key="label.arrival" />
	</b> <span class="pull-right"><fmt:formatDate
				value="${bean.trainBean.arrDate}" type="both" timeStyle="short"
				dateStyle="long" /> <b>${bean.stationTo.name}</b></span></a> <a
		class="list-group-item"><b><fmt:message
				key="label.carriage_tag" /></b><span class="pull-right">${bean.carriage.tag}</span></a>
	<a class="list-group-item"><b><fmt:message key="label.seat_num" /></b><span
		class="pull-right"> ${bean.seatNum}</span></a>
		
	<c:if test="${display eq 'full'}">
		<a class="list-group-item"><b><fmt:message
					key="label.carriage_type" /></b> <span class="pull-right"><fmt:message
					key="${bean.carriage.type.name}" /></span></a>
	</c:if>
	<a class="list-group-item"><b><fmt:message key="label.price" /></b><span
		class="pull-right">&#8372; ${bean.carriage.price}</span></a>
</div>