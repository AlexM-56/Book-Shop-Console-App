import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	
	public static Scanner input = new Scanner(System.in);
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static ArrayList<Author> allAuthors = new ArrayList<Author>();
	public static ArrayList<Book> allBooks = new ArrayList<Book>();
	
	public static DecimalFormat currency = new DecimalFormat("€0.00");
	
	public static int centralBookID = 111;
	public static int centralAuthorID = 105;
	
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args) {
		prePopulate();
		mainMenu();
	}

	/**
	 * This method is the start of our program where we can access all sub-menus
	 */
	private static void mainMenu() {
		System.out.println("Welcome to our book store");
		System.out.println("Please select an option -");
		System.out.println("\t 1 for the Books menu");
		System.out.println("\t 2 for the Authors Menu");
		System.out.println("\t S for the stock value");
		System.out.println("\t X to exit the program");
		
		switch(input.next().toUpperCase()) {
			case "1": {
				bookMenu();
				break;
			}
			
			case "2": {
				authorMenu();
				break;
			}
			
			case "X": {
				System.out.println("Program shutting down");
				System.exit(0);
				break;
			}
			
			case "S": {
				calcStockValue();
				break;
			}
			
			default: {
				System.out.println("Invalid choice. Please try again");
				break;
			}
		}
		mainMenu();
		
	}

	/**
	 * This method calculates the total value of all books combined
	 */
	private static void calcStockValue() {
		double stockValue = 0;
		for(Book b: allBooks) {
			stockValue += b.getBookPrice() * b.getBookQty();
		}
		System.out.println("Total Value of Stock: " + currency.format(stockValue));
	}

	/**
	 * Menu method for choosing to create,edit,delete or view authors
	 */
	private static void authorMenu() {
		System.out.println("********** Author Menu **********");
		System.out.println("Please select an option -");
		System.out.println("\t 1 to create a new author");
		System.out.println("\t 2 to view all authors");
		System.out.println("\t 3 to edit an author's details");
		System.out.println("\t 4 to delete an author");
		System.out.println("\t M to return to the main menu");
		
		switch(input.next().toUpperCase()) {
		
			case "1": {
				try {
					createAuthor();
				} catch (Exception e) {
					System.out.println("Error logged");
				}
				break;
			}
			case "2": {
				viewAllAuthors();
				break;
			}
			case "3": {
				try {
					editAuthor();
				} catch (Exception e) {
					System.out.println("Error logged");
				}
				break;
			}
			case "4": {
				deleteAuthor();
				break;
			}
			case "M": {
				return;
			}
			default: {
				System.out.println("Error please select a valid option");
				break;
			}
		}
			
		authorMenu();
	}

	/**
	 * This method lets us delete an author object
	 */
	private static void deleteAuthor() {
		viewAllAuthors();
		
		System.out.println("Please select which author you wish to delete by ID number");
		int toDelete = input.nextInt();
		boolean isFound = false;
		
		for(Author a : allAuthors) {
			if(toDelete == a.getAuthorID()) {
				isFound = true;
				if(a.getAuthorWorks().isEmpty()) {
					System.out.println(a.getAuthorFirstName() + " " + a.getAuthorLastName() + " has been deleted from the system");
					allAuthors.remove(a);
					break;
			}
				else {
						System.out.println("Error. Cannot delete author with " + a.getAuthorWorks().size() + " assigned books");
				}
			}
		}
		if(isFound == false) {
			System.out.println("No author with ID number " + toDelete + " was found");
			System.out.println("Returning to author menu");
		

		}	
	}

	/**
	 * This method allows us to select an author to edit
	 * @throws Exception
	 */
	private static void editAuthor() throws Exception {
		viewAllAuthors();
		
		System.out.println("Please select which author you wish to edit by ID number");
		int toEdit = input.nextInt();
		boolean isFound = false;
		
		for(Author a : allAuthors) {
			if(toEdit == a.getAuthorID()) {
				isFound = true;
				editChosenAuthor(a);
			}
		}
		if(isFound == false) {
			System.out.println("No author with ID number " + toEdit + " was found");
			System.out.println("Returning to author menu");
		}
	}

	/**
	 * This method lets us edit the attributes of a selected author
	 * @param a
	 * @throws Exception
	 */
	private static void editChosenAuthor(Author a) throws Exception {
		System.out.println("You have selected "+ a.getAuthorFirstName() + " " + a.getAuthorLastName()+" to edit");
		System.out.println("Please select an option -");
		System.out.println("\t 1 to edit first name");
		System.out.println("\t 2 to edit last name");
		System.out.println("\t R to return to author menu");
		
		switch(input.next().toUpperCase()) {
			
			case "1": {
				System.out.println("Please enter a new name to replace " + a.getAuthorFirstName());
				a.setAuthorFirstName(reader.readLine());
				System.out.println("First name has been updated to " + a.getAuthorFirstName());
				break;
			}
			case "2": {
				System.out.println("Please enter a new name to replace " + a.getAuthorLastName());
				a.setAuthorLastName(reader.readLine());
				System.out.println("Last name has been updated to " + a.getAuthorLastName());
				break;
			}
			case "R": {
				return;
			}
			default: {
				System.out.println("Error. Please select a valid option");
				break;
			}
		}
		editChosenAuthor(a);
	}

	/**
	 * This method displays all created authors currently in our program
	 */
	private static void viewAllAuthors() {
		for(Author a: allAuthors) {
			System.out.print(a.getAuthorID());
			System.out.println("\t" + a.getAuthorFirstName() + " " + a.getAuthorLastName());
		}
		
	}

	/**
	 * This method allows us to create a new author object and to give its attributes a value
	 * @throws Exception
	 */
	private static void createAuthor() throws Exception {
		Author a = new Author();
		a.setAuthorID(centralAuthorID);
		centralAuthorID++;
		System.out.println("Please enter the author's first name");
		a.setAuthorFirstName(reader.readLine());
		System.out.println("Please enter the author's last name");
		a.setAuthorLastName(reader.readLine());
		allAuthors.add(a);
		System.out.println("New author " + a.getAuthorFirstName() + " " + a.getAuthorLastName() + " has been added to the system");
		
	}

	/**
	 * Menu method for choosing to create, view, delete or edit books
	 */
	private static void bookMenu() {
		
		System.out.println("********** Book Menu **********");
		System.out.println("Please select an option -");
		System.out.println("\t 1 to create a new book");
		System.out.println("\t 2 to view all books");
		System.out.println("\t 3 to view books by author");
		System.out.println("\t 4 to edit a book's details");
		System.out.println("\t 5 to delete a book");
		System.out.println("\t M to return to the main menu");
		
		switch(input.next().toUpperCase()) {
		
			case "1": {
				try {
					createBook();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error logged");
				}
				break;
			}
			case "2": {
				viewAllBooks();
				break;
			}
			case "3": {
				viewBooksByAuthor();
				break;
			}
			case "4": {
				try {
					editBook();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error logged.");
				}
				break;
			}
			case "5": {
				deleteBook();
				break;
			}
			case "M": {
				return;
			}
			default: {
				System.out.println("Error. Please select valid option");
				break;
			}
		}
		
		bookMenu();
	}
	
	/**
	 * Method for choosing a specific book to edit its details
	 * @throws Exception
	 */
	private static void editBook() throws Exception {
		viewBookList();
		
		System.out.println("Please select the book you wish to edit by ID number");
		boolean isFound = false;
		int selectedID = input.nextInt();
		
		for(Book b: allBooks) {
			if(b.getBookID() == selectedID) {
				isFound = true;
				editChosenBook(b);
			}
		}
		if(isFound == false) {
			System.out.println("Error. No book with ID number " + selectedID + " was found");
			System.out.println("Returning to book menu");
		}
		
	}

	/**
	 * Method for editing the attributes of a selected book object
	 * @param b
	 * @throws Exception
	 */
	private static void editChosenBook(Book b) throws Exception {
		System.out.println("You have selected "+ b.getBookTitle() +" to edit");
		System.out.println("Please select an option -");
		System.out.println("\t 1 to edit book title");
		System.out.println("\t 2 to edit book author");
		System.out.println("\t 3 to edit book price");
		System.out.println("\t 4 to edit book quantity");
		System.out.println("\t R to return to author menu");
		
		switch(input.next().toUpperCase()) {
		
			case "1": {
				System.out.println("Please enter a new title to replace " + b.getBookTitle());
				b.setBookTitle(reader.readLine());
				System.out.println("Book title has been updated to " + b.getBookTitle());
				break;
			}
			
			case "2": {
				b.getBookAuthor().removeBook(b);
				chooseBookAuthor(b);
				System.out.println("Author has been updated to "+ b.getBookAuthor().getAuthorFirstName() + " " + b.getBookAuthor().getAuthorLastName());
				break;
			}
			
			case "3": {
				System.out.println("Please enter a new price for the book");
				b.setBookPrice(input.nextDouble());
				System.out.println("Price has been updated to " + currency.format(b.getBookPrice()));
				break;
			}
			
			case "4": {
				System.out.println("Please enter a new quantity for the book");
				b.setBookQty(input.nextInt());
				System.out.println("Quantity has been updated to " + b.getBookQty());
				break;
			}
			
			case "R": {
				return;
			}
			
			default: {
				System.out.println("Error. Please select a valid choice");
				break;
			}
		}
		editChosenBook(b);	
	}

	/**
	 * Method for deleting a book object 
	 */
	private static void deleteBook() {
		viewBookList();
		
		System.out.println("Please select the book you wish to delete by ID number");
		boolean isFound = false;
		int selectedID = input.nextInt();
		
		for(Book b: allBooks) {
			if(b.getBookID() == selectedID) {
				isFound = true;
				System.out.println(b.getBookTitle() + " has been deleted from the system");
				allBooks.remove(b);
				b.getBookAuthor().removeBook(b);
				break;
			}
		}
		if(isFound == false) {
			System.out.println("Error. No book with ID number " + selectedID + " was found");
			System.out.println("Returning to book menu");
		}
	}
	
	/**
	 * Method for viewing all the books of a chosen author
	 */
	private static void viewBooksByAuthor() {
		viewAllAuthors();
		System.out.println("Please select the ID number of the author whose books you wish to view");
		boolean isFound = false;
		int selectedID = input.nextInt();
		
		for (Author a: allAuthors) {
			if(selectedID == a.getAuthorID()) {
				isFound = true;
				a.printAuthorWorks();
			}
		}
		if(isFound == false) {
			System.out.println("Error. No author with ID number " + selectedID + " was found");
			System.out.println("Returning to book menu");
		}
	}

	/**
	 * Method that outputs all books and their details
	 */
	private static void viewAllBooks() {
		System.out.println("**********Book Inventory**********");
		for(Book b: allBooks) {
			b.printBookDetails();
			System.out.println(" ");
		}
	}
	
	/**
	 * Method that displays ID number and title of all books only 
	 */
	private static void viewBookList() {
		for(Book b: allBooks) {
			b.printBookList();
		}
	}

	/**
	 * Method for creating a new book object and giving its attributes values
	 * @throws Exception
	 */
	private static void createBook() throws Exception {
		Book b = new Book();
		
		b.setBookID(centralBookID);
		centralBookID ++;
		System.out.println("Please enter the book's title");
		b.setBookTitle(reader.readLine());
		chooseBookAuthor(b);
		System.out.println("Please enter the book's price");
		b.setBookPrice(input.nextDouble());
		System.out.println("Please enter the book's quantity");
		b.setBookQty(input.nextInt());
		System.out.println("New book " + b.getBookTitle() + " has been added to the system.");
		allBooks.add(b);
	}

	/**
	 * This method is for choosing the author for the book's author attribute
	 * @param b
	 */
	private static void chooseBookAuthor(Book b) {
		viewAllAuthors();
		System.out.println("To select the book's author, please choose the ID number of the book's author from our author list above");
		boolean isFound = false;
		int selectedID = input.nextInt();
		
		for(Author a: allAuthors) {
			if(a.getAuthorID() == selectedID) {
				isFound = true;
				b.setBookAuthor(a);
				a.addBook(b);
			}
		}
		if(isFound == false) {
			System.out.println("Error. No author with ID number " + selectedID + " was found");
			System.out.println("Returning to book menu");
			bookMenu();
		}
	}

	/**
	 * This method populates our program with objects when we first run it in order to save time
	 */
	private static void prePopulate() {
		Author anneAdams = new Author(101, "Anne", "Adams");
		Author bobByrne = new Author(102, "Bob", "Byrne");
		Author chrisCarter = new Author(103, "Chris", "Carter");
		Author debbieDunne = new Author(104, "Debbie", "Dunne");
		
		Book b1 = new Book(101, "A Guide to Baking", anneAdams, 15.5, 20);
		Book b2 = new Book(102, "Dancing in the Moonlight", anneAdams, 13.0, 35);
		Book b3 = new Book(103, "Hello Sunshine", anneAdams, 18.5, 18);
		Book b4 = new Book(104, "Camping for Dummies", bobByrne, 17.0, 40);
		Book b5 = new Book(105, "Deer in the headlights", bobByrne, 9.5, 24);
		Book b6 = new Book(106, "ShellShock", bobByrne, 10.5, 10);
		Book b7 = new Book(107, "Geronimo", chrisCarter, 7.0, 32);
		Book b8 = new Book(108, "Heart Stopper", chrisCarter, 19.0, 25);
		Book b9 = new Book(109, "White Rose of Persia", debbieDunne, 26.0, 50);
		Book b10 = new Book(110, "Yearn for Love", debbieDunne, 22.5, 38);
		
		anneAdams.addBook(b1);
		anneAdams.addBook(b2);
		anneAdams.addBook(b3);
		bobByrne.addBook(b4);
		bobByrne.addBook(b5);
		bobByrne.addBook(b6);
		chrisCarter.addBook(b7);
		chrisCarter.addBook(b8);
		debbieDunne.addBook(b9);
		debbieDunne.addBook(b10);
		
		allBooks.add(b1);
		allBooks.add(b2);
		allBooks.add(b3);
		allBooks.add(b4);
		allBooks.add(b5);
		allBooks.add(b6);
		allBooks.add(b7);
		allBooks.add(b8);
		allBooks.add(b9);
		allBooks.add(b10);
		
		allAuthors.add(anneAdams);
		allAuthors.add(bobByrne);
		allAuthors.add(chrisCarter);
		allAuthors.add(debbieDunne);
	}

}
