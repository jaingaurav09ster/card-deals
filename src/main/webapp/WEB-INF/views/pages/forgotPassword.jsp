<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<div class="container registration">
		<div class="row col-md-5 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Please enter your email</div>
				</div>
				<div class="panel-body">
					<c:if test="${not empty error}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${msg}</div>
					</c:if>
					<form method="POST" action="<c:url value='/forgotPassword' />" id="forgotPasswordForm">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span>
								<input type="text" name="email" id="email"
									class="form-control" placeholder="E-Mail Address" />
							</div>
						</div>
						<div class="form-group button-group">
							<a href="<c:url value='/' />" class="btn btn-primary btn-sm">Cancel</a>
							<input type="submit" value="Reset Password"
								class="btn btn-primary btn-sm" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</header>