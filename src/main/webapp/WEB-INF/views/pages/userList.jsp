<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row col-md-10 col-md-offset-1 custyle">
	<sec:authorize access="hasRole('ADMIN')">
		<a href="<c:url value='/admin/newUser' />"
			class="btn btn-primary btn-xs pull-right"><b>+</b> Add new User</a>
	</sec:authorize>
	<table class="table table-striped custab">
		<thead>
		<thead>
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>Email</th>
				<th>Mobile</th>
				<sec:authorize access="hasRole('ADMIN')">
					<th class="text-center">Action</th>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.mobile}</td>
					<td class="text-center"><sec:authorize
							access="hasRole('ADMIN')">
							<a class="btn btn-info btn-xs"
								href="<c:url value='/admin/updateUser/${user.email}' />"><span
								class="glyphicon glyphicon-edit"></span> Edit</a>
						</sec:authorize> <sec:authorize access="hasRole('ADMIN')">
							<a href="<c:url value='/admin/deleteUser/${user.email}' />"
								class="btn btn-danger btn-xs"><span
								class="glyphicon glyphicon-remove"></span> Del</a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(users) lt 1}">
				<tr>
					<td colspan="5" style="text-align: center">No Results found</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
