<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Menu Navigation -->
<nav id="mainNav"
	class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand" href="<c:url value="/home" />">Card Deals</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name != null}">
						<sec:authorize access="hasRole('ADMIN')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Administration<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/admin/user/listUsers" />">Manage
											Users</a></li>
									<li class="divider"></li>
									<li><a href="<c:url value="/admin/card/listCards" />">Manage
											Cards</a></li>
								</ul></li>
						</sec:authorize>
						<li><a href="<c:url value="/" />">About</a></li>
						<li><a href="<c:url value="/" />">Contact</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i><b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">${pageContext.request.userPrincipal.name}</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value="/user/editProfile" />">My
										Profile</a></li>
								<li><a href="<c:url value="/logout" />">Logout</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value="/" />">About</a></li>
						<li><a href="<c:url value="/" />">Contact</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i><b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/login" />">Login</a></li>
								<li><a href="<c:url value="/register" />">Sign Up</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>