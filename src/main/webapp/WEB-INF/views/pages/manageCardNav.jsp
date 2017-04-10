<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<div class="col-sm-4 col-sm-4 col-small-4 sidebar">
	<div class="nav-side-menu">
		<div class="brand">Card Manager</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
			<ul id="menu-content" class="menu-content collapse out">
				<li data-toggle="collapse" data-target="#cardManager"
					class="collapsed" id="li-cardManager"><a href="#"><i
						class="fa fa-credit-card fa-lg"></i> Card Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="cardManager">
					<li id="cardForm"><a href="<c:url value="/admin/newCard" />">New
							Card</a></li>
					<li id="cardList"><a href="<c:url value="/admin/listCards" />">Card
							List</a></li>
					<li id="updateCardForm" style="display: none;"><a href="#">Update
							Card</a></li>
					<li id="cardView" style="display: none;"><a href="#">View
							Card</a></li>
				</ul>
			</ul>
		</div>
	</div>
</div>