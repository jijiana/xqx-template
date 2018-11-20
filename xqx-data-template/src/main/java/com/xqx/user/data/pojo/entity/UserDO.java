package com.xqx.user.data.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库实体 对应数据传输实体UserDTO用于脱敏
 */
@Entity
@Table(name = "u_user")
public class UserDO {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private Boolean forbidden;

	public UserDO() {
	}

	public UserDO(Long id, String name, String password, Boolean forbidden) {
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
		return "UserDO [id=" + id + ", name=" + name + ", password=" + password + ", forbidden=" + forbidden + "]";
	}
	
}
