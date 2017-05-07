<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String query = request.getParameter("query") == null ? "" : request.getParameter("query");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div ng-app="searchApp" ng-controller="searchCtrl" id="searchApp"
	ng-init="init('${contextPath}/searchCardsAjax?query=<%=query%>')">
	<input type="hidden" value='${banks}' id="bankList"><input
		type="hidden" value='${cardTypes}' id="cardTypeList"> <input
		type="hidden" value='${categories}' id="categoryList"> <input
		type="hidden" value='${cardCategories}' id="cardCategoryList">
	<input type="hidden" value='${title}' id="titleArray"> <input
		type="hidden" value='${pageIndex}' id="pageIndex"> <input
		type="hidden" value="searchCardsAjax?query=" id="searchQuery">
	<div class="container">
		<div class="search-heading col-md-9 col-md-offset-3">
			<div class="col-md-9 col-md-9">
				<div class="heading">
					<strong>Cards matching your criteria</strong> - {{results.count}}
					Cards
				</div>
			</div>
			<div class="col-md-3">
				<select ng-init="item = sortOptions[0]"
					ng-options="sortOption as sortOption.name for sortOption in sortOptions"
					class="form-control" ng-model="item" ng-change="sort()"></select>
			</div>
		</div>
		<div class="col-md-3 prdt-right">
			<div class="w_sidebar">
				<div class="sky-form">
					<h1>Banks</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchBank"
							placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!bank.count}"
								ng-repeat="bank in bankFilters | filter:searchBank"> <input
								type="checkbox" id="bank{{bank.id}}-check"
								ng-model="bank.isChecked" ng-disabled="!bank.count"
								ng-click="filter(bank.name,bank.id, bank.displayName, $event)"><i></i>{{bank.displayName}}&nbsp;<span
								ng-show="bank.count">({{bank.count}})</span>
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>Categories</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchCategory"
							placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!category.count}"
								ng-repeat="category in categoryFilters | filter:searchCategory">
								<input type="checkbox" id="category{{category.id}}-check"
								ng-model="category.isChecked" ng-disabled="!category.count"
								ng-click="filter(category.name,category.id, category.displayName, $event)"><i></i>{{category.displayName}}&nbsp;<span
								ng-show="category.count">({{category.count}})</span>
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>Card Type</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchCardType"
							placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!cardType.count}"
								ng-repeat="cardType in cardTypeFilters | filter:searchCardType">
								<input type="checkbox" id="cardType{{cardType.id}}-check"
								ng-model="cardType.isChecked" ng-disabled="!cardType.count"
								ng-click="filter(cardType.name,cardType.id, cardType.displayName, $event)"><i></i>{{cardType.displayName}}&nbsp;<span
								ng-show="cardType.count">({{cardType.count}})</span>
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>Card Category</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control"
							ng-model="searchCardCategory" placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!cardCategory.count}"
								ng-repeat="cardCategory in cardCategoryFilters | filter:searchCardCategory">
								<input type="checkbox"
								id="cardCategory{{cardCategory.id}}-check"
								ng-model="cardCategory.isChecked"
								ng-disabled="!cardCategory.count"
								ng-click="filter(cardCategory.name,cardCategory.id, cardCategory.displayName, $event)"><i></i>{{cardCategory.displayName}}&nbsp;<span
								ng-show="cardCategory.count">({{cardCategory.count}})</span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="search-label col-md-12">
				<ul class="filter-summary-filterList col-md-12">
					<li ng-repeat="filterLabel in filterLabels"><div
							class="filter-summary-filter">
							{{filterLabel.name}} <label class="filter-summary-removeFilter"><input
								type="checkbox" id="{{filterLabel.value}}{{filterLabel.id}}"
								ng-model="filterLabels[filterLabel.name]" ng-checked="true"
								ng-click="filter(filterLabel.value,filterLabel.id, filterLabel.name, $event)"><span
								class="icon"><i class="fa fa-times"></i></span></label>
						</div></li>
					<li ng-show="filterLabels.length"><span
						class="filter-summary-resetAll"><a href="#"
							ng-click="removeAll()"> Clear All</a></span></li>
				</ul>
			</div>
			<div class="col-md-12 search-container">
				<div class="col-md-4 col-sm-6 search-item"
					ng-repeat="card in results.cards">
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
								<i class="fa fa-info-circle"></i><a href='#' class="hidden-sm">Information</a>
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
				<div class="no-results col-md-12" ng-show="!results.cards.length">No
					results found for your search! Please try again with different
					search options.</div>
			</div>
			<div class="row" ng-show="(results.count/limit)>1">
				<div class="col col-md-12">
					<ul class="pagination  pull-right">
						<li><a href="#" ng-click="paginate(pageNumber-1)">«</a></li>
						<li><a href="#" ng-class="{selected:n==pageNumber}"
							ng-repeat="n in [] | range:(results.count/limit)+1"
							ng-click="paginate(n)">{{n+1}}</a></li>
						<li><a href="#" ng-click="paginate(pageNumber+1)">»</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="loader"></div>