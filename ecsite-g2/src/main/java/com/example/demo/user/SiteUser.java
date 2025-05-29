package com.example.demo.user;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SiteUser {

	private Integer ID;

	@NotBlank(message = "パスワードは必須項目です。")
	@Size(max = 100, message = "パスワードは100文字以内で入力してください。")
	private String password;

	@NotBlank(message = "ニックネームは必須項目です。")
	@Size(max = 100, message = "ニックネームは100文字以内で入力してください。")
	private String nickName;

	private Boolean adminFlag;

	private Boolean deleteFlag;
	private String email;
	private Timestamp created_at;
	private Timestamp login_at;
	private String phone_number;

	public SiteUser() {
		super();
	}

	public Integer getID() {
		return ID;
	}

	public void setId(Integer ID) {
		this.ID = ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Boolean getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(Boolean adminFlag) {
		this.adminFlag = adminFlag;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getLogin_at() {
		return login_at;
	}

	public void setLogin_at(Timestamp login_at) {
		this.login_at = login_at;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
