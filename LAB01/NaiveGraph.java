public class NaiveGraph{
		private String[] vertices;
		private String[] edges;
		public NaiveGraph(){
				vertices = new String[0];
				edges = new String[0];
		}

		//queries
		public int numberOfVertices(){
				return vertices.length;
		}
		public int numberOfEdges(){
				int selfConnected = 0;

				for(int x = 0; x < edges.length; x++){
						String[] curr = edges[x].split(",");
						if(curr[0].equals(curr[1])){
								selfConnected++;
						}
				}
				return (edges.length - selfConnected)/2 + selfConnected;
		}
		public String listAdjacentVertices(String vertexName){
				if(getIndex(vertexName) == -1){
						return null;
				}
				String output = "";
				for(int x = 0; x < edges.length; x++){
						String[] curr = edges[x].split(",");
						if(curr[0].equals(vertexName)){
								output = output+curr[1]+" ";
						}
				}
				return output;
		}
		public boolean checkIfAdjacent(String vertexA, String vertexB){
				if(getIndex(edges, vertexA+","+vertexB) == -1){
						return false;
				}
				return true;
		}
		public boolean checkIfConnected(String vertexA, String vertexB){
				return traverseConnection(vertexA, vertexB, 1);
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


		//modifications
		public void insertVertex(String vertexName){
				vertices = insertinArray(vertices, vertexName);
		}
		public void removeVertex(String vertexName){
				vertices = deleteFromArray(vertices, vertexName);
				for(int x = 0; x < edges.length; x++){
						String[] curr = edges[x].split(",");
						if(curr[0].equals(vertexName)){
								removeEdge(curr[0], curr[1]);
						}
				}
		}
		public void insertEdge(String vertexA, String vertexB){
				if(getIndex(vertices,vertexA) != -1 && getIndex(vertices, vertexB) != -1){
						edges = insertinArray(edges, vertexA+","+vertexB);
						edges = insertinArray(edges, vertexB+","+vertexA);
				}
		}
		public void removeEdge(String vertexA, String vertexB){
				edges = deleteFromArray(edges, vertexA+","+vertexB);
				edges = deleteFromArray(edges, vertexB+","+vertexA);
		}




		private String[] insertinArray(String[] arr, String vertexName){
				if(getIndex(arr, vertexName) != -1){
						return arr;
				}
				String[] temp = new String[arr.length+1];
				System.arraycopy(arr, 0, temp, 0, arr.length);
				temp[temp.length-1] = vertexName;
				
				return temp;
		}
		private String[] deleteFromArray(String[] arr, String vertexName){
				if(getIndex(arr, vertexName) == -1){
						return arr;
				}
				int vertexIndex = getIndex(arr, vertexName);
				String[] temp = new String[arr.length-1];
				System.arraycopy(arr,0, temp, 0, vertexIndex);
				System.arraycopy(arr,vertexIndex+1, temp, vertexIndex, arr.length - vertexIndex-1);

				return temp;
		}		
		private int getIndex(String[] arr , String vertexName){
				for(int x = 0; x < arr.length; x++){
						if(arr[x].equals(vertexName)){
								return x;
						}
				}
				return -1;
		}
}