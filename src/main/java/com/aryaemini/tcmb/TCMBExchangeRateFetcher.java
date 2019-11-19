package com.aryaemini.tcmb;

import com.aryaemini.tcmb.exception.ExchangeRateException;
import com.aryaemini.tcmb.model.TCMBResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class TCMBExchangeRateFetcher {

	private static TCMBExchangeRateFetcher fetcher;
	private static final Logger logger = Logger.getLogger(TCMBExchangeRateFetcher.class.getName());
	private static final String TCMB_URL = "https://tcmb.gov.tr/kurlar/";

	private TCMBExchangeRateFetcher() {
	}

	public static TCMBExchangeRateFetcher getInstance() {
		if(fetcher == null) {
			fetcher = new TCMBExchangeRateFetcher();
		}
		return fetcher;
	}

	public TCMBResponse fetch() {
		return fetchUrl(TCMB_URL + "today.xml");
	}

	public TCMBResponse fetch(Date date) {
		String url = TCMB_URL + dateToString(date, "yyyyMM") + "/" + dateToString(date, "ddMMyyyy") + ".xml";
		return fetchUrl(url);
	}

	private TCMBResponse fetchUrl(String url) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TCMBResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (TCMBResponse) unmarshaller.unmarshal(new URL(url));
		} catch (JAXBException e) {
			logger.warning(e.getMessage());
			throw new ExchangeRateException(e.getMessage(), e);
		} catch (MalformedURLException e) {
			logger.warning(e.getMessage());
			throw new ExchangeRateException(e.getMessage(), e);
		}
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
