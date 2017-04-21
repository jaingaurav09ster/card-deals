<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">

<head>
<c:choose>
	<c:when test="${not empty content.pageTitle}">
		<title>${content.pageTitle}</title>
	</c:when>
	<c:otherwise>
		<title><tiles:getAsString name="title" /></title>
	</c:otherwise>
</c:choose>
<tiles:insertAttribute name="head" />
</head>

<body>
	<tiles:insertAttribute name="navbar" />
	<div class="container-fluid">
		<div class="row content">
			<tiles:insertAttribute name="sidebar" />
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>

</html>