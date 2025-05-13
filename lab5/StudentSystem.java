package lab5;

import java.util.ArrayList;
import java.util.List;

public class StudentSystem {
    private float x;
    private final List<Student> students = new ArrayList<>();

    public void addStudent(int index, String surname, String department, float averageGrade, int yearOfStudy) {
        students.add(new Student(index, surname, department, averageGrade, yearOfStudy));
    }

    public List<Student> findByDepartment(String department) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                result.add(student);
            }
        }
        return result;
    }

    public double calculateAverageGrade(int index) {
        for (Student student : students) {
            if (student.getIndex() == index) {
                return student.getAverageGrade();
            }
        }
        return -1;
    }

    public List<Student> findByAverageRangeGrade(float min, float max) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getAverageGrade() >= min && student.getAverageGrade() <= max) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> groupByAverage() {
        List<Student> result = new ArrayList<>();
        for (x = 2.0f; x <= 5.0f; x += 0.5f) {
            for (Student student : students) {
                if (student.getAverageGrade() == x) {
                    result.add(student);
                }
            }
        }
        return result;
    }
}


