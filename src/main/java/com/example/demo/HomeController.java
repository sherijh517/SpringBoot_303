package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CourseRespository courseRespository;

    @RequestMapping("/")
    public String listCourse (Model model){
        model.addAttribute("course", courseRespository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("courses", new Course());
        return "courseform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Course course, BindingResult result)
    {
        if (result.hasErrors()){
            return "courseform";
        }
        courseRespository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRespository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String deCourse(@PathVariable("id") long id){
        courseRespository.delete(id);
        return "redirect:/";
    }
}
