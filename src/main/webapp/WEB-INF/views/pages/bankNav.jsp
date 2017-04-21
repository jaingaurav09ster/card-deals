<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<c:set var="contextUri" value="" scope="page" />
<sec:authorize access="hasRole('ADMIN')">
	<c:set var="contextUri" value="/admin" scope="page" />
</sec:authorize>

<div class="col-sm-3 col-md-3 sidebar">
	<div class="nav-side-menu">
		<div class="brand">Card Manager</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
			<ul id="menu-content" class="menu-content collapse out">
				<div class="profile-box hidden-xs">
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="/deals/resources/images/profile-placeholder.jpg"
								alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<p>Welcome</p>
							<p>
								<strong><sec:authentication
										property="principal.firstName" /></strong>
							</p>
						</div>
					</div>
				</div>
				<li data-toggle="collapse" data-target="#cardManager"
					class="collapsed" id="li-cardManager"><a href="#"><i
						class="fa fa-credit-card fa-lg"></i> Card Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="cardManager">
					<li id="cardBankForm"><a
						href="<c:url value="${contextUri}/newCard" />">New Card</a></li>
					<li id="cardBankList"><a
						href="<c:url value="${contextUri}/listCards" />">Card List</a></li>
					<li id="updateCardForm" style="display: none;"><a href="#">Update
							Card</a></li>
					<li id="cardBankView" style="display: none;"><a href="#">View
							Card</a></li>
				</ul>
			</ul>
		</div>
	</div>
</div>