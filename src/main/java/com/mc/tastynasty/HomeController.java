package com.mc.tastynasty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UpvoteRepository upvoteRepository;

    @Autowired
    DownvoteRepository downvoteRepository;

    @RequestMapping("/")
    public String showHomepage(Model model){
        model.addAttribute("afood", new Food());
        return "index";
    }

    @RequestMapping("/savefood")
    public String foodBoard(@ModelAttribute("food") Food food, Model model){
        foodRepository.save(food);
        model.addAttribute("foods", foodRepository.findAll());
        return "foodBoard";
    }
    @RequestMapping("/updatelike/{id}")
    public String updateLikes(@PathVariable("id") long id, Model model){
        Food food = foodRepository.findById(id).get();

        if(food.getUpvotes().size() > food.getDownvotes().size()){
            food.setUpvoteBigger(1);
        } else if(food.getUpvotes().size() < food.getDownvotes().size()){
            food.setUpvoteBigger(-1);
        } else if(food.getUpvotes().size() == food.getDownvotes().size()){
            food.setUpvoteBigger(0);
        }
        Upvote upvote = new Upvote();
        upvote.setFood(food);
        upvoteRepository.save(upvote);
        model.addAttribute("foods", foodRepository.findAll());
        return "foodBoard";
    }
    @RequestMapping("/updatedislike/{id}")
    public String updateDislikes(@PathVariable("id") long id, Model model){
        Food food = foodRepository.findById(id).get();

        if(food.getUpvotes().size() > food.getDownvotes().size()){
            food.setUpvoteBigger(1);
        } else if(food.getUpvotes().size() < food.getDownvotes().size()){
            food.setUpvoteBigger(-1);
        } else if(food.getUpvotes().size() == food.getDownvotes().size()){
            food.setUpvoteBigger(0);
        }
        Downvote downvote = new Downvote();
        downvote.setFood(food);
        downvoteRepository.save(downvote);
        model.addAttribute("foods", foodRepository.findAll());
        return "foodBoard";
    }
}
