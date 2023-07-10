package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class User {

    @ExcelCellName("Environment")
    @JsonProperty("Environment")
    private String Environment;
    @ExcelCellName("User Role")
    @JsonProperty("User Role")
    private String UserRole;
    @ExcelCellName("User Id")
    @JsonProperty("User Id")
    private String UserId;
    @ExcelCellName("Password")
    @JsonProperty("Password")
    private String Password;

    public User(){

    }
    public User(String UserRole) {
        this.UserRole = UserRole;
    }



}
