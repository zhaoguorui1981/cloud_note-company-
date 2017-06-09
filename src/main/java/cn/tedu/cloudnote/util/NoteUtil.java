package cn.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;


public class NoteUtil {
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static String md5(String src) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		//将信息转为MD5处理,digest参数为byte[]
		byte[] output=md.digest(src.getBytes());
		String s=Base64.encodeBase64String(output);
		return s;
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(md5("123456"));
//		System.out.println(md5("1234567890"));
//		System.out.println(createId());
//		System.out.println(createId());
//		System.out.println(createId());
	}
}
