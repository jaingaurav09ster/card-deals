<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#cke_1_contents {
	height: 300px ! important;
}
</style>
<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>
<div class="row">
	<jsp:include page="masterDataNav.jsp" />
	<div class="col-sm-7 col-md-7 form">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">
							Update Content <span class="required">(*required fields)</span>
						</div>
						<c:url var="actionUrl" value="/admin/updateContent" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">
							Add Content <span class="required">(*required fields)</span>
						</div>
						<c:url var="actionUrl" value="/admin/newContent" />
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
				<form:form method="POST" modelAttribute="content" id="contentForm"
					action="${actionUrl}" cssClass="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<div>
						<div class="form-group">
							<label for="name" class="control-label col-md-4">Page
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
								<label for="urlMapping" class="control-label col-md-4">URL
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
								<label for="name" class="control-label col-md-4">Page
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
								<label for="description" class="control-label col-md-4">Page
									Content<span class="asteriskField">*</span>
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<form:textarea path="pageContent"
											class="form-control ckeditor" />
									</div>
									<div class="has-error">
										<form:errors path="pageContent" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label"></label>
							<div class="col-md-6">
								<a href="<c:url value='/admin/listContents' />"
									class="btn btn-primary btn-sm btn-cancel">Cancel</a>
								<c:choose>
									<c:when test="${edit}">
										<input type="submit" value="Update"
											class="btn btn-primary btn-sm" />
									</c:when>
									<c:otherwise>
										<input type="submit" value="Add"
											class="btn btn-primary btn-sm" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
				</form:form>
			</div>
		</div>
	</div>
</div>