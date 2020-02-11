public class SawBlade{
		public static void main(String[] args){
				printSawBlade(20);
		}	
		public static void printSawBlade(int k){
				for(int y = 1; y <=  k; y++){
						int descK = k;
						int descY = y;
						while(descY > 0){
								for(int x = 1 ; x <= descY ; x++){
										System.out.print("*");
								}
								for(int z = 1; z <= descK-descY; z++){
										System.out.print(" ");
								}
								descK--;
								descY--;
						}
						System.out.println("");
				}
		}
}