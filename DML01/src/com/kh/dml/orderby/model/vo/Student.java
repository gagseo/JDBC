package com.kh.dml.orderby.model.vo;

public class Student implements Comparable<Student> {

	private String name;
	private String className;
	private int score;

	public Student() {

	}

	public Student(String name, String className, int score) {
		super();
		this.name = name;
		this.className = className;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", className=" + className + ", score=" + score + "]";
	}

	@Override
	public int compareTo(Student o) {

		// ���ڵ�
		// if (!this.name.equals(o.name)) { // �̸��� ���� ������
		// return name.compareTo(o.name); // �̸������� ����
		// } else if (this.name.equals(o.name)) { // �̸��� ������
		// return o.score - score; // ���� ������ ����
		// } else {
		// return -1;// �ƴϸ� �ƹ��͵� ��������
		// }

		// ��â�ڵ�
		if (getName().compareTo(o.getName()) == 0) {
			return o.score - getScore();
		}
		return getName().compareTo(o.name);
	}

}
