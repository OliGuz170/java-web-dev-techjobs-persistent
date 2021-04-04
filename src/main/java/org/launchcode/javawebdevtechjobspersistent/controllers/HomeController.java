package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //Updates similar to Part 1 of assignment.
    //Added employerRepository
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());

/*
removed lines 44-47
 */
       //added Iterable<T> for iteration (8.4.3)
//        Iterable<Job> jobs;
//        jobs = jobRepository.findAll();
//        model.addAttribute("jobs", jobs);

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());

        //Added employer data from employerRepository into form template.
        //Same for skills
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
/*
removed lines 65-70 on 4/4
 */
        //Add employer data from employerRepository into form template.
//        Iterable<Employer> employers = employerRepository.findAll();
//        model.addAttribute("employers", employers);
//
//        List<Skill> skills = (List<Skill>) skillRepository.findAll();
//        model.addAttribute("skills", skills);

        return "add";
    }


    //Added code to select employer object that's been chosen to be affiliated w/ new job.
    //Selected employer using request parameter added to the method.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
/*
Removed: else{ from line 87
 */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } //else {

        model.addAttribute("employers", employerRepository.findById(employerId));

            //added
            Employer employer = employerRepository.findById(employerId).get();
            newJob.setEmployer(employer);

            //To get skills data from list of ids, using .findAllById(ids)
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

            jobRepository.save(newJob);

            return "redirect:";
        }

/*Added line 105 4/4 */
        @GetMapping("view/{jobId}")
        public String displayViewJob(Model model,@PathVariable int jobId) {
            return  "view";

/*Removed: lines 108-114 4/4
 */
        //Added the following
//            Optional optEmployer = jobRepository.findById(jobId);
//            if (optEmployer.isPresent()) {
//                Job job = (Job) optEmployer.get();
//                model.addAttribute("job", job);
//                return "view";
//            } else {
//                return "redirect:../";
            }
        }

