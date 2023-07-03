package com.export.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_upload")
public class FileUpload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "filename")
	private String filename;
	
	@Column(name = "created_date")
	private Date createdDate;

	public FileUpload() {
		super();
	}

	public FileUpload(Long id, String filename, Date createdDate) {
		super();
		this.id = id;
		this.filename = filename;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "FileUpload [id=" + id + ", filename=" + filename + ", createdDate=" + createdDate + "]";
	}

}
