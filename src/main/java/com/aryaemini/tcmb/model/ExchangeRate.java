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

}
