package com.codeup.springblog.controllers;

import com.codeup.springblog.services.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @RequestMapping(path="/posts", method= RequestMethod.GET)
    @ResponseBody
    public String postIndex(Model model) {
        ArrayList<Post> allPosts = new ArrayList<>();
        allPosts.add(new Post("title1", "description2"));
        allPosts.add(new Post("title2", "description2"));
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @RequestMapping(path="/posts/{id}", method= RequestMethod.GET)
    @ResponseBody
    public String post(@PathVariable int id, Model model) {
        String title = "1st title";
        String description = "1st description";
        model.addAttribute("post", new Post(title, description));
        return "posts/show";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPostForm() {

        return "View the form for creating a post... ";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPostView() {

        return "creates a new post";
    }

}
