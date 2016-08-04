package ru.myproject.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;





import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.myproject.entity.product.Saw;

@Controller
public class AdminController {
	ArrayList<Saw> saw_list = new ArrayList<Saw>();

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		SessionFactory sessionFactory = ru.myproject.hibernate.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Saw.class);
		try {

			saw_list = (ArrayList<Saw>) criteria.list();
			model.addAttribute("saw", saw_list);
			model.addAttribute("sawToEdit", new Saw());
		} finally {
		}

		return "admin";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Model model, @ModelAttribute("sawToEdit") Saw saw, HttpServletRequest request) {
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory SF = con.buildSessionFactory();
		Session session = SF.openSession();
		String userAgent = request.getHeader("User-Agent");
		String adress = "";

		if (userAgent.contains("Chrome") || userAgent.contains("Firefox")) {
			adress = "C:\\Susanin\\Programming\\EfTech\\wrike files\\wrike_attachments_56349125\\";

		}
		if (request.getParameter("pg").equals("Add")) {

			File file = new File(adress + request.getParameter("image1"));
			saw.setImage1(request.getParameter("image1"));

			file = new File(adress + request.getParameter("image2"));
			saw.setImage2(request.getParameter("image2"));

			file = new File(adress + request.getParameter("image3"));
			saw.setImage3(request.getParameter("image3"));

			file = new File(adress + request.getParameter("image4"));
			saw.setImage4(request.getParameter("image4"));

			file = new File(adress + request.getParameter("image5"));
			saw.setImage5(request.getParameter("image5"));

			DateFormat df = new SimpleDateFormat("dd/MM/yy");
		       Date dateobj = new Date();
		       System.out.println(df.format(dateobj));
		   //    saw.setDate(df.format(dateobj));

			
			
			Transaction TR = session.beginTransaction();
			session.save(saw);
			TR.commit();
		}

		if (request.getParameter("pg").equals("Delete")) {
			Transaction TR = session.beginTransaction();
			Query query = session.createQuery("delete Saw where id = :ID");
			query.setParameter("ID", saw.getId());

			int result = query.executeUpdate();
			if (result > 0) {
				System.out.println("Expensive products was removed");
			} else {
				System.out.println("nope");
			}
			TR.commit();
		}

		if (request.getParameter("pg").equals("Edit")) {
			Transaction TR = session.beginTransaction();
			Query query = session.createQuery("delete Saw where id = :ID");
			query.setParameter("ID", saw.getId());

			int result = query.executeUpdate();
			if (result > 0) {
				System.out.println("Expensive products was removed");
			} else {
				System.out.println("nope");
			}
			TR.commit();

			File file = new File(adress + request.getParameter("image1"));
			saw.setImage1(request.getParameter("image1"));

			file = new File(adress + request.getParameter("image2"));
			saw.setImage2(request.getParameter("image2"));

			file = new File(adress + request.getParameter("image3"));
			saw.setImage3(request.getParameter("image3"));

			file = new File(adress + request.getParameter("image4"));
			saw.setImage4(request.getParameter("image4"));

			file = new File(adress + request.getParameter("image5"));
			saw.setImage5(request.getParameter("image5"));

			TR = session.beginTransaction();
			session.save(saw);
			TR.commit();
		}
		Criteria criteria = session.createCriteria(Saw.class);
		saw_list = (ArrayList<Saw>) criteria.list();
		model.addAttribute("saw", saw_list);
		model.addAttribute("sawToEdit", new Saw());
		return "admin";
	}

	@RequestMapping(value = "/addFromXml", method = RequestMethod.POST)
	public String addFromXml(Model model, @ModelAttribute("sawToEdit") Saw saw, HttpServletRequest request)
			throws IOException, InvalidFormatException {

		SessionFactory sessionFactory = ru.myproject.hibernate.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Saw excelSaw = new Saw();

		// session.beginTransaction();
		Criteria criteria = session.createCriteria(Saw.class);
		XSSFWorkbook workbook = null;
		String filepath = "C:\\Susanin\\Programming\\EfTech\\wrike files\\wrike_attachments_56349125\\";

		String userAgent = request.getHeader("User-Agent");

		if (userAgent.contains("Chrome") || userAgent.contentEquals("Firefox")) {
			String adress = request.getParameter("adressExcel");
			workbook = new XSSFWorkbook(new File(filepath + adress));
		} else {
			String adress = request.getParameter("adressExcel");
			String fullAdress = filepath + adress;
			workbook = new XSSFWorkbook(new File(fullAdress)); // (new
																// File(request.getParameter("adressExcel")));
		}
		List lst = workbook.getAllPictures();
		Iterator it = lst.iterator();

		int numberOfSheets = workbook.getNumberOfSheets();
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row0 = sheet.getRow(0);
		XSSFCell cellC0 = row0.getCell((short) 2);
		excelSaw.setId(cellC0.toString());

		XSSFRow row2 = sheet.getRow(2);
		XSSFCell cellC2 = row2.getCell((short) 2);
		excelSaw.setType(cellC2.toString());

		XSSFRow row3 = sheet.getRow(3);
		XSSFCell cellC3 = row3.getCell((short) 2);
		excelSaw.setModel(cellC3.toString());

		XSSFRow row4 = sheet.getRow(4);
		XSSFCell cellC4 = row4.getCell((short) 2);
		excelSaw.setBrand(cellC4.toString());

		XSSFRow row5 = sheet.getRow(5);
		XSSFCell cellC5 = row5.getCell((short) 2);
		excelSaw.setManufacturer(cellC5.toString());

		XSSFRow row6 = sheet.getRow(6);
		XSSFCell cellC6 = row6.getCell((short) 2);
		excelSaw.setState(cellC6.toString());

		XSSFRow row7 = sheet.getRow(7);
		XSSFCell cellC7 = row7.getCell((short) 2);
		excelSaw.setYear(cellC7.toString());

		XSSFRow row8 = sheet.getRow(8);
		XSSFCell cellC8 = row8.getCell((short) 2);
		excelSaw.setLocation(cellC8.toString());

		XSSFRow row10 = sheet.getRow(10);
		XSSFCell cellC10 = row10.getCell((short) 2);
		excelSaw.setRoundSize(Integer.parseInt(convert(cellC10.toString())));

		XSSFRow row11 = sheet.getRow(11);
		XSSFCell cellC11 = row11.getCell((short) 2);
		excelSaw.setRectangularWidth(Integer.parseInt(convert(cellC11.toString())));

		XSSFRow row12 = sheet.getRow(12);
		XSSFCell cellC12 = row12.getCell((short) 2);
		excelSaw.setRectangularLength(Integer.parseInt(convert(cellC12.toString())));

		XSSFRow row13 = sheet.getRow(13);
		XSSFCell cellC13 = row13.getCell((short) 2);
		excelSaw.setBladeSpeed(cellC13.toString());

		XSSFRow row14 = sheet.getRow(14);
		XSSFCell cellC14 = row14.getCell((short) 2);
		excelSaw.setBladePower(cellC14.toString());

		XSSFRow row15 = sheet.getRow(15);
		XSSFCell cellC15 = row15.getCell((short) 2);
		excelSaw.setHydroPower(cellC15.toString());

		XSSFRow row16 = sheet.getRow(16);
		XSSFCell cellC16 = row16.getCell((short) 2);
		excelSaw.setPumpPower(cellC16.toString());

		XSSFRow row17 = sheet.getRow(17);
		XSSFCell cellC17 = row17.getCell((short) 2);
		excelSaw.setBladeSize(cellC17.toString());

		XSSFRow row18 = sheet.getRow(18);
		XSSFCell cellC18 = row18.getCell((short) 2);
		excelSaw.setBladeTension(cellC18.toString());

		XSSFRow row19 = sheet.getRow(19);
		XSSFCell cellC19 = row19.getCell((short) 2);
		excelSaw.setTransferPlacement(Integer.parseInt(convert(cellC19.toString())));

		XSSFRow row20 = sheet.getRow(20);
		XSSFCell cellC20 = row20.getCell((short) 2);
		excelSaw.setSystemControl(cellC20.toString());

		XSSFRow row21 = sheet.getRow(21);
		XSSFCell cellC21 = row21.getCell((short) 2);
		excelSaw.setBenchSize(cellC21.toString());

		XSSFRow row22 = sheet.getRow(22);
		XSSFCell cellC22 = row22.getCell((short) 2);
		excelSaw.setBenchWeight(Integer.parseInt(convert(cellC22.toString())));

		XSSFRow row24 = sheet.getRow(24);
		XSSFCell cellC24 = row24.getCell((short) 2);
		excelSaw.setPrice(Integer.parseInt(convert(cellC24.toString())));

		XSSFRow row26 = sheet.getRow(26);
		XSSFCell cellC26 = row26.getCell((short) 2);
		excelSaw.setDescription(cellC26.toString());

		/*
		 * XSSFRow row27=sheet.getRow(27); XSSFCell cellC27 =
		 * row27.getCell((short) 2); File file = new
		 * File(filepath+cellC27.toString());
		 * excelSaw.setImage1(filepath+cellC27.toString());
		 */
		File file;
		XSSFRow row27 = sheet.getRow(27);
		XSSFCell cellC27 = row27.getCell((short) 2);
		if (cellC27.toString().equals("no")) {
		} else {
			file = new File(filepath + cellC27.toString());
			excelSaw.setImage1(cellC27.toString());
		}

		XSSFRow row28 = sheet.getRow(28);
		XSSFCell cellC28 = row28.getCell((short) 2);
		if (cellC28.toString().equals("no")) {
		} else {
			file = new File(filepath + cellC28.toString());
			excelSaw.setImage2(cellC28.toString());
		}

		XSSFRow row29 = sheet.getRow(29);
		XSSFCell cellC29 = row29.getCell((short) 2);
		if (cellC29.toString().equals("no")) {
		} else {
			file = new File(filepath + cellC29.toString());
			excelSaw.setImage3(cellC29.toString());
		}

		XSSFRow row30 = sheet.getRow(30);
		XSSFCell cellC30 = row30.getCell((short) 2);
		if (cellC30.toString().equals("no")) {
		} else {
			file = new File(filepath + cellC30.toString());
			excelSaw.setImage4(cellC30.toString());
		}

		XSSFRow row31 = sheet.getRow(31);
		XSSFCell cellC31 = row31.getCell((short) 2);
		if (cellC31.toString().equals("no")) {
		} else {
			file = new File(filepath + cellC31.toString());
			excelSaw.setImage5(cellC31.toString());
		}

		Transaction TR = session.beginTransaction();
		session.save(excelSaw);
		TR.commit();

		admin(model);
		return "admin";
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

	public String convert(String data) {
		int i = data.length();
		int end = data.indexOf('.');

		char[] mass = new char[i];
		data.getChars(0, end, mass, 0);
		String data_cvt = "";
		for (int j = 0; j < mass.length; j++) {

			data_cvt = data_cvt + mass[j];
			if (data_cvt != "") {
				data = data_cvt.replace(" ", "");
			}
		}
		return data.trim();
	}
}
