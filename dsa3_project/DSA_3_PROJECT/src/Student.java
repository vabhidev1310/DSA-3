public class Student {
String studentId;
    String studentName;
    String department;
    int year;

    String preference1;
    String preference2;
    String preference3;
    String preference4;
    String preference5;

    public Student(String studentId, String studentName, String department, int year,
                   String preference1, String preference2, String preference3,
                   String preference4, String preference5) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.year = year;

        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
        this.preference4 = preference4;
        this.preference5 = preference5;
    }
}
