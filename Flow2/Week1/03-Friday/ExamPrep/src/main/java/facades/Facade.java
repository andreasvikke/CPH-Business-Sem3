package facades;

import entities.Semester;
import entities.Student;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mappers.StudentInfo;

/**
 *
 * @author andreas
 */
public class Facade {

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.count", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public long getStudentCountInSemester(long semesterId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT count(s) FROM Student s WHERE s.semester = :semester", Long.class)
                    .setParameter("semester", em.createNamedQuery("Semester.findById")
                            .setParameter("id", semesterId).getSingleResult())
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public long getStudentCountInAllSemesters() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT count(s) FROM Student s WHERE s.semester != null", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Teacher> getMostTeachedTeacher() {
        EntityManager em = getEntityManager();
        try {
            List<Teacher> teachers = em.createNamedQuery("Teacher.findAll").getResultList();

            List<Teacher> maxTeachers = new ArrayList();
            Teacher max = teachers.get(0);
            for (Teacher t : teachers) {
                if (t.getSemesterCollection().size() == max.getSemesterCollection().size()) {
                    maxTeachers.add(t);
                }
                else if (t.getSemesterCollection().size() > max.getSemesterCollection().size()) {
                    maxTeachers.removeAll(teachers);
                    maxTeachers.add(t);
                    max = t;
                }
            }
            
            return maxTeachers;
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsByFirstname(String firstname) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByFirstname").setParameter("firstname", firstname).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<StudentInfo> getAllStudentInfo() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new mappers.StudentInfo(s) FROM Student s", StudentInfo.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Student getStudent(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }
    
    public StudentInfo getStudentInfo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new mappers.StudentInfo(s) FROM Student s WHERE s.id = :id", StudentInfo.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsByLastname(String lastname) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Student.findByLastname").setParameter("lastname", lastname).getResultList();
        } finally {
            em.close();
        }
    }

    public Student createStudent(String firstname, String lastname, long semesterId) {
        EntityManager em = getEntityManager();
        Student s = new Student(firstname, lastname, em.createNamedQuery("Semester.findById", Semester.class).setParameter("id", semesterId).getSingleResult());
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public Student moveStudentSemester(long studentId, long semesterId) {
        EntityManager em = getEntityManager();
        Student s = em.createNamedQuery("Student.findById", Student.class).setParameter("id", studentId).getSingleResult();
        try {
            s.setSemester(em.createNamedQuery("Semester.findById", Semester.class).setParameter("id", semesterId).getSingleResult());
            em.getTransaction().begin();
            s = em.merge(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }
}
