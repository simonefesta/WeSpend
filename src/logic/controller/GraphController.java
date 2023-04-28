/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.sql.SQLException;
import java.util.List;
import javafx.scene.chart.PieChart.Data;
import logic.dao.GraphDAO;
import logic.exceptions.RetrieveDataException;

public class GraphController {

	GraphDAO graphDao;
	
	public GraphController() {
		
		graphDao = new GraphDAO();
	}

	public List<Data> getPieChartData(String username) throws RetrieveDataException, SQLException {
		return GraphDAO.getExpenseByType(username);
	}
}
