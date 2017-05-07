<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="col-sm-3 col-md-3 sidebar user-nav">
	<div class="nav-side-menu">
		<div class="brand">My Profile</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content2"></i>
		<div class="menu-list">
			<ul id="menu-content2" class="menu-content collapse out">
				<li data-toggle="collapse" data-target="#userManager"
					class="collapsed" id="li-userManager"><a
					href="<c:url value="/user/editProfile" />"><i
						class="fa fa-user fa-lg"></i> Personal Information</a></li>
				<li data-toggle="collapse" data-target="#userManager"
					class="collapsed" id="li-userManager"><a
					href="<c:url value="/user/addCard" />"><i
						class="fa fa-credit-card fa-lg"></i> My Cards</a></li>
				<li data-toggle="collapse" data-target="#userManager"
					class="collapsed" id="li-userManager"><a
					href="<c:url value="/user/myDeals" />"><i
						class="fa fa-gift fa-lg"></i> My Deals</a></li>
			</ul>
		</div>
	</div>
</div>