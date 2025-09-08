package com.bcp.training.expenses;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RestClientMockTest {

    @InjectMock
    @RestClient
    FraudScoreService fraudScoreService;

    @Test
    public void highFraudScoreReturns400(){
        Mockito.when(
                fraudScoreService.getByAmount(Mockito.anyDouble())
        ).thenReturn(new FraudScore(500));

        given()
                .body(
                        CrudTest.generateExpenseJson(
                                "",
                                "Expense 1"
                                ,
                                "CASH"
                                ,50000
                        )
                ).contentType(ContentType.JSON)
                .when()
                .post("/expenses/score")
                .then().statusCode(400);


    }
}
