import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
public class DataReader {
    public static ArrayList<Student> readStudents(String filePath) {

        ArrayList<Student> students = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Skip header
            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                Student student = new Student(
                    data[0],  // Student ID
                    data[1],  // Student Name
                    data[2],  // Department
                    Integer.parseInt(data[3]), // Year
                    data[4],  // Preference 1
                    data[5],  // Preference 2
                    data[6],  // Preference 3
                    data[7],  // Preference 4
                    data[8]   // Preference 5
                );

                students.add(student);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading students.");
            e.printStackTrace();
        }

        return students;
    }
    public static ArrayList<Laboratory> readLaboratories(String filePath) {

    ArrayList<Laboratory> laboratories = new ArrayList<>();

    try {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // Skip header
        br.readLine();

        // Read first data row
        String line = br.readLine();

        String[] data = line.split(",");

        laboratories.add(new Laboratory("L01", "AI Lab",
                Integer.parseInt(data[9])));

        laboratories.add(new Laboratory("L02", "DBMS Lab",
                Integer.parseInt(data[10])));

        laboratories.add(new Laboratory("L03", "Java Lab",
                Integer.parseInt(data[11])));

        laboratories.add(new Laboratory("L04", "Python Lab",
                Integer.parseInt(data[12])));

        laboratories.add(new Laboratory("L05", "Computer Networks Lab",
                Integer.parseInt(data[13])));

        laboratories.add(new Laboratory("L06", "Operating Systems Lab",
                Integer.parseInt(data[14])));

        laboratories.add(new Laboratory("L07", "Data Science Lab",
                Integer.parseInt(data[15])));

        laboratories.add(new Laboratory("L08", "Cyber Security Lab",
                Integer.parseInt(data[16])));

        br.close();

    } catch (Exception e) {
        System.out.println("Error reading laboratories.");
        e.printStackTrace();
    }

    return laboratories;
}
}
