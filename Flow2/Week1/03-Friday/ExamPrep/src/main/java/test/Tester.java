package test;

import entities.Student;
import entities.Teacher;
import facades.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mappers.StudentInfo;

/**
 *
 * @author andreas
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        Facade sf = new Facade(emf);
        Student s;
        
        System.out.println("=== All Students ===");
        for(Student se : sf.getAllStudents())
            System.out.println(se);
        
        System.out.println("=== All Students With Firstname Anders ===");
        for(Student se : sf.getAllStudentsByFirstname("Anders"))
            System.out.println(se);
        
        System.out.println("=== Create New Student ===");
        //s = sf.createStudent("Andreas", "Vikke", 1);
        //System.out.println(s);
        
        System.out.println("=== Move Student to Semester ===");
        s = sf.moveStudentSemester(1, 3);
        System.out.println(s);
        
        System.out.println("=== All Students With Lastname And ===");
        for(Student se : sf.getAllStudentsByLastname("And"))
            System.out.println(se);
        
        System.out.println("=== Get Student Count ===");
        System.out.println(sf.getStudentCount());
        
        System.out.println("=== Get Student Count In Semester 1 ===");
        System.out.println(sf.getStudentCountInSemester(1));
        
        System.out.println("=== Get Student Count In All Semesters ===");
        System.out.println(sf.getStudentCountInAllSemesters());
        
        System.out.println("=== Get Teachers With Most Semesters ===");
        for(Teacher t : sf.getMostTeachedTeacher())
            System.out.println(t);
        
        System.out.println("=== Get All StudentInfo ===");
        for(StudentInfo si : sf.getAllStudentInfo())
            System.out.println(si);
        
        System.out.println("=== Get StudentInfo ===");
        System.out.println(sf.getStudentInfo(1));
    }
}
