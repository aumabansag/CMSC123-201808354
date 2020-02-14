public class ALGraph{
		private VertexList vertexList;
		public ALGraph(){
				vertexList = new VertexList();
		}

		public int numberOfVertices(){
				return vertexList.vertexCount();
		}
		public int numberOfEdges(){
				return vertexList.edgeCount();
		}
		public String listAdjacentVertices(String vertexName){
				return vertexList.listAdjacent(vertexName);
		}
		public boolean checkIfAdjacent(String vertexA, String vertexB){
				return vertexList.checkAdjacent(vertexA, vertexB);
		}
		public boolean checkIfConnected(String vertexA, String vertexB){
				return vertexList.checkConnected(vertexA, vertexB);
		}
		public void insertEdge(String vertexA, String vertexB){
				vertexList.connect(vertexA, vertexB);
		}
		public void deleteEdge(String vertexA, String vertexB){
				vertexList.disconnect(vertexA, vertexB);
		}
		public void insertVertex(String vertexName){
				vertexList.addVertex(vertexName);
		}
		public void removeVertex(String vertexName){
				vertexList.deleteVertex(vertexName);
		} 


		public void traverseDFS(){
				if(vertexList.head == null){
						System.out.println("Graph has no vertices!");
						return;
				}

				VertexStack vStack = new VertexStack();
				vStack.push(vertexList.head.getName());
				vertexList.visit(vertexList.head.getName());
				while(!vStack.isEmpty()){

						String curr = vStack.pop();
						System.out.print(" "+curr+" ");
						String[] adj = listAdjacentVertices(curr).split(" ");
						for(int x = 1; x < adj.length; x++){
								if(!vertexList.checkVisited(adj[x])){
										vStack.push(adj[x]);
										vertexList.visit(adj[x]);
								}
						}
				}
				vertexList.unvisit();
		}
		public void traverseBFS(){
				if(vertexList.head == null){
					System.out.println("Graph has no vertices!");
					return;	
				}
				VertexQueue vQueue = new VertexQueue();
				vQueue.enqueue(vertexList.head.getName());
				vertexList.visit(vertexList.head.getName());
				while(!vQueue.isEmpty()){
						String curr = vQueue.dequeue();
						System.out.print(" "+curr+" ");
						String[] adj = listAdjacentVertices(curr).split(" ");
						for(int x = 1; x  < adj.length; x++){
								if(!vertexList.checkVisited(adj[x])){
										vQueue.enqueue(adj[x]);
										vertexList.visit(adj[x]);
								}

						}
				}
				vertexList.unvisit();
		}
		public int[] sswupl(String vertexName){
				int[] output = vertexList.sswupl(vertexName);
				vertexList.unvisit();
				return output;
		}











































		private class VertexList{
				Vertex head;
				private int edgeCount;

				private VertexList(){
						head = null;
						edgeCount = 0;
				}



				private int vertexCount(){
						int output = 0;
						Vertex curr = head;
						while(curr != null){
								output++;
								curr = curr.next;
						}
						return output;

				}



				private int edgeCount(){
						return edgeCount;
				}



				private boolean checkAdjacent(String vertexA, String vertexB){
						Vertex curr = head;
						while(curr != null){
								if(vertexA.equals(curr.getName())){
										return curr.checkAdjacent(vertexB);
								}
								curr = curr.next;
						}
						return false;
				}



				private boolean checkConnected(String vertexA, String vertexB){
						if(!(exists(vertexA) && exists(vertexB))){
								return false;
						}

						return traverseConnection(vertexA, vertexB, 1);

				}



				private boolean traverseConnection(String vertexA, String vertexB, int step){
						if(step > vertexCount()){
								return false;
						}

						if(checkAdjacent(vertexA, vertexB)){
								return true;
						}
						else{
								String[] adjacent = listAdjacent(vertexA).split(" ");
								for(int x =0; x < adjacent.length; x++){
										if(traverseConnection(adjacent[x],vertexB,step+1)){
												return true;
										}
								}	
						}
						return false;

				}

				private boolean checkVisited(String vertexName){
						if(!exists(vertexName)){
								return false;
						}
						Vertex curr = head;
						while(curr != null){
								if(vertexName.equals(curr.getName())){
										return curr.visited();
								}
								curr = curr.next;
						}
						return false;
				}
				private void visit(String vertexName){
						Vertex curr = head;
						while(curr != null){
								if(vertexName.equals(curr.getName())){
										curr.visit();
										return;
								}

								curr = curr.next;
						}
				}
				private void unvisit(){
						Vertex curr = head;
						while(curr != null){
								curr.unvisit();
								curr = curr.next;
						}
				}

				private String listAdjacent(String vertexName){
						Vertex curr = head;
						while(curr != null){
								if(vertexName.equals(curr.getName())){
										return curr.getList();
								}
								curr = curr.next;
						}
						return "";
				}



				private void addVertex(String vertexName){
						if(exists(vertexName)){
								return;
						}
						Vertex curr = head;
						if(head == null){
								head = new Vertex(vertexName);
								return;
						}

						while(curr.next != null){
								curr = curr.next;
						}
						curr.next = new Vertex(vertexName);
				}



				private void deleteVertex(String vertexName){

						if(head == null){
							return;
						}
						if(!exists(vertexName)){
							return;
						}

						Vertex curr;
						if(vertexName.equals(head.getName())){
								if(checkAdjacent(head.getName(), head.getName())){
										edgeCount--;
								}
								head = head.next;
						}
						else{
								curr = head;
								while(curr != null){
										if(curr.next != null && vertexName.equals(curr.next.getName())){
												if(checkAdjacent(curr.next.getName(), curr.next.getName())){
														edgeCount--;
												}
												curr.next = curr.next.next;
												break;
										}
										curr = curr.next;
								}
						}

						//disconnect from all other vertices
						curr = head;
						while(curr != null){
								curr.disconnect(vertexName);
								edgeCount--;
								curr = curr.next;
						}
				}		



				private void connect(String vertexA, String vertexB){
						if(!(exists(vertexA) && exists(vertexB))){
								return;
						}
						if(checkAdjacent(vertexA, vertexB)){
								return;
						}

						Vertex curr = head;
						while(curr != null){
								if(vertexA.equals(curr.getName())){
										edgeCount++;
										curr.connect(vertexB);
										if(vertexB.equals(vertexA)){
												return;
										}
								}
								else if(vertexB.equals(curr.getName())){
										curr.connect(vertexA);	

								}
								curr = curr.next;
						}
				}



				private void disconnect(String vertexA, String vertexB){
						if(!(exists(vertexA) && exists(vertexB))){
								return;
						}
						if(!(checkAdjacent(vertexA, vertexB))){
								return;
						}
						Vertex curr = head;
						while(curr != null){
								if(vertexA.equals(curr.getName())){
										edgeCount--;
										curr.disconnect(vertexB);
										if(vertexB.equals(vertexA)){
												return;
										}
								}
								else if(vertexB.equals(curr.getName())){
										edgeCount--;
										curr.disconnect(vertexA);
								}
								curr = curr.next;
						}
				}



				private boolean exists(String vertexName){
						Vertex curr= head;
						while(curr != null){
								if(vertexName.equals(curr.getName())){
										return true;
								}
								curr = curr.next;							
						}
						return false;
				}

				private int[] sswupl(String vertexName){
						int[] output = new int[vertexCount()];
						int length = 0;
						int index = 0;

						for(int x = 0; x < output.length; x++){
								output[x] = -1;
						}
						VertexQueue vQueue = new VertexQueue();
						vQueue.enqueue(vertexName);
						output[index] = length;
						index++;
						visit(vertexName);
						boolean pushed = true;

						while(!vQueue.isEmpty()){
								String curr = vQueue.dequeue();
								if(pushed == true){
										length++;
										pushed = false;
								}
								String[] adj = listAdjacentVertices(curr).split(" ");
								for(int x = 1; x  < adj.length; x++){
										if(!vertexList.checkVisited(adj[x])){
												vQueue.enqueue(adj[x]);
												output[index] = length;
												index++; 
												visit(adj[x]);
												pushed = true;
										}
								}
						}
						return output;
				}

































				private class Vertex{
						private String name;
						private boolean visited;
						Vertex next;
						private StringList stringList;

						private Vertex(String name){
								this.name = name;
								next = null;
								visited = false;
								stringList = new StringList();
						}
						private void connect(String vertexName){
								stringList.insert(vertexName);
						}
						private void disconnect(String vertexName){
								stringList.delete(vertexName);
						}
						private String getList(){
								return stringList.getList();
						}
						private int getLength(){
								return stringList.getLength();
						}
						private boolean checkAdjacent(String vertexName){
								return stringList.find(vertexName);
						}
						private String getName(){
								return name;
						}
						private boolean visited(){
								return visited;
						}
						private void visit(){
								visited = true;
						}
						private void unvisit(){
								visited = false;
						}



































						public class StringList{
								Node head;
								private StringList(){
										head = null;
								}

								private class Node{
										private String data;
										Node next;

										private Node(String name){
												data = name;
												next = null;
										}

										private String getData(){
												return data;
										}

								}

								private void insert(String vertexName){
										Node curr = head;
										if(head == null){
												head = new Node(vertexName);
												return;
										}
										while(curr.next != null){
												curr = curr.next;
										}
										curr.next = new Node(vertexName);
								}

								private void delete(String vertex){
										while(head != null && vertex.equals(head.getData())){
												head = head.next;
										}
										if(head == null){
											return;
										}

										Node curr = head;
										while(curr != null){
												while(curr.next != null && vertex.equals(curr.next.getData())){
														curr.next = curr.next.next;
												}
												curr = curr.next;
										}
								}
								private int getLength(){
										Node curr = head;
										int counter = 0;
										while(curr!= null){
												curr = curr.next;
												counter++;
										}
										return counter;
								}
								private String getList(){
										String output = "";
										Node curr = head;
										while(curr != null){
												output = output+" "+ curr.getData();
												curr = curr.next;
										}
										return output;
								}
								private boolean find(String vertexName){
										Node curr = head;
										while(curr != null){
												if(vertexName.equals(curr.getData())){
													return true;
												}
												curr = curr.next;
										}
										return false;
								}
						}
				}
		}
}