(function($) {
	"use strict"; // Start of use strict

	$(document)
			.ready(
					function() {
						var cards = new Bloodhound({
							datumTokenizer : Bloodhound.tokenizers.whitespace,
							queryTokenizer : Bloodhound.tokenizers.whitespace,
							remote : {
								url : 'searchCards/%QUERY',
								wildcard : '%QUERY'
							}
						});
						$('.typeahead')
								.typeahead(
										null,
										{
											name : 'cards',
											display : 'title',
											source : cards,
											limit : 6,
											templates : {
												suggestion : Handlebars
														.compile('<div class="row typeahead-row"><div class="col-md-2"><img src="/deals/resources/upload/card/{{imagePath}}">'
																+ '</div><div class="col-md-10"><div class="row"><strong>{{title}}</strong></div><div class="row">{{description}}</div></div></div>')
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
												lastName : {
													validators : {
														stringLength : {
															min : 2,
														},
														notEmpty : {
															message : 'Please enter last name'
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
												},
												mobile : {
													validators : {
														notEmpty : {
															message : 'Please enter phone number'
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

						$('#bankForm').bootstrapValidator({

							fields : {
								name : {
									validators : {
										notEmpty : {
											message : 'Please enter bank name'
										}
									}
								}
							}
						}).on('submit', function(e) {
							alert('asd');
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

})(jQuery); // End of use strict
