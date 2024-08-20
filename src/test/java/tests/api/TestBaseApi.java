package tests.api;

import api.ApiClient;
import config.common.ApiConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import testdata.TestDataGenerator;
import web.steps.CommonSteps;

public class TestBaseApi {

    final ApiClient apiClient = new ApiClient();
    final CommonSteps steps = new CommonSteps();
    private static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
    TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = apiConfig.getBaseUri();
    }

}