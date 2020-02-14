public class VertexQueue{
		private Node head;
		public VertexQueue(){
				head = null;
		}
		public void enqueue(String vertexName){
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
		public String dequeue(){
				String output = head.getData();
				head = head.next;
				return output;
		}
		public boolean isEmpty(){
				if(head == null){
						return true;
				}
				return false;
		}
		private class Node{
				Node next;
				private String data;
				private Node(String data){
						this.data = data;
				}	
				private String getData(){
						return data;
				}
		}
}	 
