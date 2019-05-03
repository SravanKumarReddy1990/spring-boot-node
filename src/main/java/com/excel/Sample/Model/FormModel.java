package com.excel.Sample.Model;

import java.io.InputStream;

import com.mfcw.Sample.FormAnn.FormName;

public class FormModel{
	private String fname;
	private String sname;
	private String adress;
	private Long phoneno;
	private String gender;
	private InputStream file;

	@FormName(label = "fname")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@FormName(label = "sname")
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@FormName(label = "address")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@FormName(label = "pno")
	public Long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(Long phoneno) {
		this.phoneno = phoneno;
	}

	@FormName(label = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@FormName(label = "file")
	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

}
