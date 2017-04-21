<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-9 col-md-9 main-content">
	<c:choose>
		<c:when test="${edit}">
			<h3>
				Update Fees<span class="required">&nbsp;(*required fields)</span>
			</h3>
			<c:url var="actionUrl" value="/updateFees/${cardId}" />
		</c:when>
		<c:otherwise>
			<h3>
				Add Fees<span class="required">&nbsp;(*required fields)</span>
			</h3>
			<c:url var="actionUrl" value="/newFees/${cardId}" />
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
			<form:form method="POST" modelAttribute="fees" id="addFees"
				action="${actionUrl}" cssClass="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<form:input type="hidden" path="cardId" value="${cardId}" />
				<div>
					<div class="form-group">
						<label for="firstYear" class="control-label col-md-3">First
							Year Fee<span class="asteriskField">*</span>
						</label>
						<div class="col-md-6">
							<form:input type="text" path="firstYear" id="firstYear"
								class="form-control" />
							<div class="has-error">
								<form:errors path="firstYear" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="onwards" class="control-label col-md-3">Onwards
							Fee<span class="asteriskField">*</span>
						</label>
						<div class="col-md-6">
							<form:input type="text" path="onwards" id="onwards"
								class="form-control" />
							<div class="has-error">
								<form:errors path="onwards" class="help-inline" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="apr" class="control-label col-md-3">APR</label>
						<div class="col-md-6">
							<form:input type="text" path="apr" id="apr" class="form-control" />
							<div class="has-error">
								<form:errors path="apr" class="help-inline" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-6">
						<a href="<c:url value='/listFees/${cardId}' />"
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
