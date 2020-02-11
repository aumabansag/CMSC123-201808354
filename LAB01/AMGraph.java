//unweighted, undirected
public class AMGraph{
		String[] vertexList;
		int[][] edges;
		public AMGraph(){
				vertexList = new String[0];
				edges = new int[0][0];
		}
		public int numberOfVertices(){
				return vertexList.length;
		}
		public int numberOfEdges(){
				int edgeCount = 0;
				for(int y = 0; y < edges.length; y++){
						for(int x = 0; x <=y; x++){
								if(edges[y][x] == 1){
										edgeCount++;
								}
						}
				}
				return edgeCount;
		}
		public String listAdjacentVertices(String vertexName){
				if(getIndex(vertexName) == -1){
						return null;
				}
				String output = "";
				for(int x = 0; x < edges.length; x++){
						if(edges[getIndex(vertexName)][x] == 1){
								output = output+ vertexList[x]+" ";
						}
				}
		
				return output;
		}
		public boolean checkIfAdjacent(String vertexA, String vertexB){
				if(getIndex(vertexA) == -1 || getIndex(vertexB) == -1){
						return false;
				}
				if(edges[getIndex(vertexA)][getIndex(vertexB)] == 1){
						return true;
				}
				return false;
		}
		public boolean checkIfConnected(String vertexA, String vertexB){
				return traverseConnection(vertexA,vertexB, 1);
		}
		private boolean traverseConnection(String vertexA, String vertexB, int step){
				if(step > numberOfVertices()){
						return false;
				}

				if(checkIfAdjacent(vertexA, vertexB)){
						return true;
				}
				else{
						String[] adjacent = listAdjacentVertices(vertexA).split(" ");
						for(int x =0; x < adjacent.length; x++){
								if(traverseConnection(adjacent[x],vertexB,step+1)){
										return true;
								}
						}	
				}
				return false;

		}

		public void insertVertex(String vertexName){
				if(getIndex(vertexName) != -1){
						return;
				}
				String[] temp = new String[vertexList.length+1];
				System.arraycopy(vertexList,0,temp,0,vertexList.length);
				temp[temp.length - 1] = vertexName;

				vertexList = temp;

				int[][] tempp = new int[edges.length+1][edges.length+1];
				for(int y =0; y < edges.length; y++){
						for(int x = 0; x < edges.length; x++){
								tempp[y][x] = edges[y][x];
						}
				}
				edges = tempp;

		}
		public void insertEdge(String vertexA, String vertexB){
				if(getIndex(vertexA) == -1 || getIndex(vertexB) == -1){
						return;
				}
				edges[getIndex(vertexA)][getIndex(vertexB)] = 1;
				edges[getIndex(vertexB)][getIndex(vertexA)] = 1;
		}
		public void removeVertex(String vertexName){
				if(getIndex(vertexName) == -1){
						return;
				}
				
				String[] temp = new String[vertexList.length-1];
				
				System.arraycopy(vertexList, 0, temp, 0, getIndex(vertexName));
				System.arraycopy(vertexList, getIndex(vertexName)+1, temp, 
					getIndex(vertexName),temp.length - getIndex(vertexName));
				
				vertexList = temp;

				int[][] tempp = new int[edges.length-1][edges.length-1];

				int x = 0;
				int y = 0;
				int sourceX = 0;
				int sourceY = 0;
				for(;y < tempp.length; y++){
						if(y == getIndex(vertexName)){
								sourceY++;
						}
						sourceX = 0;
						for(; x < tempp.length; x++){
								if(x == getIndex(vertexName)){
										sourceX++;
								}
								tempp[y][x] = edges[sourceY][sourceX];
								sourceX++;
						}
						sourceY++;
				}
				edges = tempp;
		}
		public void removeEdge(String vertexA, String vertexB){
				if(getIndex(vertexA) == -1 || getIndex(vertexB) == -1){
						return;
				}

				edges[getIndex(vertexA)][getIndex(vertexB)] = 0;
				edges[getIndex(vertexB)][getIndex(vertexA)] = 0;
		}
		private int getIndex(String vertexName){
				for(int x = 0; x < vertexList.length; x++){
						if(vertexList[x].equals(vertexName)){
								return x;
						}
				}
				return -1;

		}	

}