<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="carousel fade-carousel slide" data-ride="carousel"
	data-interval="8000" id="bs-carousel">
	<ol class="carousel-indicators">
		<li data-target="#bs-carousel" data-slide-to="0" class="active"></li>
		<li data-target="#bs-carousel" data-slide-to="1"></li>
		<li data-target="#bs-carousel" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner">
		<div class="item slides active">
			<div class="overlay"></div>
			<div class="slide-1"></div>
			<div class="hero">
				<hgroup>
					<h1>We are creative</h1>
					<h4>Get the best deals on your cards</h4>
				</hgroup>
				<button class="btn btn-hero btn-lg" role="button">See all
					features</button>
			</div>
		</div>
		<div class="item slides">
			<div class="overlay"></div>
			<div class="slide-2"></div>
			<div class="hero">
				<hgroup>
					<h1>We are smart</h1>
					<h4>Formulate the best personalize offers for you</h4>
				</hgroup>
				<button class="btn btn-hero btn-lg" role="button">See all
					features</button>
			</div>
		</div>
		<div class="item slides">
			<div class="overlay"></div>
			<div class="slide-3"></div>
			<div class="hero">
				<hgroup>
					<h1>We are amazing</h1>
					<h4>We are amazed if we amaze you</h4>
				</hgroup>
				<button class="btn btn-hero btn-lg" role="button">See all
					features</button>
			</div>
		</div>
	</div>
</div>
<div ng-app="homePage" ng-controller="homePage" id="controller"
	ng-init="init()">
	<div class="cards-container">
		<div class="container">
			<div class="row top-margin">
				<div class="col-md-8 col-sm-8 col-xs-8">
					<h3>Best Cards</h3>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4">
					<!-- Controls -->
					<div class="controls pull-right">
						<a class="left fa fa-chevron-left btn btn-success"
							href="#carousel-custom" data-slide="prev"></a><a
							class="right fa fa-chevron-right btn btn-success"
							href="#carousel-custom" data-slide="next"></a>
					</div>
				</div>
			</div>
			<div id="carousel-custom" class="carousel slide" data-ride="carousel"
				data-interval="false">
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<div class="row">
							<div class="col-sm-3 cards"
								ng-repeat="card in cards.cards | limitTo:4">
								<div class="col-item">
									<div class="photo">
										<img
											ng-src="${contextPath}/resources/upload/card/{{card.imagePath}}"
											class="img-responsive" alt="{{card.title}}" />
									</div>
									<div class="info">
										<h5 class="text-center">{{card.title}}</h5>
										<ul>
											<li ng-repeat="feature in card.features | limitTo:2">{{feature.title}}</li>
										</ul>
									</div>
									<div class="links">
										<p class="btn-add">
											<i class="fa fa-info-circle"></i><a href='#'
												class="hidden-sm">Information</a>
										</p>
										<p class="btn-details">
											<i class="fa fa-gift"></i><a
												href="<c:url value="/searchDeals?query=card:{{card.id}}" />"
												class="hidden-sm">Get Deals</a>
										</p>

									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="row">
							<div class="col-sm-3 cards"
								ng-repeat="card in cards.cards.slice(4,cards.cards.length)| limitTo:4">
								<div class="col-item">
									<div class="photo">
										<img
											ng-src="${contextPath}/resources/upload/card/{{card.imagePath}}"
											class="img-responsive" alt="{{card.title}}" />
									</div>
									<div class="info">
										<h5 class="text-center">{{card.title}}</h5>
										<ul>
											<li ng-repeat="feature in card.features | limitTo:2">{{feature.title}}</li>
										</ul>
									</div>
									<div class="links">
										<p class="btn-add">
											<i class="fa fa-info-circle"></i><a href='#'
												class="hidden-sm">Information</a>
										</p>
										<p class="btn-details">
											<i class="fa fa-gift"></i><a
												href="<c:url value="/searchDeals?query=card:{{card.id}}" />"
												class="hidden-sm">Get Deals</a>
										</p>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="deals-container">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-sm-8 col-xs-8">
					<h3>Best Deals</h3>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4">
					<!-- Controls -->
					<div class="controls pull-right">
						<a class="left fa fa-chevron-left btn btn-success"
							href="#carousel-custom-deal" data-slide="prev"></a><a
							class="right fa fa-chevron-right btn btn-success"
							href="#carousel-custom-deal" data-slide="next"></a>
					</div>
				</div>
			</div>
			<div id="carousel-custom-deal" class="carousel slide"
				data-ride="carousel" data-interval="false">
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<div class="row">
							<div class="col-sm-6 deals"
								ng-repeat="deal in deals.deals | limitTo:4">
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
										<h4>{{deal.title}}</h4>
										<h5>{{deal.description}}</h5>
										<h5>
											Applicable on <strong>{{deal.card.title}}</strong>
										</h5>
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
					</div>
					<div class="item">
						<div class="row">
							<div class="col-sm-3 deals"
								ng-repeat="deal in deals.deals.slice(4,deals.deals.length)| limitTo:4">
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
										<h4>{{deal.title}}</h4>
										<h5>{{deal.description}}</h5>
										<h5>
											Applicable on <strong>{{deal.card.title}}</strong>
										</h5>
									</div>
									<div class="links">
										<p class="btn-add">Valid Till {{deal.endDate}}</p>
										<p class="btn-details">
											Coupon Code <i>{{deal.couponCode}}</i>
										</p>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>