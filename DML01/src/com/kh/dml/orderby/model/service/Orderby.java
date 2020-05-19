package com.kh.dml.orderby.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kh.dml.orderby.model.vo.Student;

public class Orderby {

	List<Student> sList = new ArrayList<Student>();

	public void orderby() {

		// �̸����� ��������
		// ���� �̸��� ������ ���� ������������ ����

		sList.add(new Student("C", "BClass", 95));
		sList.add(new Student("B", "UClass", 100));
		sList.add(new Student("A", "UClass", 80));
		sList.add(new Student("B", "BCLass", 85));
		sList.add(new Student("A", "AClass", 70));
		sList.add(new Student("B", "ACLass", 85));
		sList.add(new Student("B", "CCLass", 85));
		sList.add(new Student("B", "ICLass", 15));

		Collections.sort(sList);

		for (Student s : sList) {
			System.out.println(s);
		}

		// // �����ڵ�
		// sList.sort(new Comparator<Student>() {
		//
		// @Override
		// public int compare(Student o1, Student o2) {
		// if (o1.getName().equals(o2.getName())) {
		// return -(o1.getScore() - o2.getScore());
		// }
		// return o1.getName().compareTo(o2.getName());
		// }
		//
		// });

	}
}
