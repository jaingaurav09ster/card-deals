<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row col-md-10 col-md-offset-1 custyle">
	<a href="<c:url value="/admin/newCategory" />"
		class="btn btn-primary btn-xs pull-right"><b>+</b> Add Category</a>
	<table class="table table-striped custab">
		<thead>
			<tr>
				<th>ID</th>
				<th>Card Title</th>
				<th>Card Description</th>
				<th class="text-center">Action</th>
			</tr>
		</thead>
		<c:forEach items="${categories}" var="category">
			<tr>
				<td>${category.id}</td>
				<td>${category.name}</td>
				<td>${category.description}</td>
				<td class="text-center"><a class='btn btn-info btn-xs'
					href="<c:url value="/admin/updateCardCategory/${category.id}" />"><span
						class="glyphicon glyphicon-edit"></span> Edit</a> <a
					href="<c:url value="/admin/deleteCardCategory/${category.id}" />"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Del</a></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(categories) lt 1}">
			<tr>
				<td colspan="5" style="text-align: center">No Results found</td>
			</tr>
		</c:if>
	</table>
</div>
