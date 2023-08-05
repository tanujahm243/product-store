package com.js.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.ProductCRUD;
import com.js.dto.Product;
@WebServlet("/update")
public class UpdateStage2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product p=new Product();
		int id=Integer.parseInt(req.getParameter("id"));
		p.setId(id);
		String name=req.getParameter("name");
		p.setName(name);;
		String brand=req.getParameter("brand");
		p.setBrand(brand);
		double price=Double.parseDouble(req.getParameter("price"));
		p.setPrice(price);
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		p.setQuantity(quantity);
		
		ProductCRUD pc=new ProductCRUD();
		int res=pc.updateProduct(p.getId(),p);
		
		if (res>0) {
			RequestDispatcher rd=req.getRequestDispatcher("view");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			req.setAttribute("msg", "Failed To Update Product");
			rd.forward(req, resp);
		}
	}

	}


