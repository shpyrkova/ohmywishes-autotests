package api;

import config.CredentialsConfig;
import io.restassured.response.Response;
import models.lombok.api.LoginRequestBody;
import models.lombok.SantaGame;
import models.lombok.UserCustomList;
import models.lombok.WishItem;
import org.aeonbits.owner.ConfigFactory;

import java.util.List;

import static api.specs.RequestResponseSpecs.*;
import static io.restassured.RestAssured.given;

public class ApiClient {

    protected static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    public String generateToken() {

        LoginRequestBody authData = new LoginRequestBody();
        authData.setEmail(credentialsConfig.getEmail());
        authData.setPassword(credentialsConfig.getPassword());

        Response response = given(requestSpec)
                .body(authData)
                .post("/v2/auth/login")
                .then()
                .extract()
                .response();

        return response.path("token");
    }

    public Response getAllUserWishes() {

        return given(authorizedRequestSpec)
                .queryParam("size", "24")
                .queryParam("page", "1")
                .get("/v2/users/self/wish-lists/all/wishes")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response createWishItem(String title, String description) {

        WishItem wishItemData = new WishItem();
        wishItemData.setDescription(description);
        wishItemData.setTitle(title);

        return given(authorizedRequestSpec)
                .body(wishItemData)
                .post("/v2/users/self/wishes")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getWishItemAuthorized(String id) {
        return given(authorizedRequestSpec)
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

    public Response deleteWishItem(String id) {
        return given(authorizedRequestSpec)
                .delete("/v2/users/self/wishes/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response createUserCustomList(String title, String description) {

        UserCustomList userCustomListData = new UserCustomList();
        userCustomListData.setTitle(title);
        userCustomListData.setDescription(description);

        return given(authorizedRequestSpec)
                .body(userCustomListData)
                .post("/v2/users/self/wish-lists")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response deleteUserCustomList(String id) {
        return given(authorizedRequestSpec)
                .delete("/v2/users/self/wish-lists/" + id)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response addWishItemToList(String wishId, String wishTitle, String listId) {

        WishItem updatedWishItem = new WishItem();
        updatedWishItem.setTitle(wishTitle);
        updatedWishItem.setWishLists(List.of(listId));

        return given(authorizedRequestSpec)
                .body(updatedWishItem)
                .put("/v2/users/self/wishes/" + wishId)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getActiveGames() {

        return given(authorizedRequestSpec)
                .get("/v2/secret-santa/games/active")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getOwnUserInfo() {

        return given(authorizedRequestSpec)
                .get("/v3/own-user")
                .then()
                .log()
                .all()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public String getWishItemIdFromWishlistByWishTitle(String title) {
        Response response = getAllUserWishes();
        List<WishItem> wishlist = response.jsonPath().getList("", WishItem.class);
        WishItem foundItem = wishlist.stream()
                .filter(item -> item.getTitle().equals(title))
                .findFirst()
                .orElse(null);

        return foundItem.getUnderscoreId();
    }

    public String getGameIdFromGameListByGameTitle(String title) {
        Response response = getActiveGames();
        List<SantaGame> gamelist = response.jsonPath().getList("", SantaGame.class);
        SantaGame game = gamelist.stream()
                .filter(item -> item.getTitle().equals(title))
                .findFirst()
                .orElse(null);

        return game.getId();
    }

}
