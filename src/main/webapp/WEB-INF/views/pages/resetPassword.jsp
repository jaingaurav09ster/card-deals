<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div
		class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 form col-xs-10 col-xs-offset-1">
		<div class="panel panel-default">
			<div class="panel-body">
				<p align="center">Reset Password</p>
				<p class="small-font">Please enter new password that can be used
					further for login</p>
				<c:if test="${not empty error}">
					<div class="alert alert-danger col-sm-12">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-danger col-sm-12">${msg}</div>
				</c:if>
				<form:form method="POST" modelAttribute="resetPasswordForm"
					id="resetPasswordForm">
					<form:hidden path="resetPasswordToken" />
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span>
							<form:input type="text" path="newPassword" id="newPassword"
								class="form-control" placeholder="New Password" />
						</div>
						<div class="has-error">
							<form:errors path="newPassword" class="help-inline" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span>
							<form:input type="text" path="matchPassword" id="matchPassword"
								class="form-control" placeholder="Re-enter Password" />
						</div>
						<div class="has-error">
							<form:errors path="matchPassword" class="help-inline" />
						</div>
					</div>
					<div class="form-group button-group" align="center">
						<a href="<c:url value='/login' />"
							class="btn btn-primary btn-md btn-cancel">Cancel</a> <input
							type="submit" value="Reset Password"
							class="btn btn-primary btn-md" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>