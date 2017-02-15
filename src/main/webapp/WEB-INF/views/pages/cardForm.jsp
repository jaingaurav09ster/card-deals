<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="container form-container">
		<div class="row col-md-6 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<c:choose>
						<c:when test="${edit}">
							<div class="panel-title">Update Card</div>
						</c:when>
						<c:otherwise>
							<div class="panel-title">Add Card</div>
						</c:otherwise>
					</c:choose>
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
							<form:input path="title" type="text" class="form-control"
								id="title" placeholder="Card Title" required="true" />
							<!-- bind to user.name-->
							<form:errors path="title" />
						</div>
						<div class="form-group">
							<form:input path="bank.name" type="text" class="form-control"
								id="bankName" placeholder="Bank Name" required="true" />
							<!-- bind to user.name-->
							<form:errors path="bank.name" />
						</div>
						<div class="form-group">
							<form:textarea path="description" class="form-control" id="cardDesc"
								placeholder="Card Description" maxlength="140" rows="7" />
							<form:errors path="description" class="control-label" />
							<span class="help-block"><p id="characterLeft"
									class="help-block">You have reached the limit</p></span>
						</div>
						<c:choose>
							<c:when test="${edit}">
								<button type="submit" id="submit" name="submit"
									class="btn btn-primary pull-right">Update Card</button>
							</c:when>
							<c:otherwise>
								<button type="submit" id="submit" name="submit"
									class="btn btn-primary pull-right">Add Card</button>
							</c:otherwise>
						</c:choose>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</header>