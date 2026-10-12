import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Graph {

    LinkedHashMap<String, ArrayList<String>> adjacencyList;
LinkedHashMap<String, ArrayList<String>> seatGraph;
    public Graph() {
        adjacencyList = new LinkedHashMap<>();
        seatGraph = new LinkedHashMap<>();
    }

    public void addStudent(Student student) {

        ArrayList<String> preferences = new ArrayList<>();

        preferences.add(student.preference1);
        preferences.add(student.preference2);
        preferences.add(student.preference3);
        preferences.add(student.preference4);
        preferences.add(student.preference5);

        adjacencyList.put(student.studentId, preferences);
    }

    public void displayGraph() {

        System.out.println("===== BIPARTITE GRAPH =====");

        for (String studentId : adjacencyList.keySet()) {

            System.out.println(
                studentId + " -> " +
                adjacencyList.get(studentId)
            );
        }
    }

    // Create seats for each laboratory
    public void createLabSeats(ArrayList<Laboratory> laboratories) {

        System.out.println("\n===== LABORATORY SEATS =====");

        for (Laboratory lab : laboratories) {

            System.out.println(lab.labName + ":");

            for (int i = 1; i <= lab.capacity; i++) {

                System.out.println(
                    "  " + lab.labId + "-Seat" + i
                );
            }
        }
    }
  public void connectStudentsToSeats(ArrayList<Laboratory> laboratories) {

    for (String studentId : adjacencyList.keySet()) {

        ArrayList<String> preferences = adjacencyList.get(studentId);

        ArrayList<String> seats = new ArrayList<>();

        for (String preference : preferences) {

            for (Laboratory lab : laboratories) {

                if (preference.equals(lab.labName)) {

                    for (int i = 1; i <= lab.capacity; i++) {

                        seats.add(lab.labId + "-Seat" + i);
                    }
                }
            }
        }

        seatGraph.put(studentId, seats);
    }
}
public void displaySeatGraph() {

    System.out.println("\n===== STUDENT TO LAB SEAT GRAPH =====");

    for (String studentId : seatGraph.keySet()) {

        System.out.println(
            studentId + " -> " + seatGraph.get(studentId)
        );
    }
}
public boolean dfsMatch(String studentId,
                        Set<String> visited,
                        Map<String, String> matchSeatToStudent) {

    ArrayList<String> seats = seatGraph.get(studentId);

    for (String seat : seats) {

        if (!visited.contains(seat)) {

            visited.add(seat);

            if (!matchSeatToStudent.containsKey(seat) ||
                dfsMatch(matchSeatToStudent.get(seat),
                         visited,
                         matchSeatToStudent)) {

                matchSeatToStudent.put(seat, studentId);

                return true;
            }
        }
    }

    return false;
}
public Map<String, String> maximumMatching(ArrayList<Student> students) {

    Map<String, String> matchSeatToStudent = new HashMap<>();

    for (Student student : students) {

        Set<String> visited = new HashSet<>();

        dfsMatch(
            student.studentId,
            visited,
            matchSeatToStudent
        );
    }

    return matchSeatToStudent;
}
public void displayMatching(
        Map<String, String> matchSeatToStudent,
        ArrayList<Laboratory> laboratories,
        ArrayList<Student> students) {

    System.out.println("\n========== ALLOCATION RESULT ==========");

    System.out.printf("%-10s %-20s %-8s %-25s%n",
            "Student ID", "Student Name", "Lab ID", "Laboratory");

    System.out.println(
            "----------------------------------------------------------------"
    );

    for (Student student : students) {

        boolean allocated = false;

        for (Map.Entry<String, String> entry : matchSeatToStudent.entrySet()) {

            if (entry.getValue().equals(student.studentId)) {

                String seat = entry.getKey();

                String labId = seat.substring(0, 3);

                String labName = "";

                for (Laboratory lab : laboratories) {

                    if (lab.labId.equals(labId)) {
                        labName = lab.labName;
                        break;
                    }
                }

                System.out.printf("%-10s %-20s %-8s %-25s%n",
                        student.studentId,
                        student.studentName,
                        labId,
                        labName);

                allocated = true;
                break;
            }
        }

        if (!allocated) {

            System.out.printf("%-10s %-20s %-8s %-25s%n",
                    student.studentId,
                    student.studentName,
                    "--",
                    "Not Allocated");
        }
    }
    int totalCapacity = 0;

for (Laboratory lab : laboratories) {
    totalCapacity += lab.capacity;
}

int allocated = matchSeatToStudent.size();

int unallocated = students.size() - allocated;

double allocationRate =
        (allocated * 100.0) / students.size();

System.out.println("\n========== ALLOCATION SUMMARY ==========");

System.out.println("Total Students  : " + students.size());
System.out.println("Total Lab Seats : " + totalCapacity);
System.out.println("Allocated       : " + allocated);
System.out.println("Unallocated     : " + unallocated);
System.out.printf("Allocation Rate : %.2f%%%n", allocationRate);
}
public void displayLabReport(
        Map<String, String> matchSeatToStudent,
        ArrayList<Laboratory> laboratories) {

    System.out.println("\n========== LABORATORY-WISE REPORT ==========");

    System.out.printf("%-25s %-10s %-10s%n",
            "Laboratory", "Allocated", "Capacity");

    System.out.println("-----------------------------------------------");

    for (Laboratory lab : laboratories) {

        int allocated = 0;

        for (String seat : matchSeatToStudent.keySet()) {

            if (seat.startsWith(lab.labId + "-")) {
                allocated++;
            }
        }

        System.out.printf("%-25s %-10d %-10d%n",
                lab.labName,
                allocated,
                lab.capacity);
    }
}
public void displayPreferenceReport(
        Map<String, String> matchSeatToStudent,
        ArrayList<Laboratory> laboratories,
        ArrayList<Student> students) {

    System.out.println("\n========== PREFERENCE RANK REPORT ==========");

    System.out.printf("%-10s %-20s %-25s %-12s%n",
            "Student ID", "Student Name", "Laboratory", "Preference");

    System.out.println(
            "---------------------------------------------------------------------"
    );

    for (Student student : students) {

        String assignedLab = null;

        // Find the seat assigned to this student
        for (Map.Entry<String, String> entry :
                matchSeatToStudent.entrySet()) {

            if (entry.getValue().equals(student.studentId)) {

                String seat = entry.getKey();
                String labId = seat.substring(0, 3);

                // Find laboratory name
                for (Laboratory lab : laboratories) {

                    if (lab.labId.equals(labId)) {
                        assignedLab = lab.labName;
                        break;
                    }
                }

                break;
            }
        }

        if (assignedLab == null) {

            System.out.printf("%-10s %-20s %-25s %-12s%n",
                    student.studentId,
                    student.studentName,
                    "Not Allocated",
                    "--");

            continue;
        }

        int preferenceRank = 0;

        if (assignedLab.equals(student.preference1)) {
            preferenceRank = 1;
        }
        else if (assignedLab.equals(student.preference2)) {
            preferenceRank = 2;
        }
        else if (assignedLab.equals(student.preference3)) {
            preferenceRank = 3;
        }
        else if (assignedLab.equals(student.preference4)) {
            preferenceRank = 4;
        }
        else if (assignedLab.equals(student.preference5)) {
            preferenceRank = 5;
        }

        System.out.printf("%-10s %-20s %-25s %-12s%n",
                student.studentId,
                student.studentName,
                assignedLab,
                getPreferenceText(preferenceRank));
    }
}
public String getPreferenceText(int rank) {

    if (rank == 1) {
        return "1st";
    } else if (rank == 2) {
        return "2nd";
    } else if (rank == 3) {
        return "3rd";
    } else if (rank == 4) {
        return "4th";
    } else if (rank == 5) {
        return "5th";
    }

    return "--";
}
}