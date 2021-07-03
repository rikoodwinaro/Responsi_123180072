package com.example.covid19.data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data {
	private Object metadata;
	private ArrayList<ContentItem> content;
	public ArrayList<ContentItem> getContent() { return content;}
	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
}