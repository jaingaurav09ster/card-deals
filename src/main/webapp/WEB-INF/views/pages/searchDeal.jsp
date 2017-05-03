<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div ng-app="searchApp" ng-controller="searchCtrl" id="controller"
	ng-init="init('searchDealsAjax?query=${query}')">
	<input type="hidden" value='${banks}' id="bankList"><input
		type="hidden" value='${merchants}' id="merchantList"><input
		type="hidden" value='${cards}' id="cardList"> <input
		type="hidden" value='${categories}' id="categoryList"> <input
		type="hidden" value='${cardCategories}' id="cardCategoryList">
	<input type="hidden" value='${pageIndex}' id="pageIndex"> <input
		type="hidden" value="searchDealsAjax?query=" id="searchQuery">
	<div class="container">
		<div class="search-heading col-md-9 col-md-offset-3">
			<div class="col-md-9 col-md-9">
				<div class="heading">
					<strong>Deals matching your criteria</strong> - {{results.count}}
					Deals
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
					<h1>Cards</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchCard"
							placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!card.count}"
								ng-repeat="card in cardFilters | filter:searchCard"> <input
								type="checkbox" id="card{{card.id}}-check"
								ng-model="card.isChecked"
								ng-click="filter(card.name,card.id, card.displayName, $event)"><i></i>{{card.displayName}}&nbsp;<span
								ng-show="card.count">({{card.count}})</span>
							</label>
						</div>
					</div>
				</div>
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
					<h1>Merchants</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchMerchant"
							placeholder="Search">
						<div class="col col-4">
							<label class="checkbox" ng-class="{disabled:!merchant.count}"
								ng-repeat="merchant in merchantFilters | filter:searchMerchant">
								<input type="checkbox" id="merchant{{merchant.id}}-check"
								ng-model="merchant.isChecked" ng-disabled="!merchant.count"
								ng-click="filter(merchant.name,merchant.id, merchant.displayName, $event)"><i></i>{{merchant.displayName}}&nbsp;<span
								ng-show="merchant.count">({{merchant.count}})</span>
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
				<div class="col-md-12 col-sm-12 search-item row deals"
					ng-repeat="deal in results.deals">
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
				<div class="no-results col-md-12" ng-show="!results.deals.length">No
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