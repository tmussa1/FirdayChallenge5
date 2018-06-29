package com.mc.tastynasty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int upvoteBigger;
    private String description;
    private String imageURL;

    @OneToMany(mappedBy = "food")
    private List<Upvote> upvotes;

    @OneToMany(mappedBy = "food")
    private List<Downvote> downvotes;

    public int getUpvoteBigger() {
        return upvoteBigger;
    }

    public void setUpvoteBigger(int upvoteBigger) {
        this.upvoteBigger = upvoteBigger;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<Upvote> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(List<Upvote> upvotes) {
        this.upvotes = upvotes;
    }

    public List<Downvote> getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(List<Downvote> downvotes) {
        this.downvotes = downvotes;
    }
}
