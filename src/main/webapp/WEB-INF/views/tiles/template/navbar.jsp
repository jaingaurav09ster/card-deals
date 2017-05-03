<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Menu Navigation -->

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="homePage" value="/home" />
<c:set var="adminCss" value="adminCss" />
<sec:authorize access="hasRole('BANK')">
	<c:set var="homePage" value="/bank" />
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
	<c:set var="homePage" value="/console" />
</sec:authorize>
<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
	<c:set var="adminCss" value="" />
	<div class="topbar">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<p class="pull-left hidden-xs">
						<i class="fa fa-gift"></i><span>Get Personalized deals
							tailored for you</span>
					</p>
					<p class="pull-right">
						<i class="fa fa-envelope-o"></i>trumpcard@support.com
					</p>
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<nav id="mainNav"
	class="navbar navbar-default navbar-custom ${adminCss}">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="row">
			<div class="navbar-header col-sm-2 col-md-2">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand" href="<c:url value="${homePage}" />"><img
					class="img-responsive"
					src="${contextPath}/resources/images/logo.png"></a>
			</div>
			<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
				<div class="col-sm-10 col-md-5 col-lg-6 hidden-xs nav-searchbar">
					<form role="form" action="<c:url value="/searchDeals" />"
						id="searchForm">
						<div class="input-group searchbar">
							<input type="text" class="form-control typeahead tt-query"
								autocomplete="off" name="searchQuery" id="searchQuery"
								placeholder="Get the best deals on your card, enter the card name.....">
							<input type="hidden" name="query" id="query" value="" />
							<div class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</sec:authorize>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
						<li class="dropdown visible-xs"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Deals<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/login" />">Cash Back Offer</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/register" />">Flat
										Discounts</a></li>
							</ul></li>
						<li class="dropdown mega-dropdown hidden-xs"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Cards<span
								class="caret"></span></a>
							<ul class="dropdown-menu mega-dropdown-menu">
								<li class="col-sm-3 spacing-left">
									<ul>
										<li class="dropdown-header">Featured</li>
										<div id="menCollection" class="carousel slide"
											data-ride="carousel">
											<div class="carousel-inner">
												<div class="item active">
													<a href="#"><img
														src="http://placehold.it/254x150/ff3546/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 1"></a>
													<h4>
														<small>Summer dress floral prints</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
												<div class="item">
													<a href="#"><img
														src="http://placehold.it/254x150/3498db/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 2"></a>
													<h4>
														<small>Gold sandals with shiny touch</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
												<div class="item">
													<a href="#"><img
														src="http://placehold.it/254x150/2ecc71/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 3"></a>
													<h4>
														<small>Denin jacket stamped</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
											</div>
											<!-- End Carousel Inner -->
											<!-- Controls -->
											<a class="left carousel-control" href="#menCollection"
												role="button" data-slide="prev"> <span
												class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
												<span class="sr-only">Previous</span>
											</a> <a class="right carousel-control" href="#menCollection"
												role="button" data-slide="next"> <span
												class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
												<span class="sr-only">Next</span>
											</a>
										</div>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col col-sm-offset-1">
									<ul>
										<li class="dropdown-header">Banks</li>
										<li><a href="#">Auto Carousel</a></li>
										<li><a href="#">Carousel Control</a></li>
										<li><a href="#">Left & Right Navigation</a></li>
										<li><a href="#">Four Columns Grid</a></li>
										<li class="divider"></li>
										<li class="dropdown-header">Fonts</li>
										<li><a href="#">Glyphicon</a></li>
										<li><a href="#">Google Fonts</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Categories</li>
										<li><a href="#">Navbar Inverse</a></li>
										<li><a href="#">Pull Right Elements</a></li>
										<li><a href="#">Coloured Headers</a></li>
										<li><a href="#">Primary Buttons & Default</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Much more</li>
										<li><a href="#">Easy to Customize</a></li>
										<li><a href="#">Calls to action</a></li>
										<li><a href="#">Custom Fonts</a></li>
										<li><a href="#">Slide down on Hover</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Much more</li>
										<li><a href="#">Easy to Customize</a></li>
										<li><a href="#">Calls to action</a></li>
										<li><a href="#">Custom Fonts</a></li>
										<li><a href="#">Slide down on Hover</a></li>
									</ul>
								</li>
							</ul></li>
						<li class="dropdown visible-xs"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Deals<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/login" />">Cash Back Offer</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/register" />">Flat
										Discounts</a></li>
							</ul></li>
						<li class="dropdown mega-dropdown hidden-xs"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Deals<span
								class="caret"></span></a>
							<ul class="dropdown-menu mega-dropdown-menu">
								<li class="col-sm-3 spacing-left">
									<ul>
										<li class="dropdown-header">Men Collection</li>
										<div id="menCollection" class="carousel slide"
											data-ride="carousel">
											<div class="carousel-inner">
												<div class="item active">
													<a href="#"><img
														src="http://placehold.it/254x150/ff3546/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 1"></a>
													<h4>
														<small>Summer dress floral prints</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
												<div class="item">
													<a href="#"><img
														src="http://placehold.it/254x150/3498db/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 2"></a>
													<h4>
														<small>Gold sandals with shiny touch</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
												<div class="item">
													<a href="#"><img
														src="http://placehold.it/254x150/2ecc71/f5f5f5/&text=New+Collection"
														class="img-responsive" alt="product 3"></a>
													<h4>
														<small>Denin jacket stamped</small>
													</h4>
													<button href="#" class="btn btn-default" type="button">
														<span class="glyphicon glyphicon-heart"></span> Add to
														Wishlist
													</button>
												</div>
												<!-- End Item -->
											</div>
											<!-- End Carousel Inner -->
											<!-- Controls -->
											<a class="left carousel-control" href="#menCollection"
												role="button" data-slide="prev"> <span
												class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
												<span class="sr-only">Previous</span>
											</a> <a class="right carousel-control" href="#menCollection"
												role="button" data-slide="next"> <span
												class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
												<span class="sr-only">Next</span>
											</a>
										</div>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col col-sm-offset-1">
									<ul>
										<li class="dropdown-header">Features</li>
										<li><a href="#">Auto Carousel</a></li>
										<li><a href="#">Carousel Control</a></li>
										<li><a href="#">Left & Right Navigation</a></li>
										<li><a href="#">Four Columns Grid</a></li>
										<li class="divider"></li>
										<li class="dropdown-header">Fonts</li>
										<li><a href="#">Glyphicon</a></li>
										<li><a href="#">Google Fonts</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Plus</li>
										<li><a href="#">Navbar Inverse</a></li>
										<li><a href="#">Pull Right Elements</a></li>
										<li><a href="#">Coloured Headers</a></li>
										<li><a href="#">Primary Buttons & Default</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Much more</li>
										<li><a href="#">Easy to Customize</a></li>
										<li><a href="#">Calls to action</a></li>
										<li><a href="#">Custom Fonts</a></li>
										<li><a href="#">Slide down on Hover</a></li>
									</ul>
								</li>
								<li class="col-sm-2 spacing-col">
									<ul>
										<li class="dropdown-header">Much more</li>
										<li><a href="#">Easy to Customize</a></li>
										<li><a href="#">Calls to action</a></li>
										<li><a href="#">Custom Fonts</a></li>
										<li><a href="#">Slide down on Hover</a></li>
									</ul>
								</li>
							</ul></li>
						<li><a href="<c:url value="/page/contact" />">About</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasRole('ADMIN')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Administration<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/admin/listUsers" />">Manage
											Users</a></li>
									<li class="divider"></li>
									<li><a href="<c:url value="/admin/listCards" />">Manage
											Cards</a></li>
									<li class="divider"></li>
									<li><a href="<c:url value="/admin/listBanks" />">Manage
											Master Data</a></li>
								</ul></li>
						</sec:authorize>
						<sec:authorize access="hasRole('BANK')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Administration<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/listCards" />">Manage
											Cards</a></li>
								</ul></li>
						</sec:authorize>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> &nbsp;&nbsp;&nbsp; <i
								class="glyphicon glyphicon-user"></i><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">${pageContext.request.userPrincipal.name}</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/user/editProfile" />">My
										Profile</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/logout" />">Logout</a></li>
							</ul></li>
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Sign In&nbsp;&nbsp;&nbsp; <i
								class="glyphicon glyphicon-user"></i><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/login" />">Login</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/register" />">Sign Up</a></li>
							</ul></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</nav>
