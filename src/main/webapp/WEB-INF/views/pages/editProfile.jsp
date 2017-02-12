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
					<div class="panel-title">Edit Profile</div>
				</div>
				<div class="panel-body">
					<c:if test="${not empty error}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="login-alert" class="alert alert-danger col-sm-12">${msg}</div>
					</c:if>
					<form:form method="POST" modelAttribute="user"
						id="editProfileForm">
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
									class="form-control" placeholder="E-Mail Address" disabled="true"/>
									<form:hidden path="email" />
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
						<div class="form-group button-group">
							<a href="<c:url value='/' />" class="btn btn-primary btn-sm">Cancel</a>
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</header>