package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

//MVC 디자인 패턴의 M은 Model을 의미한다.
//MODEL은 vo, dao, service로 나뉜다.
//VO는 데이터베이스의 테이블에서 조회해온 한 행(ROW)의 값들을
//저장할 목적의 객체이다.

//domain object(do) == value object(vo)
//== data transfer object(dto) == entity == bean

//VO가 되기 위한 조건
//1. 직렬화 할 것 (직렬화 ID 반드시 구분해야한다.)
//2. 캡슐화 할 것 (getter,setter가 반드시 존재 해야 한다.)
//3. 기본생성자는 필수

public class Member implements Serializable {

	// 직렬화 ID 필수
	private static final long serialVersionUID = -2151007780930896964L;

	private String userId;
	private String userPwd;
	private String userName;
	private String gender; // 데이터베이스의 char도 자바에서는 String
	private int age;
	private String phone;
	private String email;
	private String etc;
	// 데이터베이스의 date자료형 자바의 java.sql.Date 타입과 매칭
	private Date enroll_date;
	private Date lastmodified;

	public Member() {

	}

	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			String etc, Date enroll_date, Date lastmodified) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.etc = etc;
		this.enroll_date = enroll_date;
		this.lastmodified = lastmodified;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public Date getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}

	public Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", gender=" + gender
				+ ", age=" + age + ", phone=" + phone + ", email=" + email + ", etc=" + etc + ", enroll_date="
				+ enroll_date + ", lastmodified=" + lastmodified + "]";
	}

}
