package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Rrepository.PostRep;
import com.example.demo.models.Post;

@Controller
public class BlogController {

    @Autowired
    private PostRep postRep;

    @GetMapping("/blog")
    public String blogPage(Model model) {
        Iterable<Post> posts = postRep.findAll();

        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String add(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String text,
            Model model) {
        Post post = new Post(title, anons, text);

        postRep.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String detailedPage(Model model, @PathVariable(value = "id") long id) {

        if (!postRep.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> post = postRep.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "blog-detailed";
    }

    @GetMapping("/blog/{id}/update")
    public String updateItem(Model model, @PathVariable(value = "id") long id) {

        if (!postRep.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> post = postRep.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "blog-update";
    }

    @PostMapping("/blog/{id}/update")
    public String updateItem(@PathVariable(value = "id") long id, @RequestParam String title,
            @RequestParam String anons, @RequestParam String text,
            Model model) {
        Post post = postRep.findById(id).orElseThrow();

        post.SetTitle(title);
        post.SetAnons(anons);
        post.SetText(text);

        postRep.save(post);

        return "redirect:/blog";
    }


    @PostMapping("/blog/{id}/remove")
    public String deleteItem(@PathVariable(value = "id") long id, Model model) {
        Post post = postRep.findById(id).orElseThrow();

        postRep.delete(post);

        return "redirect:/blog";
    }


}