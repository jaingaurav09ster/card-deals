<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="/deals/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css">
<link
	href="/deals/resources/vendor/bootstrap/datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css">
<script
	src="/deals/resources/vendor/bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/deals/resources/vendor/ckeditor/ckeditor.js"></script>

<div class="row">
	<jsp:include page="cardDetailsNav.jsp" />
	<div class="col-sm-4 col-md-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update Deal</div>
						<c:url var="actionUrl" value="/admin/updateDeal/${cardId}" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">Add Deal</div>
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
							<label for="title" class="control-label col-xs-3">Title<span
								class="asteriskField">*</span></label>
							<div class="col-xs-6">
								<form:input type="text" path="title" id="title"
									class="form-control" placeholder="Title" />
								<div class="has-error">
									<form:errors path="title" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="startDate" class="control-label col-xs-3">Start
								Date<span class="asteriskField">*</span>
							</label>
							<div class="col-xs-6">
								<div class="input-group date" data-provide="datepicker"
									data-date-format="yyyy-mm-dd" data-date-autoclose="true"
									data-date-today-highlight="true">
									<form:input type="text" path="startDate" id="startDate"
										class="form-control" placeholder="Card Start Date"
										readonly="true" />
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
							<label for="endDate" class="control-label col-xs-3">End
								Date<span class="asteriskField">*</span>
							</label>
							<div class="col-xs-6">
								<div class="input-group date" data-provide="datepicker"
									data-date-format="yyyy-mm-dd" data-date-autoclose="true"
									data-date-today-highlight="true">
									<form:input type="text" path="endDate" id="endDate"
										class="form-control" placeholder="Card End Date"
										readonly="true" />
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
							<label for="category" class="control-label col-xs-3">Categories<span
								class="asteriskField">*</span></label>
							<div class="col-xs-5">
								<form:select multiple="true" class="form-control"
									path="categories" id="categories">
									<form:options items="${categories}" itemValue="id"
										itemLabel="name" />
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
										id="deal" placeholder="deal Description" />
								</div>
								<div class="has-error">
									<form:errors path="description" class="help-inline" />
								</div>
							</div>
							<div style="clear: both;"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-7">
							<a href="<c:url value='/admin/listDeals/${cardId}' />"
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
</div>