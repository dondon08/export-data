package com.export.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.export.dto.FileUploadDTO;
import com.export.dto.ProductDTO;

public interface UploadProductService {
	
	void setDataCategoryAndUom(List<ProductDTO> listProductDTO);

	String save(FileUploadDTO fileUploadDTO);
	
	List<FileUploadDTO> getListFileUploadDTOs();
	
	void setModalInfo(Model model, HttpSession httpSession);
	
	void setSession(HttpSession httpSession, Boolean isModal, String title, String msg);
	
	List<ProductDTO> getListProductDTOsByIdFileUpload(Long id);
	
	Optional<FileUploadDTO> getFileUploadDTOByIdFileUpload(Long id);


}
