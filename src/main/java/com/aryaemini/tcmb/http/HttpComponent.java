package com.aryaemini.tcmb.http;

import com.aryaemini.tcmb.exception.ParseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class HttpComponent {

	protected Document getDocument(String url) throws ParseException {
		try {
			HttpResponse response = response(url);
			return inputStreamToDocument(response.getEntity().getContent());
		} catch (IOException e) {
			//TODO log this
			throw new ParseException(e.getMessage(), e);
		} catch (SAXException e) {
			//TODO log this
			throw new ParseException(e.getMessage(), e);
		}
	}

	private HttpResponse response(String url) throws IOException {
		HttpGet request = new HttpGet(url);
		return httpClient().execute(request);
	}

	private Document inputStreamToDocument(InputStream is) throws IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			//TODO log this
		}
		return builder.parse(is);
	}

	private HttpClient httpClient() {
		return HttpClients.createDefault();
	}

}
