<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row col-md-10 col-md-offset-1 custyle">
	<a href="<c:url value="/admin/newCardType" />"
		class="btn btn-primary btn-xs pull-right"><b>+</b> Add Card Type</a>
	<table class="table table-striped custab">
		<thead>
			<tr>
				<th>ID</th>
				<th>Card Type</th>
				<th>Description</th>
				<th class="text-center">Action</th>
			</tr>
		</thead>
		<c:forEach items="${cardTypes}" var="cardType">
			<tr>
				<td>${cardType.id}</td>
				<td>${cardType.name}</td>
				<td>${cardType.description}</td>
				<td class="text-center"><a class='btn btn-info btn-xs'
					href="<c:url value="/admin/updateCardType/${cardType.id}" />"><span
						class="glyphicon glyphicon-edit"></span> Edit</a> <a
					href="<c:url value="/admin/deleteCardType/${cardType.id}" />"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Del</a></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(cardTypes) lt 1}">
			<tr>
				<td colspan="5" style="text-align: center">No Results found</td>
			</tr>
		</c:if>
	</table>
</div>
