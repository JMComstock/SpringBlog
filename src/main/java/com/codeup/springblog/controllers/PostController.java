package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {

        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @RequestMapping(path="/posts", method= RequestMethod.GET)
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
    public String createPostView(@Valid Post post, BindingResult bindingResult, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        if (bindingResult.hasErrors()) {

            return "posts/create";
        }
        emailService.prepareAndSend(post, "New post Created", "Your new post has been created on the Spring Blog!");
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm (@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/post/{id}/edit")
    public String editPost (@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.saveAndFlush(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost (@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
