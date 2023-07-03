package com.export.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.export.dto.FileUploadDTO;
import com.export.dto.ProductDTO;
import com.export.service.UploadProductService;
import com.export.util.FileUtil;

@Controller
@RequestMapping("/upload-product")
public class UploadProductController {

	private UploadProductService uploadProductService;
	private HttpSession httpSession;

	public UploadProductController(UploadProductService uploadProductService, HttpSession httpSession) {
		this.httpSession = httpSession;
		this.uploadProductService = uploadProductService;
	}

	@GetMapping("/list")
	public String list(Model model) {

		uploadProductService.setModalInfo(model, httpSession);
		httpSession.removeAttribute("isModalInfo");

		model.addAttribute("title", "List File Upload");
		model.addAttribute("listFileUpload", uploadProductService.getListFileUploadDTOs());
		return "upload-product/upload-product-list";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/add")
	public String add(Model model, HttpSession httpSession) {

		if (null != httpSession.getAttribute("isUpload") && (boolean) httpSession.getAttribute("isUpload")) {

			FileUploadDTO fileUploadDTO = new FileUploadDTO();
			fileUploadDTO.setFilename((String) httpSession.getAttribute("filename"));
			fileUploadDTO.setListProductDTO((List<ProductDTO>) httpSession.getAttribute("listProductDTO"));

			httpSession.removeAttribute("isUpload");
			model.addAttribute("fileUpload", fileUploadDTO);
		}

		uploadProductService.setModalInfo(model, httpSession);
		httpSession.removeAttribute("isModalInfo");

		model.addAttribute("title", "Upload List Product");
		return "upload-product/upload-product-form";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpSession httpSession) {

		if (FileUtil.hasExcelFormat(multipartFile)) {
			try {
				List<ProductDTO> listProductDTO = FileUtil.excelToProduct(multipartFile.getInputStream());

				uploadProductService.setDataCategoryAndUom(listProductDTO);

				httpSession.setAttribute("isUpload", true);
				httpSession.setAttribute("listProductDTO", listProductDTO);
				httpSession.setAttribute("filename", multipartFile.getOriginalFilename());
			} catch (Exception e) {
				System.err.println(e.getMessage());
				uploadProductService.setSession(httpSession, true, "Warning", e.getMessage());
			}
		} else {
			uploadProductService.setSession(httpSession, true, "Warning", "Unreadable file type");
		}

		return "redirect:/upload-product/add";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute(name = "fileUpload") FileUploadDTO fileUploadDTO, HttpSession httpSession) {

		String result = uploadProductService.save(fileUploadDTO);

		if (result.contains("success")) {
			uploadProductService.setSession(httpSession, true, "Info",
					"File Upload " + fileUploadDTO.getFilename() + " success to save.");

			return "redirect:/upload-product/list";
		} else {
			uploadProductService.setSession(httpSession, true, "Warning", result);
			httpSession.setAttribute("fileUpload", fileUploadDTO);

			return "redirect:/upload-product/add";
		}
	}
	
	@GetMapping("/list-product")
	public String listDetailFileUpload(Model model, @RequestParam(name = "id") Long id) {
		
		Optional<FileUploadDTO> fileUploadDTO = uploadProductService.getFileUploadDTOByIdFileUpload(id);
		List<ProductDTO> listProductDTO = uploadProductService.getListProductDTOsByIdFileUpload(id);

		model.addAttribute("title", "List Product");
		model.addAttribute("fileUploadDTO", fileUploadDTO.isPresent() ? fileUploadDTO.get() : new FileUploadDTO());
		model.addAttribute("listProduct", null != listProductDTO ? listProductDTO : new ArrayList<>());
		return "upload-product/product-list";
	}
}
