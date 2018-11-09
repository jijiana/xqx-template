package com.xqx.base.vo;

import java.io.Serializable;

/**
 * Token实例
 * @author teng
 *
 */
public class Token implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户名称
	private String userName;
	// 过期时间，单位秒
	private Long expiresIn;
	// 访问令牌
    private String accessToken;
    // 刷新令牌
    private String refreshToken;

    public Token(String userName, Long expiresIn, String accessToken, String refreshToken) {
		super();
		this.userName = userName;
		this.expiresIn = expiresIn;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
    
    public String getUserName() {
		return userName;
	}
    
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "Token [userName=" + userName + ", expiresIn=" + expiresIn + ", accessToken=" + accessToken
				+ ", refreshToken=" + refreshToken + "]";
	}
	
}
