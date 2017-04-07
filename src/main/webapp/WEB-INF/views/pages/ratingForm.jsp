<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>

<div class="row">
	<jsp:include page="cardDetailsNav.jsp" />
	<div class="col-sm-7 col-md-7 form">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update Rating <span class="required">(*required fields)</span></div>
						<c:url var="actionUrl" value="/admin/updateRating/${cardId}" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">Add Rating <span class="required">(*required fields)</span></div>
						<c:url var="actionUrl" value="/admin/newRating/${cardId}" />
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
				<form:form method="POST" modelAttribute="rating" id="addRating"
					action="${actionUrl}" cssClass="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<form:input type="hidden" path="cardId" value="${cardId}" />
					<div>
						<div class="form-group">
							<label for="rank" class="control-label col-md-4">Rating<span
								class="asteriskField">*</span></label>
							<div class="col-md-6">
								<form:select class="form-control" path="rating" id="rating">
									<c:forEach var="i" begin="0" end="5" step="1">
										<form:option value="${i}">${i}</form:option>
									</c:forEach>
								</form:select>
								<div class="has-error">
									<form:errors path="rating" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-md-4">Comments</label>
							<div class="col-md-8">
								<div class="input-group">
									<form:textarea path="comment" class="form-control ckeditor"
										id="ckEditorTextArea" placeholder="comment" />
								</div>
								<div class="has-error">
									<form:errors path="comment" class="help-inline" />
								</div>
							</div>
							
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<a href="<c:url value='/admin/listRatings/${cardId}' />"
								class="btn btn-primary btn-sm btn-cancel">Cancel</a>
							<c:choose>
								<c:when test="${edit}">
									<input type="submit" value="Update"
										class="btn btn-primary btn-sm" />
								</c:when>
								<c:otherwise>
									<input type="submit" value="Add" class="btn btn-primary btn-sm"
										 />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>