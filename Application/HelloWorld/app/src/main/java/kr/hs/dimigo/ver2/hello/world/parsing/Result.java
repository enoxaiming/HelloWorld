//
//	Result.java
//
//	Create by 준혁 하 on 3/9/2016
//	Copyright © 2016 준혁 하. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//
package kr.hs.dimigo.ver2.hello.world.parsing;


public class Result{

	private AddressComponent[] address_components;
	private AddressComponent addressComponent;
	private String formattedAddress;
	private Geometry geometry;
	private String placeId;
	private String[] types;

	public void setAddressComponents(AddressComponent[] addressComponents){
		this.address_components = addressComponents;
	}
	public AddressComponent getAddressComponents(){
		addressComponent = address_components[0];
		return this.addressComponent;
	}
	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}
	public String getFormattedAddress(){
		return this.formattedAddress;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}
	public String getPlaceId(){
		return this.placeId;
	}
	public void setTypes(String[] types){
		this.types = types;
	}
	public String[] getTypes(){
		return this.types;
	}



}