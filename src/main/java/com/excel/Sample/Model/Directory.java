package com.excel.Sample.Model;

import java.util.Date;


import com.excel.Sample.ExcelAnn.ExcelColumn;

public class Directory {
	
	private String name;
	private String path;
	private String directory;
	private Integer containedFiles;

	private Date dateFormate;

	

	@ExcelColumn(label = "Test Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelColumn(label = "Path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ExcelColumn(label = "Directory")
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@ExcelColumn(label = "ContainedFiles")
	public Integer getContainedFiles() {
		return containedFiles;
	}

	public void setContainedFiles(Integer containedFiles) {
		this.containedFiles = containedFiles;
	}

	@ExcelColumn(label = "DateFormate")
	public Date getDateFormate() {
		return dateFormate;
	}

	public void setDateFormate(Date dateFormate) {
		this.dateFormate = dateFormate;
	}
}
