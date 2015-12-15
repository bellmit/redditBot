package com.tuskiomi.ReDefine;

import java.io.File;
import java.io.IOException;

public class FileHelper {
	private final File ref;
	public FileHelper(File f){
		ref = f;
		if(f.exists()){
			System.out.println("File found. checking integrity");
		}else{
			System.out.println("File not found... creating new");
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String[] getUsers(){
		return null;
	}
	public int getTargetPop(){
		return -1;
	}
	public int getCurrentPop(){
		return 0;
	}
	

}
