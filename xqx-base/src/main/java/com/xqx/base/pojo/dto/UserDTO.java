package com.xqx.base.pojo.dto;

/**
 * 用户传输信息实体
 */
public class UserDTO {

	private Long id;
	private String name;
	private String password;
	private Boolean forbidden;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String name, String password, Boolean forbidden) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.forbidden = forbidden;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getForbidden() {
		return forbidden;
	}

	public void setForbidden(Boolean forbidden) {
		this.forbidden = forbidden;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", password=" + password + ", forbidden=" + forbidden + "]";
	}

}
