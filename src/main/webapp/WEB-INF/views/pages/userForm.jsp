<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<jsp:include page="manageUserNav.jsp" />
	<div class="col-sm-7 col-md-7 col-lg-5 form">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update User</div>
						<c:url var="actionUrl" value="/admin/updateUser" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">Add User</div>
						<c:url var="actionUrl" value="/admin/newUser" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-body">
				<c:if test="${not empty error}">
					<div class="alert alert-danger">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-danger col-sm-12">${msg}</div>
				</c:if>
				<form:form method="POST" modelAttribute="user" id="newUserForm"
					action="${actionUrl}">
					<form:input type="hidden" path="id" id="id" />
					<div>
						<div class="form-group firstName">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input type="text" path="firstName" id="firstName"
									class="form-control" placeholder="First Name" />
							</div>
							<div class="has-error">
								<form:errors path="firstName" class="help-inline" />
							</div>
						</div>

						<div class="form-group lastName">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input type="text" path="lastName" id="lastName"
									class="form-control" placeholder="Last Name" />
							</div>
							<div class="has-error">
								<form:errors path="lastName" class="help-inline" />
							</div>
						</div>
						<div style="clear: both;"></div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span>
							<c:choose>
								<c:when test="${edit}">
									<form:input type="text" path="email" id="email"
										class="form-control" disabled="true" placeholder="User ID" />
									<form:hidden path="email" />
								</c:when>
								<c:otherwise>
									<form:input type="text" path="email" id="email"
										class="form-control" placeholder="User ID" />
								</c:otherwise>
							</c:choose>
						</div>
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<form:input type="password" path="password" id="password"
								class="form-control" placeholder="Password" />
						</div>
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-earphone"></i></span>
							<form:input type="text" path="mobile" id="mobile"
								class="form-control" placeholder="Mobile" />
						</div>
						<div class="has-error">
							<form:errors path="mobile" class="help-inline" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-bank"></i></span>
							<form:select class="form-control" path="bank.id" id="bank">
								<form:option value="">Please Select Bank</form:option>
								<form:options items="${banks}" itemValue="Id" itemLabel="name" />
							</form:select>
						</div>
						<div class="has-error">
							<form:errors path="bank" class="help-inline" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list"></i></span>
							<form:select path="userRoles" items="${roles}" multiple="true"
								itemValue="id" itemLabel="type" class="form-control" />
						</div>
						<div class="has-error">
							<form:errors path="userRoles" class="help-inline" />
						</div>
					</div>
					<div class="form-group button-group">
						<c:choose>
							<c:when test="${edit}">
								<a href="<c:url value='/admin/listUsers' />"
									class="btn btn-primary btn-sm btn-cancel">Cancel</a>
								<input type="submit" value="Update"
									class="btn btn-primary btn-sm" />
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/admin/listUsers' />"
									class="btn btn-primary btn-sm btn-cancel">Cancel</a>
								<input type="submit" value="Register"
									class="btn btn-primary btn-sm" />
							</c:otherwise>
						</c:choose>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>