package org.chaney.restaurantreviewer.repository;


import org.chaney.restaurantreviewer.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Review findByRestaurantName(String name);
    void deleteReviewByReviewId(long id);
    Review findReviewByReviewId(long id);
}
