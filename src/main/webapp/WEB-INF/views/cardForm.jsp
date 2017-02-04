<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body id="page-top" class="index">
	<%@include file="navbar.jsp"%>
	<!-- Header -->
	<header>
		<div class="container">
			<div class="row col-md-6 col-md-offset-3 custyle">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">Add Card</div>
					</div>
					<div style="padding-top: 30px" class="panel-body">
						<c:if test="${not empty error}">
							<div id="login-alert" class="alert alert-danger col-sm-12">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div id="login-alert" class="alert alert-danger col-sm-12">${msg}</div>
						</c:if>
						<form:form method="post" modelAttribute="card" action="addCard">
							<div class="form-group">
								<form:input path="cardTitle" type="text" class="form-control"
									id="cardTitle" placeholder="Card Title" required="true" />
								<!-- bind to user.name-->
								<form:errors path="cardTitle" />
							</div>
							<div class="form-group">
								<form:input path="bankName" type="text" class="form-control"
									id="bankName" placeholder="Bank Name" required="true" />
								<!-- bind to user.name-->
								<form:errors path="bankName" />
							</div>
							<div class="form-group">
								<form:textarea path="cardDesc" class="form-control" id="cardDesc"
									placeholder="Card Description" maxlength="140" rows="7" />
								<form:errors path="cardDesc" class="control-label" />
								<span class="help-block"><p id="characterLeft"
										class="help-block">You have reached the limit</p></span>
							</div>
							<button type="submit" id="submit" name="submit"
								class="btn btn-primary pull-right">Add Card</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</header>
	<%@include file="footer.jsp"%>
	<!-- /container -->
</body>
</html>
