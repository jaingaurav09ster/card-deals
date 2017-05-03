<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
#cke_1_contents {
	height: 300px ! important;
}
</style>
<script src="${contextPath}/resources/vendor/ckeditor/ckeditor.js"></script>
<div class="col-sm-9 col-md-9 main-content">
	<c:choose>
		<c:when test="${edit}">
			<h3>
				Update Content Page<span class="required">&nbsp;(*required
					fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/updateContent" />
		</c:when>
		<c:otherwise>
			<h3>
				Add Content Page<span class="required">&nbsp;(*required
					fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/newContent" />
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
			<form:form method="POST" modelAttribute="content" id="contentForm"
				action="${actionUrl}" cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
					<div class="form-group">
						<label for="name" class="control-label col-md-3">Page
							Title<span class="asteriskField">*</span>
						</label>
						<div class="col-md-6">
							<form:input type="text" path="pageTitle" id="pageTitle"
								class="form-control" />
							<div class="has-error">
								<form:errors path="pageTitle" class="help-inline" />
							</div>
						</div>
					</div>
					<div>
						<div class="form-group">
							<label for="urlMapping" class="control-label col-md-3">URL
								Mapping<span class="asteriskField">*</span>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="urlMapping" id="urlMapping"
									class="form-control" />
								<div class="has-error">
									<form:errors path="urlMapping" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="control-label col-md-3">Page
								Tags </label>
							<div class="col-md-6">
								<form:input type="text" path="pageTags" id="pageTags"
									class="form-control" />
								<div class="has-error">
									<form:errors path="pageTags" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-md-3">Page
								Content<span class="asteriskField">*</span>
							</label>
							<div class="col-md-8">
								<div class="input-group">
									<form:textarea path="pageContent" class="form-control ckeditor" />
								</div>
								<div class="has-error">
									<form:errors path="pageContent" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-6">
							<a href="<c:url value='/admin/listContents' />"
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