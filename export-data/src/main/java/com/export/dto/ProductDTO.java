package com.export.dto;

import java.math.BigDecimal;

public class ProductDTO {
	private long id;
	private long productId;
	private String code;
	private String name;
	private Integer qty;
	private BigDecimal price;
	private Integer uomId;
	private Integer categoryId;
	private Long fileUploadId;
	
	private String uom;
	private String category;	
	
	public ProductDTO() {
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
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


	public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getUomId() {
		return uomId;
	}


	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Long getFileUploadId() {
		return fileUploadId;
	}

	public void setFileUploadId(Long fileUploadId) {
		this.fileUploadId = fileUploadId;
	}
	
	public ProductDTO(long id, long productId, String code, String name, Integer qty, BigDecimal price, Integer uomId,
			Integer categoryId, Long fileUploadId) {
		super();
		this.id = id;
		this.productId = productId;
		this.code = code;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.uomId = uomId;
		this.categoryId = categoryId;
		this.fileUploadId = fileUploadId;
	}

	public ProductDTO(long id, long productId, String code, String name, Integer qty, BigDecimal price, Integer uomId,
			Integer categoryId, Long fileUploadId, String uom, String category) {
		super();
		this.id = id;
		this.productId = productId;
		this.code = code;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.uomId = uomId;
		this.categoryId = categoryId;
		this.fileUploadId = fileUploadId;
		this.uom = uom;
		this.category = category;
	}	

	public ProductDTO(String code, String name, Integer qty, BigDecimal price, String uom, String category) {
		super();
		this.code = code;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.uom = uom;
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", productId=" + productId + ", code=" + code + ", name=" + name + ", qty="
				+ qty + ", price=" + price + ", uomId=" + uomId + ", categoryId=" + categoryId + ", fileUploadId="
				+ fileUploadId + ", uom=" + uom + ", category=" + category + "]";
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}


}
