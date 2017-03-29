<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>

<div class="row col-md-7 col-md-offset-2 custyle">
	<div class="panel panel-default">
		<div class="panel-heading">
			<c:choose>
				<c:when test="${edit}">
					<div class="panel-title">Update Card Category</div>
					<c:url var="actionUrl" value="/admin/updateCardCategory" />
				</c:when>
				<c:otherwise>
					<div class="panel-title">Add Card Category</div>
					<c:url var="actionUrl" value="/admin/newCardCategory" />
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
			<form:form method="POST" modelAttribute="cardCategory"
				id="addCardCategory" action="${actionUrl}"
				cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div>
					<div class="form-group">
						<label for="name" class="control-label col-xs-3">Name<span
							class="asteriskField">*</span></label>
						<div class="col-xs-6">
							<form:input type="text" path="name" id="name"
								class="form-control" placeholder="Card Category Name" />
							<div class="has-error">
								<form:errors path="name" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-xs-3">Description</label>
						<div class="col-xs-8">
							<div class="input-group">
								<form:textarea path="description" class="form-control ckeditor"
									id="cardDesc" placeholder="Card Category Description" />
							</div>
							<div class="has-error">
								<form:errors path="description" class="help-inline" />
							</div>
						</div>
						<div style="clear: both;"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-8 col-xs-10">
						<a href="<c:url value='/admin/listCardCategories' />"
							class="btn btn-primary btn-sm">Cancel</a>
						<c:choose>
							<c:when test="${edit}">
								<input type="submit" value="Update"
									class="btn btn-primary btn-sm" />
							</c:when>
							<c:otherwise>
								<input type="submit" value="Add" class="btn btn-primary btn-sm"
									style="margin-left: 23px;" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
