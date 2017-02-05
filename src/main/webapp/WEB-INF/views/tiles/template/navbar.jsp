<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Menu Navigation -->
<nav id="mainNav"
	class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
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
						<li class="page-scroll"><a href="#portfolio">Portfolio</a></li>
						<li class="page-scroll"><a href="#about">About</a></li>
						<li class="page-scroll"><a href="#contact">Contact</a></li>
						<li class="page-scroll"><a href="<c:url value="/user/editProfile" />">My Profile</a></li>
						<li class="page-scroll"><a href="<c:url value="/logout" />">Logout</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-scroll"><a href="#portfolio">Portfolio</a></li>
						<li class="page-scroll"><a href="#about">About</a></li>
						<li class="page-scroll"><a href="#contact">Contact</a></li>
						<li class="page-scroll"><a href="<c:url value="/login" />">Login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>