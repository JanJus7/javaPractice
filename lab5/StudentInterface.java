package lab5;

import java.util.List;

public interface StudentInterface {
    void addStudent(int index, String surname, String department, float averageGrade, int yearOfStudy);

    List<Student> findByDepartment(String department);

    double calculateAverageGrade(String department);

    List<Student> findByAverageRangeGrade(float min, float max);

    List<Student> groupByAverage();
}
