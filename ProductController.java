package com.cybage.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cybage.dao.OrderDaoImpl;
import com.cybage.dao.ProductDaoImpl;
import com.cybage.dao.UserDaoImpl;
import com.cybage.model.OrderDetail;
import com.cybage.model.ProductDetail;
import com.cybage.model.UserDetail;

@Controller
public class ProductController {
	
	
	@RequestMapping(value="/addproduct",method = RequestMethod.GET)
	public ModelAndView addProduct(@RequestParam("prod") int productId, String productname,BigDecimal price, ModelMap map,ProductDetail product,UserDetail user)
	{   
		
		
		System.out.println("in buy product "+productId);
		OrderDetail order=new OrderDetail();
		product.setProductId(productId);
		user.setUserId(1);
		order.setProductDetail(product);
		order.setUserDetail(user);
		
		
		product.setProductname(productname);
		order.setProductDetail(product);
		order.setUserDetail(user);
		
		product.setPrice(price);
		order.setProductDetail(product);
		order.setUserDetail(user);
		
		/*product.setQuantity(quantity);
		order.setProductDetail(product);
		order.setUserDetail(user); 
		
	/*order.setDeliveryAdd("dj");
		order.setPayment(0.0000);
		order.setDate( new Date());
		order.setQuantity(10);
		*/
	    new ProductDaoImpl().addOrder(order);
	    List<ProductDetail> products=new ProductDaoImpl().listProduct();
		map.addAttribute("selectedproduct", products);
		return new ModelAndView("orderconfirm", "command", new ProductDetail());
	}
	
	@RequestMapping(value="/checkQuantity",method = RequestMethod.POST)
	public ModelAndView checkQuantity(@ModelAttribute("selectproduct") ProductDetail quantity)
	{
		System.out.println("hello "+ quantity.getQuantity());
		new ProductDaoImpl().updateQuantity(quantity);
		return new ModelAndView("thankupage");
	}
}
