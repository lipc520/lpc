package com.demo;

import com.demo.data.CasesDataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {
    public static String JLBSESSIONID;


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://test2-login.bestjlb.com/";  //请求地址
        RestAssured.basePath = "login";
    }

    @Test(dataProvider = "loginProvider", dataProviderClass = CasesDataProvider.class)
    public void loginCases(String caseNo,String testPoit,String preResult,String YorN,
                         String name,String pword,String cType) {

        String bodyString= "username="+ name+ "&password="+pword+ "&clientType="+cType;


        Response response = given()
                .contentType("application/x-www-form-urlencoded").log().all()
                .request()
                .body(bodyString)
                .post();

        response.prettyPrint();//格式化响应报文

        //断言
        String json = response.asString();
        JsonPath jp = new JsonPath(json);

//        System.out.print("那个数值："+json);
        JLBSESSIONID=jp.getJsonObject("result.JLBSESSIONID");

        System.out.print("JLBSESSIONID:"+JLBSESSIONID);

        if(response.statusCode() == 200 && jp.getInt("code") == 200)
        { //请求成功
            Assert.assertNull(jp.get("message"),preResult);
        }else{
            Assert.assertEquals(jp.get("message"),preResult);
        }
    }
}
