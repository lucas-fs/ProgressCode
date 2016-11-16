package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.Feedback;
import com.teste.progresscode.model.object.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class FeedbackResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Feedback> feedbacks;

    public FeedbackResponse(Meta meta, List<Feedback> feedbacks) {
        this.meta = meta;
        this.feedbacks = feedbacks;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
