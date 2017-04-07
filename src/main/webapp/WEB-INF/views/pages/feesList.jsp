<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<jsp:include page="cardDetailsNav.jsp" />
	<div class="col-sm-8 col-md-8 list">
		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-md-6">
						<h3 class="panel-title">Fees</h3>
					</div>
					<div class="col col-md-6 text-right"></div>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-list"
					id="paginate">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Year Fee</th>
							<th>Onwards Fee</th>
							<th><em class="fa fa-cog"></em></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fees}" var="fees">
							<tr>
								<td>${fees.id}</td>
								<td>${fees.firstYear}</td>
								<td width="50%">${fees.onwards}</td>
								<td align="center"><a
									href="<c:url value="/admin/updateFees/${cardId}" />"
									class="btn btn-default"><em class="fa fa-pencil"></em></a></td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(fees) lt 1}">
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
					<div class="col col-xs-8" id="navigation"></div>
				</div>
			</div>
		</div>
	</div>
</div>
