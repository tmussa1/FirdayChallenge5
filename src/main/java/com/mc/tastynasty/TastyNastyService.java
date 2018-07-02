package com.mc.tastynasty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TastyNastyService {
    @Autowired
    UpvoteRepository tasties;

    @Autowired
    DownvoteRepository nasties;

    public String last5MinuteResult(Long id){
        String tastyOrNasty = "";
        ArrayList<Upvote> dishTastyVotes = (ArrayList) tasties.findAllByFood_IdAndVotedAtAfter(id, LocalDateTime.now().minusMinutes(5));
        System.out.println("Tasty Count "+ dishTastyVotes.size());

        ArrayList<Downvote> dishNastyVotes = (ArrayList) tasties.findAllByFood_IdAndVotedAtAfter(id, LocalDateTime.now().minusMinutes(5));
        System.out.println("Nasty Count "+ dishNastyVotes.size());

        if(dishTastyVotes.size() > dishNastyVotes.size()){
            tastyOrNasty="tasty";
        } else if(dishTastyVotes.size() == dishNastyVotes.size()){
            tastyOrNasty="undefined";
        } else{
            tastyOrNasty="nasty";
        }
        System.out.println(tastyOrNasty);
        return tastyOrNasty;
    }
}
