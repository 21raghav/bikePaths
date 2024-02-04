
/**
 * As per usual, my approach for designing the program was to read the assignment specifications, then start off writing
 * a half Java, half pseudocode program. Some parts were decently easy to code and others were a lot harder, like the
 * isDim method of this class and the T pop() and T pop(int K) of DLStack. The steps I took to testing the solution was
 * to start off testing using the provided TestStackMap.java file and then also create my own test file using different
 * i specifically struglled with DLStack paht method and making sure i got hte algorithm correct.
 * test cases that I assumed would be tested. Throughout the whole coding
 * process, I kept in mind that the goal was to find a path that includes all treasure chambers.
 * The class is in charge of the various chamber types and how they are marked (pushed, popped) for the path to all treasure chambers. It also looks for sealed, lighted, and dim chambers to ensure that the constraints are met.

During testing, challenges included ensuring proper handling of chambers and their connections, as well as tracking the number of discovered treasure chambers. To ensure its correctness and efficiency, the code was tested with a variety of input files and pyramid configurations. The getTreasureCount() method was used for testing purposes to ensure that the path contained the correct number of treasure chambers.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private Map pyramidMap; // Reference to the Map object representing the chambers of the pyramid
    private List<Chamber> chambersAll;
    
    public PathFinder(String fileName)  {
    	 try {
             this.pyramidMap = new Map(fileName);
         } catch (IOException e) {
             throw new RuntimeException("Error creating Map object.", e);
         }
         chambersAll = new ArrayList<>();
        
    }

    public DLStack<Chamber> path() {
        DLStack<Chamber> stack = new DLStack<>();
        int numTreasureChambers = pyramidMap.getNumTreasures();

        // Push the entrance chamber into the stack and mark it as pushed
        Chamber entrance = pyramidMap.getEntrance();
        stack.push(entrance);
        entrance.markPushed();
        chambersAll.add(entrance);

        int foundTreasureChambers = 0;

        while (!stack.isEmpty()) {
            Chamber currentChamber = stack.peek();

            if (currentChamber.isTreasure()) {
                foundTreasureChambers++;
                if (foundTreasureChambers == numTreasureChambers) {
                    return stack;
                }
            }

            Chamber nextChamber = bestChamber(currentChamber);

            if (nextChamber != null) {
                stack.push(nextChamber);
                nextChamber.markPushed();
                chambersAll.add(nextChamber);
            } else {
                stack.pop();
            }
        }

        return stack; // If no path to all treasure chambers is found, return an empty stack
    }

    public Chamber bestChamber(Chamber currentChamber) {
    	  Chamber bestTreasureChamber = null;
          Chamber bestLightedChamber = null;
          Chamber bestDimChamber = null;
          for (int i = 0; i < 6; i++) {
              Chamber neighbor = currentChamber.getNeighbour(i);
              if (neighbor != null && !neighbor.isMarked()) {
                  if (neighbor.isTreasure() && bestTreasureChamber == null) {
                      bestTreasureChamber = neighbor;
                  } else if (neighbor.isLighted() && bestLightedChamber == null) {
                      bestLightedChamber = neighbor;
                  } else if (isDim(neighbor) && bestDimChamber == null) {
                      bestDimChamber = neighbor;
                  }
              }
          }

          if (bestTreasureChamber != null) {
              return bestTreasureChamber;
          } else if (bestLightedChamber != null) {
              return bestLightedChamber;
          } else {
              return bestDimChamber;
          }
      }

    public Map getMap() {
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {
        return currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted() && hasLightedNeighbor(currentChamber);
    }

    private boolean hasLightedNeighbor(Chamber currentChamber) {
        for (int i = 0; i < 6; i++) {
            Chamber neighbor = currentChamber.getNeighbour(i);
            if (neighbor != null && neighbor.isLighted()) {
                return true;
            }
        }
        return false;
    }
    private int getTreasureCount() {
        int treasureCount = 0;
        for (Chamber chamber : chambersAll) {
            if (chamber.isTreasure()) {
                treasureCount++;
            }
        }
        return treasureCount;
    }
}
