<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<jsp:include page="masterDataNav.jsp" />
	<a href="<c:url value="/admin/newBank" />"
		class="btn btn-primary btn-xs pull-right"><b>+</b> Add Bank</a>
	<table class="table table-striped custab">
		<thead>
			<tr>
				<th>ID</th>
				<th>Bank Title</th>
				<th>Bank Description</th>
				<th class="text-center">Action</th>
			</tr>
		</thead>
		<c:forEach items="${banks}" var="bank">
			<tr>
				<td>${bank.id}</td>
				<td>${bank.name}</td>
				<td>${bank.description}</td>
				<td class="text-center"><a class='btn btn-info btn-xs'
					href="<c:url value="/admin/updateBank/${bank.id}" />"><span
						class="glyphicon glyphicon-edit"></span> Edit</a> <a
					href="<c:url value="/admin/deleteBank/${bank.id}" />"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Del</a></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(banks) lt 1}">
			<tr>
				<td colspan="5" style="text-align: center">No Results found</td>
			</tr>
		</c:if>
	</table>
</div>
