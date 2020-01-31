public class VertexStack{
		private Node head;
		public VertexStack(){
				head = null;
		}
		public void push(String input){
				Node temp = new Node(input);
				temp.next = head;
				head = temp;
		}
		public String pop(){
				if(isEmpty()){
						return null;
				}
				String output = head.getData();
				head = head.next;
				return output;
		}
		public boolean isEmpty(){
				if(head == null){
						return true;
				}
				else{
						return false;
				}
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