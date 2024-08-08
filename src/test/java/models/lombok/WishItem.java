package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"wish_lists"}, ignoreUnknown = true)
public class WishItem {

    private String id;

    @JsonProperty("_id")
    private String underscoreId;

    private String title, currency, description, link;

    private Integer price;
    private boolean isPrivate;

    @JsonProperty("wish_lists")
    private List<String> wishLists;

}
