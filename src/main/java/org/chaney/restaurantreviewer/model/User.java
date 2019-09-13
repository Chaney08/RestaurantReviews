package org.chaney.restaurantreviewer.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    @NotBlank(message = "UserName is mandatory")
    private String userName;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id"))
    private List<Role> roles;
    @OneToMany(mappedBy="user")
    private List<Review> reviews;

    public User() {

    }

    public User(String userName, String encrytedPassword) {
        this.userId = userId;
        this.userName = userName;
        this.password = encrytedPassword;
    }

    //For test data
    public User(String userName, String encrytedPassword,String fullName) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = encrytedPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return this.userName + "/" + this.fullName;
    }


}
