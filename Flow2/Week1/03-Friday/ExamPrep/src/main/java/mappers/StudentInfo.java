package mappers;

import entities.Student;

/**
 *
 * @author andreas
 */
public class StudentInfo {
    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;

    public StudentInfo(Student student) {
        this.fullName = student.getFirstname() + " " + student.getLastname();
        this.studentId = student.getId();
        if(student.getSemester() != null) {
            this.classNameThisSemester = student.getSemester().getName();
            this.classDescription = student.getSemester().getDescription();
        }
    }

    @Override
    public String toString() {
        return "StudentInfo{" + "fullName=" + fullName + ", studentId=" + studentId + ", classNameThisSemester=" + classNameThisSemester + ", classDescription=" + classDescription + '}';
    }
}
