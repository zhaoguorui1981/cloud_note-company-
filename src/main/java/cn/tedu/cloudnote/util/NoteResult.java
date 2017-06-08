package cn.tedu.cloudnote.util;

import java.io.Serializable;

//设置结果对象
public class NoteResult implements Serializable{
	private String msg;//消息
	private Integer status;//状态,0表示成功
	private Object data;//返回的数据
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [msg=" + msg + ", status=" + status + ", data=" + data + "]";
	}
	
}
