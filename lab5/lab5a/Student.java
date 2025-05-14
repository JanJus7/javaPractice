package lab5.lab5a;

public class Student extends Person {
    private final int index;
    private final String department;
    private final float averageGrade;
    private final int yearOfStudy;

    public Student(int index, String surname, String department, float averageGrade, int yearOfStudy) {
        super(surname);
        this.index = index;
        this.department = department;
        this.averageGrade = averageGrade;
        this.yearOfStudy = yearOfStudy;
    }

    public int getIndex() {
        return index;
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
