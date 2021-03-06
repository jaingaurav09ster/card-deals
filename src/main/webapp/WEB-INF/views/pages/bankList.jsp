<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="col-sm-9 col-md-9 main-content">
	<h3>List of Banks - (${fn:length(banks)})</h3>
	<hr>
	<c:if test="${param.err != null}">
		<div class="alert alert-danger">This item cannot be deleted as
			it is getting referenced/attached with some parent entity.</div>
	</c:if>
	<div class="panel panel-default panel-table">
		<div class="panel-heading">
			<div class="row">
				<div class="col col-md-6"></div>
				<div class="col col-md-6 text-right">
					<a href="<c:url value="/admin/newBank" />"
						class="btn btn-sm btn-primary btn-create">Create New</a>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-list"
				data-toggle="table" id="paginate">
				<thead>
					<tr>
						<th>Logo</th>
						<th data-sortable="true">Bank Name</th>
						<th class="hidden-xs" data-sortable="true">Sector</th>
						<th><em class="fa fa-cog"></em></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${banks}" var="bank">
						<tr>
							<c:choose>
								<c:when test="${not empty bank.imagePath}">
									<td class="hidden-xs" align="center"><img alt="image"
										src="${contextPath}/resources/upload/bank/${bank.imagePath}"></td>
								</c:when>
								<c:otherwise>
									<td class="hidden-xs" align="center"><img alt="image"
										src="${contextPath}/resources/upload/default-placeholder.png"></td>
								</c:otherwise>
							</c:choose>
							<td>${bank.name}</td>
							<td class="hidden-xs">${bank.sector}</td>
							<td align="center"><form
									action="<c:url value="/admin/deleteBank/${bank.id}" />">
									<a href="<c:url value="/admin/updateBank/${bank.id}" />"
										class="btn btn-default"><em class="fa fa-pencil"></em></a>
									<button class='btn btn-danger' type="submit"
										name="remove_levels" value="delete">
										<em class="fa fa-trash"></em>
									</button>
								</form></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(banks) lt 1}">
						<tr>
							<td colspan="4">No Results found</td>
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
<jsp:include page="confirmModal.jsp" />