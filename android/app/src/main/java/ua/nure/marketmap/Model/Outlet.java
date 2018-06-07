package ua.nure.marketmap.Model;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class Outlet extends Building {
    private List<Category> categories;
    private List<Comment> comments;
    private Double rating;

    public Outlet(String name, String address) {
        setName(name);
        setAddress(address);
        rating = null;
    }
    public Outlet(String name, String address, List<? extends LatLng> points) {
        this(name, address);
        setPoints(points);
    }
    public Outlet(String name, String address, List<? extends LatLng> points, List<? extends Category> categories) {
        this(name, address, points);
        setCategories(categories);
    }
    public Outlet(String name, String address, List<? extends LatLng> points, List<? extends Category> categories, List<? extends Comment> comments) {
        this(name, address, points, categories);
        setComments(comments);
    }

    public int getColor() {
        return (categories.size() == 1) ? categories.get(0).getColor() : ColorCollection.shared;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
    public void setCategories(List<? extends Category> categories) {
        this.categories = new ArrayList<>(categories);
    }
    public int getCategoriesCount() { return categories.size(); };
    public Category getCategory(int index) { return categories.get(index); }
    public void removeLastCategory() {
        if(categories.size() != 0) {
            categories.remove(categories.size() - 1);
        }
    }
    public void removeAllCategories() {
        categories.clear();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void setComments(List<? extends Comment> comments) {
        this.comments = new ArrayList<>(comments);
    }
    public int getCommentsCount() { return comments.size(); };
    public Comment getComment(int index) { return comments.get(index); }
    public void removeLastComment() {
        if(comments.size() != 0) {
            comments.remove(comments.size() - 1);
        }
    }
    public void removeAllComments() {
        comments.clear();
    }
}
