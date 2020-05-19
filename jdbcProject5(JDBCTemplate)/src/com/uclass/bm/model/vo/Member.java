package com.uclass.bm.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private static final long serialVersionUID = -8616854771760433260L;
	
	private String m_id;
	private String m_password;
	private int m_grade;
	private String m_tell;
	private Date m_rentable_date;

	public Member() {

	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public int getM_grade() {
		return m_grade;
	}

	public void setM_grade(int m_grade) {
		this.m_grade = m_grade;
	}

	public String getM_tell() {
		return m_tell;
	}

	public void setM_tell(String m_tell) {
		this.m_tell = m_tell;
	}

	public Date getM_rentable_date() {
		return m_rentable_date;
	}

	public void setM_rentable_date(Date m_rentable_date) {
		this.m_rentable_date = m_rentable_date;
	}

	@Override
	public String toString() {
		return "Member [아이디 = " + m_id + ", 패스워드 =" + m_password + ", 회원등급 =" + m_grade + ", 전화번호 =" + m_tell
				+ ", 대출가능 일자 =" + m_rentable_date + "]";
	}

}