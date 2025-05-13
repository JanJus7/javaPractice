package lab5;

public class Student {
    private final int index;
    private final String surname;
    private final String department;
    private final float averageGrade;
    private final int yearOfStudy;

    public Student(int index, String surname, String department, float averageGrade, int yearOfStudy) {
        this.index = index;
        this.surname = surname;
        this.department = department;
        this.averageGrade = averageGrade;
        this.yearOfStudy = yearOfStudy;
    }

    public int getIndex() {
        return index;
    }

    public String getSurname() {
        return surname;
    }

    public String getDepartment() {
        return department;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }
}
