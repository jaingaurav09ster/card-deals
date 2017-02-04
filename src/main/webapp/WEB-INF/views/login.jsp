<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Login" />
</jsp:include>
<body id="page-top" class="index">
	<%@include file="navbar.jsp"%>
	<!-- Header -->
	<header>
		<div class="container">
			<div class="row">
				<div id="loginbox"
					class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Login into your account</div>
						</div>
						<div style="padding-top: 30px" class="panel-body">
							<c:if test="${param.error != null}">
								<div id="login-alert" class="alert alert-danger col-sm-12">Invalid
									username and password.</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div id="login-alert" class="alert alert-danger col-sm-12">You
									have been logged out successfully.</div>
							</c:if>
							<c:url var="loginUrl" value="/login" />
							<form name='loginForm' action="${loginUrl}" method='POST'
								id="loginform" class="form-horizontal" role="form">
								<div style="margin-bottom: 25px" class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input
										id="login-username" class="form-control" name="ssoId" value=""
										placeholder="Username" type="text">
								</div>
								<div style="margin-bottom: 5px" class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input
										id="login-password" class="form-control" name="password"
										placeholder="Password" type="password">
								</div>
								<div style="margin-bottom: 25px;margin-left: 5px" class="input-group">
									<div class="checkbox">
										<label class="checkbox inline"><input type="checkbox" id="rememberme"
											name="remember-me"> Remember Me</label>
									</div>
								</div>
								<div style="margin-top: 10px" class="form-group">
									<div class="col-sm-12 controls">
										<button type="submit" class="btn btn-primary btn-block">Login</button>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<%@include file="footer.jsp"%>
	<!-- /container -->
</body>
</html>