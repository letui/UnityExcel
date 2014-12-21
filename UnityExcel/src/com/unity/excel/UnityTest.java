package com.unity.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.unity.excel.core.Unity;
import com.unity.excel.core.Unity2k7;
import com.unity.excel.core.Unity97;

public class UnityTest {
	public static void main(String[] args) throws FileNotFoundException {
		ExportTest();
		//ImportTest();
		//ReadRowToObject();
		//ReadToList();
		
		//Export2k7Test();
		//Import2k7Test();
		//ReadToObject2k7();
		//ReadToList2k7();
		
	}

	private static void ReadToObject2k7() throws FileNotFoundException {
		Unity u=new Unity2k7();
		DemoObject obj=u.readRow(DemoObject.class, new FileInputStream("src/temp.xlsx"), 2);
		System.out.println(obj);
	}

	private static void ReadToList2k7() throws FileNotFoundException {
		Unity u=new Unity2k7();
		FileInputStream fs=new FileInputStream("src/temp.xlsx");
		System.out.println(u.readRow(fs, 1));
		System.out.println(u.readRow(fs, 2,true));
	}

	private static void Import2k7Test() throws FileNotFoundException {
		Unity u=new Unity2k7();
		List<DemoObject> t=u.importUTable(DemoObject.class, new FileInputStream("src/temp.xlsx"));
		System.out.println(t);
	}

	private static void Export2k7Test() {
		List<DemoObject> temp=new ArrayList<DemoObject>();
		temp.add(new DemoObject("小妹",99,33.500000000000000000000000000000000000000000,new Date()));
		temp.add(new DemoObject("山炮",2,23.5,new Date()));
		Unity u=new Unity2k7();
		try {
			u.exportUTable(temp, new FileOutputStream("src/temp.xlsx")).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ReadToList() throws FileNotFoundException {
		ReadToList2k7();
	}

	private static void ReadRowToObject() throws FileNotFoundException {
		ReadToObject2k7();
	}

	private static void ImportTest() throws FileNotFoundException {
		Unity u=new Unity97();
		List<DemoObject> t=u.importUTable(DemoObject.class, new FileInputStream("src/temp.xls"));
		System.out.println(t);
	}

	private static void ExportTest() {
		List<DemoObject> temp=new ArrayList<DemoObject>();
		temp.add(new DemoObject("小妹",99,33.500000000000000000000000000000000000000000,new Date()));
		temp.add(new DemoObject("山炮",2,23.5,new Date()));
		Unity u=new Unity97();
		try {
			u.exportUTable(temp, new FileOutputStream("src/temp.xls")).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
