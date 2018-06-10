package ua.nure.marketmap.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment {
    private int ID;
    private String Text;
    private Double Rating;

    private Integer ReplyToCommentId;

    public Comment(int ID, String text, Double rating, Integer replyToCommentId) {
        this.ID = ID;
        Text = text;
        Rating = rating;
        ReplyToCommentId = replyToCommentId;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getText() {
        return Text;
    }
    public void setText(String text) {
        Text = text;
    }
    public Double getRating() {
        return Rating;
    }
    public void setRating(Double rating) {
        Rating = rating;
    }
    public Integer getReplyToCommentId() {
        return ReplyToCommentId;
    }
    public void setReplyToCommentId(Integer replyToCommentId) {
        ReplyToCommentId = replyToCommentId;
    }
}
