package com.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "projects")
public class Project implements Serializable{
	
	private static final long serialVersionUID = 2064139337274041743L;

	@Id
	private String id;
//	@NotEmpty
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String description;	
//	@NotEmpty
	private String languages;
//	@NotEmpty
	private String type;
//	@NotEmpty
	private String status;
//	@NotEmpty
	private String IDE;
	
	private String github;
	
	public Project() {}
	
	public Project(String id, String title, Date date, String description, String languages, String type, String status, String IDE, String github) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.description = description;
		this.languages = languages;
		this.type = type;
		this.IDE = IDE;
		this.status = status;
		this.github = github;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLanguages() {
		return languages;
	}

	public void setLanguage(String languages) {
		this.languages = languages;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIDE() {
		return IDE;
	}

	public void setIDE(String IDE) {
		this.IDE = IDE;
	}
	
	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((languages == null) ? 0 : languages.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((IDE == null) ? 0 : IDE.hashCode());
		result = prime * result + ((github == null) ? 0 : github.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Project))
			return false;
		Project project = (Project) obj;
		return Objects.equals(title, project.getTitle());
	}

	@Override
	public String toString() {
		return "Project [id: " + id + ", title: " + title + ", date: " + date + ", description: " + description
				+ ", languages: " + languages + ", type: " + type +", status: " + status + ", IDE: " + IDE
				+ ", github: " + github + "]"; 
				
	}
}
