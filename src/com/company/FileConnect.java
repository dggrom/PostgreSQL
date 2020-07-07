package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConnect {

	private static String AdresServer = "//AdresServer";
	private static String NameBase = "//NameBase";
	private static String NameUser = "//NameUser";
	private static String PassUser = "//PassUser";
	
	public String[] ReadFileSetting() throws IOException {
		
		String[] masivCon = new String[4];
		
		File fileCon = new File("C:/Eclipse/WorkSpace/PostgreSQL/src/com/company/SC.txt");
		
		if (!fileCon.exists()) {
			return masivCon;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(fileCon));
		
		String s;
		Integer nomS = 0;
		
		while ((s=br.readLine())!=null) {
			if (AdresServer.equals(s.toString())) {
				if((s=br.readLine())!=null) {
					masivCon[nomS] = s;
					nomS++;
				}
			} else if (NameBase.equals(s.toString())) {
				if((s=br.readLine())!=null) {
					masivCon[nomS] = s;
					nomS++;
				}
			} else if (NameUser.equals(s.toString())) {
				if((s=br.readLine())!=null) {
					masivCon[nomS] = s;
					nomS++;
				}
			} else if (PassUser.equals(s.toString())) {
				if((s=br.readLine())!=null) {
					masivCon[nomS] = s;
					nomS++;
				}
			}
			
		}
		
		
		return masivCon;
		
	}
	
	public void UpdateConnectFile() {
		
	}
	
	private void CreatConnectFile() {
		
	}
	
	
	
}
