package models.lombok.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.lombok.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOwnUserInfoResponseBody {

    private User item;

}

