/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.controller;

import java.sql.SQLException;
import java.util.List;
import logic.ab.bean.GoalBean;
import logic.ab.dao.GoalDAO;
import logic.ab.entity.Goal;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.exception.InsertException;
import logic.ab.exception.RefreshException;
import logic.ab.exception.TypeException;
import logic.ab.exception.UrlException;
import logic.ab.factory.Factory;

public class ControllerGoal 
{

	public List<Goal> retreiveGoals() throws SQLException, InsertException, TypeException, ConnectionClosedException
	{
		return GoalDAO.retreiveNewGoals();
	}
	public void addGoal(String nome, String soldiIniziali, String descrizione, String link,int type) throws InsertException, SQLException, TypeException, ConnectionClosedException
	{
		GoalBean bean = new GoalBean();
		
		bean.setNome(nome);
		bean.setPrezzo(soldiIniziali);
		bean.setDescrizione(descrizione);
		bean.setLink(link);
		bean.setTipo(type);
		
		Goal goal = Factory.createGoal(bean);
		
		goal.setNome(bean.getNome());
		goal.setPrezzo(bean.getPrezzo());
		goal.setDescrizione(bean.getDescrizione());
		goal.setLink(bean.getLink());
		goal.setTipo(bean.getTipo());
		
		GoalDAO.addNewGoal(goal);

	}
	
	public void addGoal(String nome, String soldiIniziali, String descrizione) throws InsertException, SQLException, TypeException, ConnectionClosedException
	{
		addGoal(nome,soldiIniziali,descrizione,"",0);
	}
	
	public void refreshGoal(Goal goal) throws UrlException, SQLException, RefreshException, ConnectionClosedException
	{
		goal.refresh();
		GoalDAO.updatePrice(goal);
	}
	
	public void addMoneyInHistory(String money, int idGoal) throws InsertException, SQLException, ConnectionClosedException
	{
		ControllerHistory controller = new ControllerHistory();
		controller.addMoney(money, idGoal);
	}
	
	public void deleteGoal(int id) throws SQLException, ConnectionClosedException
	{
		GoalDAO.deleteGoal(id);
	}
}
