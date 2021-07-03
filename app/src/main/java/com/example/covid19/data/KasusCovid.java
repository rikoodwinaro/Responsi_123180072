package com.example.covid19.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KasusCovid{

	private int statusCode;

	@SerializedName("data")
	@Expose
	private Data data;
	public Data getData() {
		return data;
	}
}