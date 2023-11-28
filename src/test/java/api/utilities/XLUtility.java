package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Handles Excel file operations such as reading, writing, and retrieving cell data.
 */
public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    /**
     * Constructor to initialize the class with the path to the Excel file.
     * @param path The path of the Excel file.
     */
    public XLUtility(String path)
    {
        this.path=path;
    }

    /**
     * Retrieves the row count from the specified sheet in the Excel file.
     * @param sheetName The name of the sheet in the Excel file.
     * @return The number of rows in the specified sheet.
     * @throws IOException if there's an issue accessing or reading the Excel file.
     */
    public int getRowCount(String sheetName) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }

    /**
     * Retrieves the cell count (number of columns) in the specified row and sheet.
     * @param sheetName The name of the sheet in the Excel file.
     * @param rowNum The row number in the specified sheet.
     * @return The number of cells (columns) in the specified row.
     * @throws IOException if there's an issue accessing or reading the Excel file.
     */
    public int getCellCount(String sheetName,int rowNum) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    /**
     * Retrieves data from a specific cell in the specified sheet, row, and column.
     * @param sheetName The name of the sheet in the Excel file.
     * @param rowNum The row number in the specified sheet.
     * @param colNum The column number in the specified sheet.
     * @return The data retrieved from the specified cell.
     * @throws IOException if there's an issue accessing or reading the Excel file.
     */

    public String getCellData(String sheetName,int rowNum,int colNum) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data=formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }

    /**
     * Sets data into a specific cell in the specified sheet, row, and column.
     * @param sheetName The name of the sheet in the Excel file.
     * @param rowNum The row number in the specified sheet.
     * @param colNum The column number in the specified sheet.
     * @param data The data to be set into the specified cell.
     * @throws IOException if there's an issue accessing or writing to the Excel file.
     */
    public void setCellData(String sheetName,int rowNum,int colNum,String data) throws IOException
    {
        File xlfile=new File(path);
        if(!xlfile.exists())
        {
            workbook=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            workbook.write(fo);
        }
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1)
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rowNum)==null)
            sheet.createRow(rowNum);
        row=sheet.getRow(rowNum);

        cell=row.createCell(colNum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}