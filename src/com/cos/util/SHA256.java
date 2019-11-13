package com.cos.util;

import java.security.MessageDigest;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.mysql.cj.protocol.Message;

//256bit 길이의 암호 =해시=복호화 가 안됨
public class SHA256 {
//패스원드를 암호화 해서 리턴해준다.

	public static String getEncrypt(String rawPassword, String salt) {
		// rawpassword = "qw5678qw"
		// salt="cos";
		String result = "";

		byte[] b = (rawPassword + salt).getBytes();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b); // MessageDigest 가 SHA-256 으로 암호화된 값을 들고 있음

			byte[] bResult = md.digest();

			for (byte data : bResult) {
				System.out.println(data + " ");
			}
			StringBuffer sb = new StringBuffer();
			for (byte data : bResult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			result = sb.toString();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static void main(String[] args) {
		getEncrypt("qw5678qw", "cos");
	}
}
