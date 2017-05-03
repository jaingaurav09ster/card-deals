<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script src="${contextPath}/resources/vendor/ckeditor/ckeditor.js"></script>
<div class="col-sm-9 col-md-9 main-content">
	<c:choose>
		<c:when test="${edit}">
			<h3>
				Update Document<span class="required">&nbsp;(*required fields)</span>
			</h3>
			<c:url var="actionUrl" value="/updateDocument/${cardId}" />
		</c:when>
		<c:otherwise>
			<h3>
				Add Document<span class="required">&nbsp;(*required fields)</span>
			</h3>
			<c:url var="actionUrl" value="/newDocument/${cardId}" />
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
			<form:form method="POST" modelAttribute="document" id="addDocument"
				action="${actionUrl}" cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<form:input type="hidden" path="cardId" value="${cardId}" />
				<div>
					<div class="form-group">
						<label for="name" class="control-label col-md-3">Name<span
							class="asteriskField">*</span></label>
						<div class="col-md-6">
							<form:input type="text" path="name" id="name"
								class="form-control" placeholder="Name" />
							<div class="has-error">
								<form:errors path="name" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-md-3">Description</label>
						<div class="col-md-8">
							<div class="input-group">
								<form:textarea path="description" class="form-control ckeditor"
									id="ckEditorTextArea" placeholder="document Description" />
							</div>
							<div class="has-error">
								<form:errors path="description" class="help-inline" />
							</div>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-6">
						<a href="<c:url value='/listDocuments/${cardId}' />"
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
