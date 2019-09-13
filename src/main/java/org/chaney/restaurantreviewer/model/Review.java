package org.chaney.restaurantreviewer.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long reviewId;

    @NotBlank(message = "Review Description is mandatory")
    private String reviewDescription;
    @NotBlank(message = "Restaurant name is mandatory")
    private String restauraunt;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public String getRestauraunt() {
        return restauraunt;
    }

    public void setRestauraunt(String restauraunt) {
        this.restauraunt = restauraunt;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDescriptions() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", description='" + reviewDescription + '\'' +
                ", restaurant='" + restauraunt + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
