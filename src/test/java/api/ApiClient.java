package api;

import io.restassured.response.Response;
import models.lombok.api.LoginRequestBody;
import models.lombok.UserCustomList;
import models.lombok.WishItem;

import java.util.List;

import static api.specs.RequestResponseSpecs.*;
import static io.restassured.RestAssured.given;

public class ApiClient {

    public String requestToken(String email, String password) {

        LoginRequestBody authData = new LoginRequestBody();
        authData.setEmail(email);
        authData.setPassword(password);

        Response response = given(requestSpec)
                .body(authData)
                .post("/v2/auth/login")
                .then()
                .extract()
                .response();

        return response.path("token");
    }

    public Response createWishItem(String token, WishItem wishItem) {

        return given(authorizedRequestSpec(token))
                .body(wishItem)
                .post("/v2/users/self/wishes")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getWishItemAuthorized(String token, String id) {
        return given(authorizedRequestSpec(token))
                .get("/v3/wishes/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getWishItemNonAuthorized(String id) {
        return given(requestSpec)
                .get("/v3/wishes/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response deleteWishItem(String token, String id) {
        return given(authorizedRequestSpec(token))
                .delete("/v2/users/self/wishes/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response createUserCustomList(String token, String title, String description) {

        UserCustomList userCustomListData = new UserCustomList();
        userCustomListData.setTitle(title);
        userCustomListData.setDescription(description);

        return given(authorizedRequestSpec(token))
                .body(userCustomListData)
                .post("/v2/users/self/wish-lists")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response deleteUserCustomList(String token, String id) {
        return given(authorizedRequestSpec(token))
                .delete("/v2/users/self/wish-lists/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public void addWishItemToList(String token, WishItem wishItem, String listId) {

        WishItem updatedWishItem = WishItem.builder()
                .title(wishItem.getTitle())
                .wishLists(List.of(listId))
                .build();

        given(authorizedRequestSpec(token))
                .body(updatedWishItem)
                .put("/v2/users/self/wishes/" + wishItem.getId())
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getOwnUserInfo(String token) {

        return given(authorizedRequestSpec(token))
                .get("/v3/own-user")
                .then()
                .log()
                .all()
                .spec(responseSpec)
                .extract()
                .response();
    }

}
