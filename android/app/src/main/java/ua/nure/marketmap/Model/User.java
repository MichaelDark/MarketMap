package ua.nure.marketmap.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String email;
    private String password;
    private List<Outlet> list;
    private double rating;
    private List<Comment> comments;

    public User(String email, String password, List<Outlet> list, double rating, List<Comment> comments) {
        this.email = email;
        this.password = password;
        this.list = new ArrayList<>(list);
        this.rating = rating;
        this.comments = new ArrayList<>(comments);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void addOutlet(Outlet e) {
        list.add(e);
    }
    public Outlet removeOutlet(Outlet deleting) {
        for(int i = 0; i < list.size(); i++) {
            Outlet current = list.get(i);
            if(current.getName() == deleting.getName() && current.getAddress() == deleting.getAddress()) {
                list.remove(i);
                return current;
            }
        }
        return null;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public Comment removeComment(Comment comment) {
        for(int i = 0; i < comments.size(); i++) {
            Comment current = comments.get(i);
            if(current.equals(comment)) {
                list.remove(i);
                return current;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.getRating(), getRating()) == 0 &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(list, user.list) &&
                Objects.equals(comments, user.comments);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), list, getRating(), comments);
    }
}
