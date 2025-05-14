package lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentSystem implements StudentInterface {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(int index, String surname, String department, float averageGrade, int yearOfStudy) {
        students.add(new Student(index, surname, department, averageGrade, yearOfStudy));
    }

    @Override
    public List<Student> findByDepartment(String department) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public double calculateAverageGrade(String department) {
        double sum = 0;
        int count = 0;
        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                sum += student.getAverageGrade();
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;

    }

    @Override
    public List<Student> findByAverageRangeGrade(float min, float max) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getAverageGrade() >= min && student.getAverageGrade() <= max) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public Map<Float, List<Student>> groupByAverage() {
        Map<Float, List<Student>> grouped = new HashMap<>();
        for (Student s : students) {
            float avg = s.getAverageGrade();
            grouped.computeIfAbsent(avg, k -> new ArrayList<>())
                    .add(s);
        }
        return grouped;
    }
}


