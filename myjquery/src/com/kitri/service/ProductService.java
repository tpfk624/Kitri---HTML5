package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {
	private ProductDAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	

	public List<Product>findAll(){
		return dao.selectAll();
	}
	
	public String findByNo() {
		return dao.sele;
		
	}
}
