package com.example.uasmobile_psikisku.model.update;

import com.google.gson.annotations.SerializedName;

public class UpdateData {

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}