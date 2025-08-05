package com.harsh.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harsh.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String fullName;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")

    private List<Order> orders = new ArrayList<>();


    @ElementCollection
    private List<RestaurantDto> favourites = new ArrayList<>();

    public List<RestaurantDto> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<RestaurantDto> favourites) {
        this.favourites = favourites;
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Address> address = new ArrayList<>();

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public String getemail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }






    public void setPassword(String password) {
        this.password = password;
    }


//
//    public USER_ROLE getRole() {
//        return role;
//    }

//    String role;
//    Integer orders;
//    String  favorites
//    String  addresses
//    status;
}
