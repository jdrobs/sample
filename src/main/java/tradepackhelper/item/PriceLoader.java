package tradepackhelper.item;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Utility class that provides the ability to load prices for an {@link Item} from a spreadsheet. 
 */
public class PriceLoader {

	// TODO: Move the hardcoded path to a settings file
	private String filePath = "src/main/resources/itemprices.xlsx";
	
	public PriceLoader() {
		// use the default filepath
	}
	
	/**
	 * If an alternative file path is needed
	 * @param filePath
	 */
	public PriceLoader(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Opens the spreadsheet and looks for the price for the specified item. The most recent price
	 * should be placed on the bottom of the column and it will return -1 if the item isnt found.
	 * @param itemName - item to load price from excel file
	 * @return the most updated price
	 * @throws IOException
	 */
	public int loadPrice(String itemName) throws IOException {
		int price = -1; // Default value if column not found or no values in column
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(inputStream);

		// Assuming first sheet is the one containing the data
		Sheet sheet = workbook.getSheetAt(0);

		// Find the column index associated with the columnName
		int columnIndex = findColumnIndex(sheet, itemName);

		if (columnIndex != -1) {
			// Iterate over rows in the column
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(columnIndex);
				if (cell != null && cell.getCellType() == CellType.NUMERIC) {
					price = (int) cell.getNumericCellValue();
				}
			}
		}

		workbook.close();
		inputStream.close();

		return price;
	}

	// find the column that has the price data for the specified item
	private int findColumnIndex(Sheet sheet, String itemName) {
		Row firstRow = sheet.getRow(0);
		int columnCount = firstRow.getLastCellNum();
		for (int i = 0; i < columnCount; i++) {
			Cell cell = firstRow.getCell(i);
			if (cell != null && cell.getCellType() == CellType.STRING && itemName.equalsIgnoreCase(cell.getStringCellValue())) {
				return i;
			}
		}
		// If column not found, return -1
		return -1;
	}

	/**
	 * Reorders the columns of the spreadsheet so that they are alphabetical. Purely cosmetic 
	 * change and just makes it easier to find things when updating prices in the spreadsheet.
	 * @throws IOException
	 */
	public void reorderColumnsAlphabetically() throws IOException {
		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheetAt(0);

		// Read the column names and their corresponding data
		Row headerRow = sheet.getRow(0);
		Map<String, List<Object>> columnsData = new HashMap<>();
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			Cell cell = headerRow.getCell(i);
			if (cell != null && cell.getCellType() == CellType.STRING) {
				String columnName = cell.getStringCellValue();
				List<Object> columnData = new ArrayList<>();
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					Row row = sheet.getRow(j);
					Cell dataCell = row.getCell(i);
					if (dataCell != null) {
						switch (dataCell.getCellType()) {
						case STRING:
							columnData.add(dataCell.getStringCellValue());
							break;
						case NUMERIC:
							columnData.add(dataCell.getNumericCellValue());
							break;
						// Handle other data types if necessary
						default:
							columnData.add(null);
						}
					} else {
						columnData.add(null);
					}
				}
				columnsData.put(columnName, columnData);
			}
		}

		// Sort column names alphabetically
		List<String> sortedColumnNames = new ArrayList<>(columnsData.keySet());
		Collections.sort(sortedColumnNames);

		// Reorder columns based on sorted column names
		for (int i = 0; i < sortedColumnNames.size(); i++) {
			String columnName = sortedColumnNames.get(i);
			List<Object> columnData = columnsData.get(columnName);
			// Update header row
			headerRow.getCell(i).setCellValue(columnName);
			// Update data rows
			for (int j = 1; j <= columnData.size(); j++) {
				Row row = sheet.getRow(j);
				Object data = columnData.get(j - 1);
				Cell dataCell = row.createCell(i);
				if (data instanceof String) {
					dataCell.setCellValue((String) data);
				} else if (data instanceof Number) {
					dataCell.setCellValue(((Number) data).doubleValue());
				} else {
					dataCell.setCellValue("");
				}
			}
		}

		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
