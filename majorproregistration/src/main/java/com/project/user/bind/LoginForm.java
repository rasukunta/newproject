package com.project.user.bind;
import lombok.Data;

@Data
public class LoginForm {
	




		private String email;
		private String userpwd;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUserpwd() {
			return userpwd;
		}
		public void setUserpwd(String userpwd) {
			this.userpwd = userpwd;
		}
	}

