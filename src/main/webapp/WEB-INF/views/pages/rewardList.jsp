<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-9 col-md-9 main-content">
<h3>List of Rewards - (${fn:length(rewards)})</h3>
	<hr>
	<div class="panel panel-default panel-table">
		<div class="panel-heading">
			<div class="row">
				<div class="col col-md-6">
				</div>
				<div class="col col-md-6 text-right">
					<a href="<c:url value="/newReward/${cardId}" />"
						class="btn btn-sm btn-primary btn-create">Create New</a>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-list" data-toggle="table" 
				id="paginate">
				<thead>
					<tr>
						<th data-sortable="true">Reward Name</th>
						<th><em class="fa fa-cog"></em></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rewards}" var="reward">
						<tr>
							<td>${reward.title}</td>
							<td align="center"><form
									action="<c:url value="/deleteReward/${reward.id}/${cardId}" />">
									<a
										href="<c:url value="/updateReward/${reward.id}/${cardId}" />"
										class="btn btn-default"><em class="fa fa-pencil"></em></a>
									<button class='btn btn-danger' type="submit"
										name="remove_levels" value="delete">
										<em class="fa fa-trash"></em>
									</button>
								</form></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(rewards) lt 1}">
						<tr>
							<td colspan="2">No Results found</td>
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
<jsp:include page="confirmModal.jsp" />