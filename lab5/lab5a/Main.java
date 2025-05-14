package lab5.lab5a;

public class Main {
    public static void main(String[] args) {
        StudentSystem studentSystem = new StudentSystem();
        studentSystem.addStudent(254544, "Dog", "Computer Science", 3.0f, 2);
        studentSystem.addStudent(254545, "Cat", "Mathematics", 4.5f, 2);
        studentSystem.addStudent(254546, "Whale", "Computer Science", 4.5f, 1);
        studentSystem.addStudent(254547, "Leopard", "Mathematics", 2.5f, 3);
        studentSystem.addStudent(254548, "Horse", "Physics", 5, 2);
        studentSystem.addStudent(254549, "Cow", "Computer Science", 2, 2);

        System.out.println("======== Computer Science ========");
        studentSystem.findByDepartment("Computer Science").forEach(student -> {
            System.out.println(student.getIndex() + " " + student.getSurname() + " " + student.getAverageGrade());
        });

        System.out.println("======== Avg Grade Mathematics ========");
        System.out.println(studentSystem.calculateAverageGrade("Mathematics"));

        System.out.println("======== Avg Grade 2.0 - 4.0 ========");
        studentSystem.findByAverageRangeGrade(2.0f, 4.0f).forEach(student -> {
            System.out.println(student.getIndex() + " " + student.getSurname() + " " + student.getAverageGrade());
        });

        System.out.println("======== Group by Avg Grade ========");
        studentSystem.groupByAverage().forEach((avg,list) -> {
            System.out.println("Grade " + avg + ": " + list.size() + " student(s)");
        });
    }
}
