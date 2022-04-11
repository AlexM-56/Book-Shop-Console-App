import java.text.DecimalFormat;

public class Book {
	private int bookID;
	private String bookTitle;
	private Author bookAuthor;
	private double bookPrice;
	private int bookQty;
	
	private static DecimalFormat currency = new DecimalFormat("€0.00");
	
	Book() {
		
	}

	public Book(int bookID, String bookTitle, Author bookAuthor, double bookPrice, int bookQty) {
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookQty = bookQty;
	}
	
	/**
	 * This methods prints all of the book's details
	 */
	public void printBookDetails() {
		System.out.println("Title: " + bookTitle);
		System.out.println("Author: " + bookAuthor.getAuthorFirstName() + " " + bookAuthor.getAuthorLastName());
		System.out.println("Price: " + currency.format(bookPrice));
		System.out.println("Quantity: " + bookQty);
	}
	
	/**
	 * This method prints only the ID number and title of the book
	 */
	public void printBookList() {
		System.out.print(bookID + "\t");
		System.out.println(bookTitle);
	}

	/**
	 * This is a get method
	 * @return
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * This is a set method
	 * @param bookID
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * This is a set method
	 * @param bookTitle
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public Author getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * This is a set method
	 * @param bookAuthor
	 */
	public void setBookAuthor(Author bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public double getBookPrice() {
		return bookPrice;
	}

	/**
	 * This is a set method
	 * @param bookPrice
	 */
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public int getBookQty() {
		return bookQty;
	}

	/**
	 * This is a set method
	 * @param bookQty
	 */
	public void setBookQty(int bookQty) {
		this.bookQty = bookQty;
	}
	
}
