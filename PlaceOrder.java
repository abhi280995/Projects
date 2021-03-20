import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PlaceOrder implements Runnable{

	  static int n=4;
	  static int k=0;
	  
	synchronized static int getCurrentCustomer()
	{
		k++;
		return k;
	}
	
	public void run()
	{
		ArrayList<String> order=PlaceOrder.getCurrentOrder();
		if(order!=null)
		{
			System.out.println("assign to cook");
			Cook c=new Cook();
			c.addOrderToQueue(order);
			c.prepareingOrder();
			
		}
		else
			System.out.println("No order pending");
		
	}
	
	static synchronized ArrayList<String> getCurrentOrder()
	{
		ArrayList<String> order;
		int j=PlaceOrder.getCurrentCustomer();
		System.out.println("value"+j);
		PlaceOrder p=new PlaceOrder();
		
		if(Customer.map.containsKey(j))
		{
			ArrayList<String> a=Customer.map.get(j);
			a.add(String.valueOf(j));
			
			System.out.println(a);
			Customer.map.remove(j);
			return a;
		}
		else
			return null;
	}
}