<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Menu Navigation -->

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="homePage" value="/home" />
<c:set var="adminCss" value="adminCss" />
<c:set var="mainNav" value="main-navbar" />
<sec:authorize access="hasRole('BANK')">
	<c:set var="homePage" value="/bank" />
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
	<c:set var="homePage" value="/console" />
</sec:authorize>
<header>
	<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
		<c:set var="adminCss" value="" />
		<c:set var="mainNav" value="main-nav" />
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
	<div id="${mainNav}">
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
							src="${contextPath}/resources/images/logo.jpg"></a>
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
					<div class="collapse navbar-collapse" id="navbar-collapse-1"
						ng-app="navBarApp" ng-controller="navBarController"
						ng-init="init()">
						<ul class="nav navbar-nav navbar-right">
							<li class="hidden"><a href="#page-top"></a></li>
							<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
								<li class="visible-xs"><a
									href="<c:url value="/searchDeals" />">Deals</a></li>
								<li class="dropdown mega-dropdown hidden-xs"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown">Cards<span
										class="caret"></span></a>
									<ul class="dropdown-menu mega-dropdown-menu">
										<li class="col-sm-3 spacing-left">
											<ul>
												<li class="dropdown-header">Featured</li>
												<div id="featuredCard" class="carousel slide"
													data-ride="carousel">
													<div class="carousel-inner">
														<div class="item" ng-repeat="card in cards.cards"
															ng-class="{active:$first}">
															<a href="#"><img
																ng-src="${contextPath}/resources/upload/card/{{card.imagePath}}"
																class="img-responsive" alt="{{card.title}}" /></a>
															<h4 class="text-center">
																<small>{{card.title}}</small>
															</h4>
														</div>
													</div>
													<!-- End Carousel Inner -->
													<!-- Controls -->
													<a class="left carousel-control" href="#featuredCard"
														role="button" data-slide="prev"> <span
														class="glyphicon glyphicon-chevron-left"
														aria-hidden="true"></span> <span class="sr-only">Previous</span>
													</a> <a class="right carousel-control" href="#featuredCard"
														role="button" data-slide="next"> <span
														class="glyphicon glyphicon-chevron-right"
														aria-hidden="true"></span> <span class="sr-only">Next</span>
													</a>
												</div>
											</ul>
										</li>
										<li class="col-sm-2 spacing-col"
											ng-repeat="(key, value) in navElements"
											ng-class="{'col-sm-offset-1':'bank'==key}"
											ng-if="'bank'==key || 'category'==key || 'cardCategory'==key || 'cardType'==key">
											<ul>
												<li class="dropdown-header">{{key}}</li>
												<li ng-repeat="element in value"><a
													href="<c:url value="/searchCards?query={{element.name}}%3A{{element.id}}" />">{{element.displayName}}</a></li>
											</ul>
										</li>
									</ul></li>
								<li class="visible-xs"><a
									href="<c:url value="/searchCards" />">Cards</a></li>
								<li class="dropdown mega-dropdown hidden-xs"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown">Deals<span
										class="caret"></span></a>
									<ul class="dropdown-menu mega-dropdown-menu">
										<li class="col-sm-3 spacing-left">
											<ul>
												<li class="dropdown-header">Trending</li>
												<div id="featuredDeal" class="carousel slide"
													data-ride="carousel">
													<div class="carousel-inner">
														<div class="item" ng-repeat="deal in deals.deals"
															ng-class="{active:$first}">
															<div class="col-item">
																<div class="merchant">
																	<div class="row">
																		<div class="col-sm-6 col-xs-6">
																			<img
																				ng-src="${contextPath}/resources/upload/merchant/{{deal.merchant.imagePath}}"
																				class="img-responsive" alt="{{deal.title}}" />
																		</div>
																		<div class="col-sm-6 col-xs-6">
																			<img
																				ng-src="${contextPath}/resources/upload/bank/{{deal.card.bank.imagePath}}"
																				class="img-responsive" alt="{{deal.title}}" />
																		</div>
																	</div>
																</div>
																<div class="info">
																	<h5>
																		<strong>{{deal.title}}</strong>
																	</h5>
																	<h6>{{deal.description}}</h6>
																	<h6>
																		Applicable on <strong>{{deal.card.title}}</strong>
																	</h6>
																</div>
																<div class="links">
																	<p class="btn-add">Valid Till {{deal.endDate}}</p>
																	<p class="btn-details">
																		Coupon Code: <i ng-if="deal.couponCode"><strong>{{deal.couponCode}}</strong></i><i
																			ng-if="!deal.couponCode">Not Required</i>
																	</p>
																</div>
																<div class="clearfix"></div>
															</div>
														</div>
													</div>
													<!-- End Carousel Inner -->
													<!-- Controls -->
													<a class="left carousel-control" href="#featuredDeal"
														role="button" data-slide="prev"> <span
														class="glyphicon glyphicon-chevron-left"
														aria-hidden="true"></span> <span class="sr-only">Previous</span>
													</a> <a class="right carousel-control" href="#featuredDeal"
														role="button" data-slide="next"> <span
														class="glyphicon glyphicon-chevron-right"
														aria-hidden="true"></span> <span class="sr-only">Next</span>
													</a>
												</div>
											</ul>
										</li>
										<li class="col-sm-2 spacing-col"
											ng-repeat="(key, value) in navElements"
											ng-class="{'col-sm-offset-1':'card'==key}"
											ng-if="'bank'==key || 'category'==key || 'merchant'==key || 'card'==key">
											<ul>
												<li class="dropdown-header">{{key}}</li>
												<li ng-repeat="element in value"><a
													href="<c:url value="/searchDeals?query={{element.name}}:{{element.id}}" />">{{element.displayName}}</a></li>
											</ul>
										</li>
									</ul></li>
								<li><a href="<c:url value="/page/aboutUs" />">About</a></li>
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
									data-toggle="dropdown">Hi <sec:authentication
											property="principal.firstName" /> &nbsp;&nbsp;&nbsp;<i
										class="glyphicon glyphicon-user"></i><b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">${pageContext.request.userPrincipal.name}</a></li>
										<li class="divider"></li>
										<li><a href="<c:url value="/user/editProfile" />">My
												Profile</a></li>
										<li class="divider"></li>
										<sec:authorize access="hasRole('USER')">
											<li><a href="<c:url value="/user/myDeals" />">Personalized
													Deals</a></li>
											<li class="divider"></li>
										</sec:authorize>
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
	</div>
</header>