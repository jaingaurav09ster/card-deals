<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
	<jsp:include page="manageUserNav.jsp" />
	<div class="col-sm-4 col-md-6">
		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h3 class="panel-title">List of Users</h3>
					</div>
					<div class="col col-xs-6 text-right">
						<sec:authorize access="hasRole('ADMIN')">
							<a href="<c:url value="/admin/newUser" />"
								class="btn btn-sm btn-primary btn-create">Create New</a>
						</sec:authorize>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-list"
					id="paginate">
					<thead>
						<tr>
							<th class="hidden-xs">ID</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>Email</th>
							<th>Mobile</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th><em class="fa fa-cog"></em></th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td class="hidden-xs">${user.id}</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.mobile}</td>
								<sec:authorize access="hasRole('ADMIN')">
									<td align="center"><form
										action="<c:url value="/admin/deleteUser/${user.email}" />"><a
										href="<c:url value="/admin/updateUser/${user.email}" />"
										class="btn btn-default"><em class="fa fa-pencil"></em></a>
										<button class='btn btn-danger' type="submit"
											name="remove_levels" value="delete">
											<em class="fa fa-trash"></em>
										</button></form></td>
								</sec:authorize>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(users) lt 1}">
							<tr>
								<td colspan="4" style="text-align: center">No Results found</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="col col-xs-8" id="navigation"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="confirmModal.jsp" />