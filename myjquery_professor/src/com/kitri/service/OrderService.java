package com.kitri.service;

import com.kitri.dao.OrderDAO;
import com.kitri.dto.OrderInfo;
import com.kitri.exception.AddException;

public class OrderService {
	private OrderDAO dao;
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public void addOrder(OrderInfo info) throws AddException {
		dao.insert(info);
	}
}
