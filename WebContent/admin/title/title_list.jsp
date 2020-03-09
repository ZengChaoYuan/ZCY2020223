<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
				<c:when test="${not empty requestScope.title }">
				<c:forEach items="${requestScope.page.records }" var="customer">
				   <tr>
				     <td>${customer.customerName }</td>
				     <td>${customer.sex==1?"男":"女" }</td>
				     <td>${customer.age }</td>
				     <td>${customer.tel }</td>
				     <td>${customer.useStatus==1?"启用":"禁用" }</td>
				     <td>
				      <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=updateUseStatus&customerId=${customer.customerId}">${customer.useStatus==1?"禁用":"启用" }</a>
				      
				    <!--  <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=updateDeleteStatus&customerId=${customer.customerId}">${customer.deleteStatus==1?"删除":"已删除" }</a>
				     -->
				    <!--  <a   customerId='${ customer.customerId}' onclick="deleteStatus(${customer.customerId})">${customer.deleteStatus==1?"删除":"已删除" }</a> -->
				     <a  onclick="deleteStatus(${customer.customerId})">${customer.deleteStatus==1?"删除":"已删除" }</a>
				     <a	href="${pageContext.request.contextPath}/CustomerServlet?customerAction=resetPassword&customerId=${customer.customerId}">重置密码</a>		
				     						
					</td>
				   </tr>
				</c:forEach>
				<tr>
								<td colspan="6" align="center">
									共&nbsp;${page.totalRecordsNum}&nbsp;条记录,共&nbsp;${page.totalPageNum}&nbsp;页,
									当前第&nbsp;${page.currentPageNum}&nbsp;页
									<a href="javascript:void(0)" onclick="pageMethod(1)">首页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.prevPageNum})">上一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.nextPageNum})">下一页</a> <a
									href="javascript:void(0)"
									onclick="pageMethod(${page.totalPageNum})">尾页</a>
								</td>
							</tr>
				</c:when>
				<c:otherwise>
							<tr>
								<td colspan="6">用户列表查无数据</td>
							</tr>
						</c:otherwise>
				</c:choose>	
</body>
</html>