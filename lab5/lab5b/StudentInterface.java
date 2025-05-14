package lab5.lab5b;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface StudentInterface {
    void addStudent(int index, String surname, String department, double averageGrade, int yearOfStudy);

    Stream<Student> findByDepartment(String department);

    double calculateAverageGrade(String department);

    Stream<Student> findByAverageRangeGrade(double min, double max);

    Map<Double,List<Student>> groupByAverage();
}
