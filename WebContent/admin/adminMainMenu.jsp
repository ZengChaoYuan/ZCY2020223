<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminMainMenu.css">
</head>
<body>

	<div class="menu">

		<ul>
			<c:forEach items="${sessionScope.menuList }" var="menu">
				<c:if test="${menu.menuPId==0 }">
					<li>${menu.menuName }</li>

					<li>
						<ol>
							<c:forEach items="${sessionScope.menuList }" var="childMenu">
								<c:if test="${childMenu.menuPId==menu.menuId }">
									<li><a
										href="${pageContext.request.contextPath}/${childMenu.menuUrl}"
										target="adminMainContent"> ${childMenu.menuName} </a></li>
								</c:if>
							</c:forEach>
						</ol>
					</li>
				</c:if>
			</c:forEach>
		</ul>

	</div>

</body>
</html>