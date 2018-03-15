package com.aryaemini.tcmb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Currency")
public class Currency {

	private Integer unit;
	private String code;
	private Integer crossOrder;
	private String name;
	private Double forexBuying;
	private Double forexSelling;
	private Double banknoteBuying;
	private Double banknoteSelling;
	private Double crossRateUsd;
	private Double crossRateOther;

	public String getCode() {
		return code;
	}

	@XmlAttribute(name="CurrencyCode")
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCrossOrder() {
		return crossOrder;
	}

	@XmlAttribute(name="CrossOrder")
	public void setCrossOrder(Integer crossOrder) {
		this.crossOrder = crossOrder;
	}

	public Integer getUnit() {
		return unit;
	}

	@XmlElement(name="Unit")
	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name="Isim")
	public void setName(String name) {
		this.name = name;
	}

	public Double getForexBuying() {
		return forexBuying;
	}

	@XmlElement(name="ForexBuying")
	public void setForexBuying(Double forexBuying) {
		this.forexBuying = forexBuying;
	}

	public Double getForexSelling() {
		return forexSelling;
	}

	@XmlElement(name="ForexSelling")
	public void setForexSelling(Double forexSelling) {
		this.forexSelling = forexSelling;
	}

	public Double getBanknoteBuying() {
		return banknoteBuying;
	}

	@XmlElement(name="BanknoteBuying")
	public void setBanknoteBuying(Double banknoteBuying) {
		this.banknoteBuying = banknoteBuying;
	}

	public Double getBanknoteSelling() {
		return banknoteSelling;
	}

	@XmlElement(name="BanknoteSelling")
	public void setBanknoteSelling(Double banknoteSelling) {
		this.banknoteSelling = banknoteSelling;
	}

	public Double getCrossRateUsd() {
		return crossRateUsd;
	}

	@XmlElement(name="CrossRateUSD")
	public void setCrossRateUsd(Double crossRateUsd) {
		this.crossRateUsd = crossRateUsd;
	}

	public Double getCrossRateOther() {
		return crossRateOther;
	}

	@XmlElement(name="CrossRateOther")
	public void setCrossRateOther(Double crossRateOther) {
		this.crossRateOther = crossRateOther;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Currency)) return false;

		Currency currency = (Currency) o;

		if (!getCode().equals(currency.getCode())) return false;
		return getName().equals(currency.getName());
	}

	@Override
	public int hashCode() {
		int result = getCode().hashCode();
		result = 31 * result + getName().hashCode();
		return result;
	}

}
