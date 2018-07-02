package com.mc.tastynasty;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UpvoteRepository upvoteRepository;

    @Autowired
    DownvoteRepository downvoteRepository;

    @Autowired
    private TastyNastyService foodService;

    @Autowired
    private CloudinaryConfig cloudc;

    @RequestMapping("/list")
    public String showList(Model model){
        ArrayList<Food> dishList = (ArrayList) foodRepository.findAll();
        for(Food dish: dishList){
            dish.setLast5minutes(foodService.last5MinuteResult(dish.getId()));
        }
        model.addAttribute("foods", dishList);
        return "foodBoard";
    }
    @RequestMapping("/")
    public String addFood(Model model){
        model.addAttribute("afood", new Food());
        return "index";
    }
    @RequestMapping("/savefood")
    public String foodBoard(@ModelAttribute("food") Food food, Model model, MultipartHttpServletRequest request) throws IOException{
        MultipartFile f= request.getFile("file");
        if(f.isEmpty() && food.getImageURL().isEmpty()){
            return "index";
        }
        if(food.getImageURL().isEmpty()){
            Map uploadResult = cloudc.upload(f.getBytes(), ObjectUtils.asMap("resourcetype","auto"));
            String uploadURL = (String) uploadResult.get("url");
            String uploadName = (String) uploadResult.get("public_id");
            String transformedImage = cloudc.createUrl(uploadName);
            System.out.println(transformedImage);
            System.out.println("Uploaded: "+ uploadURL);
            System.out.println("Name: " + uploadName);
            food.setImageURL(transformedImage);
        }

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
        upvote.setVotedAt(LocalDateTime.now());
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
        downvote.setVotedAt(LocalDateTime.now());
        downvoteRepository.save(downvote);
        model.addAttribute("foods", foodRepository.findAll());
        return "foodBoard";
    }
}
