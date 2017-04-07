<%@ attribute name="list" required="true" rtexprvalue="true"
	type="java.util.Collection"%>
<%@ attribute name="selectedCategories" required="true"
	rtexprvalue="true" type="java.util.Collection"%>
<%@ attribute name="level" required="true" rtexprvalue="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="level" value="${level + 1}" scope="page" />
<c:if test="${!empty list}">
	<c:forEach var="childCategory" items="${list}">
		<c:set var="isSelected" value="" scope="page" />
		<c:forEach var="selectedCategory" items="${selectedCategories}">
			<c:if test="${childCategory.id eq selectedCategory.id}">
				<c:set var="isSelected" value="selected" scope="page" />
			</c:if>
		</c:forEach>
		<option value="${childCategory.id}" style="padding-left:${level*15}px"
			${isSelected}>${childCategory.name}</option>
		<category:selectCategory list="${childCategory.categories}"
			level="${level}" selectedCategories="${selectedCategories}" />
	</c:forEach>
</c:if>
