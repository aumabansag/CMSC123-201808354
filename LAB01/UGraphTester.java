import java.util.Scanner;
public class UGraphTester{
		public void test(){
				Scanner scanner = new Scanner(System.in);
				int choice=0;
				AMGraph graph = new AMGraph();

				String vertex1 = "";
				String vertex2 = "";
				while(choice < 10){

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
										graph.removeEdge(vertex1, vertex2);
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
				System.out.println("10. Exit");
		}
} 