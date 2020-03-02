package com.demo;

import com.lpc.common.Login;
import com.demo.data.CasesDataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.demo.LoginTest;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ConfigTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://test2-html.bestjlb.com";  //请求地址
        RestAssured.basePath = "/imc/config/select";
    }

    @Test(dataProvider = "configProvider", dataProviderClass = CasesDataProvider.class)
    public void configCases(String caseNo,String testPoit,String preResult,String YorN,
                         String configType) {
        String bodyString="{\"configType\":"+configType+"}";
            Response response = given().log().all().header("JLBSESSIONID", Login.JLBSESSIONID)
                .contentType("application/json;charset=UTF-8").log().all()
                .request()
                .body(bodyString)
                .post();

        response.prettyPrint();//格式化响应报文

        //断言
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

//        System.out.print("那个数值："+json);

//
        if(response.statusCode() == 200 && jp.getInt("code") == 200)
        { //请求成功
            Assert.assertEquals(jp.get("msg"),preResult);
        }else{ //请求失败
            Assert.assertEquals(jp.get("msg"),preResult);
        }
    }
}
