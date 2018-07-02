package com.mc.tastynasty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Upvote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Food food;

    private LocalDateTime votedAt;

    private String tastyOrNasty;

    public String getTastyOrNasty() {
        return tastyOrNasty;
    }

    public void setTastyOrNasty(String tastyOrNasty) {
        this.tastyOrNasty = tastyOrNasty;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }

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
