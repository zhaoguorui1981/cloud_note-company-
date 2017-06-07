package cn.tedu.cloudnote.entity;

import java.io.Serializable;

public class CnUser implements Serializable{
	private String cnUserId;
	private String cnUserName;
	private String cnUserPassword;
	private String cnUserNick;
	private String cnUserToken;
	public String getCnUserId() {
		return cnUserId;
	}
	public void setCnUserId(String cnUserId) {
		this.cnUserId = cnUserId;
	}
	public String getCnUserName() {
		return cnUserName;
	}
	public void setCnUserName(String cnUserName) {
		this.cnUserName = cnUserName;
	}
	public String getCnUserPassword() {
		return cnUserPassword;
	}
	public void setCnUserPassword(String cnUserPassword) {
		this.cnUserPassword = cnUserPassword;
	}
	public String getCnUserNick() {
		return cnUserNick;
	}
	public void setCnUserNick(String cnUserNick) {
		this.cnUserNick = cnUserNick;
	}
	public String getCnUserToken() {
		return cnUserToken;
	}
	public void setCnUserToken(String cnUserToken) {
		this.cnUserToken = cnUserToken;
	}
	@Override
	public String toString() {
		return "CnUser [cnUserId=" + cnUserId + ", cnUserName=" + cnUserName + ", cnUserPassword=" + cnUserPassword
				+ ", cnUserNick=" + cnUserNick + ", cnUserToken=" + cnUserToken + "]";
	}
	
}
