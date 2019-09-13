package org.chaney.restaurantreviewer.controller;

import org.chaney.restaurantreviewer.model.Review;
import org.chaney.restaurantreviewer.model.User;
import org.chaney.restaurantreviewer.repository.ReviewRepository;
import org.chaney.restaurantreviewer.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping(path="/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private WebUtils webUtils;

    @RequestMapping(value = { "/", "/reviewDashboard" })
    public String reviewDashboard(Model model) {
        //Get the user and load their owned reviews to the page
        User user = webUtils.getUser();
        model.addAttribute("reviews", user.getReviews());
        return "review/reviewDashboard";
    }
    @GetMapping(value = "/reviewCreation")
    public String reviewRegistration(Model model) {
        Review review = new Review();
        model.addAttribute("reviewCreation", review);
        return "review/reviewCreation";
    }

    @PostMapping(value = "/reviewCreation")
    public String reviewRegistration(@ModelAttribute("reviewCreation") @Valid Review review,
                                   BindingResult result) {

        //we need to find by restaurant name as you can only review a restaurant once
        Review existing = reviewRepo.findByRestaurantName(review.getRestauraunt());
        if (existing != null) {
            result.rejectValue("reviewName", null, "There is already a review with that name");
        }

        if (result.hasErrors()) {
            return "review/reviewCreation";
        }else {
            review.setUser(webUtils.getUser());
            reviewRepo.save(review);
            return "redirect:/review/reviewDashboard";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteReview", method = RequestMethod.GET)
    public String deleteReview(@RequestParam(name="reviewId") long idForDeletion) {
        reviewRepo.deleteReviewByReviewId(idForDeletion);
        return "redirect:/review/reviewDashboard";
    }

    @GetMapping(value = "/reviewUpdate")
    public String updateReview(Model model, @RequestParam(name="reviewId") int idForEditing) {
        Review review = reviewRepo.findReviewByReviewId(idForEditing);
        model.addAttribute("reviewUpdate", review);
        return "review/reviewUpdate";
    }

    @PostMapping(value = "/reviewUpdate")
    public String updateReview(@ModelAttribute("reviewUpdate") @Valid Review review,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "review/reviewUpdate";
        }else {
            review.setUser(webUtils.getUser());
            reviewRepo.save(review);
            return "redirect:/review/reviewDashboard";
        }

    }


}
