<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Menu Navigation -->

<c:set var="homePage" value="/home" />
<sec:authorize access="hasRole('BANK')">
	<c:set var="homePage" value="/bank" />
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
	<c:set var="homePage" value="/console" />
</sec:authorize>


<header>
	<nav id="mainNav" class="navbar navbar-default navbar-custom">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="row">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> Menu <i
							class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand" href="<c:url value="${homePage}" />">Card
						Deals</a>
				</div>
				<form role="form" action="<c:url value="/searchCards" />"
					id="searchForm">
					<div class="col-sm-9 col-md-9 hidden-xs nav-searchbar">
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
					</div>
				</form>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="row">
				<div class="collapse navbar-collapse" id="navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li class="hidden"><a href="#page-top"></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Deals<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/login" />">Cash Back Offer</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/register" />">Flat
										Discounts</a></li>
							</ul></li>
						<li><a href="<c:url value="/page/aboutUs" />">About</a></li>
						<li><a href="<c:url value="/page/contact" />">Contact</a></li>
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
								data-toggle="dropdown"> Hi <sec:authentication
										property="principal.firstName" />&nbsp;&nbsp;&nbsp; <i
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
</header>