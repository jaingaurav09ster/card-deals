<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="container form-container">
		<div class="row col-md-5 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<c:choose>
						<c:when test="${edit}">
							<div class="panel-title">Update Card</div>
							<c:url var="actionUrl" value="/admin/card/updateCard" />
						</c:when>
						<c:otherwise>
							<div class="panel-title">Add Card</div>
							<c:url var="actionUrl" value="/admin/card/newCard" />
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
					<form:form method="POST" modelAttribute="card" id="addCard"
						action="${actionUrl}">
						<form:input type="hidden" path="id" id="id" />
						<div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="title" id="title"
										class="form-control" placeholder="Card Title" />
								</div>
								<div class="has-error">
									<form:errors path="title" class="help-inline" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="launchDate" id="launchDate"
										class="form-control" placeholder="Card Launch Date" />
								</div>
								<div class="has-error">
									<form:errors path="launchDate" class="help-inline" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="image" id="image"
										class="form-control" placeholder="Card Image" />
								</div>
								<div class="has-error">
									<form:errors path="image" class="help-inline" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="rank" id="rank"
										class="form-control" placeholder="Card Rank" />
								</div>
								<div class="has-error">
									<form:errors path="rank" class="help-inline" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span>
									<form:textarea path="description" class="form-control"
										id="cardDesc" placeholder="Card Description" maxlength="140"
										rows="7" />
								</div>
								<div class="has-error">
									<form:errors path="description" class="help-inline" />
								</div>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="form-group button-group">
							<c:choose>
								<c:when test="${edit}">
									<a href="<c:url value='/admin/card/listCards' />"
										class="btn btn-primary btn-sm">Cancel</a>
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" />
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/card/listCards' />"
										class="btn btn-primary btn-sm">Cancel</a>
									<input type="submit" value="Add"
										class="btn btn-primary btn-sm" />
								</c:otherwise>
							</c:choose>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</header>