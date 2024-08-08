package models.lombok.api;

import lombok.Data;
import models.lombok.WishItem;

@Data
public class GetWishItemResponseBody {

    private WishItem item;

}
