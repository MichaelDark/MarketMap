package ua.nure.marketmap.Model;

import java.util.Objects;

public class Comment
{
    private Outlet subject;
    private User user;
    private String text;
    private Comment replyTo;
    private Double rating;

    public Comment(Outlet subject, User user, String text, Comment replyTo, Double rating) {
        this.subject = subject;
        this.user = user;
        this.text = text;
        this.replyTo = replyTo;
        this.rating = rating;
    }

    public Outlet getSubject() {
        return subject;
    }
    public void setSubject(Outlet subject) {
        this.subject = subject;
    }
    public ua.nure.marketmap.Model.User getUser() {
        return user;
    }
    public void setUser(ua.nure.marketmap.Model.User user) {
        this.user = user;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Comment getReplyTo() {
        return replyTo;
    }
    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getSubject(), comment.getSubject()) &&
                Objects.equals(getUser(), comment.getUser()) &&
                Objects.equals(getText(), comment.getText()) &&
                Objects.equals(getReplyTo(), comment.getReplyTo()) &&
                Objects.equals(getRating(), comment.getRating());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getSubject(), getUser(), getText(), getReplyTo(), getRating());
    }
}
