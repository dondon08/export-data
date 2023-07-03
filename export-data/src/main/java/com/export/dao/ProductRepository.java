package com.export.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.export.dto.ProductDTO;
import com.export.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT new com.export.dto.ProductDTO( "
			+ "p.code, p.name, p.qty, p.price, u.name AS uom, c.name AS category) "
			+ "FROM Product p "
			+ "LEFT JOIN Uom u ON u.id = p.uomId "
			+ "LEFT JOIN Category c ON c.id = p.categoryId "
			+ "WHERE p.fileUploadId = :id ")
	List<ProductDTO> getListProductDTOsByIdFileUpload(@Param("id") Long id);

}
