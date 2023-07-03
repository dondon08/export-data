package com.export.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.export.dto.FileUploadDTO;
import com.export.entity.FileUpload;


public interface FileUploadRepository extends JpaRepository<FileUpload, Long>{

	@Query("SELECT new com.export.dto.FileUploadDTO("
			+ "id, filename, createdDate) FROM FileUpload ORDER BY createdDate")
	List<FileUploadDTO> getListFileUploadDTOs();

	@Query("SELECT new com.export.dto.FileUploadDTO("
			+ "id, filename, createdDate) FROM FileUpload WHERE id = :id")
	Optional<FileUploadDTO> getFileUploadDTOByIdFileUpload(@Param("id") Long id);

}
