<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div ng-app="searchApp" ng-controller="searchCtrl" id="controller"
	ng-init="init('<%=request.getParameter("query")%>')">
	<input type="hidden" value='${banks}' id="bankList"> <input
		type="hidden" value='${categories}' id="categoryList"> <input
		type="hidden" value='${cardCategories}' id="cardCategoryList">
	<input type="hidden" value='${title}' id="titleArray">
	<div class="container">
		<div class="search-heading col-md-9 col-md-offset-3">
			<div class="col-md-9 col-md-9">
				<div class="heading"><strong>Credit Cards Menu</strong> - {{results.length}} Cards</div>
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
						<input type="text" class="form-control" ng-model="searchBank">
						<div class="col col-4">
							<label class="checkbox"
								ng-repeat="bank in bankFilters | filter:searchBank"> <input
								type="checkbox" id="bank{{bank.id}}-check"
								ng-model="bank.isChecked"
								ng-click="filter(bank.name,bank.id, bank.displayName, $event)"><i></i>{{bank.displayName}}
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>Categories</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control" ng-model="searchCategory">
						<div class="col col-4">
							<label class="checkbox"
								ng-repeat="category in categoryFilters | filter:searchCategory">
								<input type="checkbox" id="category{{category.id}}-check"
								ng-model="category.isChecked"
								ng-click="filter(category.name,category.id, category.displayName, $event)"><i></i>{{category.displayName}}
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>Card Type</h1>
					<div class="row1 scroll-pane">
						<input type="text" class="form-control"
							ng-model="searchCardCategory">
						<div class="col col-4">
							<label class="checkbox"
								ng-repeat="cardCategory in cardCategoryFilters | filter:searchCardCategory">
								<input type="checkbox"
								id="cardCategory{{cardCategory.id}}-check"
								ng-model="cardCategory.isChecked"
								ng-click="filter(cardCategory.name,cardCategory.id, cardCategory.displayName, $event)"><i></i>{{cardCategory.displayName}}
							</label>
						</div>
					</div>
				</div>
				<div class="sky-form">
					<h1>discount</h1>
					<div class="row1 row2 scroll-pane">
						<div class="col col-4">
							<label class="radio"><input type="radio" name="radio"
								checked=""><i></i>60 % and above</label> <label class="radio"><input
								type="radio" name="radio"><i></i>50 % and above</label> <label
								class="radio"><input type="radio" name="radio"><i></i>40
								% and above</label>
						</div>
						<div class="col col-4">
							<label class="radio"><input type="radio" name="radio"><i></i>30
								% and above</label> <label class="radio"><input type="radio"
								name="radio"><i></i>20 % and above</label> <label class="radio"><input
								type="radio" name="radio"><i></i>10 % and above</label>
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
			<div class="product-block">
				<div class="no-results col-md-12" ng-show="!results.length">No
					results found for your search! Please try again with different
					search options.</div>
				<div class="col-md-4 col-sm-6" ng-repeat="card in results">
					<div class="thumbnail text-center">
						<img ng-src="/deals/resources/upload/card/{{card.imagePath}}"
							alt="Card" class="img-responsive">
						<h4 class="text-danger">{{card.title}}</h4>
						<div class="ratings">
							<span class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty"></span>
						</div>
						<p>{{card.bank.name}}</p>
						<p>{{card.cardCategory.name}} {{card.cardType.name}}</p>
						<p ng-repeat="category in card.categories">
							<strong>Categories: </strong> {{category.name}}&nbsp;
						</p>
						<div class="form-group">
							<button class="btn btn-primary center">GET DEALS</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
