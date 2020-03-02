package com.demo.data;

import com.demo.utils.ReadExcelCases;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class CasesDataProvider {

    @DataProvider(name ="loginProvider")
    public static Object[][] loginsProvider() throws IOException, BiffException {
        String filePath = "./src/test/testCases/登录信息.xls"; //测试数据相对路径
//        File file = new File(filePath);
//        System.out.println("--------------:"+file.getAbsolutePath());
        Object[][] cases = ReadExcelCases.readCases(filePath);

     /*   System.out.print(cases);*/

        return cases;
    }
    @DataProvider(name="configProvider")
    public static Object[][] configProvider() throws IOException, BiffException {
        String filePath = "./src/test/testCases/查看开关.xls"; //测试数据相对路径
//        File file = new File(filePath);
//        System.out.println("--------------:"+file.getAbsolutePath());
        Object[][] config = ReadExcelCases.readCases(filePath);

/*        System.out.print(config);*/

        return config;
    }

}
