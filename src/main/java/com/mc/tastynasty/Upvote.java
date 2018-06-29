package com.mc.tastynasty;

import javax.persistence.*;

@Entity
public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Food food;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
