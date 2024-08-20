package api.specs;

import api.ApiClient;
import config.common.UserDataConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class RequestResponseSpecs {

    static final ApiClient apiClient = new ApiClient();
    protected static final UserDataConfig userDataConfig = ConfigFactory.create(UserDataConfig.class, System.getProperties());

    public static final RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static final RequestSpecification authorizedRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .header("X-Access-Token", apiClient.requestToken(userDataConfig.getEmail(), userDataConfig.getPassword()));

    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}