package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    //used @Autowired to pull the repositories
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    //index method - responds with list of all employers in database
    //CRUD findAll() method - iterable; returns list of all employers
    //17.3.2 video
    @GetMapping("employers")
    public String displayEmployerIndex(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    //add employerRepository & method to save a valid object
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        } else {

            employerRepository.save(newEmployer); //newEmployer will be saved
            return "redirect:";
        }
    }
        //renders page to view contents of an individual employer object
        @GetMapping("view/{employerId}")
        public String displayViewEmployer (Model model,@PathVariable int employerId){

            //replacing null (optEmployer) with findById() method to look
            // for employer object from data layer
            Optional optEmployer = employerRepository.findById(employerId);
            if (optEmployer.isPresent()) {
                Employer employer = (Employer) optEmployer.get();
                model.addAttribute("employer", employer);
                return "employers/view";
            } else {
                return "redirect:../";
            }
        }
    }
