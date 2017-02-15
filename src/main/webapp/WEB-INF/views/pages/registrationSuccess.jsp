<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="container">
		<div class="row col-md-6 col-md-offset-3 custyle">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Account Created</div>
				</div>
				<div style="padding-top: 50px; padding-left: 40px"
					class="panel-body">${message}</div>
				<div style="padding-bottom: 30px; padding-top: 30px;">
					<a href="<c:url value='/' />" class="btn btn-primary btn-sm">Continue</a>
				</div>
			</div>
		</div>
	</div>
</header>