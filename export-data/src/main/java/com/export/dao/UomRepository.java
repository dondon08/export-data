package com.export.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.export.dto.UomDTO;
import com.export.entity.Uom;


public interface UomRepository extends JpaRepository<Uom, Long>{

	@Query("SELECT new com.export.dto.UomDTO("
			+ "id, code, name) FROM Uom ")
	List<UomDTO> getUomDTOs();
}
