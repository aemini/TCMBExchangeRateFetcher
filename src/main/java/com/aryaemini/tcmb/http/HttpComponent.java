package com.aryaemini.tcmb.http;

import com.aryaemini.tcmb.exception.ExchangeRateParseException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class HttpComponent {

	private static final Logger logger = Logger.getLogger(HttpComponent.class);

	protected Document getDocument(String url) throws ExchangeRateParseException {
		try {
			HttpResponse response = httpResponse(url);
			return inputStreamToDocument(response.getEntity().getContent());
		} catch (IOException e) {
			throw new ExchangeRateParseException(e.getMessage(), e);
		}
	}

	private HttpResponse httpResponse(String url) throws IOException {
		HttpGet request = new HttpGet(url);
		return httpClient().execute(request);
	}

	private Document inputStreamToDocument(InputStream is) throws ExchangeRateParseException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			return builder.parse(is);
		} catch (IOException e) {
			logger.error(e);
			throw new ExchangeRateParseException(e.getMessage(), e);
		} catch (ParserConfigurationException e) {
			logger.error(e);
			throw new ExchangeRateParseException(e.getMessage(), e);
		} catch (SAXException e) {
			logger.error(e);
			throw new ExchangeRateParseException(e.getMessage(), e);
		}
	}

	private HttpClient httpClient() {
		return HttpClients.createDefault();
	}

}
