package com.rummikub.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String email;
	    private String password;
	    private String preferences; // JSON or string for customizations
	    private String companyName; 
	    private LocalDateTime creationDate;
	    private String profilePicturePath;
	    
	    
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public LocalDateTime getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(LocalDateTime creationDate) {
			this.creationDate = creationDate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPreferences() {
			return preferences;
		}
		public void setPreferences(String preferences) {
			this.preferences = preferences;
		}
		public String getProfilePicturePath() {
			return profilePicturePath;
		}
		public void setProfilePicturePath(String profilePicturePath) {
			this.profilePicturePath = profilePicturePath;
		}
	
}