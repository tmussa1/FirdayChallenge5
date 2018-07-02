package com.mc.tastynasty;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface DownvoteRepository extends CrudRepository<Downvote, Long> {
    Iterable<Downvote>findAllByFood_IdAndVotedAtAfter(Long id, LocalDateTime theTime);
}
