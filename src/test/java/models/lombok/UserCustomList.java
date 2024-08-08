package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCustomList {

    String id, title, fullTitle, description, slug;

    @JsonProperty("_id")
    private String underscoreId;

    Integer wishesCount;
}
