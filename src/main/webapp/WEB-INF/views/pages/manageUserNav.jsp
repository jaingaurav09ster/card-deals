<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<input type="hidden" value="${module}" id="module">
<input type="hidden" value="${pageName}" id="pageName">

<div class="col-sm-4 col-md-4 sidebar">
	<div class="nav-side-menu">
		<div class="brand">User Manager</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
			<ul id="menu-content" class="menu-content collapse out">
				<li data-toggle="collapse" data-target="#userManager"
					class="collapsed" id="li-userManager"><a href="#"><i
						class="fa fa-user fa-lg"></i> User Manager <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="userManager">
					<li id="updateUserForm" style="display: none;"><a href="#">Update
							User</a></li>
					<li id="userForm"><a href="<c:url value="/admin/newUser" />">New
							User</a></li>
					<li id="userList"><a href="<c:url value="/admin/listUsers" />">User
							List</a></li>
				</ul>
			</ul>
		</div>
	</div>
</div>