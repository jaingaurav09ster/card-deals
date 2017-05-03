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
				Update Merchant Information<span class="required">&nbsp;(*required
					fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/updateMerchant" />
		</c:when>
		<c:otherwise>
			<h3>
				Add New Merchant<span class="required">&nbsp;(*required
					fields)</span>
			</h3>
			<c:url var="actionUrl" value="/admin/newMerchant" />
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
			<form:form enctype="multipart/form-data" method="POST"
				modelAttribute="merchant" id="merchantForm" action="${actionUrl}"
				cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div>
					<div class="form-group">
						<label for="name" class="control-label col-md-3">Merchant
							Name<span class="asteriskField">*</span>
						</label>
						<div class="col-md-6">
							<form:input type="text" path="name" id="name"
								class="form-control" />
							<div class="has-error">
								<form:errors path="name" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="image" class="control-label col-md-3">Logo</label>
						<div class="col-md-6">
							<div class="input-group image-preview">
								<form:input type="text"
									class="form-control image-preview-filename" disabled="disabled"
									path="imagePath" readonly="true" />
								<span class="input-group-btn">
									<button type="button"
										class="btn btn-default image-preview-clear"
										style="display: none;">
										<span class="glyphicon glyphicon-remove"></span> Clear
									</button> <!-- image-preview-input -->
									<div class="btn btn-default image-preview-input">
										<span class="glyphicon glyphicon-folder-open"></span> <span
											class="image-preview-input-title">Browse</span> <input
											type="file" accept="image/png, image/jpeg, image/gif"
											name="image" />
									</div>
								</span>
							</div>
							<div class="has-error">
								<form:errors path="imagePath" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-md-3">Description</label>
						<div class="col-md-8">
							<div class="input-group">
								<form:textarea path="description" class="form-control ckeditor"
									id="ckEditorTextArea" />
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
						<a href="<c:url value='/admin/listMerchants' />"
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
