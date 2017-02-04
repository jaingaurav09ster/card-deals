<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body id="page-top" class="index">
	<%@include file="navbar.jsp"%>
	<!-- Header -->
	<header>
		<div class="container">
			<div class="row">
				<div class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="intro-text">
						<span class="name">Card Deals</span>
						<div class="input-group input-group-lg">
							<input type="text" class="form-control typeahead tt-query"
								autocomplete="off" placeholder="Search cards..."> <span
								class="input-group-btn">
								<button class="btn btn-secondary" type="button">Go!</button>
							</span>
						</div>
						<hr class="star-light">
						<span class="skills">Deals - Discounts - Freebies</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Portfolio Grid Section -->
	<section id="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Featured Deals</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal1" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/cabin.png" class="img-responsive"
						alt="">
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal2" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/cake.png" class="img-responsive"
						alt="">
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal3" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/circus.png" class="img-responsive"
						alt="">
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal4" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/game.png" class="img-responsive"
						alt="">
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal5" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/safe.png" class="img-responsive"
						alt="">
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal6" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/images/portfolio/submarine.png" class="img-responsive"
						alt="">
					</a>
				</div>
			</div>
		</div>
	</section>
	<%@include file="footer.jsp"%>
	<!-- /container -->
	<script type="text/javascript">
			$(document).ready(function() {
				$('#home').addClass("active");
			});
		</script>
</body>
</html>
