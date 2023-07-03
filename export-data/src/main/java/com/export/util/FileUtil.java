package com.export.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.export.dto.ProductDTO;

public class FileUtil {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADER = { "product_id", "code", "name", "id_category", "qty", "price", "id_uom" };
	static String SHEET = "product";

	public static boolean hasExcelFormat(MultipartFile file) {
		return !TYPE.equals(file.getContentType()) ? false : true;
	}

	@SuppressWarnings("resource")
	public static List<ProductDTO> excelToProduct(InputStream is) throws IOException {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					Iterator<Cell> cellsInRow = currentRow.iterator();
					Cell cellA = cellsInRow.next();
					Cell cellB = cellsInRow.next();
					Cell cellC = cellsInRow.next();
					Cell cellD = cellsInRow.next();
					Cell cellE = cellsInRow.next();
					Cell cellF = cellsInRow.next();
					Cell cellG = cellsInRow.next();

					if (!(HEADER[0].equalsIgnoreCase(cellA.getStringCellValue())
							&& HEADER[1].equalsIgnoreCase(cellB.getStringCellValue())
							&& HEADER[2].equalsIgnoreCase(cellC.getStringCellValue())
							&& HEADER[3].equalsIgnoreCase(cellD.getStringCellValue())
							&& HEADER[4].equalsIgnoreCase(cellE.getStringCellValue())
							&& HEADER[5].equalsIgnoreCase(cellF.getStringCellValue())
							&& HEADER[6].equalsIgnoreCase(cellG.getStringCellValue()))) {
						throw new Exception("Columns do not match");
					}
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				ProductDTO productDTO = new ProductDTO();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						productDTO.setProductId((long) currentCell.getNumericCellValue());
						break;
					case 1:
						productDTO.setCode(currentCell.getStringCellValue());
						break;
					case 2:
						productDTO.setName(currentCell.getStringCellValue());
						break;
					case 3:
						productDTO.setCategoryId((int) currentCell.getNumericCellValue());
						break;
					case 4:
						productDTO.setQty((int) currentCell.getNumericCellValue());
						break;
					case 5:
						productDTO.setPrice(new BigDecimal(currentCell.getNumericCellValue()));
						break;
					case 6:
						productDTO.setUomId((int) currentCell.getNumericCellValue());
						break;
					default:
						break;
					}

					cellIdx++;
				}

				productDTOs.add(productDTO);
			}

			workbook.close();

			return productDTOs;
		} catch (Exception e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

}
