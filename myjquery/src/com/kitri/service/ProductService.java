package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {
	private ProductDAO dao;
	
	public ProductService() {//서비스에 DAO를  써야하니까 
		dao = new ProductDAO();
	}
	

	public List<Product> findAll(){
	return dao.selectAll();
	}

	public Product findByNo(String prod_no) {
		System.out.println("service 들어옴");
		Product p = dao.selectByNo(prod_no);
		System.out.println(p);
		return p;
	}


}
