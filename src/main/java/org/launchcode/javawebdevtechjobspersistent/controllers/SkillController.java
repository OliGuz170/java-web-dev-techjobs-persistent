package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public String displaySkillsIndex(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }

        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping("view/{skillId}") //***SHOULD THIS BE skillId??? line 45/46
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional optEmployer = skillRepository.findById(skillId);
        if (optEmployer.isPresent()) {
            Skill skill = (Skill) optEmployer.get();
            model.addAttribute("skills", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}

//Copied from EmployerController. Replaced "Employer" w/ "Skill"
//NOTE: NOT COMPLETELY SURE IF I USED SKILL & SKILLS CORRECTLY
//NOTE: NOT SURE IF LINE 48 FINDBYID USE IS CORRECT