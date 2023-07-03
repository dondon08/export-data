package com.export.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_id")
	private long productId;

	@Column(name = "code")
	private String code;
 
	@Column(name = "name")
	private String name;

	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "uom_id")
	private Integer uomId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "file_upload_id")
	private Long fileUploadId;
	
	public Product() {
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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Product(long id, long productId, String code, String name, Integer qty, BigDecimal price, Integer uomId,
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", code=" + code + ", name=" + name + ", qty=" + qty
				+ ", price=" + price + ", uomId=" + uomId + ", categoryId=" + categoryId + ", fileUploadId="
				+ fileUploadId + "]";
	}

}
