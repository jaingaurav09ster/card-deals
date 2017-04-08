<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row cardList">
	<jsp:include page="manageCardNav.jsp" />
	<div class="col-sm-8 col-md-8 list">
		<div class="panel panel-default panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-md-6">
						<h3 class="panel-title">List of Cards</h3>
					</div>
					<div class="col col-md-6 text-right">
						<a href="<c:url value="/admin/newCard" />"
							class="btn btn-sm btn-primary btn-create">Create New</a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<table
					class="table table-striped table-bordered table-list card-table"
					id="paginate">
					<thead>
						<tr>
							<th class="hidden-xs">ID</th>
							<th class="hidden-xs">Image</th>
							<th>Title</th>
							<th>Bank</th>
							<th>Type</th>
							<th class="hidden-xs">Category</th>
							<th><em class="fa fa-cog"></em></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cards}" var="card">
							<tr>
								<td class="hidden-xs">${card.id}</td>
								<td class="hidden-xs" align="center"><img alt="image"
									src="/deals/resources/upload/${card.imagePath}"
									style="width: 50px; height: auto;"></td>
								<td>${card.title}</td>
								<td>${card.bank.name}</td>
								<td>${card.cardType.name}</td>
								<td class="hidden-xs">${card.cardCategory.name}</td>
								<td><form
										action="<c:url value="/admin/deleteCard/${card.id}" />">
										<a href="<c:url value="/admin/updateCard/${card.id}" />"
											class="btn btn-default"><em class="fa fa-pencil"
											title="Update Card"></em></a> <a
											href="<c:url value="/admin/listDeals/${card.id}" />"
											class="btn btn-default"><em class="fa fa-gear"
											title="Manage Card"></em></a> <a
											href="<c:url value="/admin/updateCard/${card.id}" />"
											class="btn btn-default"><em class="fa fa-view"
											title="View Card"></em></a>
										<button class='btn btn-danger' type="submit"
											name="remove_levels" value="delete" title="Delete Card">
											<em class="fa fa-trash"></em>
										</button>
									</form></td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(cards) lt 1}">
							<tr>
								<td colspan="7">No Results found</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="col col-xs-8" id="navigation"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="confirmModal.jsp" />