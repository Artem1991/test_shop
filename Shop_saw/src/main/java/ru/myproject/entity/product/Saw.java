package ru.myproject.entity.product;


import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import javax.sql.rowset.serial.*;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.util.Base64Decoder;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sql.rowset.serial.SerialBlob;

//import org.hibernate.annotations.Table;

import javax.persistence.Table;


@Entity
@Table(name = "saw")
public class Saw  implements Serializable{
	private static final long serialVersionUID = -7427619012799617697L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="model")
	private String model;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="manufacturer")
	private String manufacturer;
	
	@Column(name="state")
	private String state;
	
	@Column(name="year")
	private String year;
	
	@Column(name="location")
	private String location;
	
	@Column(name="max_round_size")
	private int roundSize;
	
	@Column(name="max_rectangular_length")
	private int rectangularLength;
	
	@Column(name="max_rectangular_width")
	private int rectangularWidth;
	
	@Column(name="blade_speed")
	private String bladeSpeed;
	
	@Column(name="blade_power")
	private String bladePower;
	
	@Column(name="hydro_power")
	private String hydroPower;
	
	@Column(name="pump_power")
	private String pumpPower;
	
	@Column(name="blade_size")
	private String bladeSize;
	
	@Column(name="blade_tension")
	private String bladeTension;
	
	@Column(name="transfer_placement")
	private int transferPlacement;
	
	@Column(name="system_control")
	private String systemControl;
	
	@Column(name="bench_size")
	private String benchSize;
	
	@Column(name="bench_weight")
	private int benchWeight;
	
	@Column(name="price")
	private int price;
	
	@Column(name="data")
	private java.util.Date date;

	@Column(name="image1")
	private String image1;
	
	@Column(name="image2")
	private String image2;
	
	@Column(name="image3")
	private String image3;
	
	@Column(name="image4")
	private String image4;
	
	@Column(name="image5")
	private String image5;
	
	@Column(name="description")
	private String description;
	
	
	
	public Saw(){
		
	}
	
	public Saw(int id){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getRoundSize() {
		return roundSize;
	}

	public void setRoundSize(int roundSize) {
		this.roundSize = roundSize;
	}
	
	public int getRectangularLength() {
		return rectangularLength;
	}

	public void setRectangularLength(int rectangularLength) {
		this.rectangularLength = rectangularLength;
	}
	
	public int getRectangularWidth() {
		return rectangularWidth;
	}

	public void setRectangularWidth(int rectangularWidth) {
		this.rectangularWidth = rectangularWidth;
	}
	
	public String getBladeSpeed() {
		return bladeSpeed;
	}

	public void setBladeSpeed(String bladeSpeed) {
		this.bladeSpeed = bladeSpeed;
	}
	
	public String getBladePower() {
		return bladePower;
	}

	public void setBladePower(String bladePower) {
		this.bladePower = bladePower;
	}
	
	public String getHydroPower() {
		return hydroPower;
	}

	public void setHydroPower(String hydroPower) {
		this.hydroPower = hydroPower;
	}
	
	public String getPumpPower() {
		return pumpPower;
	}

	public void setPumpPower(String pumpPower) {
		this.pumpPower = pumpPower;
	}
	
	public String getBladeSize() {
		return bladeSize;
	}

	public void setBladeSize(String bladeSize) {
		this.bladeSize = bladeSize;
	}
	
	public String getBladeTension() {
		return bladeTension;
	}

	public void setBladeTension(String bladeTension) {
		this.bladeTension = bladeTension;
	}
	
	public int getTransferPlacement() {
		return transferPlacement;
	}

	public void setTransferPlacement(int transferPlacement) {
		this.transferPlacement = transferPlacement;
	}
	
	public String getSystemControl() {
		return systemControl;
	}

	public void setSystemControl(String systemControl) {
		this.systemControl = systemControl;
	}
	
	public String getBenchSize() {
		return benchSize;
	}

	public void setBenchSize(String benchSize) {
		this.benchSize = benchSize;
	}
	
	public int getBenchWeight() {
		return benchWeight;
	}

	public void setBenchWeight(int benchWeight) {
		this.benchWeight = benchWeight;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public java.util.Date getDate(){
		return date;
	}
	
	public void setDate(java.util.Date date){
		this.date = date;
	}

	public String getImage1() {
		return image1;
       
	}
	
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	
	public String getImage2() {
		return image2;
	}
	
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	
	public String getImage3() {
		return image3;
       
	}
	
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	
	
	public String getImage4() {
		return image4;
	}
	
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	
	
	public String getImage5() {
		return image5;       
	}
	
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}


