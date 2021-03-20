import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class Customer{

	
	public static Map<Integer,ArrayList<String>> map=new HashMap<Integer,ArrayList<String>> ();
	
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
//		int n=3;
		
		
		System.out.println("Enter no of customers");
		int n,k;
		n=sc.nextInt();
		//Queue<Integer> q=new SynchronousQueue<Integer>();
		
		for(int i=1;i<=n;i++)
		{
			//(Customer.q).add(i);
			System.out.println("Please enter for orderID"+i);
			ArrayList<String> list=new ArrayList<String>();//Creating arraylist  
			System.out.println("Enter no of items. And it should be greater than 0 and less than or equal to 3");
			k=sc.nextInt();
			

			for(int j=0;j<k;j++)
			{
				String temp="";
				System.out.println("Enter your item ");
				temp=sc.next();
				list.add(temp);
			    
			}
			
			map.put(i,list);
		}
		
		
		Cook c=new Cook();
		c.initializeCook(5);
		
		
		System.out.println("Size is "+map.size());
		
	ExecutorService service=Executors.newFixedThreadPool(10);
		
		PlaceOrder p=new PlaceOrder();
		
		for(int i=1;i<=n;i++)
		{
			service.execute(new PlaceOrder());
		}

		service.shutdown();
	}
		 

}
