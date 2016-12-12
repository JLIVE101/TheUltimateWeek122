package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Music;
import ca.sheridancollege.beans.Student;

public class DAO {

	SessionFactory sessionFactory = new Configuration().configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();

	public void transaction() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Student student1 = new Student(1, "Frank");
		Student student2 = new Student(2, "Sally");

		session.save(student1);
		session.save(student2);

		session.getTransaction().commit();
		session.close();
	}

	public void saveStudent(Student student) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.saveOrUpdate(student);

		session.getTransaction().commit();
		session.close();
	}

	public List<Student> getStudentList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Student");
		List<Student> studentList = (List<Student>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return studentList;
	}

	public Student getStudent(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Student.byId").setInteger("id", id);
		Student student = (Student) query.getResultList().get(0);

		session.getTransaction().commit();
		session.close();

		return student;
	}
	
	public void deleteStudent(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Student.byId").setParameter("id", id);
		Student student = (Student) query.getResultList().get(0);
		
		//delete student
		session.delete(student);

		session.getTransaction().commit();
		session.close();
	}
	
	public List<Student> getCustomizedStudent() {
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		//Query query = session.createQuery("from Student");
		//List<Student> studentList = (List<Student>) query.getResultList();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> c = cb.createQuery(Student.class);
		Root<Student> root = c.from(Student.class);
		
		c.select(root);
		c.where(cb.equal(root.get("name"), "zebra"));
		
		c.where(cb.equal(root.get("music").get("genre"), "Pop"));
		
		
		c.orderBy(cb.desc(root.get("name")));
		
		
		List<Student> studentList = session.createQuery(c).getResultList();

		session.getTransaction().commit();
		session.close();

		return studentList;
		
	}

}
