<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
Product product = (Product)request.getAttribute("product");
%>

<%= product.getProd_detail()%>