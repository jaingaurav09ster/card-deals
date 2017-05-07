(function($) {
	"use strict"; // Start of use strict

	$(document)
			.ready(
					function() {

						$('#main-nav').affix({
							offset : {
								top : $('header').height()
							}
						});

						$(".dropdown").hover(
								function() {
									$('.dropdown-menu', this).not(
											'.in .dropdown-menu').stop(true,
											true).slideDown("400");
									$(this).toggleClass('open');
								},
								function() {
									$('.dropdown-menu', this).not(
											'.in .dropdown-menu').stop(true,
											true).slideUp("400");
									$(this).toggleClass('open');
								});

						var cards = new Bloodhound(
								{
									datumTokenizer : Bloodhound.tokenizers.whitespace,
									queryTokenizer : Bloodhound.tokenizers.whitespace,
									remote : {
										url : contextPath
												+ '/searchCardsAjax?query=title:%QUERY',
										wildcard : '%QUERY',
										filter : function(cards) {
											return cards.cards;
										}
									}
								});
						$('.typeahead')
								.on(
										'typeahead:selected',
										function() {
											$('input#query')
													.val(
															'title:'
																	+ $(
																			'input#searchQuery')
																			.val());
											$("#searchForm").submit();
										})
								.typeahead(
										null,
										{
											name : 'cards',
											display : 'title',
											source : cards,
											limit : 6,
											templates : {
												suggestion : Handlebars
														.compile('<div class="row typeahead-row"><div class="col-md-3 col-sm-3"><img class="img-responsive" src="'
																+ contextPath
																+ '/resources/upload/card/{{imagePath}}">'
																+ '</div><div class="col-md-9 col-sm-9"><div class="row"><strong>{{title}}</strong></div><div class="row">{{description}}</div></div></div>')
											}
										});
						$("#searchForm")
								.submit(
										function() {
											if ($('input#searchQuery').val()) {
												$('input#query')
														.val(
																'title:'
																		+ $(
																				'input#searchQuery')
																				.val());
											}
										});

						var cardList = new Bloodhound(
								{
									datumTokenizer : Bloodhound.tokenizers.whitespace,
									queryTokenizer : Bloodhound.tokenizers.whitespace,
									remote : {
										url : contextPath
												+ '/searchCardsAjax?query=title:%QUERY&filter=true',
										wildcard : '%QUERY',
										filter : function(cards) {
											return cards.cards;
										}
									}
								});
						$('.autocomplete')
								.on('typeahead:selected', function() {

								})
								.typeahead(
										null,
										{
											name : 'cards',
											display : 'title',
											source : cardList,
											limit : 6,
											autoselect : true,
											templates : {
												suggestion : Handlebars
														.compile('<div class="row typeahead-row"><div class="col-md-3 col-sm-3"><img class="img-responsive" src="'
																+ contextPath
																+ '/resources/upload/card/{{imagePath}}">'
																+ '</div><div class="col-md-9 col-sm-9"><div class="row"><strong>{{title}}</strong></div></div></div>')
											}
										});

						$('#registrationForm, #editProfileForm, #newUserForm')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												firstName : {
													validators : {
														stringLength : {
															min : 2,
														},
														notEmpty : {
															message : 'Please enter first name'
														}
													}
												},
												email : {
													validators : {
														notEmpty : {
															message : 'Please enter email address'
														},
														emailAddress : {
															message : 'Please supply a valid email address'
														}
													}
												},
												password : {
													validators : {
														notEmpty : {
															message : 'Please enter password'
														},
														stringLength : {
															min : 4,
															message : 'Password should be atleast 4 digits'
														}
													}
												}
											}
										}).on('submit', function(e) {

								});

						$('#forgotPasswordForm')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												email : {
													validators : {
														notEmpty : {
															message : 'Please enter email address'
														},
														emailAddress : {
															message : 'Please supply a valid email address'
														}
													}
												}
											}
										}).on('submit', function(e) {
								});
						$('#resetPasswordForm')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												newPassword : {
													validators : {
														notEmpty : {
															message : 'Please supply the password'
														},
														stringLength : {
															min : 4,
															message : 'Password should be atleast 4 digits'
														}
													}
												},
												matchPassword : {
													validators : {
														notEmpty : {
															message : 'Please re-enter password'
														},
														stringLength : {
															min : 4,
															message : 'Password should be atleast 4 digits'
														}
													}
												}
											}
										}).on('submit', function(e) {
								});

						$('#loginform')
								.bootstrapValidator(
										{
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												email : {
													validators : {
														notEmpty : {
															message : 'Please enter email address'
														}
													}
												},
												password : {
													validators : {
														notEmpty : {
															message : 'Please enter password'
														}
													}
												}
											}
										}).on('submit', function(e) {
								});

						NProgress.start();
						NProgress.set(0.4);
						// Increment
						var interval = setInterval(function() {
							NProgress.inc();
						}, 1000);

						NProgress.done();
						clearInterval(interval);

						$('#paginate').paginate({
							limit : 10,
							initialPage : 0,
							navigationWrapper : $('#navigation'),
							navigationClass : 'pagination pull-right'
						});

					});

	$(document).on('click', '#close-preview', function() {
		$('.image-preview').popover('hide');
		// Hover befor close the preview
		$('.image-preview').hover(function() {
			$('.image-preview').popover('show');
		}, function() {
			$('.image-preview').popover('hide');
		});
	});

	$(function() {
		// Create the close button
		var closebtn = $('<button/>', {
			type : "button",
			text : 'x',
			id : 'close-preview',
			style : 'font-size: initial;',
		});
		closebtn.attr("class", "close pull-right");
		// Set the popover default content
		$('.image-preview').popover({
			trigger : 'manual',
			html : true,
			title : "<strong>Preview</strong>" + $(closebtn)[0].outerHTML,
			content : "There's no image",
			placement : 'bottom'
		});
		// Clear event
		$('.image-preview-clear').click(function() {
			$('.image-preview').attr("data-content", "").popover('hide');
			$('.image-preview-filename').val("");
			$('.image-preview-clear').hide();
			$('.image-preview-input input:file').val("");
			$(".image-preview-input-title").text("Browse");
		});
		// Create the preview image
		$(".image-preview-input input:file").change(
				function() {
					var img = $('<img/>', {
						id : 'dynamic',
						width : 250,
						height : 200
					});
					var file = this.files[0];
					var reader = new FileReader();
					// Set preview image into the popover data-content
					reader.onload = function(e) {
						$(".image-preview-input-title").text("Change");
						$(".image-preview-clear").show();
						$(".image-preview-filename").val(file.name);
						img.attr('src', e.target.result);
						$(".image-preview").attr("data-content",
								$(img)[0].outerHTML).popover("show");
					}
					reader.readAsDataURL(file);
				});
	});

	$(function() {
		if ($('#module') != null) {
			$('#' + $('#module').val()).addClass('in');
			$('#li-' + $('#module').val()).addClass('active');
			$('#' + $('#pageName').val()).show();
			$('#' + $('#pageName').val()).addClass('active');
		}
	});

	function toggleIcon(e) {
		$(e.target).prev('.panel-heading').find(".more-less").toggleClass(
				'glyphicon-plus glyphicon-minus');
	}
	$('.panel-group').on('hidden.bs.collapse', toggleIcon);
	$('.panel-group').on('shown.bs.collapse', toggleIcon);

	var searchQuery = '';

	var addFilter = function(name, value) {
		if (searchQuery.indexOf(name + ":") !== -1) {
			var index = searchQuery.indexOf(name + ":");
			index = index + 1 + name.length;
			searchQuery = [ searchQuery.slice(0, index), value + ",",
					searchQuery.slice(index) ].join('');
		} else {
			if (searchQuery.match("query=$")) {
				searchQuery = searchQuery + name + ":" + value;
			} else {
				searchQuery = searchQuery + "::" + name + ":" + value;
			}
		}
		return searchQuery;
	}

	var addAttribute = function(name, value) {
		if (searchQuery.indexOf(name + ":") !== -1) {
			var index = searchQuery.indexOf(name + ":");
			index = index + 1 + name.length;
			var rem = searchQuery.slice(index);
			if (rem.indexOf("::") !== -1) {
				searchQuery = [ searchQuery.slice(0, index), value,
						rem.slice(rem.indexOf("::")) ].join('');
			} else {
				searchQuery = [ searchQuery.slice(0, index), value ].join('');
			}
		} else {
			if (searchQuery.match("query=$")) {
				searchQuery = searchQuery + name + ":" + value;
			} else {
				searchQuery = searchQuery + "::" + name + ":" + value;
			}
		}
		return searchQuery;
	}

	var addFilterLabel = function(name, value, displayName) {
		var scope = angular.element($("#searchApp")).scope();
		scope.filterLabels.push({
			'name' : displayName,
			'id' : value,
			'value' : name
		});
	}

	var removeFilterLabel = function(value, name) {
		var scope = angular.element($("#searchApp")).scope();

		for (var i = 0; i < scope.filterLabels.length; i++) {
			if (scope.filterLabels[i].id == value) {
				scope.filterLabels.splice(i, 1);
				break;
			}
		}
		$('#' + name + value + '-check').attr('checked', false);
	}

	var removeFilter = function(name, value) {
		if (searchQuery.indexOf(name + ":") !== -1) {
			var index = searchQuery.indexOf(name + ":");
			var pre = searchQuery.slice(0, index);
			var rem = searchQuery.slice(index);
			if (rem.indexOf("::") !== -1) {
				var ind = rem.indexOf("::");
				var suf = rem.slice(ind);
				rem = rem.slice(0, ind);
				if (rem.indexOf(',') == -1) {
					rem = '';
					if (pre.match("::" + "$")) {
						pre = pre.slice(0, -2);
					} else if (suf.match("^" + "::")) {
						suf = suf.slice(2);
					}
				} else {
					if (rem.match(value + "$")) {
						rem = rem.replace("," + value, "");
					} else {
						rem = rem.replace(value + ",", "");
					}
				}
				searchQuery = [ pre, rem, suf ].join('');
			} else {
				searchQuery = removeFilterVal(rem, pre, value);
			}
		}
		return searchQuery;
	}

	var removeFilterVal = function(rem, pre, value) {
		if (rem.indexOf(',') == -1) {
			if (pre.match("::" + "$")) {
				searchQuery = pre.slice(0, -2);
			} else {
				searchQuery = pre;
			}
		} else {
			if (rem.match(value + "$")) {
				rem = rem.replace("," + value, "");
			} else {
				rem = rem.replace(value + ",", "");
			}
			searchQuery = [ pre, rem ].join('');
		}
		return searchQuery;
	}

	var searchApp = angular
			.module('searchApp', [])
			.controller(
					'searchCtrl',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.cardFilters = [];
								$scope.titleFilters = [];
								$scope.merchantFilters = [];
								$scope.cardTypeFilters = [];
								$scope.limit = 12;
								$scope.pageNumber = $('#pageIndex').val();

								$scope.bankFilters = $.parseJSON($('#bankList')
										.val());
								if ($('#cardList').val() != null) {
									$scope.cardFilters = $.parseJSON($(
											'#cardList').val());
								}
								if ($('#merchantList').val() != null) {
									$scope.merchantFilters = $.parseJSON($(
											'#merchantList').val());
								}
								if ($('#cardTypeList').val() != null) {
									$scope.cardTypeFilters = $.parseJSON($(
											'#cardTypeList').val());
								}
								$scope.categoryFilters = $.parseJSON($(
										'#categoryList').val());
								$scope.cardCategoryFilters = $.parseJSON($(
										'#cardCategoryList').val());
								if ($('#titleArray').val() != null) {
									$scope.titleFilters = $.parseJSON($(
											'#titleArray').val());
								}
								$scope.filterLabels = [];
								angular.forEach($scope.bankFilters, function(
										value, index) {
									if (value.isChecked) {
										$scope.filterLabels.push({
											'name' : value.displayName,
											'id' : value.id,
											'value' : value.name
										});
									}
								})
								angular.forEach($scope.cardFilters, function(
										value, index) {
									if (value.isChecked) {
										$scope.filterLabels.push({
											'name' : value.displayName,
											'id' : value.id,
											'value' : value.name
										});
									}
								})
								angular.forEach($scope.merchantFilters,
										function(value, index) {
											if (value.isChecked) {
												$scope.filterLabels.push({
													'name' : value.displayName,
													'id' : value.id,
													'value' : value.name
												});
											}
										})
								angular.forEach($scope.categoryFilters,
										function(value, index) {
											if (value.isChecked) {
												$scope.filterLabels.push({
													'name' : value.displayName,
													'id' : value.id,
													'value' : value.name
												});
											}
										})
								angular.forEach($scope.cardCategoryFilters,
										function(value, index) {
											if (value.isChecked) {
												$scope.filterLabels.push({
													'name' : value.displayName,
													'id' : value.id,
													'value' : value.name
												});
											}
										})
								angular.forEach($scope.cardTypeFilters,
										function(value, index) {
											if (value.isChecked) {
												$scope.filterLabels.push({
													'name' : value.displayName,
													'id' : value.id,
													'value' : value.name
												});
											}
										})
								angular.forEach($scope.titleFilters, function(
										value, index) {
									$scope.filterLabels.push({
										'name' : value.displayName,
										'id' : value.id,
										'value' : value.name
									});
								})
								$scope.init = function(query) {
									if (query != null && query != 'null') {
										searchQuery = query;
									}
									$http.get(searchQuery).then(
											function(response) {
												$scope.results = response.data;
											});
								};
								$scope.sortOptions = [ {
									'name' : 'Sort By'
								}, {
									'name' : 'Popularity'
								}, {
									'name' : 'New'
								}, {
									'name' : 'Bank'
								} ];
								$scope.filter = function(name, value,
										displayName, event) {
									$('.loader-wrap').show();
									searchQuery = addAttribute('pageIndex', 0);
									$scope.pageNumber = 0;
									if (event.target.checked) {
										searchQuery = addFilter(name, value);
										$http
												.get(searchQuery)
												.then(
														function(response) {
															$('.loader-wrap').hide();
															$scope.results = response.data;
															var url = searchQuery
																	.replace(
																			'Ajax',
																			'');
															url = url
																	.replace(
																			/:/g,
																			'%3A');
															window.history
																	.pushState(
																			'page',
																			null,
																			url);
															addFilterLabel(
																	name,
																	value,
																	displayName);
														});
									} else {
										searchQuery = removeFilter(name, value);
										$http
												.get(searchQuery)
												.then(
														function(response) {
															$('.loader-wrap').hide();
															$scope.results = response.data;
															var url = searchQuery
																	.replace(
																			'Ajax',
																			'');
															url = url
																	.replace(
																			/:/g,
																			'%3A');
															window.history
																	.pushState(
																			'page',
																			null,
																			url);
															removeFilterLabel(
																	value, name);
														});
									}
								}

								$scope.removeAll = function() {
									$('.loader-wrap').show();
									searchQuery = addAttribute('pageIndex', 0);
									$scope.pageNumber = 0;
									angular
											.forEach(
													$scope.filterLabels,
													function(value, index) {
														searchQuery = removeFilter(
																value.value,
																value.id);
														$(
																'#'
																		+ value.value
																		+ value.id
																		+ '-check')
																.attr(
																		'checked',
																		false);
													})
									$http.get(searchQuery).then(
											function(response) {
												$('.loader-wrap').hide();
												$scope.results = response.data;
												var url = searchQuery.replace(
														'Ajax', '');
												url = url.replace(/:/g, '%3A');
												window.history.pushState(
														'page', null, url);
												$scope.filterLabels = [];
											});
								}

								$scope.sort = function() {
									$('.loader-wrap').show();
									searchQuery = addAttribute('pageIndex', 0);
									$scope.pageNumber = 0;
									if ($scope.item.name == 'Bank') {
										searchQuery = addAttribute('order',
												'asc');
										searchQuery = addAttribute('orderBy',
												'bank.name');
									} else if ($scope.item.name == 'New') {
										searchQuery = addAttribute('order',
												'desc');
										searchQuery = addAttribute('orderBy',
												'lastModifiedDate');
									}
									$http.get(searchQuery).then(
											function(response) {
												$('.loader-wrap').hide();
												$scope.results = response.data;
												var url = searchQuery.replace(
														'Ajax', '');
												url = url.replace(/:/g, '%3A');
												window.history.pushState(
														'page', null, url);
											});
								}

								$scope.paginate = function(pageIndex) {
									if (pageIndex < 0
											|| pageIndex > ($scope.results.count / $scope.limit)) {
										return;
									}
									$scope.pageNumber = pageIndex;
									searchQuery = addAttribute('pageIndex',
											pageIndex);
									searchQuery = addAttribute('limit',
											$scope.limit);
									$http.get(searchQuery).then(
											function(response) {
												$scope.results = response.data;
												var url = searchQuery.replace(
														'Ajax', '');
												url = url.replace(/:/g, '%3A');
												window.history.pushState(
														'page', null, url);
											});
								}
							} ]);

	searchApp.filter('range', function() {
		return function(input, total) {
			total = parseInt(total);

			for (var i = 0; i < total; i++) {
				input.push(i);
			}
			return input;
		};
	});

	var navBar = angular
			.module('navBarApp', [])
			.controller(
					'navBarController',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.init = function() {
									$http
											.get(
													contextPath
															+ "/getNavElements")
											.then(
													function(response) {
														$scope.navElements = response.data;
														var stores;
														var storeHTML = '';
														var banks;
														var bankHTML = '';
														var cards;
														var cardHTML = '';
														var categoryHTML = '';
														var categories;
														angular
																.forEach(
																		$scope.navElements,
																		function(
																				value,
																				index) {
																			if (index == 'merchant') {
																				stores = value;
																			} else if (index == 'category') {
																				categories = value;
																			} else if (index == 'card') {
																				cards = value;
																			} else if (index == 'bank') {
																				banks = value;
																			}
																		})
														for (var i = 0; i < stores.length; i++) {
															storeHTML = storeHTML
																	+ '<p><a href="'
																	+ contextPath
																	+ '/searchDeals?query='
																	+ stores[i].name
																	+ '%3A'
																	+ stores[i].id
																	+ '">'
																	+ stores[i].displayName
																	+ '</a>'
																	+ '</p>';
															if (i == 4) {
																break;
															}
														}
														for (var i = 0; i < categories.length; i++) {
															categoryHTML = categoryHTML
																	+ '<p><a href="'
																	+ contextPath
																	+ '/searchDeals?query='
																	+ categories[i].name
																	+ '%3A'
																	+ categories[i].id
																	+ '">'
																	+ categories[i].displayName
																	+ '</a>'
																	+ '</p>';
															if (i == 4) {
																break;
															}
														}
														for (var i = 0; i < banks.length; i++) {
															bankHTML = bankHTML
																	+ '<p><a href="'
																	+ contextPath
																	+ '/searchDeals?query='
																	+ banks[i].name
																	+ '%3A'
																	+ banks[i].id
																	+ '">'
																	+ banks[i].displayName
																	+ '</a>'
																	+ '</p>';
															if (i == 4) {
																break;
															}
														}
														for (var i = 0; i < cards.length; i++) {
															cardHTML = cardHTML
																	+ '<p><a href="'
																	+ contextPath
																	+ '/searchDeals?query='
																	+ cards[i].name
																	+ '%3A'
																	+ cards[i].id
																	+ '">'
																	+ cards[i].displayName
																	+ '</a>'
																	+ '</p>';
															if (i == 4) {
																break;
															}
														}
														$(".footer-store")
																.html(storeHTML);
														$(".footer-category")
																.html(
																		categoryHTML);
														$(".footer-card").html(
																cardHTML);
														$(".footer-bank").html(
																bankHTML);
													});
								};

								var cardSearchQuery = contextPath
										+ "/searchCardsAjax?query=limit%3A3%3A%3AorderBy%3Arank%3A%3Aorder%3Aasc";
								var dealSearchQuery = contextPath
										+ "/searchDealsAjax?query=limit%3A3%3A%3AorderBy%3Arank%3A%3Aorder%3Aasc";
								$http.get(cardSearchQuery).then(
										function(response) {
											$scope.cards = response.data;
										});
								$http.get(dealSearchQuery).then(
										function(response) {
											$scope.deals = response.data;
										});
							} ]);

	var homePage = angular
			.module('homePageApp', [])
			.controller(
					'homePageController',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.init = function() {
									var cardSearchQuery = contextPath
											+ "/searchCardsAjax?query=limit%3A8%3A%3AorderBy%3Arank%3A%3Aorder%3Aasc";
									var dealSearchQuery = contextPath
											+ "/searchDealsAjax?query=limit%3A8%3A%3AorderBy%3Arank%3A%3Aorder%3Aasc";
									$http.get(cardSearchQuery).then(
											function(response) {
												$scope.cards = response.data;
											});
									$http.get(dealSearchQuery).then(
											function(response) {
												$scope.deals = response.data;
											});
								};
							} ]);

	$('#homePage').ready(function() {
		angular.bootstrap($('#homePage'), [ 'homePageApp' ]);
	});

	$('#searchApp').ready(function() {
		angular.bootstrap($('#searchApp'), [ 'searchApp' ]);
	});

})(jQuery); // End of use strict
