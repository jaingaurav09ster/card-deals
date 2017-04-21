<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade" id="confirm">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Confirmation</h4>
			</div>
			<div class="modal-body">
				<p class="smallFont">Are you sure you want to delete?</p>
				<div class="row">
					<div class="col-12-xs text-center">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Delete</button>
						<button type="button" data-dismiss="modal" class="btn">Cancel</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<script>
	$('table.table').on('click', 'button[name="remove_levels"]',function(e) {
		var $form = $(this).closest('form');
		var $anchor = $(this);
		e.preventDefault();
		$('#confirm').modal({
			backdrop : 'static',
			keyboard : false
		}).one('click', '#delete', function(e) {
			$form.trigger('submit');
		});
	});
</script>