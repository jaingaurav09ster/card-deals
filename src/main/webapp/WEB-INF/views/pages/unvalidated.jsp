<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row col-md-6 col-md-offset-3 custyle">
	<div class="panel panel-default">
		<div class="panel-heading">
			<c:choose>
				<c:when test="${tokenState ne null && tokenState eq 'expired'}">
					<div class="panel-title">Expired Token</div>
				</c:when>
				<c:otherwise>
					<div class="panel-title">Invalid Token</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="padding-top: 50px; padding-left: 40px" class="panel-body">${message}</div>
		<div style="padding-bottom: 30px; padding-top: 30px;">
			<c:choose>
				<c:when test="${tokenState ne null && tokenState eq 'expired'}">
					<a href="<c:url value='/reGenerateToken?email=${email}' />"
						class="btn btn-primary btn-sm">Re-generate</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value='/' />" class="btn btn-primary btn-sm">Continue</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
