package com.fsvmxd67.MyWebSite.controllers;

import com.fsvmxd67.MyWebSite.models.ArticleModel;
import com.fsvmxd67.MyWebSite.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticlesController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String articlesPage(Model model) {
        Iterable<ArticleModel> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articlesPage";
    }

    @GetMapping("/articles/add")
    public String articleAddPage(Model model) {
        return "articleAddPage";
    }

    @PostMapping("/articles/add")
    public String articlePostAddPage(
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model) {
        ArticleModel articleModel = new ArticleModel(title, anons, full_text);
        articleRepository.save(articleModel);
        return "redirect:/articles";
    }

    @GetMapping("/article/{id}")
    public String articleDetailsPage(@PathVariable(value = "id") long id, Model model) {
        if (!articleRepository.existsById(id)) {
            return "redirect:/articles";
        }
        Optional<ArticleModel> articleModel = articleRepository.findById(id);
        ArrayList<ArticleModel> articleList = new ArrayList<>();
        articleModel.ifPresent(articleList::add);
        model.addAttribute("article", articleList);
        return "articleDetailsPage";
    }

    @GetMapping("/article/{id}/edit")
    public String articleRditPage(@PathVariable(value = "id") long id, Model model) {
        if (!articleRepository.existsById(id)) {
            return "redirect:/articles";
        }
        Optional<ArticleModel> articleModel = articleRepository.findById(id);
        ArrayList<ArticleModel> articleList = new ArrayList<>();
        articleModel.ifPresent(articleList::add);
        model.addAttribute("article", articleList);
        return "articleEditPage";
    }

    @PostMapping("/article/{id}/edit")
    public String articlePostUpdatePage(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model) {
        ArticleModel articleModel = articleRepository.findById(id).orElseThrow();
        articleModel.setTitle(title);
        articleModel.setAnons(anons);
        articleModel.setFull_text(full_text);
        articleRepository.save(articleModel);
        return "redirect:/articles";
    }

    @PostMapping("/article/{id}/remove")
    public String articlePostDeletePage(
            @PathVariable(value = "id") long id,
            Model model) {
        ArticleModel articleModel = articleRepository.findById(id).orElseThrow();
        articleRepository.delete(articleModel);
        return "redirect:/articles";
    }
}
