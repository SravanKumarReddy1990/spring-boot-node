package com.excel.Sample.Actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.util.XSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.excel.Sample.ExcelAnn.ExcelColumn;
import com.excel.Sample.ExcelAnn.ExcelReport;

public class ExcelActionXssf {
	private XSSFWorkbook workbook = null;
	private String workbookName = "Book1.xls";
	private Map<String, String> fieldLabelMap = new HashMap<String, String>();
	private List<String> orderLabels = new ArrayList<String>();
	private CellStyle columnHeaderCellStyle = null;

	public ExcelActionXssf() {
		initialize();
	}

	private void initialize() {
		setWorkbook(new XSSFWorkbook());
		setColumnHeaderCellStyle(createColumnHeaderCellStyle());
	}

	private CellStyle createColumnHeaderCellStyle() {
		CellStyle cellStyle = getWorkbook().createCellStyle();
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		//cellStyle.setFillBackgroundColor(new HSSFColor.GREY_25_PERCENT()
			//	.getIndex());
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	public void closeWorksheet() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(getWorkbookName());
			getWorkbook().write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private XSSFSheet getSheetWithName(String name) {
		XSSFSheet sheet = workbook.getSheet(name);
		return sheet;
	}

	private void initializeForRead(InputStream inp) throws IOException {
		workbook = new XSSFWorkbook(inp);
	}

	private <T> void processAnnotations(T object) {
		Class<?> clazz = object.getClass();
		ExcelReport reportAnnotation = (ExcelReport) clazz
				.getAnnotation(ExcelReport.class);
		
		
		for (Method method : clazz.getMethods()) {

			ExcelColumn excelColumn = method.getAnnotation(ExcelColumn.class);
			if ((excelColumn != null) && !excelColumn.ignore()) {
				getFieldLabelMap().put(excelColumn.label(), method.getName());
				getOrderLabels().add(excelColumn.label());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> readData(String classname, InputStream inp)
			throws Exception {
		Class clazz = Class.forName(classname);
		processAnnotations(clazz.newInstance());
		initializeForRead(inp);
		XSSFSheet sheet = getSheetWithName("Sheet1");
		List<T> result = new ArrayList<T>();
		Map<String, String> mp = new HashMap<String, String>();
		Iterator<Row> rowIterator = sheet.rowIterator();
		int rowCount = 0;
		while (rowIterator.hasNext()) {
			T one = (T) clazz.newInstance();
			try {
				int colCount = 0;
				result.add(one);
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (rowCount == 0) {
						mp.put(colCount + "", cell.getStringCellValue()
								.toString().trim());

					} else {
						int type = cell.getCellType();
						String labelName = mp.get(colCount + "");
						String getter = getFieldLabelMap().get(labelName);
						String fieldName = getter.substring(3);
						fieldName = decapitalize(fieldName);
						Method method = constructMethod(clazz, fieldName);
						if (type == 1) {
							String value = cell.getStringCellValue();
							Object[] values = new Object[1];
							values[0] = value;
							method.invoke(one, values);
						} else if (type == 0) {
							Double num = cell.getNumericCellValue();
							Class<?> returnType = getGetterReturnClass(clazz,
									fieldName);
							if (returnType == Integer.class) {
								method.invoke(one,(Integer) num.intValue());
							} else if (returnType == Double.class) {
								method.invoke(one, (Integer) num.intValue());
							} else if (returnType == Float.class) {
								method.invoke(one, num.floatValue());
							} else if (returnType == Date.class) {
								method.invoke(one, cell.getDateCellValue());
							}
						} else if (type == 3) {
							double num = cell.getNumericCellValue();
							Object[] values = new Object[1];
							values[0] = num;
							method.invoke(one, values);
						}
					}
					colCount++;
				}
			} catch (Exception e) {
				System.out.println("from excel action xssf : "+e);
			}
			rowCount++;
		}
		return result;
	}

	private Class<?> getGetterReturnClass(Class<?> clazz, String fieldName) {
		String methodName = "get" + capitalize(fieldName);
		Class<?> returnType = null;
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
				returnType = method.getReturnType();
				break;
			}
		}
		return returnType;
	}

	@SuppressWarnings("unchecked")
	private Method constructMethod(Class clazz, String fieldName)
			throws SecurityException, NoSuchMethodException {
		Class<?> fieldClass = getGetterReturnClass(clazz, fieldName);
		return clazz.getMethod("set" + capitalize(fieldName), fieldClass);
	}

	public <T> void writeReportToExcel(List<T> data) throws Exception {
		processAnnotations(data.get(0));
		Sheet sheet = getWorkbook().createSheet(
				data.get(0).getClass().getName());
		int rowCount = 0;
		int columnCount = 0;
		Row row = sheet.createRow(rowCount++);
		for (String labelName : getOrderLabels()) {
			Cell cel = row.createCell(columnCount++);
			cel.setCellValue(labelName);
			cel.setCellStyle(getColumnHeaderCellStyle());
		}
		Class<? extends Object> classz = data.get(0).getClass();
		for (T t : data) {
			row = sheet.createRow(rowCount++);

			columnCount = 0;

			for (String label : getOrderLabels()) {
				String methodName = getFieldLabelMap().get(label);
				Cell cel = row.createCell(columnCount);
				Method method = classz.getMethod(methodName);
				Object value = method.invoke(t, (Object[]) null);
				if (value != null) {
					if (value instanceof String) {
						cel.setCellValue((String) value);
					} else if (value instanceof Long) {
						cel.setCellValue((Long) value);
					} else if (value instanceof Integer) {
						cel.setCellValue((Integer) value);
					} else if (value instanceof Double) {
						cel.setCellValue((Double) value);
					}
				}
				columnCount++;
			}
		}
	}

	public Map<String, String> getFieldLabelMap() {
		return fieldLabelMap;
	}

	public void setFieldLabelMap(Map<String, String> fieldLabelMap) {
		this.fieldLabelMap = fieldLabelMap;
	}

	public List<String> getOrderLabels() {
		return orderLabels;
	}

	public void setOrderLabels(List<String> orderLabels) {
		this.orderLabels = orderLabels;
	}

	public String capitalize(String string) {
		String capital = string.substring(0, 1).toUpperCase();
		return capital + string.substring(1);
	}

	public String decapitalize(String string) {
		String capital = string.substring(0, 1).toLowerCase();
		return capital + string.substring(1);
	}

	public String getWorkbookName() {
		return workbookName;
	}

	public void setWorkbookName(String workbookName) {
		this.workbookName = workbookName;
	}

	void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	Workbook getWorkbook() {
		return workbook;
	}

	public CellStyle getColumnHeaderCellStyle() {
		return columnHeaderCellStyle;
	}

	public void setColumnHeaderCellStyle(CellStyle columnHeaderCellStyle) {
		this.columnHeaderCellStyle = columnHeaderCellStyle;
	}

}
