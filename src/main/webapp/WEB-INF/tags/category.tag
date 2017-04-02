<%@ attribute name="list" required="true" rtexprvalue="true"
	type="java.util.Collection"%>
<%@ attribute name="childCategoryName" required="true"
	rtexprvalue="true"%>
<%@ attribute name="childCategoryId" required="true" rtexprvalue="true"%>
<%@ attribute name="level" required="true" rtexprvalue="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="level" value="${level + 1}" scope="page" />
<c:if test="${!empty list}">
	<c:choose>
		<c:when test="${childCategoryName eq 'menu-content'}">
			<c:set var="cssClass">menu-content collapse out tree</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="cssClass">sub-menu collapse</c:set>
		</c:otherwise>
	</c:choose>
	<ul id="${fn:replace(childCategoryName,' ', '')}"
		class="${cssClass} ul-${childCategoryId}">
		<c:forEach var="childCategory" items="${list}">
			<c:choose>
				<c:when test="${!empty childCategory.categories}">
					<li data-toggle="collapse"
						data-target="#${fn:replace(childCategory.name,' ', '')}"
						class="collapsed" id="li-${childCategory.id}"
						style="padding-left:${level*25}px"><a
						href="<c:url value="/admin/listChildCategories/${childCategory.id}" />">
							${childCategory.name} <span class="arrow"></span>
					</a></li>
				</c:when>
				<c:otherwise>
					<li data-toggle="collapse" data-target="#${childCategory.name}"
						class="collapsed" id="li-${childCategory.name}"
						style="padding-left:${level*25}px"><a
						href="<c:url value="/admin/listChildCategories/${childCategory.id}" />">
							${childCategory.name} </a></li>
				</c:otherwise>
			</c:choose>
			<category:category list="${childCategory.categories}"
				childCategoryName="${childCategory.name}" level="${level}"
				childCategoryId="${childCategory.id}" />
		</c:forEach>
	</ul>
</c:if>
