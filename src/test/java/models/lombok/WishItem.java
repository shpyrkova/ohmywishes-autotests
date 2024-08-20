package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishItem {

    private String id;

    @JsonProperty("_id")
    private String underscoreId;

    private String title, currency, description, link, picture, trackingId, photo, color;

    private Integer price;

    private boolean fulfilled, copiedByMe, assigned, assignedByMe, idea, assignee;

    @JsonProperty("wish_lists")
    private List<String> wishLists;

}
