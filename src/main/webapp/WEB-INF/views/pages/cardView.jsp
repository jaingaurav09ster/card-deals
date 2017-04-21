<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-9 col-md-9 accordion main-content">
	<h3>View Card</h3>
	<hr>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne"> <i
						class="more-less glyphicon glyphicon-plus"></i>Card Details
					</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<div class="col-xs-12 col-sm-4 text-center image-box">
						<figure>
							<img src="/deals/resources/upload/card/${card.imagePath}"
								alt="Card" class="img-responsive">
						</figure>
					</div>
					<div class="col-xs-12 col-sm-8 card-detail-box">
						<h3>${card.title}</h3>
						<p>
							<strong>${card.bank.name}</strong>
						</p>
						<p>
							<strong>${card.cardCategory.name} ${card.cardType.name}</strong>
						</p>
						<p>
							<strong>Categories: </strong>
							<c:forEach items="${card.categories}" var="category">${category.name}&nbsp;</c:forEach>
						</p>
						<p>
							<strong>Launched On: </strong>${card.launchDate}
						</p>
						<p>
							<strong>Rank: </strong>${card.rank}
						</p>
						${card.description}
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
		<c:if test="${fn:length(card.deals) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo"> <i
							class="more-less glyphicon glyphicon-plus"></i>Deals
						</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<c:forEach items="${card.deals}" var="deal">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>${deal.title}</h4>
								<p>
									<strong>Start Date:</strong> ${deal.startDate}
								</p>
								<p>
									<strong>End Date:</strong> ${deal.endDate}
								</p>
								<p>
									<strong>Categories: </strong>
									<c:forEach items="${deal.categories}" var="category">${category.name}&nbsp;</c:forEach>
								</p>
								<p>
									<strong>${deal.offerType.title}</strong> for max value
									${deal.maxValueUnit} ${deal.maxValue}
								</p>
								<p>
									<strong>Rank: </strong>${deal.rank}
								</p>
								${deal.description}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(card.rewards) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree"> <i
							class="more-less glyphicon glyphicon-plus"></i>Rewards
						</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<c:forEach items="${card.rewards}" var="reward">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>${reward.title}</h4>
								<p>
									<strong>Categories: </strong>
									<c:forEach items="${reward.categories}" var="category">${category.name}&nbsp;</c:forEach>
								</p>
								${reward.description}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${card.fees ne null}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingFour">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseFour"
							aria-expanded="false" aria-controls="collapseFour"> <i
							class="more-less glyphicon glyphicon-plus"></i>Fees
						</a>
					</h4>
				</div>
				<div id="collapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<div class="panel-body">
						<div class="col-xs-12 col-sm-6 detail-box">
							<p>
								<strong>First Year Fee: ${card.fees.firstYear}</strong>
							</p>
							<p>
								<strong>Onward Fee: ${card.fees.onwards}</strong>
							</p>
							<p>
								<strong>APR: ${card.fees.apr}</strong>
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(card.joiningPerks) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingFive">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseFive"
							aria-expanded="false" aria-controls="collapseFive"> <i
							class="more-less glyphicon glyphicon-plus"></i>Joining perks
						</a>
					</h4>
				</div>
				<div id="collapseFive" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFive">
					<div class="panel-body">
						<c:forEach items="${card.joiningPerks}" var="joiningPerk">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>${joiningPerk.title}</h4>
								<p>
									<strong>Categories: </strong>
									<c:forEach items="${joiningPerk.categories}" var="category">${category.name}&nbsp;</c:forEach>
								</p>
								${joiningPerk.description}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(card.features) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingSix">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseSix"
							aria-expanded="false" aria-controls="collapseSix"> <i
							class="more-less glyphicon glyphicon-plus"></i>Features
						</a>
					</h4>
				</div>
				<div id="collapseSix" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingSix">
					<div class="panel-body">
						<c:forEach items="${card.features}" var="feature">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>${feature.title}</h4>
								<p>
									<strong>Categories: </strong>
									<c:forEach items="${feature.categories}" var="category">${category.name}&nbsp;</c:forEach>
								</p>
								${feature.description}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(card.rating) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingSeven">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseSeven"
							aria-expanded="false" aria-controls="collapseSeven"> <i
							class="more-less glyphicon glyphicon-plus"></i>Rating
						</a>
					</h4>
				</div>
				<div id="collapseSeven" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingSeven">
					<div class="panel-body">
						<c:forEach items="${card.rating}" var="rating">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>
									<c:forEach var="i" begin="1" end="5" step="1">
										<c:choose>
											<c:when test="${i <= rating.rating }">
												<a href="#"> <span class="fa fa-star"></span></a>
											</c:when>
											<c:otherwise>
												<a href="#"> <span class="fa fa-star-o"></span></a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</h4>
								${rating.comment}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(card.documents) gt 0}">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingEight">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseEight"
							aria-expanded="false" aria-controls="collapseEight"> <i
							class="more-less glyphicon glyphicon-plus"></i>Documents
						</a>
					</h4>
				</div>
				<div id="collapseEight" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingEight">
					<div class="panel-body">
						<c:forEach items="${card.documents}" var="document">
							<div class="col-xs-12 col-sm-6 detail-box">
								<h4>${document.name}</h4>
								${document.description}
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</div>
