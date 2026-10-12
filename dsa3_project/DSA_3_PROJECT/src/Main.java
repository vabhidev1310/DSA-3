import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filePath =
            "../data/student_lab_allocation_dataset_one_row_per_student.csv";

        ArrayList<Student> students =
                DataReader.readStudents(filePath);

        ArrayList<Laboratory> laboratories =
                DataReader.readLaboratories(filePath);

        Graph graph = new Graph();

        for (Student student : students) {
            graph.addStudent(student);
        }

        graph.createLabSeats(laboratories);
        graph.connectStudentsToSeats(laboratories);

        Map<String, String> matching = null;

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println(" STUDENT-LAB ALLOCATION SYSTEM");
            System.out.println("========================================");

            System.out.println("1. Display Students");
            System.out.println("2. Display Laboratories");
            System.out.println("3. Display Preference Graph");
            System.out.println("4. Perform Maximum Matching");
            System.out.println("5. View Allocation");
            System.out.println("6. View Laboratory Report");
            System.out.println("7. View Preference Report");
            System.out.println("8. View Summary");
            System.out.println("9. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    graph.displayGraph();
                    break;

                case 2:
                    graph.createLabSeats(laboratories);
                    break;

                case 3:
                    graph.displaySeatGraph();
                    break;

                case 4:
                    matching = graph.maximumMatching(students);

                    System.out.println(
                        "\nMaximum matching completed."
                    );

                    System.out.println(
                        "Total Allocated Students: " +
                        matching.size()
                    );

                    break;

                case 5:

                    if (matching == null) {

                        System.out.println(
                            "\nPlease perform Maximum Matching first."
                        );

                    } else {

                        graph.displayMatching(
                            matching,
                            laboratories,
                            students
                        );
                    }

                    break;

                case 6:

                    if (matching == null) {

                        System.out.println(
                            "\nPlease perform Maximum Matching first."
                        );

                    } else {

                        graph.displayLabReport(
                            matching,
                            laboratories
                        );
                    }

                    break;

                case 7:

                    if (matching == null) {

                        System.out.println(
                            "\nPlease perform Maximum Matching first."
                        );

                    } else {

                        graph.displayPreferenceReport(
                            matching,
                            laboratories,
                            students
                        );
                    }

                    break;

                case 8:

                    if (matching == null) {

                        System.out.println(
                            "\nPlease perform Maximum Matching first."
                        );

                    } else {

                        int totalCapacity = 0;

                        for (Laboratory lab : laboratories) {
                            totalCapacity += lab.capacity;
                        }

                        int allocated = matching.size();

                        int unallocated =
                                students.size() - allocated;

                        double allocationRate =
                                (allocated * 100.0) /
                                students.size();

                        System.out.println(
                            "\n========== ALLOCATION SUMMARY =========="
                        );

                        System.out.println(
                            "Total Students  : " +
                            students.size()
                        );

                        System.out.println(
                            "Total Lab Seats : " +
                            totalCapacity
                        );

                        System.out.println(
                            "Allocated       : " +
                            allocated
                        );

                        System.out.println(
                            "Unallocated     : " +
                            unallocated
                        );

                        System.out.printf(
                            "Allocation Rate : %.2f%%%n",
                            allocationRate
                        );
                    }

                    break;

                case 9:

                    System.out.println(
                        "\nThank you for using the system!"
                    );

                    break;

                default:

                    System.out.println(
                        "\nInvalid choice. Please try again."
                    );
            }

        } while (choice != 9);

        scanner.close();
    }
}