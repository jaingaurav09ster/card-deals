<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<jsp:include page="manageCardNav.jsp" />
	<div class="col-sm-8 col-md-8 render-content">
		<h4>
			Hi
			<sec:authentication property="principal.firstName" />
			, Welcome to the Portal
		</h4>

		<h4>Manage Cards</h4>
		<p>Manage Cards provide a interface to manage the cards owned by
			you.</p>
		<ul>
			<li>List of all existing cards can be accessed</li>
		</ul>
		<ul>
			<li>A new card can be created</li>
		</ul>
		<ul>
			<li>Existing card can be updated</li>
		</ul>
		<ul>
			<li>Card properties can be added and updated. Card deals, fees, features, ratings etc. can be managed from here</li>
		</ul>
	</div>
</div>