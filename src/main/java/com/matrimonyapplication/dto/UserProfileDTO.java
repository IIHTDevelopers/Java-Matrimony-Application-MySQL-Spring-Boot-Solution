package com.matrimonyapplication.dto;

import jakarta.validation.constraints.NotBlank;

public class UserProfileDTO {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String sex;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String address;

	private String likes;

	private String dislikes;

	public UserProfileDTO() {
		super();
	}

	public UserProfileDTO(Long id, @NotBlank String name, @NotBlank String sex, @NotBlank String phoneNumber,
			@NotBlank String address, String likes, String dislikes) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.likes = likes;
		this.dislikes = dislikes;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	@Override
	public String toString() {
		return "UserProfileDTO [id=" + id + ", name=" + name + ", sex=" + sex + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}
}
