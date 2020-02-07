import java.util.Scanner;
public class UGraphTester{
		public void test(){
				Scanner scanner = new Scanner(System.in);
				int choice=0;
				ALGraph graph = new ALGraph();

				String vertex1 = "";
				String vertex2 = "";
				while(choice <= 12){

						printMenu();

						choice = scanner.nextInt();
						switch(choice){
								case 1:
										System.out.println("There are "+graph.numberOfVertices()+" vertices in the graph.");
										break;
								case 2:
										System.out.println("There are "+graph.numberOfEdges()+" edges in the graph.");
										break;
								case 3:
										System.out.println("Input vertex name: ");
										vertex1  = scanner.next();
										System.out.println("Adjacent vertices of " +vertex1+": "+graph.listAdjacentVertices(vertex1));
										break;
								case 4:
										System.out.println("Input vertex names: ");
										vertex1 = scanner.next();
										vertex2 = scanner.next();
										System.out.print(vertex1+" and "+vertex2+" are");
										if(!graph.checkIfAdjacent(vertex1, vertex2)){
												System.out.print(" not ");
										}
										System.out.print(" adjacent. \n");
										break;
								case 5:
										System.out.println("Input vertex names: ");
										vertex1 = scanner.next();
										vertex2 = scanner.next();
										System.out.print(vertex1+" and "+vertex2+" are");
										if(!graph.checkIfConnected(vertex1, vertex2)){
												System.out.print(" not ");
										}
										System.out.print(" connected. \n");

										break;
								case 6:
										System.out.println("Input the two vertices to connect: ");
										vertex1 = scanner.next();
										vertex2 = scanner.next();
										graph.insertEdge(vertex1, vertex2);

										break;
								case 7:
										System.out.println("Input the two vertices to disconnect: ");
										vertex1 = scanner.next();
										vertex2 = scanner.next();
										graph.deleteEdge(vertex1, vertex2);
										break;
								case 8:
										System.out.println("Input name for new vertex: ");
										vertex1= scanner.next();
										graph.insertVertex(vertex1);
										break;
								case 9:
										System.out.println("Input name of vertex to delete: ");
										vertex1 = scanner.next();
										graph.removeVertex(vertex1);
										break;
								case 10:
										System.out.println("Depth First Traversal: ");
										graph.traverseDFS();
										System.out.println("\nBreadth First Traversal: ");
										graph.traverseBFS();
										break;
								case 11:
										System.out.println("Input source node: ");
										vertex1 = scanner.next();
										graph.traverseBFS(vertex1);
										System.out.println("");
										int[] lengths = graph.sswupl(vertex1);
										for(int x =0; x< lengths.length; x++){
												if(lengths[x] != -1){
														System.out.print(lengths[x]+" ");	
												}
												
										}
										break;
								case 12: 
										System.out.println("TOPOLOGICAL SORT!");
										try{
												graph.topologicalSort(graph);	
										}catch(CyclicException e){
												System.out.println("error ka dude pare chong bro");
										}

										break;

						}		
				}
		}

		public void printMenu(){
				System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~GRAPH ADT TESTER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("1. Number of Vertices");
				System.out.println("2. Number of Edges");
				System.out.println("3. List Adjacent Vertices");
				System.out.println("4. Check if Adjacent");
				System.out.println("5. Check if Connected");
				System.out.println("6. Insert an Edge");
				System.out.println("7. Delete an Edge");
				System.out.println("8. Add a Vertex");
				System.out.println("9. Delete Vertex");
				System.out.println("10. Traverse");
				System.out.println("11. SSWUPL");
				System.out.println("12. Exit");
		}
} 