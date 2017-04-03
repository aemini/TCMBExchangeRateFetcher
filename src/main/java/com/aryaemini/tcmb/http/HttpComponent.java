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
import java.net.UnknownHostException;

public class HttpComponent {

	private static final Logger logger = Logger.getLogger(HttpComponent.class);

	protected Document getDocument(String url) throws ExchangeRateParseException {
		try {
			HttpResponse response = httpResponse(url);
			if(response.getStatusLine().getStatusCode() != 200)
				throw new ExchangeRateParseException(response.getStatusLine().toString());
			return inputStreamToDocument(response.getEntity().getContent());
		} catch (IOException e) {
			throw new ExchangeRateParseException(e.getMessage(), e);
		}
	}

	private HttpResponse httpResponse(String url) throws ExchangeRateParseException, IOException {
		HttpGet request = new HttpGet(url);
		try {
			return httpClient().execute(request);
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
			throw new ExchangeRateParseException(e.getMessage(), e);
		}
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
