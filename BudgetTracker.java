package program1;

import java.util.Scanner;
import java.util.Arrays;


public class BudgetTracker  {
	 
	
	//helper method to find the total expenses 
	
	public static double calculateTotal(double[] validExpenses) {
	double total= 0;
	for (int i =0; i< validExpenses.length ; i++ ) { 
		total += validExpenses[i];
	}
	return total;
	}
	

	//helper method to find the remaining balance
	
	public static double getRemainingBalance( double  income, double[] validExpenses) {
	double total= calculateTotal(validExpenses);
	return income - total;
	}	
	

	  // Find maximum expense and its name
    public static String findMaxExpenseWithName(double[] validExpenses, String[] validNames) {
        if (validExpenses.length == 0) return "No expenses recorded.";
        double max = validExpenses[0];
        String maxName = validNames[0];
        for (int i = 1; i < validExpenses.length; i++) {
            if (validExpenses[i] > max) {
                max = validExpenses[i];
                maxName = validNames[i];
            }
        }
        return String.format("%s: $%.2f", maxName, max);
    }

    // Find minimum expense and its name
    public static String findMinExpenseWithName(double[] validExpenses, String[] validNames) {
        if (validExpenses.length == 0) return "No expenses recorded.";
        double min = validExpenses[0];
        String minName = validNames[0];
        for (int i = 1; i < validExpenses.length; i++) {
            if (validExpenses[i] < min) {
                min = validExpenses[i];
                minName = validNames[i];
            }
        }
        return String.format("%s: $%.2f", minName, min);
    }
		
		
	//helper Bubble Sort method to sort expenses in ascending order
	
    public static void bubbleSort(double[] validExpenses, String[] validNames) {
	 int n = validExpenses.length; // Get the length of the expenses array 
	 boolean swapped;
	    // Outer loop for each pass through the array
    for (int i= 0; i < n -1  ; i++) {
	
    	swapped = false;//turns the swapped false to start the loop
    	// Inner loop for comparing adjacent elements
	for(int j = 0; j < n -i -1 ; j++ ) { // After each pass, the largest element is at the end of the sorted array
    
		//if statement that compare adjacent expenses
		if (validExpenses[j]> validExpenses[j+1]) { 
		
		// If the current expense is greater than the next one, swap them
			
	double temp = validExpenses[j];// Store the current expense in a temporary variable which is temp
	validExpenses[j] = validExpenses[j+1];// Move the larger expense to the replace the original smaller one which is towards the left
	validExpenses[j +1] = temp;// Place the smaller original expense in the right position
	
	   // Swap corresponding names
    String tempName = validNames[j];
    validNames[j] = validNames[j + 1];
    validNames[j + 1] = tempName;	
    swapped = true; //after a swapped happens so we need another pass
	
		}
	 }
	
	if (!swapped) break; //stop early if the whole loop is already sorted 
       }
	}
    
    public static void editExpense(String[] validNames, double[] validExpenses, Scanner scanner) {
        if (validExpenses.length == 0) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        System.out.println("Enter the name of the expense you want to edit (or type '-1' to cancel): ");
        String expenseNameToEdit = scanner.nextLine();

        if (expenseNameToEdit.equals("-1")) {
            System.out.println("Edit operation canceled.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < validNames.length; i++) {
            if (validNames[i].equalsIgnoreCase(expenseNameToEdit)) {
                found = true;
                System.out.println("You selected: " + validNames[i] + " - Current amount: $" + validExpenses[i]);

                double newExpense = -1;
       while (newExpense <= 0) {
         System.out.print("Enter new amount for " + validNames[i] + ": ");
         if (scanner.hasNextDouble()) {
         newExpense = scanner.nextDouble();
              if (newExpense > 0) {
             validExpenses[i] = newExpense;
          
             System.out.println("Expense updated successfully!");
               } else {
           
            System.out.println("Expense must be positive. Try again.");
         }
    } else {
            System.out.println("Invalid input! Please enter a valid number.");
                 scanner.next(); // Clear the buffer
              }
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Expense not found! Please make sure you entered the correct name.");
        }
    }
    
   
	 //main method

	public static void main(String[] args) {  //main method that implements everything
		
		Scanner scanner = new Scanner(System.in); // scanner system that uses the user's input
		String[] expenseNames = new String[10];
		double [] expenses = new double [10]; // iterates the value of expenses into new double with only 10 array squares
		int count = 0; //count of expense that are valid which means they are not negative nor zero 
		
		 
	 double income = -1; // initialize to a value that is invalid (so we can enter the loop)

     // Keep asking until a valid income is entered
	 while (income <= 0) {// income should be greater than  zero or it goes to the if statement 
         System.out.println("Enter your monthly income: (Enter a postive number only)");//shows Enter your monthly income:

         if (scanner.hasNextDouble()) { // Check if the input is a valid double
             income = scanner.nextDouble();
             if (income <= 0) {
                 System.out.println("Income must be a positive number. Please try again.");
             }
         } else {
             System.out.println("Invalid input! Please enter a valid numeric value for income.");
             scanner.next(); // Clear the invalid input from the buffer
         }
     }
	   // Output the valid income
     System.out.println("Your valid monthly income is: " + income); //shows the value of the valid income given by the user
    
  // Prompt user to enter up to 10 expense amounts
     System.out.println("Enter up to 10 expense name and amounts :");

     // Loop to allow the user to input expenses up to 10 times maximum
     while (count < 10) {
         scanner.nextLine(); // Consume newline before taking input
         System.out.println("Enter name of expense " + (count + 1) + " (or type '-1' to stop): ");
         String expenseName = scanner.nextLine();
         // If the user types "-1", stop taking input
         if (expenseName.equals("-1")) {
             break; // Exit the loop
         }
         if (expenseName.isEmpty()) {
             System.out.println("Expense name cannot be empty. Try again.");
             continue; // Continue to the next iteration
         }
         // Prompt for the expense amount and validate it
         double enteredExpense = -1;
         while (enteredExpense <= 0) {
             System.out.print("Enter amount for " + expenseName + " (must be positive): ");
             if (scanner.hasNextDouble()) {
                 enteredExpense = scanner.nextDouble();
              
                 if (enteredExpense > 0) {
                     expenseNames[count] = expenseName;
                     expenses[count] = enteredExpense;
                     count++;
                     System.out.println("Expense added successfully!");
                     continue;
                 } else {
                     System.out.println("Expense must be positive. Try again.");
                 }
             } else {
                 System.out.println("Invalid input! Please enter a valid number.");
                 scanner.next(); // Clear the buffer
             }
         }

         if (count == 10) {
             System.out.println("You have reached the maximum number of expenses.");
             break; // Stop taking more inputs 
         }
     }

     // Proceed to menu and other options
     String[] validExpenseNames = Arrays.copyOf(expenseNames, count);
     double[] validExpenses = Arrays.copyOf(expenses, count);
		 int choice = 0;
		 do {
		     System.out.println("\n==== Budget Tracker Menu ===");
		     System.out.println("1. Show total expenses");
		     System.out.println("2. Show remaining balance");
		     System.out.println("3. Show highest and lowest expenses"); 
		     System.out.println("4. Sort expenses (ascending)");
		     System.out.println("5. Edit an expense");
		     System.out.println("6. Exit");
		     System.out.print("Please select an option (1-6): ");
		     

if (scanner.hasNextInt()) {
    choice = scanner.nextInt();
    scanner.nextLine(); // Clears the buffer
} else {
    System.out.println("Invalid input! Please enter a number between 1-6.");
    scanner.nextLine(); // Clear invalid input
    continue;
}
		     
		     switch(choice) {
		         case 1:
		        	   if (validExpenses.length == 0) {
	                        System.out.println("No expenses recorded yet.");
	                    } else {
	                        double totalExpenses = calculateTotal(validExpenses);
	                        System.out.printf("Total Expenses: $%.2f%n", totalExpenses);
	                        if (totalExpenses > income) {
	                            System.out.println("WARNING: Your total expenses exceed your income! This is not sustainable.");
	                        }
	                    }
	                    break;

		         case 2:
		        	 if (validExpenses.length == 0) {
	                        System.out.println("No expenses recorded. Your remaining balance is your full income: $" + income);
	                    } else {
	                        double remainingBalance = getRemainingBalance(income, validExpenses);
	                        System.out.printf("Remaining Balance: $%.2f%n", remainingBalance);
	                        if (remainingBalance < 0) {
	                            System.out.println("ALERT: You are spending more than you earn! Consider adjusting your expenses.");
	                        }
	                    }
	                    break;


		         case 3:
		             if (validExpenses.length == 0) {
		                 System.out.println("No expenses recorded yet.");
		             } else {
		            	 System.out.println("Maximum expense: " + findMaxExpenseWithName(validExpenses, validExpenseNames));
	                        System.out.println("Minimum expense: " + findMinExpenseWithName(validExpenses, validExpenseNames));
	                    }
	                    break;

		         case 4:
		             if (validExpenses.length == 0) {
		                 System.out.println("No valid expenses to sort.");
		             } else {
		            	 bubbleSort(validExpenses, validExpenseNames);
	                        System.out.println("Sorted Expenses:");
	                        for (int i = 0; i < validExpenses.length; i++) {
	                            System.out.printf("%s: $%.2f%n", validExpenseNames[i], validExpenses[i]);
	                        }
	                    }
	                    break;
		         case 5:
	                    editExpense(validExpenseNames, validExpenses, scanner);
	                    break;
		     
		         case 6:
		             System.out.println("Exiting Budget Tracker... Goodbye! :)");
		             break;

		         default:
		             System.out.println("Invalid choice! Try again.");
		     }
		 } while (choice != 6);
        scanner.close(); // Close the scanner
    }

}
	
	

