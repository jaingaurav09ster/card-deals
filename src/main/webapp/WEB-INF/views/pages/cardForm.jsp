<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="category"%>

<link
	href="/deals/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css">
<link
	href="/deals/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css">
<script
	src="/deals/resources/vendor/bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>

<jsp:include page="manageCardNav.jsp" />
<c:set var="level" value="-1" scope="page" />
<div class="col-sm-4 col-md-8">
	<div class="panel panel-default">
		<div class="panel-heading">
			<c:choose>
				<c:when test="${edit}">
					<div class="panel-title">Update Card</div>
					<c:url var="actionUrl" value="/admin/updateCard" />
				</c:when>
				<c:otherwise>
					<div class="panel-title">Add Card</div>
					<c:url var="actionUrl" value="/admin/newCard" />
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
			<form:form enctype="multipart/form-data" method="POST"
				modelAttribute="card" id="addCard" action="${actionUrl}"
				cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<div>
					<div class="form-group">
						<label for="title" class="control-label col-xs-3">Title<span
							class="asteriskField">*</span></label>
						<div class="col-xs-6">
							<form:input type="text" path="title" id="title"
								class="form-control" placeholder="Card Title" />
							<div class="has-error">
								<form:errors path="title" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="launchDate" class="control-label col-xs-3">Launch
							Date<span class="asteriskField">*</span>
						</label>
						<div class="col-xs-6">
							<div class="input-group date" data-provide="datepicker"
								data-date-format="yyyy-mm-dd" data-date-autoclose="true"
								data-date-today-highlight="true">
								<form:input type="text" path="launchDate" id="launchDate"
									class="form-control" placeholder="Card Launch Date"
									readonly="true" />
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
							<div class="has-error">
								<form:errors path="launchDate" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="image" class="control-label col-xs-3">Image<span
							class="asteriskField">*</span></label>
						<div class="col-xs-6">
							<div class="input-group image-preview">
								<form:input type="text"
									class="form-control image-preview-filename" disabled="disabled"
									path="imagePath" readonly="true" placeholder="Card Image" />
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
						<label for="bank" class="control-label col-xs-3">Bank<span
							class="asteriskField">*</span></label>
						<div class="col-xs-5">
							<form:select class="form-control" path="bank.id" id="bank">
								<form:option value="">Please Select</form:option>
								<form:options items="${banks}" itemValue="Id" itemLabel="name" />
							</form:select>
							<div class="has-error">
								<form:errors path="bank" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="control-label col-xs-3">Card Type<span
							class="asteriskField">*</span>
						</label>
						<div class="col-xs-5">
							<form:select class="form-control" path="cardType.id" id="type">
								<form:option value="">Please Select</form:option>
								<form:options items="${cardTypes}" itemValue="Id"
									itemLabel="name" />
							</form:select>
							<div class="has-error">
								<form:errors path="cardType" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="cardCategory" class="control-label col-xs-3">Card
							Category<span class="asteriskField">*</span>
						</label>
						<div class="col-xs-5">
							<form:select class="form-control" path="cardCategory.id"
								id="cardCategory">
								<form:option value="">Please Select</form:option>
								<form:options items="${cardCategories}" itemValue="id"
									itemLabel="name" />
							</form:select>
							<div class="has-error">
								<form:errors path="cardCategory" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="category" class="control-label col-xs-3">Categories<span
							class="asteriskField">*</span></label>
						<div class="col-xs-5">
							<form:select multiple="true" class="form-control"
								path="categories" id="categories">
								<category:selectCategory list="${categories}" level="${level}" />
							</form:select>
							<div class="has-error">
								<form:errors path="categories" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="rank" class="control-label col-xs-3">Rank<span
							class="asteriskField">*</span></label>
						<div class="col-xs-3">
							<form:select class="form-control" path="rank" id="rank">
								<c:forEach var="i" begin="0" end="10" step="1">
									<form:option value="${i}">${i}</form:option>
								</c:forEach>
							</form:select>
							<div class="has-error">
								<form:errors path="rank" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-xs-3">Description</label>
						<div class="col-xs-8">
							<div class="input-group">
								<form:textarea path="description" class="form-control ckeditor"
									id="cardDesc" placeholder="Card Description" />
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
						<a href="<c:url value='/admin/listCards' />"
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
