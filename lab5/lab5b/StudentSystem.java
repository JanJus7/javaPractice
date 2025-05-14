package lab5.lab5b;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentSystem implements StudentInterface {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(int index, String surname, String department, double averageGrade, int yearOfStudy) {
        students.add(new Student(index, new Person(surname), department, averageGrade, yearOfStudy));
    }

    @Override
    public Stream<Student> findByDepartment(String dept) {
        return students.stream()
                       .filter(s -> s.department().equalsIgnoreCase(dept));
    }

    @Override
    public double calculateAverageGrade(String dept) {
        return students.stream()
                       .filter(s -> s.department().equalsIgnoreCase(dept))
                       .mapToDouble(Student::averageGrade)
                       .average()
                       .orElse(0.0);
    }

    @Override
    public Stream<Student> findByAverageRangeGrade(double min, double max) {
        return students.stream()
                       .filter(s -> s.averageGrade() >= min && s.averageGrade() <= max);
    }

    @Override
    public Map<Double, List<Student>> groupByAverage() {
        return students.stream()
                       .collect(Collectors.groupingBy(Student::averageGrade));
    }
}
