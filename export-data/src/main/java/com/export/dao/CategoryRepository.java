package com.export.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.export.dto.CategoryDTO;
import com.export.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT new com.export.dto.CategoryDTO("
			+ "id, code, name) FROM Category")
	List<CategoryDTO> getSmlCategoryDTOs();
}
