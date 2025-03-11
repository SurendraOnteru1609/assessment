package com.coding.assessment.dto;

import com.coding.assessment.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Country is required")
    @Pattern(regexp = "France", message = "Users should be from France")
    private String country;
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age should be grater than 18 years")
    private Integer age;
    @NotBlank(message = "Gender is required")
    private String gender;
    private String userType;
    public User toEntity() {
        return new User(this.name, this.country, this.age, this.gender, this.userType);
    }
}
