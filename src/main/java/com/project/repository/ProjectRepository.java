package com.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, Long>{
	
	List<Project> findByTitle(String title);
	
	List<Project> findByLanguages(String languages);
	
	void deleteByTitle(String title);

	//pagination
//	public Page<Project> getPaginated(int pageNum, int pageSize);
}
