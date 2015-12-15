package com.tuskiomi.ReDefine;

import java.io.File;

public class Start {

	public static void main(String[] args) {
		print("initiating reddit bot V0.0");
		print("reading Config...");
		
		File config = new File("config.txt");
		FileHelper configwrapper = new FileHelper(config);
		

	}
	private static void print(String s){
		System.out.println(s);
	}

}
