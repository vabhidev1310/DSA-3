Student-to-Laboratory Allocation System Using Maximum Bipartite Matching
📌 Project Overview
The Student-to-Laboratory Allocation System is a Java-based console application that automatically assigns students to laboratories based on their preferences and the available laboratory capacity.

The project uses the Maximum Bipartite Matching algorithm with Depth-First Search (DFS) to maximize the number of students allocated to laboratory seats.

Student details, laboratory preferences, and laboratory capacities are stored in a CSV dataset. The system processes this data and generates allocation results and reports.

🎯 Objectives
Automate student-to-laboratory allocation.
Consider students' laboratory preferences.
Respect the capacity of each laboratory.
Maximize the number of successfully allocated students.
Ensure each student receives at most one allocation.
Generate allocation, laboratory-wise, and preference-rank reports.
Demonstrate the practical application of graph algorithms.
✨ Features
Read student details and preferences from a CSV file.
Load laboratory capacities from the dataset.
Construct a bipartite preference graph.
Represent each laboratory seat as an individual vertex.
Perform maximum matching using DFS and augmenting paths.
Display allocated and unallocated students.
Generate laboratory-wise allocation reports.
Display the preference rank of assigned laboratories.
Calculate the total allocation rate.
Provide an interactive console menu.
🧠 Algorithm Used
Maximum Bipartite Matching
A bipartite graph consists of two separate sets of vertices. In this project:

Left set: Students.
Right set: Individual laboratory seats.
Edges: Connections between students and seats belonging to their preferred laboratories.
Each student can be assigned to at most one seat, and each seat can accommodate at most one student.

The project uses a DFS-based matching algorithm to find valid assignments. If a preferred seat is already occupied, the algorithm checks whether the existing assignment can be rearranged to accommodate another student.

Algorithm Steps
Read student information and laboratory capacities from the CSV dataset.
Create student and laboratory objects.
Build the student preference graph.
Create individual seats according to laboratory capacities.
Connect students to seats in their preferred laboratories.
Apply DFS-based maximum matching.
Display the final allocation and reports.
Calculate allocation statistics.
Complexity
For the standard DFS-based bipartite matching algorithm:

Time complexity: O(V × E) in the worst case.
Space complexity: O(V + E) for the graph and matching-related storage.
Here, V represents the number of vertices and E represents the number of edges in the expanded student-to-seat graph.

🛠️ Technologies Used
Programming Language: Java
Data Structure: Bipartite Graph, Adjacency List
Algorithm: Maximum Bipartite Matching using DFS
Collections: ArrayList, HashMap, LinkedHashMap, HashSet
File Handling: FileReader, BufferedReader
Dataset Format: CSV
Development Environment: Visual Studio Code or any Java-compatible IDE
Execution Environment: Terminal / Command Prompt
📂 Project Structure
DSA_3_PROJECT/
├── data/
│   └── student_lab_allocation_dataset_one_row_per_student.csv
│
├── src/
│   ├── Student.java
│   ├── Laboratory.java
│   ├── DataReader.java
│   ├── Graph.java
│   └── Main.java
│
└── README.md
File Descriptions
File Description
Student.java Stores student details and laboratory preferences.
Laboratory.java Stores laboratory IDs, names, and capacities.
DataReader.java Reads student and laboratory information from the CSV file.
Graph.java Builds the graph, performs matching, and generates reports.
Main.java Runs the application and manages the console menu.
CSV dataset Contains student details, five preferences, and laboratory capacities.
📊 Dataset Details
The project uses a single CSV dataset containing:

100 students.
8 laboratories.
5 laboratory preferences per student.
Laboratory capacities stored in the same dataset.
70 total laboratory seats.
The dataset provides the input
