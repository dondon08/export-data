package com.export.dto;

public class CategoryDTO {

	private Long id;
	private String code;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	public CategoryDTO(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public CategoryDTO() {}
	
	@Override
	public String toString() {
		return "SmlCategory [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	

}
