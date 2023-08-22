package com.sample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Test {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		SystemEvent se = new SystemEvent();
		List<Long> ids = new ArrayList<>();
		
		ids.add(1l);
		ids.add(2l);
		ids.add(3l);
		se.setIds(ids);
		se.setEvent("EUR");
		se.setMessage("EURO");
		se.setDate(new Date());

		JAXBContext contextObj = JAXBContext.newInstance(SystemEvent.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(se, new FileOutputStream("C:\\Users\\shael\\Documents\\Study\\question.xml"));  
	}

}
