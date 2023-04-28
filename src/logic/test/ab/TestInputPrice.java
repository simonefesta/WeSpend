package test.ab;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.ab.bean.GoalBean;

class TestInputPrice
{

	@Test
	void inputPrice() 
	{
		GoalBean goalbean = new GoalBean();
		String prezzo="2a";
		boolean flag=false;
		
		try
		{
			goalbean.setPrezzo(prezzo);
		}
		catch(Exception e)
		{
			flag=true;
		} 

		assertEquals(true, flag);
	}

}
