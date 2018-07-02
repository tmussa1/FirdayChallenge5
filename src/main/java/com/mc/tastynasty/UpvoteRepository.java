package com.mc.tastynasty;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface UpvoteRepository extends CrudRepository<Upvote, Long> {
    Iterable<Upvote>findAllByFood_IdAndVotedAtAfter(Long id, LocalDateTime theTime);
}
