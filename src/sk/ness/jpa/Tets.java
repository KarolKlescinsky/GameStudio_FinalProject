package sk.ness.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Tets {

	public static void main(String[] args) throws Exception {

		Student student = new Student();
		student.setMeno("janko");
		student.setPriezvisko("Hrasko");
		student.setVek(18);
		
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		em.persist(student);
		JpaHelper.commitTransaction();

		
		Query query =em.createQuery("SELECT s FROM Student s Where s.meno=:meno");
		query.setParameter("meno", "Ferko");
		System.out.println(query.getResultList());
		
		JpaHelper.beginTransaction();
		student=em.find(Student.class,2);
		student.setVek(35);
	//	em.remove(student);
		JpaHelper.commitTransaction();
		System.out.println(student);
		
	}

}
