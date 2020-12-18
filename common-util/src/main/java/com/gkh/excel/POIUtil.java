package com.gkh.excel;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gekaihui
 * @Description excel读写工具类
 * @date 2020/10/21
 */
@Slf4j
public class POIUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     * @param fileName
     * @throws IOException
     */
    public static List<String[]> readExcel(String fileName) throws IOException {
        Workbook workbook = null;
        FileInputStream inputStream = null;
        // 获取Excel后缀名
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 获取Excel文件
        File excelFile = new File(fileName);
        inputStream = new FileInputStream(excelFile);
        //检查文件
        checkFile(excelFile, fileType);
        //获得Workbook工作薄对象
        workbook = getWorkBook(inputStream, fileType);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }
    public static void checkFile(File file, String fileType) throws IOException{
        //判断文件是否存在
        if(null == file){
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //判断文件是否是excel文件
        if(!fileType.equals(xls) && !fileType.equals(xlsx)){
            log.error(file + "不是excel文件");
            throw new IOException(file + "不是excel文件");
        }
    }
    public static Workbook getWorkBook(FileInputStream inputStream, String fileType) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileType.equals(xls)){
                //2003
                workbook = new HSSFWorkbook(inputStream);
            }else if(fileType.equals(xlsx)){
                //2007
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
