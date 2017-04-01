<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<jsp:include page="cardDetailsNav.jsp" />
	<div class="col-sm-4 col-md-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${edit}">
						<div class="panel-title">Update Fees</div>
						<c:url var="actionUrl" value="/admin/updateFees/${cardId}" />
					</c:when>
					<c:otherwise>
						<div class="panel-title">Add Fees</div>
						<c:url var="actionUrl" value="/admin/newFees/${cardId}" />
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
				<form:form method="POST" modelAttribute="fees" id="addFees"
					action="${actionUrl}" cssClass="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<form:input type="hidden" path="cardId" value="${cardId}" />
					<div>
						<div class="form-group">
							<label for="firstYear" class="control-label col-xs-3">First Year Fee<span
								class="asteriskField">*</span></label>
							<div class="col-xs-6">
								<form:input type="text" path="firstYear" id="firstYear"
									class="form-control" placeholder="First Year Fee" />
								<div class="has-error">
									<form:errors path="firstYear" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="onwards" class="control-label col-xs-3">Onwards Fee<span
								class="asteriskField">*</span></label>
							<div class="col-xs-6">
								<form:input type="text" path="onwards" id="onwards"
									class="form-control" placeholder="Onwards Fee" />
								<div class="has-error">
									<form:errors path="onwards" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="apr" class="control-label col-xs-3">Apr</label>
							<div class="col-xs-6">
								<form:input type="text" path="apr" id="apr"
									class="form-control" placeholder="Apr" />
								<div class="has-error">
									<form:errors path="apr" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-7">
							<a href="<c:url value='/admin/listFees/${cardId}' />"
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