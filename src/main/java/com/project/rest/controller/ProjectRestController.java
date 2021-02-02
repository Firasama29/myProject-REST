package com.project.rest.controller;

import java.io.FileNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.exception.ProjectNotFoundException;
import com.project.model.Project;
import com.project.repository.ProjectRepository;
import com.project.service.ReportService;

import net.sf.jasperreports.engine.JRException;

import com.project.dto.ResponseTemplate;
import com.project.dto.Websites;
import com.project.exception.NoDataFoundException;
import com.project.exception.ProjectAlreadyExists;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/projects")
public class ProjectRestController {
	
	Logger log = LoggerFactory.getLogger(ProjectRestController.class);
	
	@Autowired
	private ProjectRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ReportService service;
	
	//------------------------------------------------------------------------------------generate jasper report
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReport(format);
	}
	
	//------------------------------------------------------------------------------------add
	@PostMapping
	public Project saveProject(@RequestBody Project project) {
		return repository.save(project);
	}
//	public ResponseEntity<?> saveProject(@RequestBody Project project, String message) {
//		
//		List<Project> projects = repository.findAll();
//		for(Project other : projects) {
//			if(other.equals(project)) {
//				log.error("project already exists!");
//				throw new ProjectAlreadyExists(message); 
//			}
//		}
//		log.info("successfully saved");
//		repository.save(project);
//		return new ResponseEntity<>("Project added successfully", HttpStatus.OK);
//	}
	
	//------------------------------------------------------------------------------------get all
	@GetMapping("/getAll")
	public List<Project> getProjects(){
		List<Project> projects = repository.findAll();
		if(projects.isEmpty()) {
			throw new NoDataFoundException();
		}
		
		return projects;
	}
	
	//------------------------------------------------------------------------------------pagination
	public Page<Project> getPaginated(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return repository.findAll(pageable);
	}
	
	@GetMapping("/page/{pageNum}/size/{pageSize}")
	public List<Project> getProjectsPaginated(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
		Page<Project> page = getPaginated(pageNum, pageSize);
		List<Project> projects = page.getContent();
		return projects;
	}
	
//TODO ------------------------------------------------------------------------------------find by title
	@GetMapping("/title/{title}")
	public List<Project> findByTitle(@PathVariable("title") String title, Project project, String message)
			throws ProjectNotFoundException{
			
		List<Project> projects = repository.findByTitle(title); 
			log.info(title);
			if(!(projects.equals(project))) {
				log.error(message);
				new ResponseEntity<Object>(new ProjectNotFoundException(message), HttpStatus.NOT_FOUND);
			}
			return projects;
	}
	
	//------------------------------------------------------------------------------------find projects by language
	@GetMapping("/{languages}")
	public List<Project> findJavaProjects(@PathVariable("languages") String languages) throws ProjectNotFoundException{
		
		try {
			return repository.findByLanguages(languages);
		} catch(Exception e) {
			throw new ProjectNotFoundException("project does not exist");
		}
	}
	
	
//TODO 	
	@PutMapping("/title/{title}")
	public String updateProject(@PathVariable("title") String title, @RequestBody Project project) {
				log.info("updated successfully");
				return  "project '" + project.getTitle() + "' Updated Successfully\n" + repository.save(project);
	}
	
//TODO fix exception
	@DeleteMapping("/title/{title}")
	public String deleteByTitle(@PathVariable("title") String title){
		try {
			repository.deleteByTitle(title);
			log.info("deleted");
			return "Project '" + title + "' Deleted Successfully";
		}catch(Exception e) {
			log.error("no project exists!");
			throw new ProjectAlreadyExists("no");
		}
	}
}	
