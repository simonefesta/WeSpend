/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.view;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import logic.ab.controller.ControllerGoal;
import logic.ab.controller.ControllerHistory;
import logic.ab.entity.Goal;
import logic.ab.entity.History;
import logic.ab.exception.AlertPrinter;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.exception.InsertException;
import logic.ab.exception.RefreshException;
import logic.ab.exception.TypeException;
import logic.ab.exception.UrlException;




public class ViewController 
{
	boolean visible=false;
	boolean visible1=false;
	boolean checked=true;
	boolean visibleDeleteButton=false;
	boolean visibleAddMoneyButton=false;
	boolean visibleRefreshButton=false;
	
	private static final String RETRYMSG = "Retry";
	private static final String BDERRORMESSAGEMAIN = "Error from DB operation";
	private static final String BDERRORMESSAGEMINOR = "Retry";
	private static final String CONNECTIONLOST = "Reconnection";
	private static final String CONNECTIONLOSTMINORMSG = "Wait..";
	
	private List<Goal> welcomeGoal = null;
	private ArrayList<ArrayList<History>> steps;
	private ControllerGoal controller;
	
	
	

    @FXML
    private Button addMoney;
	
    @FXML
    private Button submitButton;
    
    @FXML
    private Button refresh;

    @FXML
    private Text noGoalText;
    
    @FXML
    private Text title;
	
    @FXML
    private NumberAxis xAxis;
    
    @FXML
    private TextField search;
    
    @FXML
    private Button showTrend;

    @FXML
    private Text moneyGoal;
    
    @FXML
    private RadioButton checkAmazon;

    @FXML
    private RadioButton checkEbay;

    @FXML
    private Button refresh1;

    @FXML
    private ListView<String> list1= new ListView();
    
    @FXML
    private StackPane pannelloGrafico;
    
    @FXML
    private LineChart<Number, Number> grafico;
    
    @FXML
    private Button addGoal;
    
    @FXML
    private StackPane pannello2;

    @FXML
    private TextField newGoalLink;

    @FXML
    private TextField newGoalNome;

    @FXML
    private TextField newGoalSoldi;

    @FXML
    private Button newGoalButtonInserisci;

    @FXML
    private TextArea newGoalDescrizione;
    
    @FXML
    private CheckBox checkBoxLink;

    @FXML
    private TextField insertMoney;
    
    
    @FXML
    private Button addMoneyHistoryButton;
    
    @FXML
    private StackPane pannelloAddMoney;
    
    
    @FXML
    private Button refreshGoals;

    @FXML
    private ProgressIndicator progress;
    
    @FXML
    private Button deleteGoalButton;
    
    private XYChart.Series dataSeries1;
    
    final ToggleGroup group;
    
	
    
    
  /////////////////
    
    
    //Costruttore  il costruttore viene chiamato per primo, quindi @FXMLvengono popolati tutti i campi annotati, (ATT)
    public ViewController()
    {
    	group = new ToggleGroup();

    	dataSeries1 = new XYChart.Series();

   	 	dataSeries1.setName(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
    	
    }
    
    
    //Inizializzare qui (ATT)
    @FXML
    public void initialize() 
    {
    	checkEbay.setToggleGroup(group);
    	checkAmazon.setToggleGroup(group);
    	checkAmazon.setSelected(true);
    	
    	controller = new ControllerGoal();
    	welcome(controller);
    	
    	xAxis.setTickLabelFormatter(new StringConverter<Number>() {
    		
    		@Override
    		public String toString(Number t) {
    			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(t.longValue()));
    		}
    		
    		@Override
    		public Number fromString(String string) {
    			throw new UnsupportedOperationException("Not supported.");
    		}
    	});
    	
    	
    	grafico.getData().add(dataSeries1);	 

    }
    
    @FXML
    void showAddMoneyForm(ActionEvent event) 
    {
    	if(!visible)
    	{
    		visible=true;
    		pannelloAddMoney.setVisible(true);
    	}
    	else
    	{
    		visible=false;
    		pannelloAddMoney.setVisible(false);
    	}
    }

    @FXML
    void onClick(MouseEvent event) 
    {
    	
    	progress.setVisible(false);
    	if(list1.getSelectionModel().getSelectedItem()!=null)
   	 	{
    			visibleRefreshButton=true;
    			refresh.setVisible(true);
         		visibleDeleteButton=true;
        		deleteGoalButton.setVisible(true);
         		visibleAddMoneyButton=true;
         		addMoney.setVisible(true);
         		
         		String nome = welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getNome();
            	String prezzo = Float.toString(welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getPrezzo());
            	int index = list1.getSelectionModel().getSelectedIndex();
            	
            	plotter(nome,prezzo,index);
   	 	}
   	 	else
   	 	{	
   	 		visibleRefreshButton=false;
   	 		refresh1.setVisible(false);
   	 		visibleDeleteButton=false;
     		deleteGoalButton.setVisible(false);
     		visibleAddMoneyButton=false;
     		addMoney.setVisible(false);
    		title.setText("");
    		moneyGoal.setText("");
    		
   	 	}

    }

    @FXML
    void showAddGoal(ActionEvent event) 
    {
    	
		if(!visible1)
    	{
    		visible1=true;
    		pannello2.setVisible(true);
    	}
    	else
    	{
    		visible1=false;
    		pannello2.setVisible(false);
    	}
    }
    
    @FXML
    void newGoalInserisci(ActionEvent event) 
    {
    	try
    	{
    		if(!checked&& checkAmazon.isSelected())
    			controller.addGoal(newGoalNome.getText(), newGoalSoldi.getText(), newGoalDescrizione.getText(), newGoalLink.getText(),1);
    		else if(!checked && checkEbay.isSelected())
    			controller.addGoal(newGoalNome.getText(), newGoalSoldi.getText(), newGoalDescrizione.getText(), newGoalLink.getText(),2);
    		else
    			controller.addGoal(newGoalNome.getText(), newGoalSoldi.getText(), newGoalDescrizione.getText());
    		
    	}
    	catch(InsertException e)
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, "Error on insert", e.getMessage(), RETRYMSG);
    		return;
    	}
    	catch(NumberFormatException e)
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, "Error on insert price", "Range accepted is [-2147483648,2147483647]", RETRYMSG);
    		return;
    	}
    	catch (SQLException e) 
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, BDERRORMESSAGEMAIN, e.getMessage(), BDERRORMESSAGEMINOR);
    		return;
		} 
    	catch (TypeException e) 
    	{
			AlertPrinter.showAlert(AlertType.ERROR, "Error on type about Goal", e.getMessage(), "Goal type is setted to default (GoalOffline)");
			return;
    	} catch (ConnectionClosedException e) 
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, CONNECTIONLOST, e.getMessage(), CONNECTIONLOSTMINORMSG);
		}
    	
    	welcome(controller);
    	
    	if(!visible1)
    	{
    		visible1=true;
    		pannello2.setVisible(true);
    	}
    	else
    	{
    		visible1=false;
    		pannello2.setVisible(false);
    	}
    	

    }
    
    @FXML
    void checkLink(ActionEvent event) 
    {
    	if(checked)
    	{
    		checked=false;
    		newGoalLink.setEditable(true);
    		newGoalLink.clear();
    		checkAmazon.setVisible(true);
    		checkEbay.setVisible(true);
    	}
    	else
    	{
    		checked=true;
    		newGoalLink.setEditable(false);
    		newGoalLink.clear();
    		checkAmazon.setVisible(false);
    		checkEbay.setVisible(false);
    	}
    }
    
    @FXML
    void searchEnter(ActionEvent event) 
    {
    	if(search.getText().trim().isEmpty())
    	{
        	list1.getItems().clear();
    		for(int i=0; i<welcomeGoal.size(); i++)
        	{
        		
        		list1.getItems().add(welcomeGoal.get(i).getNome());
        		steps.add( (ArrayList<History>) welcomeGoal.get(i).getHistory());
        	}
    	}
    	else
    	{
        	list1.getItems().clear();
    		for(int i=0; i<welcomeGoal.size(); i++)
        	{
        		if(welcomeGoal.get(i).getNome().toLowerCase().contains(search.getText().toLowerCase()))
        		{
        			list1.getItems().add(welcomeGoal.get(i).getNome());
        			steps.add( (ArrayList<History>) welcomeGoal.get(i).getHistory());
        		}
        		
        	}
    	}
    	
    }
    
    @FXML
    int addHistory(ActionEvent event) 
    {
    	String nome = welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getNome();
    	String prezzo = Float.toString(welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getPrezzo());
    	int index = list1.getSelectionModel().getSelectedIndex();
    	try
    	{
    		controller.addMoneyInHistory(insertMoney.getText(),welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getId()); 
    	}
    	catch(InsertException e)
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, "Error on insert", e.getMessage(), RETRYMSG);
    		return 0;
    	}
    	catch(NumberFormatException e)
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, "Error on insert price", "Range accepted is [-2147483648,2147483647]", RETRYMSG);
    		return 0;
    	} 
    	catch (SQLException e) 
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, BDERRORMESSAGEMAIN, e.getMessage(), BDERRORMESSAGEMINOR);
		} catch (ConnectionClosedException e) 
    	{
			AlertPrinter.showAlert(AlertType.ERROR, CONNECTIONLOST, e.getMessage(), CONNECTIONLOSTMINORMSG);
		}
    	
    	welcome(controller);
    	this.plotter(nome,prezzo,index);
    	list1.getSelectionModel().select(index);
    	if(!visible)
    	{
    		visible=true;
    		pannelloAddMoney.setVisible(true);
    	}
    	else
    	{
    		visible=false;
    		pannelloAddMoney.setVisible(false);
    	}
    	
    	
    	return 1;
    }
    
    
    @FXML
    void refreshGoals(ActionEvent event) 
    {
    	welcome(controller);
    }
    
    
    @FXML
    void deleteGoal(ActionEvent event) 
    {
    	if(list1.getSelectionModel().getSelectedItem()!=null)
    	{
    		try 
    		{
				controller.deleteGoal(welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getId());
			} 
    		catch (SQLException e) 
    		{
    			AlertPrinter.showAlert(AlertType.ERROR, BDERRORMESSAGEMAIN, e.getMessage(), BDERRORMESSAGEMINOR);
			} 
    		catch (ConnectionClosedException e) 
    		{
    			AlertPrinter.showAlert(AlertType.ERROR, CONNECTIONLOST, e.getMessage(), CONNECTIONLOSTMINORMSG);
			}
    		welcome(controller);
    	}
    }

    

    @FXML
    void refreshGoal(ActionEvent event) 
    {
    	progress.setVisible(true);
    	int index = list1.getSelectionModel().getSelectedIndex();
    	if(list1.getSelectionModel().getSelectedItem()!=null)
    	{
    		progress.setProgress(0.1);
    		try 
    		{
				controller.refreshGoal(welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()));
			} 
    		catch (UrlException e) 
    		{
    			AlertPrinter.showAlert(AlertType.ERROR, "Error from URL", e.getMessage(), "Change URL");
			} 
    		catch (SQLException e) 
    		{
    			AlertPrinter.showAlert(AlertType.ERROR, BDERRORMESSAGEMAIN, e.getMessage(), BDERRORMESSAGEMINOR);
			} 
    		catch (RefreshException e) 
    		{
				AlertPrinter.showAlert(AlertType.WARNING, "Information about Refresh" , e.getMessage(), "");
			} 
    		catch (ConnectionClosedException e) 
    		{
    			AlertPrinter.showAlert(AlertType.ERROR, CONNECTIONLOST, e.getMessage(), CONNECTIONLOSTMINORMSG);
			}
    		progress.setProgress(0.5);
    		welcome(controller);
    		list1.getSelectionModel().select(index);
    		String nome = welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getNome();
        	String prezzo = Float.toString(welcomeGoal.get(list1.getSelectionModel().getSelectedIndex()).getPrezzo());
    		progress.setProgress(0.9);
    		this.plotter(nome,prezzo,index);
    		
    		progress.setProgress(1);
    	}

    }
    
    
    private void welcome(ControllerGoal controller)
    {
    	try 
    	{
    		welcomeGoal = getAllGoal(controller);
		} 
    	catch (SQLException | InsertException e) 
    	{
			AlertPrinter.showAlert(AlertType.ERROR, BDERRORMESSAGEMAIN, e.getMessage(), BDERRORMESSAGEMINOR);
		} 
    	catch (TypeException e) 
    	{
    		AlertPrinter.showAlert(AlertType.ERROR, "Error on type about Goal", e.getMessage(), "Goal type is setted to default (GoalOffline)");
		} catch (ConnectionClosedException e) 
    	{
			AlertPrinter.showAlert(AlertType.ERROR, CONNECTIONLOST, e.getMessage(), CONNECTIONLOSTMINORMSG);
		}
    	list1.getItems().clear();
 		if(welcomeGoal != null)
 		{
 	  		noGoalText.setVisible(false);
 			steps = new ArrayList<>();
 			for(int i=0; i<welcomeGoal.size(); i++)
 			{
 				if(welcomeGoal.get(i)!=null)
 				{
 					list1.getItems().add(welcomeGoal.get(i).getNome());
 					steps.add( (ArrayList<History>) welcomeGoal.get(i).getHistory());
 				}
    		
 			}
 		}
 		else
 	  		noGoalText.setVisible(true);
    }
    
    private List<Goal> getAllGoal(ControllerGoal controller) throws SQLException, InsertException, TypeException, ConnectionClosedException
    {
    	List<Goal> listOfGoals = null;
		ArrayList<History> stepss = null;
		ControllerHistory controllerHistory = new ControllerHistory();
		listOfGoals = controller.retreiveGoals();
		if(!listOfGoals.isEmpty())
		{
			for(int i=0; i<listOfGoals.size(); i++)
			{
				stepss= (ArrayList<History>)controllerHistory.transactionHistory(listOfGoals.get(i));
				if(stepss.isEmpty())
					listOfGoals.get(i).addNewHistoryVector(null);
				else
					listOfGoals.get(i).addNewHistoryVector(stepss);
			}
		}
		else
		{
				listOfGoals=null;
				return listOfGoals;
		}

		return listOfGoals;
    }
    
    private void plotter(String nome, String prezzo, int id)
    {
         		int sum=0;
         		title.setText(nome);
         		moneyGoal.setText(prezzo);
         		dataSeries1.getData().clear();
         		dataSeries1.getData().removeAll();
         		grafico.getData().removeAll();
         		
                if(steps.get(id)!=null)
                {

                	for(int i=0; i<steps.get(id).size(); i++)
                	{
                		sum = sum +steps.get(id).get(i).getMoney();
                		dataSeries1.getData().add(new XYChart.Data(steps.get(id).get(i).getDate().toEpochSecond(ZoneOffset.of("+1"))*1000, sum));	
                	}
               	
                }

    }
    
    
    
}
