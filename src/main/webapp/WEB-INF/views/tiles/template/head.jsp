<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	var contextPath="${contextPath}";
</script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"
	type="text/css">
<!-- Optional theme -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->

<!-- Theme CSS -->
<link href="${contextPath}/resources/stylesheets/typeahead.css" rel="stylesheet">

<link rel="stylesheet"
	href="${contextPath}/resources/vendor/nProgress/css/nprogress.css"
	type="text/css">
<link rel="stylesheet"
	href="${contextPath}/resources/vendor/bootstrap/css/bootstrap-table.css"
	type="text/css">
<link href="${contextPath}/resources/stylesheets/style.css" rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/stylesheets/font1.css"
	rel="stylesheet" type="text/css">
<link
	href="${contextPath}/resources/stylesheets/font2.css"
	rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="${contextPath}/resources/images/favicon.ico">

<!-- jQuery -->
<script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${contextPath}/resources/vendor/jquery/jquery.easing.min.js"></script>
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrapValidator.js"></script>
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap-table.js"></script>
<script src="${contextPath}/resources/javascripts/typeahead.bundle.js"></script>
<script src="${contextPath}/resources/javascripts/handlebars-v4.0.5.js"></script>
<script src="${contextPath}/resources/vendor/jquery/jquery.paginate.js"></script> 	
<script src="${contextPath}/resources/vendor/nProgress/js/nprogress.js"></script>
<script src="${contextPath}/resources/javascripts/angular.min.js"></script>
<script src="${contextPath}/resources/javascripts/script.js"></script>