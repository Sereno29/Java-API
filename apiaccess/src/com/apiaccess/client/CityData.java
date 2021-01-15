package com.apiaccess.client;

import com.google.gwt.core.client.JavaScriptObject;

public class CityData extends JavaScriptObject{
	
	protected CityData() {}
	
	public final native String getIbgeId();
	public final native String getName();
	public final native String getUf();
	public final native String getIsCapital();
	public final native String getLatitude();
	public final native String getLongitude();
	public final native String getAlternativeName();
	public final native String getMesoregion();
	public final native String getMicroregion();
	
	
	
}
