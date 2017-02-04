<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
	<div class="container" style="text-align: left">
		<div class="row col-md-10 col-md-offset-1 custyle">
			<table class="table table-striped custab">
				<thead>
					<a href="/deals/admin/card/showAddForm"
						class="btn btn-primary btn-xs pull-right"><b>+</b> Add new
						Card</a>
					<tr>
						<th>ID</th>
						<th>Card Title</th>
						<th>Card Description</th>
						<th>Bank Name</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<c:forEach items="${cards}" var="card">
					<tr>
						<td>${card.cardId}</td>
						<td>${card.cardTitle}</td>
						<td>${card.cardDesc}</td>
						<td>${card.bankName}</td>
						<td class="text-center"><a class='btn btn-info btn-xs'
							href="/deals/admin/card/showUpdateForm/${card.cardId}"><span
								class="glyphicon glyphicon-edit"></span> Edit</a> <a
							href="/deals/admin/card/deleteCard/${card.cardId}"
							class="btn btn-danger btn-xs"><span
								class="glyphicon glyphicon-remove"></span> Del</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</header>