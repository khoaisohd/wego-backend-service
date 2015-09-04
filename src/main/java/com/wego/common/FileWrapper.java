package com.wego.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class FileWrapper {
	public static String LOCAL = "local";
	public static String REMOTE = "remote";
	
	private String path;
	private String type;
	
	public FileWrapper(String path, String type) {
		super();
		this.path = path;
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public BufferedReader read() throws IOException{
		if (type.equals(FileWrapper.LOCAL)){
			return new BufferedReader(new FileReader(path));
		} else if (type.equals(FileWrapper.REMOTE)) {
			URL fileUrl = new URL(path);
			return new BufferedReader(new InputStreamReader(fileUrl.openStream()));
		}
		return null;
	}
}
