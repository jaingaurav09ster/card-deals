<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="category"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">
<c:set var="level" value="-1" scope="page" />
<div class="col-sm-3 col-md-3 sidebar">
	<div class="nav-side-menu">
		<div class="brand">Category Tree</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
		<div class="back"><a href="<c:url value="/admin/listCategories" />">Back</a></div>
			<category:category list="${rootCategories}"
				childCategoryName="menu-content" level="${level}" childCategoryId="" />
		</div>
	</div>
</div>

<script>
	$(function() {
		if ($('#module') != null) {
			$('.ul-' + $('#parentId').val()).addClass('in');
			$('.ul-' + $('#parentId').val()).parents().addClass('in');
			$('#li-' + $('#parentId').val()).addClass('active');
			$('#li-' + $('#parentId').val()).parents().addClass('in');
		}
	});
</script>