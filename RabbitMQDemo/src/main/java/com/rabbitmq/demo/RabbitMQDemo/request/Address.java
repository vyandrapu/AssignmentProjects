package com.rabbitmq.demo.RabbitMQDemo.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address implements Serializable{
	private String addressLine;
	private String city;
	private String state;
	private String postCode;
	private String country;
	private String phoneNumber;
	
	public Address() {
		
	}
	
	public Address(String addressLine,String city,String state,String postCode,String country,String phoneNumber) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.country = country;
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", postCode="
				+ postCode + ", country=" + country + ", phoneNumber=" + phoneNumber + "]";
	}

	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

}
