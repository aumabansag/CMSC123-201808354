import java.util.Scanner;
public class GraphTester{
		public GraphTester(){}
		public void start(){
				Scanner scan = new Scanner(System.in);
				int choice = 0;
				while(choice != 10){
						
						printMenu();
						choice = scan.nextInt();
				}
		}
}