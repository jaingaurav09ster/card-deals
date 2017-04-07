<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 custyle form">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">Forgot Password</div>
		</div>
		<div class="panel-body">${message}</div>
		<div class="panel-footer" align='center'>
			<a href="<c:url value='/' />" class="btn btn-primary btn-sm">Continue</a>
		</div>
	</div>
</div>
