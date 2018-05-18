package com.yura.lampak.store.model.entities;


public class Feedback {

    private int feedback_id;
    private int user_id;
    private int product_id;
    private int rating;
    private String content;

    public Feedback() {
    }

    public Feedback(int user_id, int product_id, int raiting, String content) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.rating = raiting;
        this.content = content;
    }

    public Feedback(int user_id, int product_id, String content) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.content = content;
    }

    public Feedback(int user_id, int product_id, int raiting) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.rating = raiting;
    }

    public int getFeedbackId() {
        return feedback_id;
    }

    public void setFeedbackId(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int raiting_id) {
        this.rating = raiting_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Feedback: " +
                "\n feed_id: " + feedback_id +
                "\n user_id: " + user_id +
                "\n prod_id: " + product_id+
                "\n rating: " + rating +
                "\n content: " + content;
    }
}
