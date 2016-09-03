//
//	RootClass.java
//
//	Create by 준혁 하 on 3/9/2016
//	Copyright © 2016 준혁 하. All rights reserved.
//	Model file Generated using: 
//	Vin.Favara's JSONExportV https://github.com/vivi7/JSONExport 
//	(forked from Ahmed-Ali's JSONExport)
//
package kr.hs.dimigo.ver2.hello.world.parsing;


public class RootClass{

	private Result[] results;
	private Result result;
	private String status;

	public void setResults(Result[] results){
		this.results = results;
	}
	public Result getResults(){
		results = this.results;
		result = results[0];
		return result;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}



}