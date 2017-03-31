<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<jsp:include page="masterDataNav.jsp" />
	<div class="col-md-7 col-md-offset-0">
		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h3 class="panel-title">List of Banks</h3>
					</div>
					<div class="col col-xs-6 text-right">
						<a href="<c:url value="/admin/newBank" />"
							class="btn btn-sm btn-primary btn-create">Create New</a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-list"
					id="paginate">
					<thead>
						<tr>
							<th class="hidden-xs">ID</th>
							<th>Bank Name</th>
							<th>Description</th>
							<th><em class="fa fa-cog"></em></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${banks}" var="bank">
							<tr>
								<td class="hidden-xs">${bank.id}</td>
								<td>${bank.name}</td>
								<td width="50%">${bank.description}</td>
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
								<td colspan="4" style="text-align: center">No Results found</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="col col-xs-4"></div>
					<div class="col col-xs-8" id="navigation"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="confirmModal.jsp" />