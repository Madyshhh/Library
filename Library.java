package bookmanagement;

public class Library {
	private boolean suspended;
	private boolean borrowed;
	private int MAX_BOOKS_STORED = 150;
	
	
	public Library(boolean suspended, boolean borrowed) {
		this.suspended = suspended;
		this.borrowed = borrowed;
	}
	


	public boolean getSuspended() {
		return suspended;
	}


	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}


	public boolean getBorrowed() {
		return borrowed;
	}


	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}


	public int getMAX_BOOKS_STORED() {
		return MAX_BOOKS_STORED;
	}


	
}
