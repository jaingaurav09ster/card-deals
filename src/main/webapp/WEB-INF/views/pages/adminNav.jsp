<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<div class="col-sm-4 col-md-4 sidebar admin-nav">

	<div class="nav-side-menu">
	<div class="brand">Administration</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content2"></i>
		<div class="menu-list">
			<ul id="menu-content2" class="menu-content collapse out">
				<li data-toggle="collapse" data-target="#userManager"
					class="collapsed" id="li-userManager"><a href="#"><i
						class="fa fa-user fa-lg"></i> User Manager <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="userManager">
					<li id="updateUserForm" style="display: none;"><a href="#">Update
							User</a></li>
					<li id="userForm"><a href="<c:url value="/admin/newUser" />">New
							User</a></li>
					<li id="userList"><a href="<c:url value="/admin/listUsers" />">User
							List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#cardManager"
					class="collapsed" id="li-cardManager"><a href="#"><i
						class="fa fa-credit-card fa-lg"></i> Card Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="cardManager">
					<li id="cardForm"><a
						href="<c:url value="${contextUri}/newCard" />">New Card</a></li>
					<li id="cardList"><a
						href="<c:url value="${contextUri}/listCards" />">Card List</a></li>
					<li id="updateCardForm" style="display: none;"><a href="#">Update
							Card</a></li>
					<li id="cardView" style="display: none;"><a href="#">View
							Card</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#bankManager"
					class="collapsed" id="li-bankManager"><a href="#"><i
						class="fa fa-bank fa-lg"></i> Bank Manager <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="bankManager">
					<li id="updateBankForm" style="display: none;"><a href="#">Update
							Bank</a></li>
					<li id="bankForm"><a href="<c:url value="/admin/newBank" />">New
							Bank</a></li>
					<li id="bankList"><a href="<c:url value="/admin/listBanks" />">Bank
							List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#categoryManager"
					class="collapsed" id="li-categoryManager"><a href="#"><i
						class="fa fa-shopping-bag fa-lg"></i> Category Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="categoryManager">
					<li id="updateCategoryForm" style="display: none;"><a href="#">Update
							Category</a></li>
					<li id="categoryForm"><a
						href="<c:url value="/admin/newCategory" />">New Category</a></li>
					<li id="categoryList"><a
						href="<c:url value="/admin/listCategories" />">Category List</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#cardCategoryManager"
					id="li-cardCategoryManager" class="collapsed"><a href="#"><i
						class="fa fa-cc-visa fa-lg"></i> Card Category Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="cardCategoryManager">
					<li id="updateCardCategoryForm" style="display: none;"><a
						href="#">Update Card Category</a></li>
					<li id="cardCategoryForm"><a
						href="<c:url value="/admin/newCardCategory" />">New Card
							Category</a></li>
					<li id="cardCategoryList"><a
						href="<c:url value="/admin/listCardCategories" />">Card
							Category List</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#cardTypeManager"
					class="collapsed" id="li-cardTypeManager"><a href="#"><i
						class="fa fa-credit-card fa-lg"></i> Card Type Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="cardTypeManager">
					<li id="updateCardTypeForm" style="display: none;"><a href="#">Update
							Card Type</a></li>
					<li id="cardTypeForm"><a
						href="<c:url value="/admin/newCardType" />">New Card Type</a></li>
					<li id="cardTypeList"><a
						href="<c:url value="/admin/listCardTypes" />">Card Type List</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#offerTypeManager"
					class="collapsed" id="li-offerTypeManager"><a href="#"><i
						class="fa fa-gift fa-lg"></i> Offer Type Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="offerTypeManager">
					<li id="updateOfferTypeForm" style="display: none;"><a
						href="#">Update Offer Type</a></li>
					<li id="offerTypeForm"><a
						href="<c:url value="/admin/newOfferType" />">New Offer Type</a></li>
					<li id="offerTypeList"><a
						href="<c:url value="/admin/listOfferTypes" />">Offer Type List</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#offerUnitManager"
					class="collapsed" id="li-offerUnitManager"><a href="#"><i
						class="fa fa-calculator fa-lg"></i> Offer Unit Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="offerUnitManager">
					<li id="updateofferUnitForm" style="display: none;"><a
						href="#">Update Card Type</a></li>
					<li id="offerUnitForm"><a
						href="<c:url value="/admin/newOfferUnit" />">New Offer Unit
							Type</a></li>
					<li id="offerUnitList"><a
						href="<c:url value="/admin/listOfferUnits" />">Offer Unit Type
							List</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#contentManager"
					class="collapsed" id="li-contentManager"><a href="#"><i
						class="fa  fa-newspaper-o fa-lg"></i> Content Manager <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="contentManager">
					<li id="updateContentForm" style="display: none;"><a href="#">Update
							Content</a></li>
					<li id="contentForm"><a
						href="<c:url value="/admin/newContent" />">New Content</a></li>
					<li id="contentList"><a
						href="<c:url value="/admin/listContents" />">Content List</a></li>
				</ul>
			</ul>
		</div>
	</div>
</div>