//
//	Geometry.java
//
//	Create by 준혁 하 on 3/9/2016
//	Copyright © 2016 준혁 하. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//
package kr.hs.dimigo.ver2.hello.world;

import android.location.Location;



public class Geometry{

	private Location location;
	private String locationType;
	private Viewport viewport;

	public void setLocation(Location location){
		this.location = location;
	}
	public Location getLocation(){
		return this.location;
	}
	public void setLocationType(String locationType){
		this.locationType = locationType;
	}
	public String getLocationType(){
		return this.locationType;
	}
	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}
	public Viewport getViewport(){
		return this.viewport;
	}


}