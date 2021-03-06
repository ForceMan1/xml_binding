package xml_binding;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import igor.xml.CreditCard;

public class creditCardXMLTest {
	public static final String creditCardXML = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
			"<creditCard number=\"12345678\">\n" +
			"    <expiry_date>10/14</expiry_date>\n" +
			"    <control_number>566</control_number>\n" +
			"    <type>Visa</type>\n" +
			"</creditCard>";
	@Test
	public void shouldMarshallACreditCard() throws JAXBException {
		CreditCard creditCard = new CreditCard("12345678", "10/14", 566, "Visa");
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(CreditCard.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(creditCard, writer);
		System.out.println(writer);
		
		assertEquals(creditCardXML, writer.toString().trim());
	}

	@Test
	public void shouldUnmarshallACreditCard() throws JAXBException {
		StringReader reader = new StringReader(creditCardXML);
		JAXBContext context = JAXBContext.newInstance(CreditCard.class);
		Unmarshaller u = context.createUnmarshaller();
		CreditCard creditCard = (CreditCard)u.unmarshal(reader);
		
		assertEquals("12345678", creditCard.getNumber());
		assertEquals("10/14", creditCard.getExpiryDate());
		assertEquals((Object)566, creditCard.getControlNumber());
		assertEquals("Visa", creditCard.getType());
		
	}
}
