package ru.myproject.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.hibernate.criterion.Projections;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import ru.myproject.entity.product.Saw;
import ru.myproject.entity.user.Contact;
import ru.myproject.hibernate.HibernateUtil;

@Controller
public class ContactController {

	@RequestMapping(value = "/message_send", method = RequestMethod.POST)
	public String messegeSend(Model model, @ModelAttribute("contact") Contact contact, HttpServletRequest request) {
		
		
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		Transaction TR = session.beginTransaction();
		session.save(contact);
		Criteria criteria = session.createCriteria(Contact.class);
		model.addAttribute("contact", new Contact());
		
		TR.commit();

		
		return "contact";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model, @ModelAttribute("contact") Contact contact, HttpServletRequest request) {
		model.addAttribute("contact", new Contact());
		return "contact";
	}
}
