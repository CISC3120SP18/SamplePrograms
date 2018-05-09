package edu.cuny.brooklyn.web;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gpa")
public class GpaController {
	private Random rng;
	
	public GpaController() {
		rng = new Random();
	}

	@RequestMapping("/{studentid}")
	public String getGpa(@PathVariable(name = "studentid") String sId, Model model) {
		double gpa = rng.nextDouble()*2. + 2;
		model.addAttribute("gpa", gpa);
		model.addAttribute("sid", sId);
		return "dispgpa"; // return the name of the view. The name match a template html file.
	}
}
