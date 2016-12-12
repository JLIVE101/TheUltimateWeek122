package ca.sheridancollege.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.dao.DAO;

@Controller
public class HomeController {

	private DAO dao = new DAO();
	
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("students", dao.getStudentList());
		
		return "displayStudents";
	}
	
	@RequestMapping("/addStudent")
	public String addStudent(Model model) {

		Student student = new Student();
		model.addAttribute("student", student);
		
		return "addStudent";
	}
	
	
	
	@RequestMapping("/saveStudent")
	public String home(Model model, @ModelAttribute Student student) {

		dao.saveStudent(student);
		model.addAttribute("studentList", dao.getStudentList());
				
		return "displayStudents";
	}
	
	@RequestMapping(value="/getStudent/{id}", produces="application/json")
	@ResponseBody
	public Map<String, Object> getStudent(@PathVariable int id) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		Student student = dao.getStudent(id);
		data.put("student", student);
		
		return data;
		
	}
	 
	//this is the method you copy.
	//its pretty much the same as the above method, but instead of calling dao.getStudent which gets back 
	//only one student. we're calling dao.getStudentList() which returns all students
	@RequestMapping(value = "/getAllStudents", produces="application/json")
	@ResponseBody
	public Map<String, Object> getAllStudents() {
		
		//a Map object is a key value pair. Similar to json
		Map<String, Object> data = new HashMap<String, Object>();
		
		
		//just like you do model.setAttribute(<some string>, object) we're doing the same here
		//im storing all my students into a key called students
		//then i simply return that the data object that i created.
		data.put("students", dao.getStudentList());
		
		
		return data;
	}
	
	@RequestMapping(value = "/editStudent/{id}")
	public String editStudent(Model model, @PathVariable int id){
		
		model.addAttribute("student", dao.getStudent(id));
		
		return "addStudent";
	}
	
	@RequestMapping(value = "/deleteStudent/{id}")
	public String deleteStudent(Model model, @PathVariable int id){
		
		dao.deleteStudent(id);
		
		model.addAttribute("students", dao.getStudentList());
		
		return "displayStudents";
	}
	
	
	@RequestMapping(value = "/getCustomStudents", produces="application/json")
	@ResponseBody
	public Map<String, Object> getCustomizedStudents() {
		
		//a Map object is a key value pair. Similar to json
		Map<String, Object> data = new HashMap<String, Object>();
		
		
		//just like you do model.setAttribute(<some string>, object) we're doing the same here
		//im storing all my students into a key called students
		//then i simply return that the data object that i created.
		data.put("students", dao.getCustomizedStudent());
		
		
		return data;
	}
	
	
	

	
}
