package com.rabbitmq.demo.RabbitMQDemo.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class MessageRequest implements Serializable {
    private static final long serialVersionUID = -1764970284520387975L;

    private String name;
    private String email;
    private List<Address> addresses;
   
    
    public MessageRequest(String name,String email,List<Address> addresses) {
    	super();
    	this.name = name;
    	this.email = email;
    	this.addresses = addresses;
    	
    }

    @Override
	public String toString() {
		return "MessageRequest [name=" + name + ", email=" + email + ", addresses=" + addresses + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public MessageRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}