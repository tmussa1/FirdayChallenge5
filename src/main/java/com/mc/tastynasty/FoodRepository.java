package com.mc.tastynasty;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {
    Iterable<Food> findAllByUpvotesInAndDownvotesIn(Iterable<Upvote>tasties, Iterable<Downvote>nasties);
}
