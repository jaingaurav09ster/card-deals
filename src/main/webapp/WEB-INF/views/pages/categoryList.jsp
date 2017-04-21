<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-9 col-md-9 main-content">
	<h3>Category List - (${fn:length(categories)})</h3>
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
					<a href="<c:url value="/admin/newCategory" />"
						class="btn btn-sm btn-primary btn-create">Create New</a>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-list"
				data-toggle="table" id="paginate">
				<thead>
					<tr>
						<th data-sortable="true">Category Name</th>
						<th><em class="fa fa-cog"></em></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="category">
						<tr>
							<td>${category.name}</td>
							<td align="center"><form
									action="<c:url value="/admin/deleteCategory/${category.id}" />">
									<a
										href="<c:url value="/admin/updateCategory/${category.id}" />"
										class="btn btn-default" title="Update Category"><em
										class="fa fa-pencil"></em></a> <a
										href="<c:url value="/admin/listChildCategories/${category.id}" />"
										class="btn btn-default"><em class="fa fa-child"
										title="Add Child"></em></a>
									<button class='btn btn-danger' type="submit"
										name="remove_levels" value="delete" title="Delete Category">
										<em class="fa fa-trash"></em>
									</button>

								</form></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(categories) lt 1}">
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
				<div class="col col-md-8" id="navigation"></div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="confirmModal.jsp" />