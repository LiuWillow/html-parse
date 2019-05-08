package com.lwl.parser;

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
import java.util.List;

/**
 * @author liuweilong
 * @description
 * @date 2019/5/8 13:51
 */
public class ExcelUtils {
    public static List<BackgroundNews> listFromExcel(File file) throws IOException {
        String[] split = file.getName().split(Constants.REGEX_SPLIT);
        String suffix = split[split.length - 1];

        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        if ("xls".equals(suffix)){
            workbook = new HSSFWorkbook(inputStream);
        }
        if ("xlsx".equals(suffix)){
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            int cellCount = row.getPhysicalNumberOfCells();
            for (int j = 0; j < cellCount; j++) {
                Cell cell = row.getCell(j);
                String stringCellValue = cell.getStringCellValue();
                System.out.println(stringCellValue);
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\中非\\线上问题\\2019-05-07海关爬数据\\海关总署-需求1\\海关文章-需求1\\海关文章-需求1-batch1.xlsx");
        listFromExcel(file);
    }
}
