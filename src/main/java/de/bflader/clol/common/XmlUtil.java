package de.bflader.clol.common;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil {
	public static String convertToXml(Object source, Class<?>... classes) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(classes);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.marshal(source, sw);
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(File file, Class<T> classes) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(classes);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(file);
	}
}
