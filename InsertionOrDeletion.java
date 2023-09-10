import java.util.ArrayList;
import javax.swing.JOptionPane;
public class InsertionOrDeletion{
//this class is responsible for all the methods that deals with adding or deleting items
	public static ArrayList<Groceries> addToList(ArrayList<Groceries> list, String name, double price, int idCounter){
		//add the new item to the arrayList
		list.add(new Groceries(name, price, idCounter));
		return list;
		//return new arrayList
	}

	public static String askForDeletion() {
		//this method asks the user for the id of the item they would like to be removed

		String itemId = JOptionPane.showInputDialog ("Please enter the Id of the item that you would like to be removed\n(You can check your shopping list to find out the id of your item)");//hint the user that they can check their shopping list to find the id of their item 
		
		//parse the string input into an integer since JOptionPane only takes string from the user
		return itemId;
		//return the integer id
	}

	public static String askNewItemName() {
		//asks the user for the name of their new item
		String newItemName = JOptionPane.showInputDialog ("Please add your new item");
		return newItemName;
	}

	public static String askNewItemPrice() {
		//ask the user for the price of their new item
		String newItemPrice = JOptionPane.showInputDialog ("Please enter your new item's price");
		return newItemPrice;
	}

	public static int binarySearch(int arr[], int x)
	{
		//binary search for the user's item, int arr[] stands for the list of id, int x stands for user's input id
		int l = 0;
		int r = arr.length - 1;

		// Checking element in whole array
		while (l <= r) {
			int m = l + (r - l) / 2;

			// Check if x is present at mid
			if (arr[m] == x)
				return m;

			// If x greater, ignore left half
			if (arr[m] < x)
				l = m + 1;

			// If x is smaller, element is on left side
			// so ignore right half
			else
				r = m - 1;
		}

		// If we reach here, element is not present
		return -1;
	}

	public static int [] getListOfId(ArrayList<Groceries> arr) {
		//iterate through the arrayList of objects and create a new int array consisting of solely the id attribute
		int [] idList = new int[arr.size()];

		int i;
		for(i = 0; i < arr.size(); i++) {
			idList[i] = arr.get(i).getId();					
		}
		return idList;
		//return the int array
	}
}

