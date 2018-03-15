package com.aryaemini.tcmb;

import com.aryaemini.tcmb.exception.ExchangeRateException;
import com.aryaemini.tcmb.model.TCMBResponse;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TCMBExchangeRateFetcher {

	private static final Logger logger = Logger.getLogger(TCMBExchangeRateFetcher.class);

	public static TCMBResponse fetch() throws ExchangeRateException {
		return fetchUrl("http://tcmb.gov.tr/kurlar/today.xml");
	}

	public static TCMBResponse fetch(Date date) throws ExchangeRateException {
		String url = "http://tcmb.gov.tr/kurlar/" + dateToString(date, "yyyyMM") + "/" + dateToString(date, "ddMMyyyy") + ".xml";
		return fetchUrl(url);
	}

	private static TCMBResponse fetchUrl(String url) throws ExchangeRateException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TCMBResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (TCMBResponse) unmarshaller.unmarshal(new URL(url));
		} catch (Exception e) {
			logger.warn(e.getMessage());
			throw new ExchangeRateException(e.getMessage(), e);
		}
	}

	private static Date strToDate(String strDate, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date date;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	private static String dateToString(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

}
