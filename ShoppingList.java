/*
 * This program allows the user to add or delete items from a shopping list
 * It asks the user to enter the price of the item after adding each item
 * They can click cancel to exit the program
 * Author: Robin Rong
 * Date: March 7th, 2022
 */
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
public class ShoppingList extends InsertionOrDeletion{
	//inherit InsertionOrDeletion class to use its methods
	static boolean errorMessageNotNumber() {
		//this method can be used when the user enters an undesirable input
		boolean wentToCatch = true;
		JOptionPane.showMessageDialog(null,
				"Improper input, try again",
				"Improper input",
				JOptionPane.ERROR_MESSAGE);
		return wentToCatch;
	}
	static void errorMessageIdNotFound() {
		//if keyIndex is equal to -1, that means the binary search could not find the user's item
		JOptionPane.showMessageDialog(null,
				"Item Id not found (try checking the shopping list to confirm if you entered the right id",
				"Improper input",
				JOptionPane.ERROR_MESSAGE);
	}
	static void programDescription() {
		//prints program description
		JOptionPane.showMessageDialog(null, "This program allows the user to add or delete items from a shopping list\r\nIt asks the user to enter the price of the item after adding each item\r\nClick cancel to exit the program", "Program Description",  
				JOptionPane.INFORMATION_MESSAGE);
	}

	static void goodBye() {
		//used when cancel button is clicked, prints goodbye message and exits the program
		JOptionPane.showMessageDialog(null, "Exiting program...\nGoodbye!","PROGRAM TERMINATION",   
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	static void displayList(ArrayList<Groceries> listOfGroceries) {
		//this method display the arraylist in a Jscrollpane so that when the user has many items in their shopping list, they can scroll through their list and the window size can remain the same throughout

		// create list model for JList
		DefaultListModel<Groceries> model = new DefaultListModel<Groceries>();
		// add all words from wordList to model
		for(Groceries grocery : listOfGroceries){
			model.addElement(grocery);
		}

		// create JList with model - model
		JList<Groceries> list = new JList<Groceries>(model);
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(list));
		JOptionPane.showMessageDialog(null, panel);
	}

	static String selectionInterface(String message) {
		//provides an interface for the user to select an action
		Object[] options= {"Add Item", "Delete Item", "Check Shopping List"}; //Scroll-down menu for JOptionPane.showInputDialog
		String selection = (String)JOptionPane.showInputDialog(null, message, "Shopping List",
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); //user's choice
		return selection;
	}
	public static void main(String[] args) {
		int idCounter = 0;
		String message="Choose an activity\n"; //instruction for user  
		ArrayList<Groceries> list = new ArrayList<Groceries>();
		//an arraylist consisting of objects from the Groceries class
		String newItemName = "";
		String newItemPrice = "";
		double newItemPriceNum = 0;
		String itemId = "";
		int deletionId = 0;
		int [] idList;
		int keyIndex;
		boolean keepAsking = true;
		boolean loopUnsuccessful = true;
		//initialize all necessary variables

		programDescription();

		while(true){
			//keep running the program until the user clicks cancel button
			String selection = selectionInterface(message);
			if (selection == "Add Item") 
			{//if the user selects Add Item, perform below code to allow them to add their item

				newItemName = askNewItemName();
				//ask the user to enter the name of their item

				if(newItemName == null) {
					//if the user clicks cancel button, call goodBye method
					goodBye();
				}//little restriction for the item name as I would like the user to have the freedom to enter whatever they want as the item name, so as long as the input doesn't crash the program, it will be accepted as a string value
				keepAsking = true;
				while (keepAsking == true){
					//set up a loop for asking the user the price of their item

					keepAsking = false;
					try {
						//try asking the user the price of their item and parse the string input into a double
						newItemPrice = askNewItemPrice();
						newItemPriceNum = Double.parseDouble(newItemPrice);
					}
					catch(NumberFormatException e) {
						//if the user enters something that cannot be parsed into a double, print error message and set keepAsking to true so the loop runs again until no exception is caught
						//System.out.println(e) prints out the error for testing purposes
						keepAsking = errorMessageNotNumber();
					}
					catch(NullPointerException exc) {
						//if NullPointerException occurs, the user has clicked the cancel button, therefore call goodBye method to exit the program
						goodBye();
					}
				}
				idCounter ++;
				//have an idCounter the increases by 1 every time the user adds an item to the list, this helps sort the items in chronological order later and the user can delete items by their assigned id
				list = addToList(list, newItemName, newItemPriceNum, idCounter);
				//add the user's new item to the list, including its name, price, and id
				Collections.sort(list);
				//sort the new list according to the items' id  (see Groceries class for more detail on this)
			} 

			else if (selection == "Delete Item") 
			{//if the user selects Delete Item, perform below actions to allow them to remove their item

				while(loopUnsuccessful == true) {
					//set up a loop for asking the user the id of their item
					loopUnsuccessful = false;	
					itemId=askForDeletion();
					if(itemId == null) {
						//if the user clicks cancel button, terminate the program
						
						goodBye();
					}
					try{
						deletionId = Integer.parseInt(itemId);;
						//try asking the user for the id of the item they would like to remove	
					}
					catch(NumberFormatException ex) {
						//if the user enters something other than an integer, this error will occur, set loopUnsuccessful to true so the program asks for item id again until the user provides a valid input
						//System.out.println(ex); prints out the exception for testing purposes
						loopUnsuccessful = errorMessageNotNumber();
					}
				}
				idList = getListOfId(list);
				//call getListOfId method to get an int array consisting of solely the id
				keyIndex = binarySearch(idList, deletionId);
				//perform binary search on the list of id to find the index of the user's desired item
				if(keyIndex == -1) {
					//if keyIndex is equal to -1, that means the binary search could not find the user's item

					errorMessageIdNotFound();//set loopUnsuccessful to true to ask the user again
				}
				else {
					//if keyIndex isn't equal to -1, that means the binary search has successfully found the user's item
					list.remove(keyIndex);
					//remove the user's item
				}
			} 
			else if(selection == "Check Shopping List")
				//if the user selects Check Shopping List, call displayList method to display their shopping list
			{
				displayList(list);
			}
			else {
				goodBye();
			}//user selected the 'Cancel' option
		}
	}
}