package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String getAd(Model model) {

        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/single")
    public String getSingleAd(Model model) {

        model.addAttribute("ads", adDao.findByTitle("Couch for sale"));
        return "ads/singleAd";
    }
}
