package com.cyzy.bean;

public class City {
	private int cityId;
	private String cityName;
	private int cityPId;

	public City(int cityId, String cityName, int cityPId) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityPId = cityPId;
	}

	public City() {
		super();
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityPId() {
		return cityPId;
	}

	public void setCityPId(int cityPId) {
		this.cityPId = cityPId;
	}

}
