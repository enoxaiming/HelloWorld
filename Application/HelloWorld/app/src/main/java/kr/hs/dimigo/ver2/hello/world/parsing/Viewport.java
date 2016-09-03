//
//	Viewport.java
//
//	Create by 준혁 하 on 3/9/2016
//	Copyright © 2016 준혁 하. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//
package kr.hs.dimigo.ver2.hello.world.parsing;
import android.location.Location;

import org.json.*;
import java.util.*;


public class Viewport{

	private Location northeast;
	private Location southwest;

	public void setNortheast(Location northeast){
		this.northeast = northeast;
	}
	public Location getNortheast(){
		return this.northeast;
	}
	public void setSouthwest(Location southwest){
		this.southwest = southwest;
	}
	public Location getSouthwest(){
		return this.southwest;
	}


}