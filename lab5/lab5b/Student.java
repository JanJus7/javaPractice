package lab5.lab5b;

public record Student(
    int index,
    Person personalData,
    String department,
    double averageGrade,
    int yearOfStudy
) {
    public Student { }
}
