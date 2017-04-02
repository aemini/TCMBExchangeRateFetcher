package com.aryaemini.tcmb.model;

import java.util.Date;

public class ExchangeRate {

	private String bulletinNo;
	private Date date;
	private String currencyCode;
	private String currencyName;
	private Integer unit;
	private Double forexBuying;
	private Double forexSelling;
	private Double banknoteBuying;
	private Double banknoteSelling;
	private Double crossRateUsd;
	private Double crossRateOther;

	public String getBulletinNo() {
		return this.bulletinNo;
	}

	public void setBulletinNo(String bulletinNo) {
		this.bulletinNo = bulletinNo;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getUnit() {
		return this.unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Double getForexBuying() {
		return this.forexBuying;
	}

	public void setForexBuying(Double forexBuying) {
		this.forexBuying = forexBuying;
	}

	public Double getForexSelling() {
		return this.forexSelling;
	}

	public void setForexSelling(Double forexSelling) {
		this.forexSelling = forexSelling;
	}

	public Double getBanknoteBuying() {
		return this.banknoteBuying;
	}

	public void setBanknoteBuying(Double banknoteBuying) {
		this.banknoteBuying = banknoteBuying;
	}

	public Double getBanknoteSelling() {
		return this.banknoteSelling;
	}

	public void setBanknoteSelling(Double banknoteSelling) {
		this.banknoteSelling = banknoteSelling;
	}

	public Double getCrossRateUsd() {
		return this.crossRateUsd;
	}

	public void setCrossRateUsd(Double crossRateUsd) {
		this.crossRateUsd = crossRateUsd;
	}

	public Double getCrossRateOther() {
		return this.crossRateOther;
	}

	public void setCrossRateOther(Double crossRateOther) {
		this.crossRateOther = crossRateOther;
	}

	@Override
	public String toString() {
		return "ExchangeRate [bulletinNo=" + this.bulletinNo +
				",date=" + this.date +
				",currencyCode=" + this.currencyCode +
				",currencyName=" + this.currencyName +
				",unit=" + this.unit +
				",forexBuying=" + this.forexBuying +
				",forexSelling=" + this.forexSelling +
				",banknoteBuying=" + this.banknoteBuying +
				",banknoteSelling=" + this.banknoteSelling +
				",crossRateUsd=" + this.crossRateUsd +
				",crossRateOther=" + this.crossRateOther +
				"]";
	}

}
