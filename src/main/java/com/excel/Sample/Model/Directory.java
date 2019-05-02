package com.excel.Sample.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.excel.Sample.ExcelAnn.ExcelColumn;

@Entity
@Table(name = "directory")
public class Directory {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "Test_Name")
	private String name;
	@Column(name = "Path", nullable = false)
	private String path;
	@Column(name = "Directory", nullable = false)
	private String directory;
	@Column(name = "ContainedFiles", nullable = false)
	private Integer containedFiles;

	@Column(name = "DateFormate", nullable = false)
	private Date dateFormate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
