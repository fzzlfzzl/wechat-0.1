package com.site.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

public class XmlObject {

	private Document doc;
	private Element element;

	public XmlObject(String root) {
		this.doc = new Document();
		this.element = new Element(root);
		doc.addContent(element);
	}

	private XmlObject(Element element, Document doc) {
		this.element = element;
		this.doc = doc;
	}

	public XmlObject get(String key) {
		Element ret = element.getChild(key);
		if (null == ret) {
			ret = new Element(key);
			element.addContent(ret);
		}
		return new XmlObject(ret, doc);
	}

	public XmlObject get(String key, int index) {
		int length = getLength(key);
		if (index > length) {
			throw new RuntimeException("Out Of Bound");
		} else if (index == length) {
			Element ret = new Element(key);
			element.addContent(ret);
			return new XmlObject(ret, doc);
		} else {
			Element ret = element.getChildren(key).get(index);
			return new XmlObject(ret, doc);
		}
	}

	public String getAttribute(String key) {
		return element.getAttributeValue(key);
	}

	public int getLength(String key) {
		return element.getChildren(key).size();
	}

	public void setText(String value) {
		element.setText(value);
	}

	public void setText(long value) {
		element.setText(String.valueOf(value));
	}

	public void setCDATA(String value) {
		element.setContent(new CDATA(value));
	}

	public String getText() {
		return element.getText();
	}

	public static XmlObject toXmlObject(InputStream is) {
		@SuppressWarnings("deprecation")
		SAXBuilder builder = new SAXBuilder(false);
		Document document;
		try {
			document = builder.build(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new XmlObject(document.getRootElement(), document);
	}

	public static XmlObject toXmlObject(String xml) {
		try {
			return toXmlObject(new ByteArrayInputStream(xml.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String toXmlString() {
		Format fmt = Format.getPrettyFormat();
		XMLOutputter out = new XMLOutputter();
		out.setFormat(fmt);
		return out.outputString(doc);
	}

	public static class Convert {
		public static org.w3c.dom.Document toDocument(XmlObject obj) {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = dbf.newDocumentBuilder();
				return db.parse(new InputSource(new StringReader(obj.toXmlString())));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String toString() {
		return toXmlString();
	}
}
