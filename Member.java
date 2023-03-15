package bookmanagement;

public class Member {
	private String name;
	private final int MAX_LOANS = 3;
	private int booksBorrowed = 0;
	private int id;
	
	public Member(String name, int booksBorrowed, int id) {
		this.name = name;
		this.booksBorrowed = booksBorrowed;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBooksBorrowed() {
		return booksBorrowed;
	}
	public void setBooksBorrowed(int booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMAX_LOANS() {
		return MAX_LOANS;
	}
	
}
