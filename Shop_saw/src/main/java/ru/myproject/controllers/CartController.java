package ru.myproject.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.myproject.entity.product.Saw;
import ru.myproject.entity.user.Authority;
import ru.myproject.entity.user.Cart;
import ru.myproject.entity.user.User;

@Controller
public class CartController {


	User user = new User();

	Set<String> type = new HashSet<String>();
	Set<String> brend = new HashSet<String>();
	Set<String> manufacturer = new HashSet<String>();
	Set<String> location = new HashSet<String>();
	Set<String> state = new HashSet<String>();
	Set<String> year = new HashSet<String>();
	ArrayList<Saw> saw_list = new ArrayList<Saw>();
	
	@SuppressWarnings("rawtypes")
	ArrayList<Set> sort_list = new ArrayList<Set>();
	
	
	private void updateCart(HttpServletRequest request){
		ArrayList<String> quanti = new ArrayList<String>();//список с количеством каждого товара в корзине
		HttpSession se=request.getSession(true);
		SessionFactory sessionFactory = ru.myproject.hibernate.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		ArrayList<Saw> saw_cart = new ArrayList<>();//список товаров в корзине
		ArrayList<Cart> orderCart=(ArrayList<Cart>)se.getAttribute("cart_list");
		
		quanti.clear();
		
		
		if(orderCart!=null){
			for (Cart item : orderCart) {
				Query sawQuery = session.createQuery("from Saw  where id = :SAWID");
				String cartId = item.getSawID();
				sawQuery.setParameter("SAWID",cartId );
				List currentSaw = sawQuery.list();
				Saw saw = (Saw) currentSaw.get(0);
				saw_cart.add(saw);
				Integer currentQuantity=item.getQuantity();
				quanti.add(currentQuantity.toString());
				}
		}
		
		int inCart=0;
		int i=0;
		int totalPrice=0;
		for(String s:quanti){
			inCart+=Integer.parseInt(s);
			totalPrice+=Integer.parseInt(s)*saw_cart.get(i).getPrice();
			i++;
		}		
		se.setAttribute("inCart", inCart);
		se.setAttribute("totalPrice", totalPrice);
	}
	

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(Model model, @ModelAttribute("sawToCart") Saw saw, HttpServletRequest request) {
		ArrayList<Saw> saw_list = new ArrayList<Saw>();//список товаров в каталоге
		ArrayList<Saw> saw_cart = new ArrayList<Saw>();//список товаров в корзине
		Set<String> model_saw = new HashSet<String>();//список для фильтра
		Set<String> brend = new HashSet<String>();//список для фильтра
		Set<String> manufacturer = new HashSet<String>();//список для фильтра
		Set<String> location = new HashSet<String>();//список для фильтра
		Set<String> state = new HashSet<String>();//список для фильтра
		Set<String> year = new HashSet<String>();//список для фильтра
		ArrayList<Cart> cart_list = new ArrayList<Cart>();
		Cart curentCart = new Cart();
		Saw saw_current = new Saw();
		String thingQ;
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		Transaction TR = session.beginTransaction();
		saw_current.setId((String) request.getParameter("pg"));
		if(request.getParameter("quantity")==null){
			thingQ="1";
		}else{
			thingQ = request.getParameter("quantity");
		}
		
		Criteria criteria = session.createCriteria(Saw.class);
		try {

			saw_list = (ArrayList<Saw>) criteria.list();
		} finally {
		}
		Query query = session.createQuery("from Saw where id = :ID");
		query.setParameter("ID", saw_current.getId());
		List<?> list = query.list();		
		Saw sawToCart = (Saw) list.get(0);
		curentCart.setSawID(sawToCart.getId());
		curentCart.setStatus("cart");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		curentCart.setDate(dateFormat.format(date));
		HttpSession sessionsa = request.getSession(true);
		cart_list=(ArrayList<Cart>) sessionsa.getAttribute("cart_list");
		saw_cart=(ArrayList<Saw>) sessionsa.getAttribute("saw_cart");
		if(cart_list==null){
			cart_list = new ArrayList<Cart>();
		}
		cart_list.add(curentCart);
		model.addAttribute("saw", saw_list);
		if(saw_cart==null){
			saw_cart = new ArrayList<Saw>();
		}
		saw_cart.add(sawToCart);
		curentCart.setQuantity(Integer.parseInt(thingQ));
		try {

			saw_list = (ArrayList<Saw>) criteria.list();
			sessionsa.setAttribute("saw", saw_list);
		} finally {
		}

		model_saw.clear();
		brend.clear();
		location.clear();
		manufacturer.clear();
		state.clear();
		year.clear();

		for (Saw sawTemp : saw_list) {
			model_saw.add(sawTemp.getModel());
			brend.add(sawTemp.getBrand());
			location.add(sawTemp.getLocation());
			manufacturer.add(sawTemp.getManufacturer());
			state.add(sawTemp.getState());
			year.add(sawTemp.getYear());
		}
		sort_list.add(model_saw);
		sort_list.add(brend);
		sort_list.add(location);
		sort_list.add(manufacturer);
		sort_list.add(state);
		sort_list.add(year);
		sessionsa.setAttribute("cart_list", cart_list);
		sessionsa.setAttribute("saw_cart", saw_cart);
		sessionsa.setAttribute("sortList", sort_list);//лист элементов для боковой панели
		 updateCart(request);
		
		return "clientCatalog";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String toCart(Model model, HttpServletRequest request) {
		ArrayList<String> quanti = new ArrayList<String>();//список с количеством каждого товара в корзине
		SessionFactory sessionFactory = ru.myproject.hibernate.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
				HttpSession sessionsa = request.getSession(true);
				ArrayList<Cart> cart_list_user = (ArrayList<Cart>)sessionsa.getAttribute("cart_list");
				ArrayList<Saw> saw_cart =(ArrayList<Saw>)sessionsa.getAttribute("saw_cart");//список товаров в корзине
				quanti.clear();
				saw_cart.clear();
				if(cart_list_user!=null){
					for (Cart item : cart_list_user) {
						Query sawQuery = session.createQuery("from Saw  where id = :SAWID");
						String cartId = item.getSawID();
						sawQuery.setParameter("SAWID",cartId );
						List currentSaw = sawQuery.list();
						Saw saw = (Saw) currentSaw.get(0);
						saw_cart.add(saw);
						Integer currentQuantity=item.getQuantity();
						quanti.add(currentQuantity.toString());
					}
					updateCart(request);
				}
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName();
				sessionsa.setAttribute("sessionName", name);
		sessionsa.setAttribute("sawCart", saw_cart);
		sessionsa.setAttribute("quantity", quanti);
		
		return "cart";
	}
	
	
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(
			ModelMap model, HttpServletRequest request/* , @ModelAttribute("sawToEdit") User user */) {

		ArrayList<Cart> cart_list = new ArrayList<Cart>();
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		String buttonFull = request.getParameter("button");
		HttpSession sessionsa = request.getSession(true);
		
		if (request.getParameter("button")!=null){//редактирование товаров в корзине
			String button = buttonFull.substring(0, buttonFull.length()-1);
			String sign = buttonFull.substring(buttonFull.length()-1,buttonFull.length());
			
			cart_list = (ArrayList<Cart>)sessionsa.getAttribute("cart_list");
			int num;
			Cart cartToEdit;
			for(num=0;num<cart_list.size();num++){
				cartToEdit=cart_list.get(num);
				if(cartToEdit.getSawID().equals(button)){
					switch(sign){
					case "-":
						if(cartToEdit.getQuantity()<2){
							cart_list.remove(num);
						}else{
							cartToEdit.setQuantity(cartToEdit.getQuantity()-1);
							cart_list.remove(num);
							cart_list.add(num, cartToEdit);
						}
						
						break;
					case "+":
						cartToEdit.setQuantity(cartToEdit.getQuantity()+1);
						cart_list.remove(num);
						cart_list.add(num, cartToEdit);
						break;
					case "x":
						cart_list.remove(num);
						break;
					}
					
					sessionsa.setAttribute("cart_list", cart_list);
				}
			}
			toCart((Model) model, request);
			return  "cart";
		}else{//заказ товаров
			HttpSession se=request.getSession(true);
			ArrayList<Cart> orderCart=(ArrayList<Cart>)se.getAttribute("cart_list");
		for(int i=0;i<orderCart.size();i++){
			session = SF.openSession();
			Transaction TR = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Cart itemInCart=orderCart.get(i);
			Cart cartAdd=new Cart();
			
			
			String name = request.getUserPrincipal().getName();

			Query queryUsername = session.createQuery("from User where username = :USERNAME");
			queryUsername.setParameter("USERNAME", name);
			List<?> userList = queryUsername.list();
			User userCart = (User) userList.get(0);
			
			cartAdd.setQuantity(itemInCart.getQuantity());
			cartAdd.setSawID(itemInCart.getSawID());
			cartAdd.setUserID(userCart.getId());
			cartAdd.setStatus("order");
			cartAdd.setDate(dateFormat.format(date));
			
			con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SF = con.buildSessionFactory();
			session = SF.openSession();
			TR = session.beginTransaction();
			session.save(cartAdd);
			TR.commit();
		}


		cart_list.clear();
		sessionsa.setAttribute("cart_list", cart_list);
		return "index";}
	}
	
	
}
