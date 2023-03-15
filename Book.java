package bookmanagement;

public class Book extends Library {
	private long isbn;
	private String title;
	private String author;
	private String publisher;
	private String publishDate;
	private int qty;
	private boolean suspended;
	private boolean borrowed;
	
	
//	default constructor that allows to initialise default values
	public Book() {
		this(0000000000000, "NA", "NA", "NA", "NA", 0, false, false);
	}//end default constructor

//	constructor initialising instance fields with values from driver class
	public Book(long isbn, String title, String author, String publisher, String publishDate, int qty, boolean suspended, boolean borrowed) {
		super(suspended, borrowed);
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.qty = qty;
		this.publishDate = publishDate;
		this.suspended = suspended;
		this.borrowed = borrowed;
	}//end Book constructor

//getters and setters for each instance field
	public long getIsbn() {
		return isbn;
	}


	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}

	
	
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	
	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	@Override
	public String toString() {
		return "------------- Book --------------\n" +
				"\"" + title + "\"\n" + 
				"Written by: " + author + "\n" +
				"Published by: " + publisher + "\n" + 
				"Published on: " + publishDate + "\n" +
				"\n" +
				"ISBN: " + isbn + "\n" +
				"Copies available to borrow: " + qty + "\n" +
				"---------------------------------\n";
	}



	
	
	
	
	
}
