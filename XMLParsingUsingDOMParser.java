package fileIO;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import java.io.File;
import java.io.IOException;

public class XMLParsingUsingDOMParser {
	public static void main(String[] args) {
		XMLParsingUsingDOMParser demo = new XMLParsingUsingDOMParser();
		try {
			demo.readDataFromXMLFile();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	private void readDataFromXMLFile() throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuildFact.newDocumentBuilder();
		Document doc = docBuilder.parse(new File("src/resources/Employees.xml"));
		
		doc.getDocumentElement().normalize();
		
		System.out.println("The Root tag is: "+doc.getDocumentElement().getNodeName());
		
		NodeList nodeList = doc.getElementsByTagName("staff");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println("The Node is: "+node.getNodeName());
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				System.out.println("Id:=>"+element.getAttribute("id"));
				System.out.println("First Name:=>"+element.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Last Name:=>"+element.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Nick Name:=>"+element.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Salary:=>"+element.getElementsByTagName("salary").item(0).getTextContent());
			}
		}
	}
}
