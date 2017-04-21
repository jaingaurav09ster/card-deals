<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<c:set var="contextUri" value="" scope="page" />
<sec:authorize access="hasRole('ADMIN')">
	<c:set var="contextUri" value="/admin" scope="page" />
</sec:authorize>

<div class="col-sm-3 col-md-3 sidebar">
	<div class="nav-side-menu">
		<c:if test="${fn:length(cardName)  gt 25}">
			<c:set var="cardName">${fn:substring(cardName, 0, 25)}..</c:set>
		</c:if>
		<div class="brand">
			<a href="<c:url value="${contextUri}/listCards" />" title="${cardName}">&lt;&lt;${cardName}</a>
		</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
			<ul id="menu-content" class="menu-content collapse out">
				<li data-toggle="collapse" data-target="#dealManager"
					class="collapsed" id="li-dealManager"><a href="#"><i
						class="fa fa-gift fa-lg"></i> Deal <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="dealManager">
					<li id="updateDealForm" style="display: none;"><a href="#">Update
							Deal</a></li>
					<li id="dealForm"><a
						href="<c:url value="/newDeal/${cardId}" />">New Deal</a></li>
					<li id="dealList"><a
						href="<c:url value="/listDeals/${cardId}" />">Deal List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#feesManager"
					class="collapsed" id="li-feesManager"><a href="#"><i
						class="fa fa-calculator fa-lg"></i> Fees <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="feesManager">
					<li id="updateFeesForm"><a
						href="<c:url value="/updateFees/${cardId}" />">Add/Update
							Fees</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#rewardManager"
					class="collapsed" id="li-rewardManager"><a href="#"><i
						class="fa fa-trophy fa-lg"></i> Reward <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="rewardManager">
					<li id="updateRewardForm" style="display: none;"><a href="#">Update
							Reward</a></li>
					<li id="rewardForm"><a
						href="<c:url value="/newReward/${cardId}" />">New Reward</a></li>
					<li id="rewardList"><a
						href="<c:url value="/listRewards/${cardId}" />">Reward
							List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#joiningPerkManager"
					class="collapsed" id="li-joiningPerkManager"><a href="#"><i
						class="fa fa-money fa-lg"></i> Joining Perk <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="joiningPerkManager">
					<li id="updateJoiningPerkForm" style="display: none;"><a
						href="#">Update JoiningPerk</a></li>
					<li id="joiningPerkForm"><a
						href="<c:url value="/newJoiningPerk/${cardId}" />">New
							Joining Perk</a></li>
					<li id="joiningPerkList"><a
						href="<c:url value="/listJoiningPerks/${cardId}" />">Joining
							Perk List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#featureManager"
					class="collapsed" id="li-featureManager"><a href="#"><i
						class="fa fa-briefcase fa-lg"></i> Feature <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="featureManager">
					<li id="updateFeatureForm" style="display: none;"><a href="#">Update
							Feature</a></li>
					<li id="featureForm"><a
						href="<c:url value="/newFeature/${cardId}" />">New
							Feature</a></li>
					<li id="featureList"><a
						href="<c:url value="/listFeatures/${cardId}" />">Feature
							List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#ratingManager"
					class="collapsed" id="li-ratingManager"><a href="#"><i
						class="fa fa-star-o fa-lg"></i> Rating <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="ratingManager">
					<li id="updateRatingForm" style="display: none;"><a href="#">Update
							Rating</a></li>
					<li id="ratingForm"><a
						href="<c:url value="/newRating/${cardId}" />">New Rating</a></li>
					<li id="ratingList"><a
						href="<c:url value="/listRatings/${cardId}" />">Rating
							List</a></li>
				</ul>
				<li data-toggle="collapse" data-target="#documentManager"
					class="collapsed" id="li-documentManager"><a href="#"><i
						class="fa fa-file fa-lg"></i> Document <span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="documentManager">
					<li id="updateDocumentForm" style="display: none;"><a href="#">Update
							Document</a></li>
					<li id="documentForm"><a
						href="<c:url value="/newDocument/${cardId}" />">New
							Document</a></li>
					<li id="documentList"><a
						href="<c:url value="/listDocuments/${cardId}" />">Document
							List</a></li>
				</ul>
			</ul>
		</div>
	</div>
</div>