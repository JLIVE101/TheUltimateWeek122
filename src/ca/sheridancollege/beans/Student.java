package ca.sheridancollege.beans;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Student.byId", query="from Student where id = :id")
public class Student implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Embedded
	private Music music;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", music=" + music + "]";
	}

}
