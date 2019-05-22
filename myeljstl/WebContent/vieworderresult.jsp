<%@page import="com.kitri.dto.Product"%>
<%@page import="com.kitri.dto.OrderLine"%>
<%@page import="java.util.Date"%>
<%@page import="com.kitri.dto.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- c를 사용하려고 선언?  -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="vieworder">
	<h4 style = "text-align: center" >주문내역보기</h4><br>
	<table style="width:80%; margin: 0 auto; ">
		<tr>
			<th>주문번호</th>
			<th>주문일자</th>
			<th>주문상품번호</th>
			<th>상품명</th>
			<th>가격</th>
			<th>주문수량</th>
		</tr>
		
<%-- List<OrderInfo> list = (List)request.getAttribute("orderlist");
   for(OrderInfo info: list){
--%> 

<c:forEach var="info" items="${requestScope.orderlist}"><tr>
   	   
<%--	 int order_no = info.getOrder_no();//주문번호
	 Date order_dt = info.getOrder_dt();//주문일자
	 List<OrderLine> lines = info.getLines();
--%>
	
	<c:set var="order_no" value="${info.order_no}"/>
	<c:set var="order_dt" value=""${info.order_dt}"/>
	<c:set var="lines" value="${info.lines}"/>
	<c:set var="lineSize" value="${fn:length(lines)}"/>   
		<td rowspan="${lineSize }">${order_no}</td>
     	<td rowspan="${lineSize }">${order_dt}</td>
     
<%--	 
	 //for(OrderLine line: lines){
	for(int i=0; i<lines.size(); i++){
		OrderLine line = lines.get(i);
		Product p = line.getProduct();
		String prod_no = p.getProd_no();
		String prod_name = p.getProd_name();
		int prod_price = p.getProd_price();
		int order_quantity = line.getOrder_quantity();
--%>    
	
	<c:forEach var="line" items="${lines}" varStatus="obj">
		<c:set var="p" value="${line.product }"/>
		<c:if test="${obj.index>0 }">
		</tr><tr>
		</c:if>
		
       <%--=i>0?"</tr><tr>":""--%>
       <td>${p.prod_no}</td><td>%{p.prod_name}</td><td>${p.prod_price}</td><td>${line.order_quantity}</td>
       
       
     </c:forEach>	
   </tr> 
</c:forEach>
 </table>
</div>    
       
       
  <%--      
	 }//end line
</c:forEach>	
   </tr> 
}//end info   
 
 </table>
</div> --%>


		
<%-- <% List<OrderInfo> list = (List) request.getAttribute("orderlist");
	for (OrderInfo info : list) {
%> <tr>
<%		int order_no = info.getOrder_no(); //주문번호
		Date order_dt = info.getOrder_dt(); //주문일자
%>		<td><%=order_no %></td><td><%=order_dt%></td>
<%
		List<OrderLine> lines = info.getLines();
		for (OrderLine line : lines) {
			Product p = line.getProduct();
			String prod_no = p.getProd_no();
			String prod_name = p.getProd_name();
			int prod_price = p.getProd_price();
			int order_quantity = line.getOrder_quantity();
		} //end line
%>	</tr>

<% } //end info
%>
	</table>
</div> --%>