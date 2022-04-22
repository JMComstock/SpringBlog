package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
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
    public String createPostView(@ModelAttribute Post post) {
        User user = userDao.findById(1);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

//    @GetMapping("/posts/{id}/edit")
//    public String showEditForm(@PathVariable("id") long id, Model model) {
//        Post post = postDao.getById(id);
//        model.addAttribute("post", post);
//        return "posts/edit";
//    }
//
//    @PostMapping("/posts/{id}/edit")
//    public String edit (@PathVariable long id,
//                        @RequestParam(name = "title") String title,
//                        @RequestParam(name = "body") String body,
//                        Model model) {
//        Post post = postDao.getById(id);
//        post.setTitle(title);
//        post.setBody(body);
//        model.addAttribute("post", postDao.saveAndFlush(post));
//        return "redirect:/posts/" + id;
//    }


}
