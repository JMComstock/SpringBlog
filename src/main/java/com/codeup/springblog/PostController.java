package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndex() {

        return "posts index page";
    }

    @RequestMapping(path="/posts/{id}", method= RequestMethod.GET)
    @ResponseBody
    public String post(@PathVariable Integer id) {

        return "This is showing post " + id;
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
