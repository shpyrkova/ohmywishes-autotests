package tests.api;

import api.ApiClient;
import config.ApiConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import steps.CommonSteps;

public class TestBase {

    final ApiClient apiClient = new ApiClient();
    final CommonSteps steps = new CommonSteps();
    private static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = apiConfig.getBaseUri();
    }

}