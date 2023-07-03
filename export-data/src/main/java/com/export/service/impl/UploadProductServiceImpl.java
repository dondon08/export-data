package com.export.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;

import com.export.dao.CategoryRepository;
import com.export.dao.FileUploadRepository;
import com.export.dao.UomRepository;
import com.export.dao.ProductRepository;
import com.export.dto.FileUploadDTO;
import com.export.dto.ProductDTO;
import com.export.entity.Category;
import com.export.entity.FileUpload;
import com.export.entity.Product;
import com.export.entity.Uom;
import com.export.service.UploadProductService;

@Service
public class UploadProductServiceImpl implements UploadProductService {

	private ProductRepository productRepository;
	private UomRepository uomRepository;
	private CategoryRepository categoryRepository;
	private FileUploadRepository fileUploadRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UploadProductServiceImpl(ProductRepository productRepository, UomRepository uomRepository,
			CategoryRepository categoryRepository, FileUploadRepository fileUploadRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.uomRepository = uomRepository;
		this.categoryRepository = categoryRepository;
		this.fileUploadRepository = fileUploadRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void setDataCategoryAndUom(List<ProductDTO> listProductDTO) {

		Map<Long, Uom> mapUom = getDataMapUom();
		Map<Long, Category> mapCategory = getDataMapCategory();

		listProductDTO.stream().forEach(productDTO -> {
			productDTO.setUom(null != mapUom.get(productDTO.getUomId().longValue())
					? mapUom.get(productDTO.getUomId().longValue()).getName()
					: "");
			productDTO.setCategory(null != mapCategory.get(productDTO.getCategoryId().longValue())
					? mapCategory.get(productDTO.getCategoryId().longValue()).getName()
					: "");
		});

	}

	public Map<Long, Uom> getDataMapUom() {
		List<Uom> listUom = uomRepository.findAll();

		Map<Long, Uom> map = listUom.stream().collect(Collectors.toMap(Uom::getId, uom -> uom));

		return map;
	}

	public Map<Long, Category> getDataMapCategory() {
		List<Category> listCategory = categoryRepository.findAll();

		Map<Long, Category> map = listCategory.stream()
				.collect(Collectors.toMap(Category::getId, category -> category));

		return map;
	}

	@Override
	@Transactional
	public String save(FileUploadDTO fileUploadDTO) {

		try {

			FileUpload fileUpload = new FileUpload();
			fileUpload.setFilename(fileUploadDTO.getFilename());
			fileUpload.setCreatedDate(new Date());

			fileUploadRepository.save(fileUpload);

			List<Product> listProducts = mapToEntity(fileUploadDTO.getListProductDTO());

			listProducts.stream().forEach(product -> {
				product.setFileUploadId(fileUpload.getId());
			});

			productRepository.saveAll(listProducts);

		} catch (Exception e) {
			System.err.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "product failed to save";
		}
		return "success";
	}

	public List<Product> mapToEntity(List<ProductDTO> productDTOList) {
		return productDTOList.stream().map(productDTO -> modelMapper.map(productDTO, Product.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<FileUploadDTO> getListFileUploadDTOs() {
		return fileUploadRepository.getListFileUploadDTOs();
	}

	@Override
	public void setModalInfo(Model model, HttpSession httpSession) {
		if (null != httpSession.getAttribute("isModalInfo") && (boolean) httpSession.getAttribute("isModalInfo")) {
			model.addAttribute("isModalInfo", httpSession.getAttribute("isModalInfo"));
			model.addAttribute("modalInfoTitle", httpSession.getAttribute("modalInfoTitle"));
			model.addAttribute("modalInfoMsg", httpSession.getAttribute("modalInfoMsg"));
		}
	}

	@Override
	public void setSession(HttpSession httpSession, Boolean isModal, String title, String msg) {
		httpSession.setAttribute("isModalInfo", isModal);
		httpSession.setAttribute("modalInfoTitle", title);
		httpSession.setAttribute("modalInfoMsg", msg);
	}

	@Override
	public List<ProductDTO> getListProductDTOsByIdFileUpload(Long id) {
		return productRepository.getListProductDTOsByIdFileUpload(id);
	}

	@Override
	public Optional<FileUploadDTO> getFileUploadDTOByIdFileUpload(Long id) {
		return fileUploadRepository.getFileUploadDTOByIdFileUpload(id);
	}
}
