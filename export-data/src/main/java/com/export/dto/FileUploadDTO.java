package com.export.dto;

import java.util.Date;
import java.util.List;

public class FileUploadDTO {
	
	private Long id;
	private String filename;
	private Date createdDate;
	
	private List<ProductDTO> listProductDTO;

	public FileUploadDTO() {
		super();
	}

	public FileUploadDTO(Long id, String filename, Date createdDate) {
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

	public List<ProductDTO> getListProductDTO() {
		return listProductDTO;
	}

	public void setListProductDTO(List<ProductDTO> listProductDTO) {
		this.listProductDTO = listProductDTO;
	}

}
