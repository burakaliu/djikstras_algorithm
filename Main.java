/*
Dijkstra's Algorithm implementation starter code
Christopher Kramer
*/
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList; //ArrayLists are just handy, yo
// Need Scanner and java.io.* to read files
// Need MinHeap.java in the project so that the MinHeap works
import java.util.LinkedList;

class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Welcome to Dijkstra Town!");

    // Reading the file and creating the Matrix
    // Because the size of the Matrix is in the file,
    // we only have to read through the file once

    Scanner file = new Scanner(new File("graph.txt"));

    int size = file.nextInt();
    // We can assume the first integer in the file is the size of the Matrix
    // (remember the matrix must be square so we only need a size, not rows/cols)

    // Declare the graph matrix
    int[][] graph = new int[size][size];

    //Scan all the values into the graph
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++) {
        graph[i][j] = file.nextInt();
        /*ROW-MAJOR FORMAT (ie, row is the 1st number, column is the 2nd number*/
      }
    }

    System.out.println("graph.txt loaded");
    // at this point, the graph matrix should be populated,
    // printing it just to make sure:

    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++) {
        System.out.print(graph[i][j]+"|");
      }
      System.out.println();
    }

    //vars
    int[] distances= new int[size];
    



    boolean doStuff = true;
    while(doStuff == true){
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Which node to start at?");
      int start = keyboard.nextInt();
      if (start < 0 || start >= size) {
        System.out.println("Pick a valid node");
        continue; // continue tells java to immediately start the next "round" of the loop
      }
      System.out.println("Which node to end at?");
      int end = keyboard.nextInt();
      if (end == start || end < 0 || end >= size) {
        System.out.println("Pick a valid node");
        continue;
      }

      //Dijkstra's Algorithm START!

      //priority queue
      MinHeap<DNode> pq = new MinHeap<DNode>(); //read about DNode at bottom of file!
      ArrayList<DNode> visited = new ArrayList<DNode>();//list of visited nodes

      DNode dstart = new DNode();
      dstart.num = start; dstart.prev = start; dstart.tdist = 0;
      pq.insert(dstart); //insert first node inot priority queue
      // might be missing other variables
      for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++) {
          DNode node = new DNode();
          node.num = i; node.prev = i-1; node.tdist = Integer.MAX_VALUE;
          pq.insert(node); //insert node into pq
        }
      }

      
      while (pq.peek()!= null){
        // Algorithm goes here!
        // 1 Get the next node to visit from the priority queue
        DNode visit = pq.delete();
        // NOTE: it's possible to have more than one "copy" of the same node in the priority queue
        // So you must check that the node you got from the queue is unvisited.
        for(int i = 0; i < size; i++){
          for(int j = 0; j < size; j++) {
            if (graph[visit.num][j] >= 1){
              //System.out.println(i + " " + j + " have a connection");
              int tempDistance = visit.tdist + graph[visit.num][j];
              System.out.println(visit.tdist);
              System.out.println(graph[visit.num][j]);
              if (tempDistance < visit.tdist){
                visit.tdist = tempDistance;
                visit.prev = j;
              }
            }
          }
        }
        //System.out.println("Visiting: " + visit.num);
      }
      
      
      /*
       * for each vertex V in G          //initialization; initial path set to infinite
        path[V] <- infinite
        previous[V] <- NULL
        If V != S, add V to Priority Queue PQueue
    path [S] <- 0
     
    while PQueue IS NOT EMPTY
        U <- Extract MIN from PQueue
        for each unvisited adjacent_node  V of U
            tempDistance <- path [U] + edge_weight(U, V)
            if tempDistance < path [V]
                path [V] <- tempDistance
                previous[V] <- U
    return path[], previous[]
       */

      boolean dijkgo = true;
      while(dijkgo == true){//algorithm ends when you visit the end node

        

      }


      // print out results here!


    }

  }

  // Our priority-queue has to store both the node number, the previous node, and 
  // the tentative distance - tentative distance is what represents the priority
  // and should be what determines placement in the MinHeap
  static class DNode implements Comparable {// needs to implement Comparable to work with MinHeap
    int num; // node label
    int prev; // previous node
    int tdist; // tentative distance
    LinkedList<Integer> aList[];

    public int compareTo(Object o) { //compareTo must take a generic Object as a parameter
      // because of java reasons
      DNode dn = (DNode) o; // convert it to a DNode
      if (this.tdist < dn.tdist) return -1;
      else if (this.tdist > dn.tdist) return 1;
      else return 0;
    }
  }
}