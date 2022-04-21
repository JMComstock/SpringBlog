package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {

        this.postDao = postDao;
        this.userDao = userDao;
    }

    @RequestMapping("/posts")
    public String getPosts(Model model) {

        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path="/posts/{id}", method= RequestMethod.GET)
    public String post(@PathVariable long id, Model model) {
        model.addAttribute("posts", postDao.findAllById(id));
        return "posts/show";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.GET)
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String createPostView(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }

}
