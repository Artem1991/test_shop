package ru.myproject.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ru.myproject.entity.product.Saw;
import ru.myproject.entity.user.*;
import ru.myproject.hibernate.HibernateUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CatalogController {
	
	int page_number=1;
	String listStyle="list"; 
	@SuppressWarnings("rawtypes")
	ArrayList<Set> sort_list = new ArrayList<Set>();//  общий список дл€ фильтра

	private final String ERROR_MESSAGE = "Ќеправильные им€/пароль";
	private final String DUPLICATE_ERROR_MESSAGE = "“акой пользователь уже существует";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
	//	inCart=updateCart();
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", ERROR_MESSAGE);
		return "login";
	}
	
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public String delivery(ModelMap model) {
		return "delivery";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(ModelMap model) {
		return "payment";
	}
	
	@RequestMapping(value = "/toRegistrationForm", method = RequestMethod.GET)
	public String toRegistrationForm(ModelMap model) {
		try {

			model.addAttribute("newUser", new User());
			model.addAttribute("newAuthority", new Authority());
		} finally {
		}

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(ModelMap model, @ModelAttribute("sawToEdit") User user){
		Configuration con = new Configuration();
		Authority authority = new Authority();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		Transaction TR = session.beginTransaction();
		authority.setAuthority("ROLE_USER");
		authority.setusername(user.getUsername());
		user.setEnabled(1);

		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.max("id"));
		Integer maxId = (Integer) criteria.uniqueResult();

		user.setId(maxId + 1);
		authority.setId(maxId + 1);
		session.save(user);
			TR.commit();
		
		
		session = SF.openSession();
		TR = session.beginTransaction();
		session.save(authority);
		TR.commit();
		return "login";
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String client(Model model, HttpServletRequest request) {
		ArrayList<Saw> saw_list = new ArrayList<Saw>();//список товаров в каталоге
		Set<String> model_saw = new HashSet<String>();//список дл€ фильтра
		Set<String> brend = new HashSet<String>();//список дл€ фильтра
		Set<String> manufacturer = new HashSet<String>();//список дл€ фильтра
		Set<String> location = new HashSet<String>();//список дл€ фильтра
		Set<String> state = new HashSet<String>();//список дл€ фильтра
		Set<String> year = new HashSet<String>();//список дл€ фильтра
		User user = new User();//переменна€ дл€ хранени€ текущего клиента
		SessionFactory sessionFactory = ru.myproject.hibernate.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Saw.class);
		HttpSession sessionsa = request.getSession(true);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Query query = session.createQuery("from User where username = :USERNAME");
		query.setParameter("USERNAME", name);
		List<?> list = query.list();
		if (!list.isEmpty()) {
			user = (User) list.get(0);
		}

		saw_list = (ArrayList<Saw>) criteria.list();
		model_saw.clear();
		brend.clear();
		location.clear();
		manufacturer.clear();
		state.clear();
		year.clear();

		for (Saw saw : saw_list) {
			model_saw.add(saw.getType());
			brend.add(saw.getBrand());
			location.add(saw.getLocation());
			manufacturer.add(saw.getManufacturer());
			state.add(saw.getState());
			year.add(saw.getYear());
		}
		sort_list.add(model_saw);
		sort_list.add(brend);
		sort_list.add(location);
		sort_list.add(manufacturer);
		sort_list.add(state);
		sort_list.add(year);
		sessionsa.setAttribute("sessionName", name);
		updateCart(request);
		if(request.getUserPrincipal()==null){
			sessionsa.setAttribute("userName", "null");
		}else{
			sessionsa.setAttribute("userName", request.getUserPrincipal().getName());
		}
		
		
		
		
		ArrayList<Saw> saw_list_filter = new ArrayList<Saw>();
		String[] checkbox_find_brand = request.getParameterValues("filterBrand");
		
		
		String testStyle=request.getParameter("style");
		if(checkbox_find_brand!=null){
			for(Saw saw : saw_list){
				for(String str : checkbox_find_brand){
					if(saw.getBrand().equals(str)){
						saw_list_filter.add(saw);
					}
				}
			}
			saw_list=saw_list_filter;
		}
		

		ArrayList<Saw> saw_list_filter_l = new ArrayList<Saw>();
		String[] checkbox_find_location = request.getParameterValues("filterLocation");
		if(checkbox_find_location!=null){
			for(Saw saw : saw_list){
				for(String str : checkbox_find_location){
					if(saw.getLocation().equals(str)){
						saw_list_filter_l.add(saw);
					}
				}
			}
			saw_list=saw_list_filter_l;
		}

		String search = request.getParameter("search");
		if(search!=null){
			for(Saw saw : saw_list){
				if(saw.getBrand().toLowerCase().equals(search.toLowerCase())){
					saw_list_filter.add(saw);
				}
			}
			saw_list=saw_list_filter;
		}
		ArrayList<Saw> saw_list_filter_min=new ArrayList<Saw>();
		ArrayList<Saw> saw_list_filter_max=new ArrayList<Saw>();
		String minimum=request.getParameter("min_price");
		if(minimum!=null&&minimum!=""){
			int minimum_price = Integer.parseInt(minimum);
			for(Saw saw : saw_list){
				if(saw.getPrice()>minimum_price){
					saw_list_filter_min.add(saw);
				}
			}
			saw_list=saw_list_filter_min;
		}
		
		
		String maximum=request.getParameter("max_price");
		if(maximum!=null&&maximum!=""){
			int maximum_price = Integer.parseInt(maximum);
			for(Saw saw : saw_list){
				if(saw.getPrice()<maximum_price){
					saw_list_filter_max.add(saw);
				}
			}
			saw_list=saw_list_filter_max;
		}
		
		
		
		int list_counter=saw_list.size()/12;
		int tail=saw_list.size()%12;
		if(tail>0)list_counter++;
		if((String) request.getParameter("style")!=null){
			listStyle = request.getParameter("style");
		}
		
		
		if((String) request.getParameter("page")!=null){
			page_number = Integer.parseInt((String) request.getParameter("page"));
		}
		
		ArrayList<Saw> saw_list_display = new ArrayList<>();
		int start = 12*(page_number-1);
		int end = 12*page_number;
		for(int i=start;i<end&&i<saw_list.size();i++){
			saw_list_display.add(saw_list.get(i));
		}
		sessionsa.setAttribute("saw", saw_list_display);//список доступных товаров
		sessionsa.setAttribute("pageNumber", list_counter);//количество страниц в каталоге
		sessionsa.setAttribute("style", listStyle);
		sessionsa.setAttribute("sortList", sort_list);//лист элементов дл€ боковой панели 
		return "clientCatalog";
	}

	
	

	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {

		return "accessDenied";
	}

	
	
	@RequestMapping(value = "/merchandise", method = RequestMethod.GET)
	public String merchandise(Model model, HttpServletRequest request) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sFactory = configuration.buildSessionFactory();
		Session session = sFactory.openSession();
		HttpSession se = request.getSession(true);
		Saw thing = new Saw();
		thing.setId(request.getParameter("btn"));
		Query query = session.createQuery("from Saw where id = :ID");
		query.setParameter("ID", thing.getId());
		List<?> list = query.list();
		thing = (Saw) list.get(0);
		se.setAttribute("merchandise", thing);
		updateCart(request);
		return "merchandise";
	}

	
	
	@RequestMapping(value = "/compare", method = RequestMethod.GET)
	public String compare(
			Model model, HttpServletRequest request) {
		ArrayList<Saw> saw_compare = new ArrayList<Saw>();//список товаров дл€ сравнени€
		ArrayList<Saw> saw_list = new ArrayList<Saw>();//список товаров в каталоге
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Saw.class);
		HttpSession se = request.getSession(true);
		String[] checkboxValues = request.getParameterValues("compare");
		String btnParameter = request.getParameter("btn");
		if (btnParameter==null||btnParameter.equals("compare")) {
			saw_compare.clear();
			try {

				saw_list = (ArrayList<Saw>) criteria.list();
				int checkbox = 0;
				if (saw_list != null) {
					for (Saw item : saw_list) {
						if (checkboxValues != null) {
							if (checkboxValues.length > checkbox) {
								if (item.getId().equals(checkboxValues[checkbox])/* == */) {
									saw_compare.add(item);
									checkbox++;
								}
							}
						}

					}
				}

				se.setAttribute("sawCompare", saw_compare);
			} finally {
			}

		} else {
			Saw thing = new Saw();
			thing.setId(request.getParameter("btn"));
			Query query = session.createQuery("from Saw where id = :ID");
			query.setParameter("ID", thing.getId());
			List<?> list = query.list();
			thing = (Saw) list.get(0);
			se.setAttribute("merchandise", thing);

			updateCart(request);
			return "merchandise";
		}
		return "compare";
	}

	public byte[] convertImage(File file) {
		byte[] image = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(image);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
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

}
