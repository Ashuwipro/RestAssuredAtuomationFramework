package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {

    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook xssfWorkbook;
    public XSSFSheet xssfSheet;
    public XSSFRow xssfRow;
    public XSSFCell xssfCell;
    public CellStyle cellStyle;
    String path;

    public XLUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        int rowCount = xssfSheet.getLastRowNum();
        xssfWorkbook.close();
        fileInputStream.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        int cellCount = xssfRow.getLastCellNum();
        xssfWorkbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        xssfCell = xssfRow.getCell(colNum);

        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try {
            data = dataFormatter.formatCellValue(xssfCell);
        }catch (Exception e){
            data = "";
        }
        xssfWorkbook.close();
        fileInputStream.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            xssfWorkbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(path);
            xssfWorkbook.write(fileOutputStream);
        }

        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);

        if (xssfWorkbook.getSheetIndex(sheetName) == -1)
            xssfWorkbook.createSheet(sheetName);
        xssfSheet = xssfWorkbook.getSheet(sheetName);

        if (xssfSheet.getRow(rowNum)==null)
            xssfSheet.createRow(rowNum);
        xssfRow = xssfSheet.getRow(rowNum);

        xssfCell = xssfRow.createCell(colNum);
        xssfCell.setCellValue(data);
        fileOutputStream = new FileOutputStream(path);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        xssfCell = xssfRow.getCell(colNum);

        cellStyle = xssfWorkbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        xssfCell.setCellStyle(cellStyle);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(rowNum);
        xssfCell = xssfRow.getCell(colNum);

        cellStyle = xssfWorkbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        xssfCell.setCellStyle(cellStyle);
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
