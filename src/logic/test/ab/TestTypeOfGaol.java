package test.ab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.ab.bean.GoalBean;
import logic.ab.exception.InsertException;

class TestTypeOfGaol {

	@Test
	void typeTestLink() 
	{ 
		GoalBean goal = new GoalBean();
		goal.setTipo(1);
		boolean flag=false;
		try
		{
			goal.setLink("");
		}
		catch(InsertException e)
		{
			flag=true;
		}
		
		assertEquals(true, flag);
	}

}
