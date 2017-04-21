<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-9 col-md-9 main-content">
	<h3>Admin Console</h3>
	<hr>
	<div class="panel panel-default">
		<div class="panel-body console-content">
			<h4>
				Hi
				<sec:authentication property="principal.firstName" />
				, Welcome to the administration console
			</h4>
			<p>Here you can manage following items</p>

			<h4>Manage Users</h4>
			<p>Manage Users provide a interface to create new users and
				manage existing users. List of all existing users can be accessed
				from here. And the user can be deleted and disabled as well.</p>

			<h4>Manage Cards</h4>
			<p>Manage Cards provide a interface to create new cards and
				manage existing cards. List of all existing cards can be accessed
				from here. Properties specific to card can be added here.</p>

			<h4>Manage Master Data</h4>
			<p>Master data can be managed from here. The data can be created
				on fly and can be used for the various functionalities</p>

		</div>
	</div>
</div>