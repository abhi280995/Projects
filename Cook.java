import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cook implements Runnable{

	public static Queue<ArrayList<String>> q=new LinkedList<ArrayList<String>>();
	public static Map<Integer,Integer> cooks=new HashMap<Integer,Integer> ();
	
	void initializeCook(int n)
	{
		for(int i=1;i<=n;i++)
		{
			cooks.put(i, 0);
		}
	}
	
	public synchronized void changeStatusOfCook(int key,int val)
	{
		cooks.put(key,val);
			
	}
	
	public synchronized void chefPreparingOrder(int key,ArrayList<String> order)
	{
		System.out.println("chefPreparingOrder called");
		changeStatusOfCook(key,1);
		

		System.out.println("Preparing order by chef having id>>>"+key);
		
		System.out.println("size of order is"+order.size());
		Machine m=new Machine();
		for(int i=0;i<order.size();i++)
		{
			if(i==order.size()-1)
			{
				System.out.println("Order completed for cutomer id"+order.get(i));
			}
			String item=order.get(i);
			System.out.println("item is"+item);
    	
			if(item.equalsIgnoreCase("Burger"))
			{
				System.out.println("Grill called");
				m.grill();
    		
			} else if(item.equalsIgnoreCase("coffee"))
			{
				System.out.println("Coffee Machine called");
				m.coffee();
			} else if(item.equalsIgnoreCase("fries"))
			{
				System.out.println("Fryer called");
				m.fryer();
			}
		}
		
		changeStatusOfCook(key,0);
		
		System.out.println("Done order for "+key);
	}
	
	public void addOrderToQueue(ArrayList<String> order)
	{
		q.add(order);
	}
	public synchronized ArrayList<String> getCurrentOrder()
	{
		System.out.println("Cooking order called");
		ArrayList<String> order2;
		if(!q.isEmpty())
		 order2=q.poll();
		else
			return null;
		
		return order2;
		
	}
	 int c=0;
	
	public synchronized void getFreeChef(ArrayList<String> order)
	{
		System.out.println("Insidddeeee"+cooks.get((c+1)%(cooks.size()+1)));
		
		if(cooks.get((c+1)%(cooks.size()+1))==0)
		{
			c=(c+1)%(cooks.size()+1);
			System.out.println("inside if");
			chefPreparingOrder(c,order);
		}
		else
		{
			System.out.println("issue");
			System.out.println("else");
			
			/*Iterator<Map.Entry<Integer,Integer>> itr = cooks.entrySet().iterator(); 
	          while(itr.hasNext()) 
		        { 
		             Map.Entry<Integer,Integer> entry = itr.next();
		             if(entry.getValue()==0)
		             {
		            	 	chefPreparingOrder(entry.getKey(),order);
		            	 	System.out.println("else called");
		            	 	return;
		        	}
		        }*/    
			
		    for (int a : cooks.keySet())  
	        { 
			   if(cooks.get(a)==0)
			   {
			 	 	System.out.println("else called");
			           
				 	chefPreparingOrder(a,order);
				 	if(q.isEmpty())
				 	{
				 			return;
				 	}
				 	else
				 	{
				 		Cook c=new Cook();
				 		ArrayList<String> u=c.getCurrentOrder();
				 		order=u;
				 		System.out.println("other part");
				           
				 	}
					/*
					 * else { while(!q.isEmpty()) {
					 * 
					 * } }
					 */
			   }
		    }
			
			/*Set set=cooks.entrySet();//Converting to Set so that we can traverse  
			Iterator itr=set.iterator();  
			while(itr.hasNext()){ 
	        Map.Entry entry=(Map.Entry)itr.next();  
	        if(entry.getValue().equals(0))
	        {
	        	c=(int)entry.getKey();
	        	chefPreparingOrder((int)entry.getKey(),order);
	        	
	        	System.out.println("inside while");
	    		
	        	if(q.isEmpty())
	        	break;
	        }
	    }*/
			
			    
		}
	}
	
	@Override
	public void run() {
		if(!q.isEmpty())
		{
			Cook c=new Cook();
			ArrayList<String> order=c.getCurrentOrder();
			if(order==null)
			{

				System.out.println("No orders pending");
				return ;
			}
			else
			{
				getFreeChef(order);
			}
		}
		else
		{
			System.out.println("No orders pending");
			return ;
		}
	}
	
	public void prepareingOrder() {
		// TODO Auto-generated method stub
		
	ExecutorService service=Executors.newFixedThreadPool(4);
	
	System.out.println("q size"+q.size());
	int n=q.size();
		for(int i=1;i<=n;i++)
		{
			service.execute(new Cook());
		}
	}
}
