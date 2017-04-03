package com.aryaemini.tcmb;

import com.aryaemini.tcmb.exception.ExchangeRateException;
import com.aryaemini.tcmb.exception.ExchangeRateParseException;
import com.aryaemini.tcmb.http.HttpComponent;
import com.aryaemini.tcmb.model.ExchangeRate;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TCMBExchangeRates extends HttpComponent {

	public  static final Logger logger = Logger.getLogger(TCMBExchangeRates.class);

	public List<ExchangeRate> fetch() throws ExchangeRateException {
		String url = "http://tcmb.gov.tr/kurlar/today.xml";
		Document document;
		try {
			document = getDocument(url);
			return parse(document);
		} catch (ExchangeRateParseException e) {
			logger.error(e.getMessage(), e);
			throw new ExchangeRateException(e.getMessage(), e);
		}
	}

	public List<ExchangeRate> fetch(Date date) throws ExchangeRateException {
		String url = "http://tcmb.gov.tr/kurlar/" + dateToString(date, "yyyyMM") + "/" + dateToString(date, "ddMMyyyy") + ".xml";
		try {
			Document document = getDocument(url);
			return parse(document);
		} catch (ExchangeRateParseException e) {
			logger.error(e.getMessage(), e);
			throw new ExchangeRateException(e.getMessage(), e);
		}
	}

	private List<ExchangeRate> parse(Document document) throws ExchangeRateParseException {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		Node getDateElement = null;
		NodeList currencyElements = null;

		try {
			getDateElement = (Node) xPath.evaluate("Tarih_Date", document, XPathConstants.NODE);
			currencyElements = (NodeList) xPath.evaluate("Tarih_Date/Currency", document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			logger.fatal(e.getMessage(), e);
		}

		if(getDateElement == null || currencyElements.getLength() == 0)
			throw new ExchangeRateParseException("Wrong XML pattern");

		String strBulletinDate = getDateElement.getAttributes().getNamedItem("Date").getNodeValue();
		String strBulletinNo = getDateElement.getAttributes().getNamedItem("Bulten_No").getNodeValue();
		List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

		for(int i = 0; i < currencyElements.getLength(); i++) {
			ExchangeRate exchangeRate = new ExchangeRate();
			Element element = (Element) currencyElements.item(i);
			String currencyCode;
			Integer unit;
			Double forexBuying = null;
			Double forexSelling = null;
			Double banknoteBuying = null;
			Double banknoteSelling = null;
			Double crossRateUsd = null;
			Double crossRateOther = null;

			String currencyName = element.getElementsByTagName("Isim").item(0).getTextContent();
			String strBanknoteBuying = element.getElementsByTagName("BanknoteBuying").item(0).getTextContent();
			String strBanknoteSelling = element.getElementsByTagName("BanknoteSelling").item(0).getTextContent();
			String strForexBuying = element.getElementsByTagName("ForexBuying").item(0).getTextContent();
			String strForexSelling = element.getElementsByTagName("ForexSelling").item(0).getTextContent();
			String strCrossRateUsd = element.getElementsByTagName("CrossRateUSD").item(0).getTextContent();
			String strCrossRateOther = element.getElementsByTagName("CrossRateOther").item(0).getTextContent();

			if(strBanknoteBuying.length() != 0)
				banknoteBuying = Double.parseDouble(strBanknoteBuying);

			if(strBanknoteSelling.length() != 0)
				banknoteSelling = Double.parseDouble(strBanknoteSelling);

			if(strForexBuying.length() != 0)
				forexBuying = Double.parseDouble(strForexBuying);

			if(strForexSelling.length() != 0)
				forexSelling = Double.parseDouble(strForexSelling);

			if(strCrossRateUsd.length() != 0)
				crossRateUsd = Double.parseDouble(strCrossRateUsd);

			if(strCrossRateOther.length() != 0)
				crossRateOther = Double.parseDouble(strCrossRateOther);

			currencyCode = element.getAttribute("CurrencyCode");
			unit = Integer.parseInt(element.getElementsByTagName("Unit").item(0).getTextContent());

			exchangeRate.setDate(strToDate(strBulletinDate, "MM/dd/yyyy"));
			exchangeRate.setBulletinNo(strBulletinNo);
			exchangeRate.setCurrencyName(currencyName.trim());
			exchangeRate.setCurrencyCode(currencyCode.trim());
			exchangeRate.setUnit(unit);
			exchangeRate.setBanknoteBuying(banknoteBuying);
			exchangeRate.setBanknoteSelling(banknoteSelling);
			exchangeRate.setForexBuying(forexBuying);
			exchangeRate.setForexSelling(forexSelling);
			exchangeRate.setCrossRateUsd(crossRateUsd);
			exchangeRate.setCrossRateOther(crossRateOther);

			exchangeRates.add(exchangeRate);
		}

		return exchangeRates;
	}

	private Date strToDate(String strDate, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date date;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	private String dateToString(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

}
