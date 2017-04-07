<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>
<div class="row">
	<jsp:include page="masterDataNav.jsp" />
	<div class="col-sm-7 col-md-7 form">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update Card Type <span class="required">(*required fields)</span></div>
						<c:url var="actionUrl" value="/admin/updateCardType" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">Add Card Type <span class="required">(*required fields)</span></div>
						<c:url var="actionUrl" value="/admin/newCardType" />
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
				<form:form method="POST" modelAttribute="cardType" id="addCardType"
					action="${actionUrl}" cssClass="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<div>
						<div class="form-group">
							<label for="name" class="control-label col-md-4">Name<span
								class="asteriskField">*</span></label>
							<div class="col-md-6">
								<form:input type="text" path="name" id="name"
									class="form-control" placeholder="Card Type" />
								<div class="has-error">
									<form:errors path="name" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-md-4">Description</label>
							<div class="col-md-8">
								<div class="input-group">
									<form:textarea path="description" class="form-control ckeditor"
										id="ckEditorTextArea" placeholder="Card Type Description" />
								</div>
								<div class="has-error">
									<form:errors path="description" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-6">
							<a href="<c:url value='/admin/listCardTypes' />"
								class="btn btn-primary btn-sm btn-cancel">Cancel</a>
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" />
								</c:when>
								<c:otherwise>
									<input type="submit" value="Add" class="btn btn-primary btn-sm" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>