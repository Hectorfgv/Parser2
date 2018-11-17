package parser_persona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	public Parser() {
		libros = new ArrayList<Libro>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("libro");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (persona)
				Element elLibro = (Element) nl.item(i);
				
				String titulo = getTexto(elLibro,"titulo");
				String anyo = getAtributo(elLibro,"titulo","anyo");
				//System.out.println("El título es: "+titulo);
				String editor = getTexto(elLibro,"editor");
				//System.out.println("El editor es: "+editor);
				String paginas = getTexto(elLibro,"paginas");
				//System.out.println("Páginas vale: "+paginas);
				
				ArrayList<String> nombres = new ArrayList<String>();
				
				NodeList nl2 = elLibro.getElementsByTagName("autor");
				if (nl2 != null && nl2.getLength() > 0) {
					
					Element elAutor = (Element) nl2.item(0);
					
					NodeList nl3 = elAutor.getElementsByTagName("nombre");
					
					if (nl3 != null && nl3.getLength() > 0) {
						
						for(int j=0; j<nl3.getLength();j++) {
							Element elNombre = (Element) nl3.item(j);
							String nombre = elNombre.getFirstChild().getNodeValue();
							nombres.add(nombre);
							//System.out.println("Nombre autor: "+nombre);
						}
						//System.out.println("\n");
						
					}
					
					
				}
				// obtenemos una persona
				Libro l = new Libro();
				l.setTitulo(titulo);
				l.setEditor(editor);
				l.setPaginas(Integer.parseInt(paginas));
				l.setAutores(nombres);
				l.setAnyo(Integer.parseInt(anyo));
				
				// lo añadimos al array
				//personas.add(p);
				libros.add(l);
			}
		}
	}
	
	private String getTexto (Element e, String etiqueta) {
		
		NodeList nl = e.getElementsByTagName(etiqueta);
		if (nl != null && nl.getLength() > 0) {
			
				
				Element subelement = (Element) nl.item(0);
				String valor = subelement.getFirstChild().getNodeValue();
				return valor;
	}
		return null;
	}
	
	private String getAtributo (Element e, String etiqueta, String atr) {
		
		NodeList nl = e.getElementsByTagName(etiqueta);
		if (nl != null && nl.getLength() > 0) {
			
				
				Element subelement = (Element) nl.item(0);
				String valor = subelement.getAttribute(atr);
				
				return valor;
	}
		return null;
	}
		

	/*
	private Persona getPersona(Element personaEle){
		
		//para cada elemento persona, obtenemos su nombre y su edad
		String nombre = getTextValue(personaEle,"nombre");
		int edad = getIntValue(personaEle,"edad");
		
		//Creamos una nueva persona con los elementos leídos del nodo
		Persona e = new Persona(nombre,edad);

		return e;		
		
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	*/
	public void print(){

		Iterator it = libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}
	}
}
	
	

