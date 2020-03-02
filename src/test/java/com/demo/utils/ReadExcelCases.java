package com.demo.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExcelCases {

    public static Object[][] readCases(String filePath) throws IOException, BiffException {

        InputStream inputStream = new FileInputStream(filePath);
        Workbook rwb = Workbook.getWorkbook(inputStream);

        Sheet sheet = rwb.getSheet(0);
        int rsRows = sheet.getRows(); // 获取总行数
        int rsColums = sheet.getColumns();//获取总列数

        int countY = 0;
        for (int i = 1; i < rsRows; i++) {
            if(sheet.getCell(3, i).getContents().equals("Y"))  //统计需要执行的案例数
                countY++;
        }

        Object[][] cases = new Object[countY][rsColums];

        int x =0;
        for (int i = 1; i < rsRows; i++) {
            if(sheet.getCell(3, i).getContents().equals("Y")){  //执行标识为“Y”才记录数组
                for (int j = 0; j < rsColums; j++) {
                    cases[x][j] = sheet.getCell(j, i).getContents();
                }
                x++;
            }
        }
        return cases;
    }
    public static Object[][] configCases(String filePath) throws IOException, BiffException {

        InputStream inputStream = new FileInputStream(filePath);
        Workbook rwb = Workbook.getWorkbook(inputStream);

        Sheet sheet = rwb.getSheet(0);
        int rsRows = sheet.getRows(); // 获取总行数
        int rsColums = sheet.getColumns();//获取总列数

        int countY = 0;
        for (int i = 1; i < rsRows; i++) {
            if(sheet.getCell(3, i).getContents().equals("Y"))  //统计需要执行的案例数
                countY++;
        }

        Object[][] configs = new Object[countY][rsColums];

        int x =0;
        for (int i = 1; i < rsRows; i++) {
            if(sheet.getCell(3, i).getContents().equals("Y")){  //执行标识为“Y”才记录数组
                for (int j = 0; j < rsColums; j++) {
                    configs[x][j] = sheet.getCell(j, i).getContents();
                }
                x++;
            }
        }
        return configs;
    }
}
