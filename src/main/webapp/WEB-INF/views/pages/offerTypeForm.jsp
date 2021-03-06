<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="category"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<link
	href="${contextPath}/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${contextPath}/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css">
<script
	src="${contextPath}/resources/vendor/bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="${contextPath}/resources/vendor/ckeditor/ckeditor.js"></script>

<c:set var="level" value="-1" scope="page" />
<div class="col-sm-9 col-md-9 main-content">
	<c:choose>
		<c:when test="${edit}">
			<h3>
				Update Offer Type<span class="required">&nbsp;(*required
					fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/updateOfferType" />
		</c:when>
		<c:otherwise>
			<h3>
				Add Offer Type<span class="required">&nbsp;(*required fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/newOfferType" />
		</c:otherwise>
	</c:choose>
	<hr>
	<div class="panel panel-default">
		<div class="panel-body">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-danger col-sm-12">${msg}</div>
			</c:if>
			<form:form method="POST" modelAttribute="offerType" id="addOfferType"
				action="${actionUrl}" cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div>
					<div class="form-group">
						<label for="title" class="control-label col-md-3">Offer Name<span
							class="asteriskField">*</span></label>
						<div class="col-md-6">
							<form:input type="text" path="title" id="title"
								class="form-control" />
							<div class="has-error">
								<form:errors path="title" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-md-3">Description</label>
						<div class="col-md-8">
							<form:textarea path="description" class="form-control ckeditor"
								id="ckEditorTextArea" />
							<div class="has-error">
								<form:errors path="description" class="help-inline" />
							</div>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-6">
						<a href="<c:url value='/admin/listOfferTypes' />"
							class="btn btn-primary btn-md btn-cancel">Cancel</a>
						<c:choose>
							<c:when test="${edit}">
								<input type="submit" value="Update"
									class="btn btn-primary btn-md" />
							</c:when>
							<c:otherwise>
								<input type="submit" value="Add" class="btn btn-primary btn-md" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
