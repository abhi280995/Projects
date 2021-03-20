
public class Machine{
	
	public static int fryerCount=5,grillCount=5,coffeeCount=5;
	
	
	public void fryer()
	{
		if((--fryerCount)>0)
		{
			
		System.out.println("Frying");
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fryerCount++;
		
		}
		else
			System.out.println("Can't perform fryer operation");

		System.out.println("Done opertn");
		return;
			
	}
	public void grill()
	{
		if((--grillCount)>0)
		{
			
			System.out.println("grillCount"+grillCount);
			
		System.out.println("Grilling");
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grillCount++;
		
		}
		else
			System.out.println("Can't perform grilling operation");
		

		System.out.println("Done opertn");
		return;
		
	}
	public void coffee()
	{
		if((--coffeeCount)>0)
		{
			
		System.out.println("Coffee");
		try {

			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coffeeCount++;
		
		}
		else
			System.out.println("Can't perform this operation");
		
		
		System.out.println("Done opertn");
		return;
		
	}		
}