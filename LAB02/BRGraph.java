public class BRGraph{
		public BRGraph(){

		}

		private class EdgeList{
				private Node head;
				private class Node{
						private Node next;
						private String[] data;
						private Node(String edge){
								next = null;
								data = edge.split(" ");	
						}
				}
		}
}