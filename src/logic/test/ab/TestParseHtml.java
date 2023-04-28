package test.ab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.ab.entity.Goal;
import logic.ab.entity.GoalEbay;
import logic.ab.exception.RefreshException;
import logic.ab.exception.UrlException;

class TestParseHtml {

	@Test
	void parseHtml() 
	{
		Goal goal = new GoalEbay();
		goal.setLink("https://www.ebay.it/itm/Notebook-Lenovo-IdeaPad-S145-15IWL-15-6-Intel-i7-RAM-8GB-SSD-256GB-81MV00S3IX/163856415566?_trkparms=aid%3D111001%26algo%3DREC.SEED%26ao%3D1%26asc%3D20160908105057%26meid%3Dec129855c12049a8b508b22e27d86138%26pid%3D100675%26rk%3D1%26rkt%3D15%26mehot%3Dpp%26sd%3D163856415566%26itm%3D163856415566%26pmt%3D0%26noa%3D1%26pg%3D2380057&_trksid=p2380057.c100675.m4236&_trkparms=pageci%3A02993a38-47aa-11ea-9dbb-74dbd180d0fa%7Cparentrq%3A12a296371700a99b4215cee5ffebcb32%7Ciid%3A1");
		float prezzo=(float) 549.9;
		
		try {
			goal.refresh();
		} catch (UrlException | RefreshException e) {
			prezzo=0;
		}
		assertEquals(prezzo, goal.getPrezzo());
	}

}
