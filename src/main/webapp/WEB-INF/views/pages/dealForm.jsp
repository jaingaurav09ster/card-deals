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

<c:set var="level" value="-1" scope="page" />
<div class="row">
	<jsp:include page="cardDetailsNav.jsp" />
	<div class="col-sm-7 col-md-7 form">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update Deal <span class="required">(*required fields)</span></div>
						<c:url var="actionUrl" value="/admin/updateDeal/${cardId}" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">
							Add Deal <span class="required">(*required fields)</span>
						</div>
						<c:url var="actionUrl" value="/admin/newDeal/${cardId}" />
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
				<form:form method="POST" modelAttribute="deal" id="addDeal"
					action="${actionUrl}" cssClass="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<form:input type="hidden" path="cardId" value="${cardId}" />
					<div>
						<div class="form-group">
							<label for="title" class="control-label col-md-4">Title<span
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
							<label for="startDate" class="control-label col-md-4">Start
								Date </label>
							<div class="col-md-6">
								<div class="input-group date" data-provide="datepicker"
									data-date-format="yyyy-mm-dd" data-date-autoclose="true"
									data-date-today-highlight="true">
									<form:input type="text" path="startDate" id="startDate"
										class="form-control" readonly="true" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>
								<div class="has-error">
									<form:errors path="startDate" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="endDate" class="control-label col-md-4">End
								Date </label>
							<div class="col-md-6">
								<div class="input-group date" data-provide="datepicker"
									data-date-format="yyyy-mm-dd" data-date-autoclose="true"
									data-date-today-highlight="true">
									<form:input type="text" path="endDate" id="endDate"
										class="form-control" readonly="true" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>
								<div class="has-error">
									<form:errors path="endDate" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="control-label col-md-4">Categories</label>
							<div class="col-md-6">
								<form:select multiple="true" class="form-control"
									path="categories" id="categories">
									<category:selectCategory list="${categories}" level="${level}"
										selectedCategories="${deal.categories}" />
								</form:select>
								<div class="has-error">
									<form:errors path="categories" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="offerType" class="control-label col-md-4">Offer
								Type</label>
							<div class="col-md-6">
								<form:select class="form-control" path="offerType.id"
									id="offerType">
									<form:option value="">Please Select</form:option>
									<form:options items="${offerTypes}" itemValue="Id"
										itemLabel="title" />
								</form:select>
								<div class="has-error">
									<form:errors path="offerType" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="dealValue" class="control-label col-md-4">Value</label>
							<div class="col-md-3">
								<form:input type="text" path="dealValue" id="dealValue"
									class="form-control" />
								<div class="has-error">
									<form:errors path="dealValue" class="help-inline" />
								</div>
							</div>
							<div class="col-md-3">
								<form:select class="form-control" path="valueUnit"
									id="valueUnit">
									<form:option value="">Unit</form:option>
									<form:options items="${offerUnits}" itemValue="Id"
										itemLabel="title" />
								</form:select>
								<div class="has-error">
									<form:errors path="valueUnit" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="maxValue" class="control-label col-md-4">Max
								Value</label>
							<div class="col-md-3">
								<form:input type="text" path="maxValue" id="dealValue"
									class="form-control" />
								<div class="has-error">
									<form:errors path="maxValue" class="help-inline" />
								</div>
							</div>
							<div class="col-md-3">
								<form:select class="form-control" path="maxValueUnit"
									id="maxValueUnit">
									<form:option value="">Unit</form:option>
									<form:options items="${offerUnits}" itemValue="Id"
										itemLabel="title" />
								</form:select>
								<div class="has-error">
									<form:errors path="maxValueUnit" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="rank" class="control-label col-md-4">Rank</label>
							<div class="col-md-6">
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
							<label for="description" class="control-label col-md-4">Description</label>
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
						<label class="col-md-4 control-label"></label>
						<div class="col-md-6">
							<a href="<c:url value='/admin/listDeals/${cardId}' />"
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