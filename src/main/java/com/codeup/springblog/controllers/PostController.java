package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.services.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {

        this.postDao = postDao;
    }

    @RequestMapping("/posts")
    public String getPosts(Model model) {

        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path="/posts/{id}", method= RequestMethod.GET)
    public String post(@PathVariable Long id, Model model) {
        model.addAttribute("posts", postDao.findAllById(id));
        return "posts/show";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.GET)
    public String createPostForm() {
        return "posts/create";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String createPostView(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description) {
        postDao.save(new Post(title, description));
        return "redirect:/posts";
    }

}
