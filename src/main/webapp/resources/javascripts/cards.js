(function($) {
	"use strict"; // Start of use strict

	// jQuery for page scrolling feature - requires jQuery Easing plugin
	$('.page-scroll a').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop : ($($anchor.attr('href')).offset().top - 50)
		}, 1250, 'easeInOutExpo');
		event.preventDefault();
	});

	// Highlight the top nav as scrolling occurs
	$('body').scrollspy({
		target : '.navbar-fixed-top',
		offset : 51
	});

	// Offset for Main Navigation
	$('#mainNav').affix({
		offset : {
			top : 100
		}
	})

	// Floating label headings for the contact form
	$(function() {
		$("body").on(
				"input propertychange",
				".floating-label-form-group",
				function(e) {
					$(this).toggleClass("floating-label-form-group-with-value",
							!!$(e.target).val());
				}).on("focus", ".floating-label-form-group", function() {
			$(this).addClass("floating-label-form-group-with-focus");
		}).on("blur", ".floating-label-form-group", function() {
			$(this).removeClass("floating-label-form-group-with-focus");
		});
	});

	var cards = new Bloodhound({
		datumTokenizer : Bloodhound.tokenizers.whitespace,
		queryTokenizer : Bloodhound.tokenizers.whitespace,
		remote : {
			url : 'searchCards/%QUERY',
			wildcard : '%QUERY'
		}
	});
	$('.typeahead').typeahead(null, {
		name : 'cards',
		display : 'cardTitle',
		source : cards,
		limit : 10
	});

	$('#characterLeft').text('140 characters left');
	$('#message').keydown(function() {
		var max = 140;
		var len = $(this).val().length;
		if (len >= max) {
			$('#characterLeft').text('You have reached the limit');
			$('#characterLeft').addClass('red');
			$('#btnSubmit').addClass('disabled');
		} else {
			var ch = max - len;
			$('#characterLeft').text(ch + ' characters left');
			$('#btnSubmit').removeClass('disabled');
			$('#characterLeft').removeClass('red');
		}
	});

	$('#registrationForm, #editProfileForm', '#forgotPasswordForm')
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
										message : 'Please supply your first name'
									}
								}
							},
							lastName : {
								validators : {
									stringLength : {
										min : 2,
									},
									notEmpty : {
										message : 'Please supply your last name'
									}
								}
							},
							email : {
								validators : {
									notEmpty : {
										message : 'Please supply your email address'
									},
									emailAddress : {
										message : 'Please supply a valid email address'
									}
								}
							},
							password : {
								validators : {
									notEmpty : {
										message : 'Please supply your email address'
									},
									stringLength : {
										min : 4,
										message : 'Password should be atleast 4 digits'
									}
								}
							},
							mobile : {
								validators : {
									notEmpty : {
										message : 'Please supply your phone number'
									},
									phone : {
										country : 'US',
										message : 'Please supply a vaild phone number with area code'
									}
								}
							}
						}
					}).on('submit', function(e) {
				
			});

	NProgress.configure({
		showSpinner : false
	});
	NProgress.start();
	NProgress.set(0.4);
	// Increment
	var interval = setInterval(function() {
		NProgress.inc();
	}, 1000);
	$(document).ready(function() {
		NProgress.done();
		clearInterval(interval);
	});
})(jQuery); // End of use strict
