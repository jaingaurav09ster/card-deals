<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div
		class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 form">
		<div class="panel panel-default">
			<div class="panel-body">
				<p align="center">Sign up with us</p>
				<p align="center" class="small-font">Create account and get access to the
					personalized deals specially tailored for you.</p>
				<c:if test="${not empty error}">
					<div class="alert alert-danger col-sm-12">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-danger col-sm-12">${msg}</div>
				</c:if>
				<form:form method="POST" modelAttribute="user" id="registrationForm">
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
							<form:input type="text" path="email" id="email"
								class="form-control" placeholder="E-Mail Address" />
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
							<form:input type="text" path="mobile" id="email"
								class="form-control" placeholder="Mobile" />
						</div>
						<div class="has-error">
							<form:errors path="mobile" class="help-inline" />
						</div>
					</div>
					<div class="form-group button-group" align="center">
						<a href="<c:url value='/login' />"
							class="btn btn-primary btn-sm btn-cancel">Cancel</a> <input
							type="submit" value="Register" class="btn btn-primary btn-sm" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
