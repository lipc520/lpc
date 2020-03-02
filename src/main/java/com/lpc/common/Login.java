package com.lpc.common;

/**
 * Created by IntelliJ IDEA.
 * Description:
 * User: lipengcheng
 * Date: 2020/1/15
 */
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Login {
/*
    public static String accessToken;
*/
    public static String JLBSESSIONID;

    @Test
    public void login() {
        ValidatableResponse res=given().log().all().formParam("username", "13588184105").
                formParam("clientType", "WEB").
                formParam("password", "123456").
                header("content-type", "application/x-www-form-urlencoded").
                post("https://test2-login.bestjlb.com/login").then().log().all().
                statusCode(200);
        //获取指定头部信息的值
        JLBSESSIONID=res.extract().jsonPath().getString("result.JLBSESSIONID");
/*
        // accessToken =res.getBody().jsonPath().getString("result.access_token");
*/

        System.out.println(JLBSESSIONID);
//        accessToken=res.extract().headers().getValue("accessToken");

    }

}

