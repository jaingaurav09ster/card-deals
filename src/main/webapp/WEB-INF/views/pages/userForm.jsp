<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="container form-container">
		<div class="row col-md-5 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Add User</div>
				</div>
				<div style="padding-top: 30px;" class="panel-body">
					<c:if test="${not empty error}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${msg}</div>
					</c:if>

					<form:form method="POST" modelAttribute="user">
						<form:input type="hidden" path="id" id="id" />
						<div class="form-group">
							<c:choose>
								<c:when test="${edit}">
									<form:input type="text" path="email" id="email"
										class="form-control" disabled="true" placeholder="User ID" />
									<form:hidden path="email" />
								</c:when>
								<c:otherwise>
									<form:input type="text" path="email" id="email"
										class="form-control" placeholder="User ID" />
									<div class="has-error">
										<form:errors path="email" class="help-inline" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="form-group">
							<form:input type="text" path="firstName" id="firstName"
								class="form-control" placeholder="First Name" />
							<div class="has-error">
								<form:errors path="firstName" class="help-inline" />
							</div>
						</div>

						<div class="form-group">
							<form:input type="text" path="lastName" id="lastName"
								class="form-control" placeholder="Last Name" />
							<div class="has-error">
								<form:errors path="lastName" class="help-inline" />
							</div>
						</div>

						<div class="form-group">
							<form:input type="password" path="password" id="password"
								class="form-control" placeholder="Password" />
							<div class="has-error">
								<form:errors path="password" class="help-inline" />
							</div>
						</div>

						<div class="form-group">
							<form:input type="text" path="email" id="email"
								class="form-control" placeholder="Email" />
							<div class="has-error">
								<form:errors path="email" class="help-inline" />
							</div>
						</div>
						<div class="form-group">
							<form:select path="userProfiles" items="${roles}" multiple="true"
								itemValue="id" itemLabel="type" class="form-control" />
							<div class="has-error">
								<form:errors path="userProfiles" class="help-inline" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-lable" for="userProfiles"></label>
							<c:choose>
								<c:when test="${edit}">
									<a href="<c:url value='/admin/user/list' />"
										class="btn btn-primary btn-sm">Cancel</a>
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" style="float: right" />
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/user/list' />"
										class="btn btn-primary btn-sm">Cancel</a>
									<input type="submit" value="Register"
										class="btn btn-primary btn-sm" style="float: right" />
								</c:otherwise>
							</c:choose>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</header>