package com.marceloserpa.sparkpoc;

public class Sale {

	private Integer idSalesman;
	private Integer idSale;
	private Double price;

	public Sale() {
		super();
	}

	public Sale(Integer idSalesman, Integer idSale, Double price) {
		this.idSalesman = idSalesman;
		this.idSale = idSale;
		this.price = price;
	}

	public Integer getIdSalesman() {
		return idSalesman;
	}

	public void setIdSalesman(Integer idSalesman) {
		this.idSalesman = idSalesman;
	}

	public Integer getIdSale() {
		return idSale;
	}

	public void setIdSale(Integer idSale) {
		this.idSale = idSale;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
