/*
 * Copyright 2020 Adriano Brugnoni, Alessandro Fato, Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import logic.Session;
import logic.ab.entity.Goal;
import logic.ab.entity.History;
import logic.dao.TransactionInformation;

public class Query
{
	/* Alessandro fato - prepared statment declaration */
    private static final String CHECKCREDENTIAL = "SELECT idUtente FROM utente WHERE username=? AND password=?";
    private static final String GETUSERINFORMATION = "SELECT username, idUtente, deposit FROM utente WHERE username=?";
    private static final String CREATEUSER = "INSERT INTO utente (username, deposit, password) VALUES (?,?,?)";
    private static final String CHECKUSEREXISTS = "SELECT idUtente FROM utente WHERE username=?";
    private static final String GETLASTTRANSACTIONINDEX = "SELECT idTr FROM transaction ORDER BY idTr DESC LIMIT 1";
    private static final String INSERTTRANSACTION = "INSERT INTO transaction (idTr, username, price, state, isDebt, isShared, toUser, fromUser, type, comment, date) "
    												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String DELETETRANSACTION = "DELETE FROM transaction WHERE idTr=?";
    
    private static final String GETFRIENDUSERS = "    SELECT userTwo.username AS username\n" + 
		  			 "    FROM wespend.utente AS userOne, wespend.utente AS userTwo, wespend.friendships AS friends\n" + 
					 "    WHERE userOne.idUtente = friends.userOne AND userTwo.idUtente = friends.userTwo AND \n" + 
					 "    userOne.username = ?\n" + 
					 "    UNION\n" + 
					 "    SELECT userOne.username\n" + 
					 "    FROM wespend.utente AS userOne, wespend.utente AS userTwo, wespend.friendships AS friends\n" + 
					 "    WHERE userOne.idUtente = friends.userOne AND userTwo.idUtente = friends.userTwo AND \n" + 
					 "    userTwo.username = ?";
    
    private static final String CREATEFACEBOOKUSER = "INSERT INTO utente (username, deposit, facebookId) VALUES (?,?,?)";
    private static final String CHECKFACEBOOKUSEREXISTS = "SELECT idUtente FROM utente WHERE facebookId=?";
    private static final String GETFACEBOOKUSERINFORMATION = "SELECT username, idUtente, deposit FROM utente WHERE facebookId=?";
    private static final String UPDATEDEBT = "UPDATE transaction SET isDebt=1, toUser=? WHERE (idTr = ?) and (username = ?)"; 
    private static final String UPDATESHARED = "UPDATE transaction SET isShared=1, price=? WHERE (idTr = ?) and (username = ?)"; 
    private static final String UPDATETRANSACTIONSTATUS = "UPDATE wespend.transaction\n" + 
    		"SET state=2\n" + 
    		"WHERE idTr=? AND idTr NOT IN (\n" + 
    		"	\n" + 
    		"    SELECT idTransaction FROM (\n" + 
    		"		SELECT idTr AS idTransaction\n" + 
    		"		FROM wespend.transaction\n" + 
    		"		WHERE state=0 AND idTr = ?\n" + 
    		"		GROUP BY idTransaction\n" + 
    		"    ) AS c\n" + 
    		");";

    /* Alessandro fato - end */
    
    
    /* Simone Festa - prepared statment declaration */
    private static final String DATAORDER = "SELECT * FROM transaction WHERE state=2 AND username=? ORDER BY idTr DESC";
   
    private static final String GETGRAPH 	=   "SELECT SUM(price),type\n"+
    											"FROM transaction WHERE state=2 AND username=?"+
    											"GROUP BY type";
    private static final String PAYMENT = "SELECT SUM(price)" + 
    									  "FROM transaction\n" + 
    									  "WHERE state=2 AND username=?";	
    private static final String DEBT = "SELECT idTr,date,type,price,fromUser,comment\n"+	
    		 						   "FROM transaction\n"+
    		 						   "WHERE (isDebt = 1 OR isShared =1) AND username=? AND state=0 ORDER BY date DESC";
    private static final String ACCEPT = "UPDATE transaction SET state=1 WHERE (idTr=?) AND username=?";
    private static final String DECLINE = "DELETE FROM transaction WHERE (idTr=?)";
  
    /* Simone Festa - end */
    
    
    /* Adriano Brugnoni - Query */
    private static final String GETGOAL="Select idGoal, nome, Budget, tipo, data, descrizione, link FROM goal WHERE utente_idUtente=? ORDER BY data DESC";
    private static final String ADDGOAL="INSERT INTO goal(nome,Budget,tipo,data,descrizione,link,utente_idUtente) VALUES (?,?,?,?,?,?,?)";
    private static final String ADDHISTORY="INSERT INTO storico(Soldi,Date,goal_idGoal1) VALUES (?,?,?)";
    private static final String GETHISTORY="Select Soldi,Date as date FROM storico WHERE goal_idGoal1= ?";
    private static final String DELETEGOAL="DELETE FROM goal WHERE idGoal= ?";
    private static final String UPDATEPRICE="UPDATE goal SET Budget = ? WHERE idGoal=?";
    private static final String UPDATETYPEAFTERERROR="UPDATE goal SET tipo = 0 WHERE idGoal=?";
    private static final String TESTCONNECTION="SELECT 1";

    										
    										    
    
    private Query() {
		throw new IllegalStateException("Utility class");
	}

    /* Adriano Brugnoni - Query */
    public static ResultSet selectNewGoals(PreparedStatement pstmt) throws SQLException
    {
    	pstmt.setString(1, Integer.toString(Session.getUserId()));
        return pstmt.executeQuery();
    }

   
    public static String sqlSelectNewGoals() 
    {
    	return GETGOAL; 
    }
    
    public static int addNewGoal(PreparedStatement pstmt, Goal goal) throws SQLException
    {
    	//ATT Devi gestire l'inserimento dell'ID utente
    	        
    	pstmt.setString(1, goal.getNome());
    	pstmt.setString(2, Double.toString(goal.getPrezzo()));
    	pstmt.setString(3, Integer.toString(goal.getTipo()));
    	pstmt.setString(4, java.time.LocalDateTime.now().toString());
    	pstmt.setString(5, goal.getDescrizione());
    	pstmt.setString(6, goal.getLink());
    	pstmt.setString(7, Integer.toString(Session.getUserId()));
    	
        return pstmt.executeUpdate();
    }

    public static String sqlAddNewGoal() 
    {
    	return ADDGOAL; 
    }
    
    public static ResultSet selectHistoryOfGoal(PreparedStatement pstmt, int id) throws SQLException
    {
    	
    	pstmt.setString(1, Integer.toString(id));
    	return pstmt.executeQuery();
    }
    public static String sqlSelectHistoryOfGoal() 
    {
    	return GETHISTORY; 
    }
    
    public static int addNewHistory(PreparedStatement pstmt, History history) throws SQLException
    {

    	pstmt.setString(1, Integer.toString(history.getMoney()));
    	pstmt.setString(2, history.getDate().toString());
    	pstmt.setString(3, Integer.toString(history.getIdGoal()));
    	

    	return pstmt.executeUpdate();
    }
    public static String sqlAddHistory() 
    {
    	return ADDHISTORY; 
    }
    
    public static int deleteGoal(PreparedStatement pstmt, int id) throws SQLException
    {
    	pstmt.setString(1, Integer.toString(id));
    	return pstmt.executeUpdate();
    }
    public static String sqlDeleteGoal() 
    {
    	return DELETEGOAL; 
    }
    public static int updatePrice(PreparedStatement pstmt, Goal goal) throws SQLException
    {
    	pstmt.setString(1, Double.toString(goal.getPrezzo()));
    	pstmt.setString(2, Integer.toString(goal.getId()));

    	return pstmt.executeUpdate();
    }
    public static String sqlUpdatePrice() 
    {
    	return UPDATEPRICE; 
    }
    
    public static int updateTypeAfterError(PreparedStatement pstmt, int idUser) throws SQLException
    {
    	pstmt.setString(1,Integer.toString(idUser));
    	return pstmt.executeUpdate();
    }
    public static String sqlUpdateTypeAfterError() 
    {
    	return UPDATETYPEAFTERERROR; 
    }
    
    public static void testConnection(Statement stmt) throws SQLException
    {
    	stmt.executeQuery(TESTCONNECTION);
    }
    
    
    
    
    /* Simone Festa - Query */    
    
    public static ResultSet orderDate (PreparedStatement pstmt,String user) throws SQLException
    {	pstmt.setString(1, user);
        return pstmt.executeQuery();
    }
    
    public static String sqlOrderDate() 
    {
    	return DATAORDER;
    }
    
    public static ResultSet graphVista (PreparedStatement pstmt,String username) throws SQLException
    {	pstmt.setString(1, username);
        return pstmt.executeQuery();
    }

    
    public static String sqlGraphVista() 
    {
    	return GETGRAPH;
    }

    public static ResultSet payment (PreparedStatement pstmt,String usern) throws SQLException
    {	pstmt.setString(1, usern);
        return pstmt.executeQuery();
    }
    
    public static String sqlPayment()
    { 
    	return PAYMENT; 
    }
    
    public static ResultSet debt (PreparedStatement pstmt,String usrname) throws SQLException
    {	pstmt.setString(1, usrname);
        return pstmt.executeQuery();
    }
    
    
    public static String sqlDebt() 
    {
    	return DEBT;
    }
    
    public static int accept (PreparedStatement pstmt,int id,String username) throws SQLException
    {	pstmt.setInt(1,id);
    	pstmt.setString(2,username);
        return pstmt.executeUpdate();
    }
    
    
    public static String sqlAccept() 
    {
    	return ACCEPT;
    }
    
    public static int decline (PreparedStatement pstmt,int id) throws SQLException
    {	pstmt.setInt(1,id);
        return pstmt.executeUpdate();
    }
    
    
    
    public static String sqlDecline() 
    {
    	return DECLINE;
    }
    
   
    

    /* Simone Festa - end */ 

    /* Alessandro Fato - query */ 
    public static String sqlCheckCredential() {    	
    	return CHECKCREDENTIAL; 
    }
    
    public static ResultSet checkCredential(PreparedStatement pstmt, String user, String pass) throws SQLException {
    	
    	pstmt.setString(1, user);
    	pstmt.setString(2, pass);
    	
    	return pstmt.executeQuery();
    }

    public static String sqlGetUserInformation() {
    	return GETUSERINFORMATION; 
    }	
    
    public static ResultSet getUserInformation(PreparedStatement pstmt, String usernm) throws SQLException {
    	
    	pstmt.setString(1, usernm);
    	
    	return pstmt.executeQuery();
    }	
    
    public static String sqlCreateUser() {
    	return CREATEUSER;
    }	
        
    public static int createUser(PreparedStatement pstmt, String user, String pass, String startDeposit) throws SQLException {
    	
    	pstmt.setString(1, user);
    	pstmt.setString(2, startDeposit);
    	pstmt.setString(3, pass);
    	
    	return pstmt.executeUpdate();
    }	
    
    public static String sqlCheckUserExists() {
    	return CHECKUSEREXISTS;
    }
    
    public static ResultSet checkUserExists(PreparedStatement pstmt, String nickname) throws SQLException {
    	
    	pstmt.setString(1, nickname);
    	
    	return pstmt.executeQuery();
    }

    public static String sqlGetLastTransactionIndex() {
    	
    	return GETLASTTRANSACTIONINDEX;
    }
    
    public static ResultSet getLastTransactionIndex(PreparedStatement pstmt) throws SQLException {
    	
    	return pstmt.executeQuery();
    }

    public static String sqlInsertTransaction() {

    	return INSERTTRANSACTION;
    }
    
    public static int insertTransaction(PreparedStatement pstmt, TransactionInformation ti) throws SQLException {
    	
    	pstmt.setString(1, ti.getIdTr());
    	pstmt.setString(2, ti.getUsername());
    	pstmt.setString(3, ti.getPrice());
    	pstmt.setString(4, ti.getState());
    	pstmt.setString(5, ti.getIsDebt());
    	pstmt.setString(6, ti.getIsShared());    	
    	pstmt.setString(7, ti.getToUser());
    	pstmt.setString(8, ti.getFromUser());
    	pstmt.setString(9, ti.getType());
    	pstmt.setString(10, ti.getComment());
    	pstmt.setString(11, ti.getDateStr());

    	return pstmt.executeUpdate();
    } 
    
    public static String sqlDeleteTransaction() {

    	return DELETETRANSACTION;
    }
    
    public static void deleteTransaction(PreparedStatement pstmt, String index) throws SQLException {

    	pstmt.setString(1, index);
    	pstmt.executeQuery();
    }
     
    public static String sqlGetFriendUsers() {
    	return GETFRIENDUSERS;
    }
    
    public static ResultSet getFriendUsers(PreparedStatement pstmt, String user) throws SQLException {
    	
    	pstmt.setString(1, user);
    	pstmt.setString(2, user);
    	
    	return pstmt.executeQuery();
    }   
    
    public static String sqlCreateFacebookUser() {
    	return CREATEFACEBOOKUSER;
    }	
        
    public static int createFacebookUser(PreparedStatement pstmt, String user, String startDeposit, String userId) throws SQLException {
    	
    	pstmt.setString(1, user);
    	pstmt.setString(2, startDeposit);
    	pstmt.setString(3, userId);
    	
    	return pstmt.executeUpdate();
    }	
    
    public static String sqlCheckFacebookUserExists() {
    	return CHECKFACEBOOKUSEREXISTS;
    }
    
    public static ResultSet checkFacebookUserExists(PreparedStatement pstmt, String userId) throws SQLException {
    	
    	pstmt.setString(1, userId);

    	return pstmt.executeQuery();
    }
    
    public static String sqlGetFacebookUserInformation() {
    	return GETFACEBOOKUSERINFORMATION; 
    }	
    
    public static ResultSet getFacebookUserInformation(PreparedStatement pstmt, String id) throws SQLException {
    	 
    	pstmt.setString(1, id);
    	
    	return pstmt.executeQuery();
    }	
   
    public static String sqlUpdateDebt() {
    	return UPDATEDEBT; 
    }
    
    public static int updateDebt(PreparedStatement pstmt, String debtUser, String user, String index) throws SQLException {
    
    	pstmt.setString(1, debtUser);
    	pstmt.setString(2, index);
    	pstmt.setString(3, user);
    	
    	return pstmt.executeUpdate();
    }

    public static String sqlUpdateShared() {
    	return UPDATESHARED; 
    }
    
    public static int updateShared(PreparedStatement pstmt, String price, String user, String index) throws SQLException {
    
    	pstmt.setString(1, price);
    	pstmt.setString(2, index);
    	pstmt.setString(3, user);
    	
    	return pstmt.executeUpdate();
    }
    
    public static String sqlUpdateStatus() {
    	return UPDATETRANSACTIONSTATUS; 
    }
    
    public static int updateStatus(PreparedStatement pstmt, int idTr) throws SQLException {
    	
    	pstmt.setInt(1, idTr);
    	pstmt.setInt(2, idTr);
    	    	
    	return pstmt.executeUpdate();
    }
}
    /* Alessandro Fato - end */ 