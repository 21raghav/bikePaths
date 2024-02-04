**Overview**

This Java program aims to enhance visitor accessibility in a National Park by creating optimal bike paths connecting natural treasures within the park. The park is modeled as interconnected hexagonal chambers, each with specific characteristics like sealed, lighted, dim, dark, entrance, and treasure chambers.

**Chambers Overview**

Sealed Chambers: Inaccessible to cyclists.

Lighted Chambers: Safe for biking due to skylight.

Dim Chambers: Adjacent to lighted chambers, safe for biking.

Dark Chambers: No light, unsafe for biking.

Entrance Chamber: Starting point, lighted.

Treasure Chambers: Hold valuable relics, lighted.

**Program Constraints**
Prefer treasure chambers over others.

Prefer lighted chambers over dim chambers.

Path Finding Algorithm

The program uses a stack-based algorithm to find a path from the entrance chamber to all treasure chambers, adhering to specified constraints.

**Classes Implemented**

DLStack.java: Extended stack ADT using doubly linked lists.
PathFinder.java: Computes optimal paths in the National Park.
Getting Started
Prerequisites
Java Development Kit (JDK) installed.
Running the Program
Clone the repository:

bash
Copy code
git clone <repository>
Compile and run the program:

bash
Copy code
javac *.java
java Pyramid map1.txt
Class Descriptions
DLStack: Represents an extended stack ADT using a doubly linked list.
PathFinder: Computes paths from the entrance to treasure chambers, following specified constraints.
