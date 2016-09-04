package kr.hs.dimigo.ver2.hello.world.parsing;
//
//	AddressComponent.java
//
//	Create by 준혁 하 on 3/9/2016
//	Copyright © 2016 준혁 하. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//


public class AddressComponent{

	private String long_name;
	private String short_name;
	private String[] types;

	public void setLongName(String longName){
		this.long_name = longName;
	}
	public String getLongName(){
		return this.long_name;
	}
	public void setShortName(String shortName){
		this.short_name = shortName;
	}
	public String getShortName(){
		return this.short_name;
	}
	public void setTypes(String[] types){
		this.types = types;
	}
	public String[] getTypes(){
		return this.types;
	}



}