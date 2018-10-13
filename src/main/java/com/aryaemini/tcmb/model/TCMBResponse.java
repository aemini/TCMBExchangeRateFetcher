package com.aryaemini.tcmb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@XmlRootElement(name="Tarih_Date")
public class TCMBResponse {

	private String bulletinNo;
	private String strDate;
	private List<Currency> currencies;

	public String getBulletinNo() {
		return bulletinNo;
	}

	@XmlAttribute(name="Bulten_No")
	public void setBulletinNo(String bulletinNo) {
		this.bulletinNo = bulletinNo;
	}

	public Date getDate() {
		return strToDate(strDate, "dd.MM.yyyy");
	}

	public String getStrDate() {
		return this.strDate;
	}

	@XmlAttribute(name="Tarih")
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	@XmlElement(name="Currency")
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TCMBResponse)) return false;

		TCMBResponse that = (TCMBResponse) o;

		if (!getBulletinNo().equals(that.getBulletinNo())) return false;
		return strDate.equals(that.strDate);
	}

	@Override
	public int hashCode() {
		int result = getBulletinNo().hashCode();
		result = 31 * result + strDate.hashCode();
		return result;
	}

}
