package com.xqx.user.data.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_money")
public class UserMoney {
	@Id
	@GeneratedValue
	private Long id;
	private Long u_id;
	private String money;
	
	
	public UserMoney() {
		super();
	}
	public UserMoney(Long id, Long u_id, String money) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.money = money;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getU_id() {
		return u_id;
	}
	public void setU_id(Long u_id) {
		this.u_id = u_id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "UserMoney [id=" + id + ", u_id=" + u_id + ", money=" + money + "]";
	}
}
