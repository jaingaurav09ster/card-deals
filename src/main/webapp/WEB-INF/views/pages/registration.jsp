<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<div class="container">
		<div class="row col-md-6 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">User Registration Form</div>
				</div>
				<div style="padding-top: 30px; padding-left: 40px"
					class="panel-body">
					<c:if test="${not empty error}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${msg}</div>
					</c:if>

					<form:form method="POST" modelAttribute="user"
						class="form-horizontal">
						<form:input type="hidden" path="id" id="id" />

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="ssoId">User
									ID</label>
								<div class="col-md-7">
									<c:choose>
										<c:when test="${edit}">
											<form:input type="text" path="ssoId" id="ssoId"
												class="form-control input-sm" disabled="true" />
											<form:hidden path="ssoId" />
										</c:when>
										<c:otherwise>
											<form:input type="text" path="ssoId" id="ssoId"
												class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="ssoId" class="help-inline" />
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="firstName">First
									Name</label>
								<div class="col-md-7">
									<form:input type="text" path="firstName" id="firstName"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="firstName" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="lastName">Last
									Name</label>
								<div class="col-md-7">
									<form:input type="text" path="lastName" id="lastName"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="lastName" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="password">Password</label>
								<div class="col-md-7">
									<form:input type="password" path="password" id="password"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="password" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="email">Email</label>
								<div class="col-md-7">
									<form:input type="text" path="email" id="email"
										class="form-control input-sm" />
									<div class="has-error">
										<form:errors path="email" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
						<sec:authorize access="hasRole('ADMIN')">
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
									<div class="col-md-7">
										<form:select path="userProfiles" items="${roles}"
											multiple="true" itemValue="id" itemLabel="type"
											class="form-control input-sm" />
										<div class="has-error">
											<form:errors path="userProfiles" class="help-inline" />
										</div>
									</div>
								</div>
							</div>
						</sec:authorize>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="userProfiles"></label>
								<div class="col-md-7">
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
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</header>