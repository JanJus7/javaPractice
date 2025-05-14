package lab5.lab5a;

import java.util.List;
import java.util.Map;

public interface StudentInterface {
    void addStudent(int index, String surname, String department, float averageGrade, int yearOfStudy);

    List<Student> findByDepartment(String department);

    double calculateAverageGrade(String department);

    List<Student> findByAverageRangeGrade(float min, float max);

    Map<Float,List<Student>> groupByAverage();
}
