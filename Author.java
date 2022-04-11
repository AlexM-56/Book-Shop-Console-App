import java.util.ArrayList;

public class Author {
	
	private int authorID;
	private String authorFirstName;
	private String authorLastName;
	private ArrayList<Book> authorWorks = new ArrayList<Book>();
	
	public Author() {
		
	}

	public Author(int authorID, String authorFirstName, String authorLastName) {
		this.authorID = authorID;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
	}
	
	/**
	 * This method adds a book to the author's ArrayList
	 * @param b
	 */
	public void addBook(Book b) {
		authorWorks.add(b);
	}
	
	/**
	 * This method removes a book from the author's ArrayList
	 * @param b
	 */
	public void removeBook(Book b) {
		authorWorks.remove(b);
	}
	
	/**
	 * Method for printing all of the author's books
	 */
	public void printAuthorWorks() {
		System.out.println("************" + getAuthorFirstName() + " " + getAuthorLastName() + " List of Works************");
		for(Book b: authorWorks) {
			System.out.println(b.getBookTitle());
		}
	}
	
	/**
	 * This is a get method
	 * @return
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * This is a set method
	 * @param authorID
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public String getAuthorFirstName() {
		return authorFirstName;
	}

	/**
	 * This is a set method
	 * @param authorFirstName
	 */
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public String getAuthorLastName() {
		return authorLastName;
	}

	/**
	 * This is a set method
	 * @param authorLastName
	 */
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	/**
	 * This is a get method
	 * @return
	 */
	public ArrayList<Book> getAuthorWorks() {
		return authorWorks;
	}

	/**
	 * This is a set method
	 * @param authorWorks
	 */
	public void setAuthorWorks(ArrayList<Book> authorWorks) {
		this.authorWorks = authorWorks;
	}
	
}
