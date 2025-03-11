package com.coding.assessment.entity;

import com.coding.assessment.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String country;
    private String gender;
    @Column(name="user_type")
    private String userType;

    public User(String name, String country, Integer age, String gender, String userType) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.gender = gender;
        this.userType = userType;
    }

    public User() {}

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setName(this.name);
        userDTO.setCountry(this.country);
        userDTO.setAge(this.age);
        userDTO.setGender(this.gender);
        userDTO.setUserType(this.userType);
        return userDTO;
    }
}
