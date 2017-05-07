
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script src="${contextPath}/resources/vendor/ckeditor/ckeditor.js"></script>

<c:url value="/user/addCard" var="actionUrl"/>
<div class="col-sm-9 col-md-9 main-content">
	<h3>Manage Cards</h3>
	<p>Add your cards and get access to personalized deals specially
		tailored for you.</p>
	<hr>
	<div class="panel panel-default">
		<div class="panel-body">
			
			<c:if test="${not empty error}">
				<div class="alert alert-warning col-sm-12">Please select the value from the suggested values</div>
			</c:if>
			<form:form method="post" id="addCardForm" action="${actionUrl}" class="form-horizontal">
				<div>
					<div class="form-group">
						<label for="name" class="control-label col-md-3">Card Name<span
							class="asteriskField">*</span></label>
						<div class="col-md-5">
							<input type="text" name="cardName" id="cardSearch"
								class="form-control autocomplete tt-query" />
						</div>
						<div class="col-md-1">
							<input type="submit" value="Add" class="btn btn-primary btn-md" />
						</div>
					</div>
				</div>
			</form:form>
			<div class="panel panel-default panel-table">
				<div class="panel-body">
					<table class="table table-striped table-bordered table-list"
						data-toggle="table" id="paginate">
						<thead>
							<tr>
								<th>Image</th>
								<th data-sortable="true">Card Name</th>
								<th><em class="fa fa-cog"></em></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${user.cards}" var="card">
								<tr>
									<c:choose>
										<c:when test="${not empty card.imagePath}">
											<td class="hidden-xs" align="center"><img alt="image"
												src="${contextPath}/resources/upload/card/${card.imagePath}"></td>
										</c:when>
									</c:choose>
									<td>${card.title}</td>
									<td align="center"><form
											action="<c:url value="/user/deleteCard/${card.id}" />">
											<button class='btn btn-danger' type="submit"
												name="remove_levels" value="delete">
												<em class="fa fa-trash"></em>
											</button>
										</form></td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(user.cards) lt 1}">
								<tr>
									<td colspan="3">No Results found</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col col-md-4"></div>
						<div class="col col-md-8" id="navigation"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="confirmModal.jsp" />