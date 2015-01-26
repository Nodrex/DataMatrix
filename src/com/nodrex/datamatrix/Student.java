package com.nodrex.datamatrix;

import java.io.Serializable;
import java.util.Comparator;

class Student implements Serializable, Comparable<Student> {

	private static final long serialVersionUID = 4415646979965730446L;

	private String firstName = "Nodar";
	private String lastName = "Tchumbadze";
	private int age = 22;
	private double mark = 100;

	public Student() {
	}

	public Student(String firstName, String lastName, int age, double mark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mark = mark;
	}

	public Student(Student student) {
		this(student.firstName, student.lastName, student.age, student.mark);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public double getMark() {
		return mark;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@Override
	public int compareTo(Student o) {
		if (this.mark == o.mark)
			return 0;
		if (this.mark < o.mark)
			return -1;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		Student otherStudent = (Student) obj;
		if (this.firstName.equals(otherStudent.firstName)
				&& this.lastName.equals(otherStudent.lastName)
				&& this.age == otherStudent.age
				&& this.mark == otherStudent.mark)
			return true;
		return false;
	}

	@Override
	public String toString() {
		String studentDescription = "";
		studentDescription += this.firstName + "\n";
		studentDescription += this.lastName + "\n";
		studentDescription += "Age: " + this.age + "\n";
		studentDescription += "Mark: " + this.mark + "\n";
		studentDescription += "____________________" + "\n";
		return studentDescription;
	}

}

abstract class MyComparator implements Comparator<Student[]> {

	@Override
	public int compare(Student[] o1, Student[] o2) {
		return 0;
	}

}
