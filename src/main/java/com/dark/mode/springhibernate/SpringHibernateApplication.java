package com.dark.mode.springhibernate;

import com.dark.mode.springhibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
public class SpringHibernateApplication implements CommandLineRunner {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public SpringHibernateApplication(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateApplication.class, args);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> List<T> list(Query q) {
        return q.list();
    }

    @Override
    public void run(String... args) throws Exception {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Student> fromStudent = list(session.createQuery("from Student s where s.firstName='zoren' or s.firstName = 'claire'"));
        fromStudent.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
        entityManagerFactory.close();
    }

}
