package com.unity.excel;

import java.util.Date;

import com.unity.excel.annotations.UColumn;
import com.unity.excel.annotations.UFormatter;
import com.unity.excel.annotations.UMapper;
import com.unity.excel.annotations.UStyleBinder;
import com.unity.excel.annotations.UTable;

@UTable()
public class DemoObject {
	
	public DemoObject(){
		
	}
	public DemoObject(String name) {
		super();
		this.name = name;
	}

	public DemoObject(String name, int age, double width) {
		super();
		this.name = name;
		this.age = age;
		this.width = width;
	}
	public DemoObject(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public DemoObject(String name, int age, double width, Date birthday) {
		super();
		this.name = name;
		this.age = age;
		this.width = width;
		this.birthday = birthday;
	}

	@UColumn(Head="姓名",Index=1)
	private String name;
	@UColumn(Head="年龄",Index=2)
	@UMapper(JsonMap="{'1':'一','2':'二'}")
	private int age;
	@UColumn(Head="腰围",Index=3,Suffix="寸",Prefix="SN-")
	@UFormatter(FormatPartten="##.##")
	private double width;
	
	@UColumn(Index=4)
	@UFormatter(FormatPartten="yyyy-MM-dd")
	@UStyleBinder()
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DemoObject [name=" + name + ", age=" + age + ", width=" + width
				+ ", birthday=" + birthday + "]";
	}
	
}
