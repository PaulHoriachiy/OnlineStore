package com.company.store.repository;

import com.company.store.entities.Feedback;

import java.util.Collection;

public interface FeedbackDAO {

    Collection<Feedback> getAllFeedback();
    Collection<Feedback> getAllFeedbackForUser(int user_id);
    Collection<Feedback> getAllFeedbackForProduct(int product_id);
    float getProductRating(int product_id);
    Feedback getUserFeedbackOnProduct(int user_id, int product_id);
    Feedback getFeedbackById(int feedb_id);

    boolean saveFeedback(Feedback feedback);
    boolean removeFeedback(int feedb_id);
}
