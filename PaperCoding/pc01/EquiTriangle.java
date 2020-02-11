public class EquiTriangle{
		public static void main(String[] args){
					printTriangle(4);
		}
		public static void printTriangle(int k){
				for(int y = 1; y  <=  k; y++){
						for(int x =1 ; x <= (k+k-1); x++){
								if(x + y >= k+1 &&  x + y <= (k+1+2*(y-1)) && k % 2 != (x+y) % 2){
										System.out.print("*");
								}
								else{
										System.out.print(" ");
								}
						}
						System.out.println("");
				}			
		}
}