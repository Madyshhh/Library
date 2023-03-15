package bookmanagement;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookDriver {

	public static void main(String[] args) {

		// opening a scanner for user input
		Scanner scanner = new Scanner(System.in);

		// create new Set to store books
		HashSet<Book> books = new HashSet<Book>();

		// create books for testing
		Book book = new Book(1234567890123L, "Test book", "Author", "Publisher", "25.01.2022", 3, false, false);
		Book anotherBook = new Book(9781501171345L, "The last thing he told me", "Laura Dave", "Simon & Schuster",
				"04.05.2021", 2, false, false);

		// add books to hashset for testing
		books.add(book);
		books.add(anotherBook);

		// create new set for members
		HashSet<Member> members = new HashSet<Member>();

		// create members for testing
		Member member = new Member("Test", 0, 12345678);
		Member anotherMember = new Member("Test Two", 0, 11234567);
		Member madara = new Member("Madara Vetra", 0, 574151);

		// add members to set for testing
		members.add(member);
		members.add(anotherMember);
		members.add(madara);

		// create a hashset to hold the books user has borrowed
		// hashset can be used to display the report
		HashMap<Integer, Long> userBorrowed = new HashMap<Integer, Long>();

		// show the welcome message when starting the program
		System.out.println("-- Welcome to The Abbey library of Saint Gall! --");

		// show menu when starting the program
		showMenu(books, members, userBorrowed, scanner);

	}// end main

	static void showMenu(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {
		int menuChoice = getMenuOption(scanner);
		executeMenuChoice(menuChoice, books, members, userBorrowed, scanner);
	}// end showMenu

	static void executeMenuChoice(int menuChoice, HashSet<Book> books, HashSet<Member> members,
			HashMap<Integer, Long> userBorrowed, Scanner scanner) {
		// tells what each menu option does
		switch (menuChoice) {
		case 1:
			System.out.println("--- Add books ---");
			addBooks(books, members, userBorrowed, scanner);
			break;
		case 2:
			System.out.println("--- Search books ---");
			searchBooks(books, members, userBorrowed, scanner);
			break;
		case 3:
			System.out.println("--- Borrow books ---");
			borrowBook(books, members, userBorrowed, scanner);
			break;
		case 4:
			System.out.println("--- Remove books ---");
			removeBook(books, members, userBorrowed, scanner);
			break;
		case 5:
			System.out.println("--- Display all books ---");
			displayBooks(books, members, userBorrowed, scanner);
			break;
		}// end switch
	}// end executeMenuChoice

	static int getMenuOption(Scanner scanner) {
		int menuChoice = -1;

		do {
			// try to catch exception if wrong data type entered
			try {
				// asks user to choose menu option from 0 - 4
				System.out.println("\n1. Add books" + "\n2. Search books" + "\n3. Borrow books" + "\n4. Remove books"
						+ "\n5. Display all books" + "\n0. Exit");
				menuChoice = scanner.nextInt();

				// if number entered smalled than 0 or bigger than 4, show error message
				if (menuChoice < 0 || menuChoice > 5) {
					System.out.println("Wrong value entered!");
				} // end if

			} catch (InputMismatchException e) {
				System.out.println("Incorrect data type entered!");

				// clears the scanner buffer
				scanner.nextLine();
			} // end catch

		} while (menuChoice < 0 || menuChoice > 5);// end do

		// return what option user chose
		return menuChoice;

	}// end getMenuOption

	static void removeBook(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {

		boolean bookExists = false;

		try {
			System.out.println("Enter books ISBN number: ");
			long enteredIsbn = scanner.nextLong();
			while (Long.toString(enteredIsbn).length() < 13 || Long.toString(enteredIsbn).length() > 13) {
				System.out.println("Wrong ISBN number!");
				System.out.println("Enter books ISBN number: ");
				enteredIsbn = scanner.nextLong();
			} // end while

			// go through each book until the wanted book found
			for (Book book : books) {
				if (book.getIsbn() == enteredIsbn) {

					// sets bookExists to true if book with entered ISBN found
					bookExists = true;

					// show how many copies of this book in library and ask user to enter how many
					// he wants to remove
					System.out.println("There`s " + book.getQty() + " copies of \"" + book.getTitle() + "\".");
					System.out.println("How many copies do you want to remove?");
					int bookQty = scanner.nextInt();

					// remove copies user entered
					int newQty = book.getQty() - bookQty;
					book.setQty(newQty);

					System.out.println(bookQty + " copies of \"" + book.getTitle() + "\" removed!");
					System.out.println(book.getQty() + " copies left.");

					if (book.getQty() == 0) {
						books.remove(book);
					}
				} // end if
			} // end for
			if (!bookExists) {
				// shows a message if can`t find a book with entered ISBN
				System.out.println("Book with this ISBN not in the library!");
			} // end if
		} catch (InputMismatchException e) {
			System.out.println("Incorrect data type entered!");

			// clears the scanner buffer
			scanner.nextLine();
		} // end catch
		catch (ConcurrentModificationException a) {
			// catches the exception for removing book in a loop, but still works
		} // end catch

		// show menu after finished removing
		showMenu(books, members, userBorrowed, scanner);
	}// end removeBook

	static void displayBooks(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {
		// loops through all books and displays details of them
		for (Book eachBook : books) {
			System.out.println(eachBook.toString());
		} // end for

		// takes user back to menu
		showMenu(books, members, userBorrowed, scanner);
	}// end displayBooks

	static void borrowBook(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {
		boolean bookExists = false;

		// initialize values to store isbn number and user id to later add to hashMap
		long isbnNumber = 0;
		int userID = 0;

		try {
			// asks for isbn value
			System.out.println("Enter ISBN of the book you want to borrow: ");
			long isbn = scanner.nextLong();

			// checks if the isbn is 13 numbers and asks user again until 13 numbers entered
			while (Long.toString(isbn).length() < 13 || Long.toString(isbn).length() > 13) {
				System.out.println("Wrong ISBN number!");
				System.out.println("Enter books ISBN number: ");
				isbn = scanner.nextLong();
			} // end while

			// asks for member id value
			System.out.println("Enter your member ID: ");
			long id = scanner.nextLong();

			// checks if the isbn is 13 numbers and asks user again until 13 numbers entered
			while (Long.toString(id).length() < 8 || Long.toString(id).length() > 8) {
				System.out.println("Wrong member ID number!");
				System.out.println("Enter your member ID number: ");
				id = scanner.nextLong();
			} // end while

			// checks if any books in library
			if (books.isEmpty()) {
				// shows an error message if no books available
				System.out.println("Book not found!");
				System.out.println("Library is empty.");
			} else {
				for (Book book : books) { // loop to get all the books
					for (Member member : members) { // loop to get all the members
						if (book.getIsbn() == isbn && member.getId() == id) { // gets the member and book with the isbn
																				// and id entered
							// sets bookExists to true if book with entered ISBN found
							bookExists = true;
							// checks if user not over loan allowance
							if (member.getBooksBorrowed() <= member.getMAX_LOANS()) {
								// shows books information
								System.out.println(book.toString());
								// sets book borrowed to true
								book.setBorrowed(true);
								// takes 1 off from quantity
								book.setQty(book.getQty() - 1);
								// adds to members borrowed books
								member.setBooksBorrowed(member.getBooksBorrowed() + 1);
								// prints message
								System.out.println(member.getName() + ", book borrowed successfully!");

								isbnNumber = book.getIsbn();
								userID = member.getId();

								// add to the hashmap books ISBN and user ID to see which user borrowed the book
								userBorrowed.put(userID, isbnNumber);
							} else {
								// prints error message if user over loan allowance
								System.out.println("You can`t borrow any more books!");
							} // end else
						} // end if
					} // end for loop for Members
				} // end for loop for Books
			} // end else
			//if (!bookExists) {
				// shows a message if can`t find a book with entered ISBN
				//System.out.println("Book with this ISBN not in the library!");
			//} // end if
		} catch (InputMismatchException e) {
			// shows error message if wrong data type entered
			System.out.println("Incorrect data type entered!");
		} // end catch

		// shows menu after borrowing a book
		showMenu(books, members, userBorrowed, scanner);
	}// end borrowBook

	static void searchBooks(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {
		boolean bookExists = false;

		try {
			// asks for isbn value
			System.out.println("Enter books ISBN number: ");
			long enteredIsbn = scanner.nextLong();

			// checks if the isbn is 13 numbers and asks user again until 13 numbers entered
			while (Long.toString(enteredIsbn).length() < 13 || Long.toString(enteredIsbn).length() > 13) {
				System.out.println("Wrong ISBN number!");
				System.out.println("Enter books ISBN number: ");
				enteredIsbn = scanner.nextLong();
			} // end while

			// checks if any books in library
			if (books.isEmpty()) {
				// shows an error message if no books available
				System.out.println("Book not found!");
				System.out.println("Library is empty.");
			} else {
				// loops through each book to see if isbn matching and book not suspended
				for (Book book : books) {
					// if isbn matching and book not suspended
					if (book.getIsbn() == enteredIsbn && book.getSuspended() == false) {
						// set bookExists to true
						bookExists = true;
						// show book details
						System.out.println(book.toString());
					} // end if
				} // end for
					// if no book found, show a message
				if (!bookExists) {
					System.out.println("Book not found!");
					System.out.println("No book with this ISBN in library!");
				} // end if
			} // end else
		} catch (InputMismatchException e) {
			// shows error message if wrong data type entered
			System.out.println("Please enter only numbers!");

			// clears the scanner buffer
			scanner.nextLine();
		} // end catch

		// shows menu after finished searching
		showMenu(books, members, userBorrowed, scanner);
	}// end searchBooks

	static void addBooks(HashSet<Book> books, HashSet<Member> members, HashMap<Integer, Long> userBorrowed,
			Scanner scanner) {
		int bookQty = -1;
		String name;
		String author;
		String publisher;
		String publishDate;

		boolean bookExists = false;

		do {
			// try catch to catch incorrect datatypes entered
			try {
				// allows user to enter how many copies to add
				System.out.println("How many copies of this book you want to add?");
				bookQty = scanner.nextInt();

				// checks that user entered a positive number
				if (bookQty > 0) {

					// asks for isbn value
					System.out.println("Enter the ISBN of the book: ");
					long isbn = scanner.nextLong();

					// clears the scanner buffer
					scanner.nextLine();

					// checks if the isbn is 13 numbers and asks user again until 13 numbers entered
					while (Long.toString(isbn).length() < 13 || Long.toString(isbn).length() > 13) {
						System.out.println("ISBN has to be 13 numbers long!");
						System.out.println("Enter the ISBN of the book: ");
						isbn = scanner.nextLong();

						// clears the scanner buffer
						scanner.nextLine();
					} // end while

					// checks if Hashset books is empty
					if (books.isEmpty()) {

						// if is empty, asks for book details
						System.out.println("Enter the name of the book: ");
						name = scanner.nextLine();

						System.out.println("Enter the authors name: ");
						author = scanner.nextLine();

						System.out.println("Enter publishers name: ");
						publisher = scanner.nextLine();

						System.out.println("Enter publishing date: ");
						publishDate = scanner.nextLine();

						// creates a new book
						createBook(books, isbn, name, author, publisher, publishDate, bookQty, false, false);
					} else {
						// if not empty, checks if there is a book with matching isbn
						for (Book book : books) {
							if (book.getIsbn() == isbn) {
								// if book is already in library, sets bookExists to true
								bookExists = true;
								System.out.println("The book is already in the library!");
								// adds more copies of this book
								book.setQty(book.getQty() + bookQty);
								System.out.println(bookQty + " more copies of this book added in Library!");
							} // end if
						} // end for
							// if no book with matching isbn, creates a new book
						if (bookExists == false) {

							// asks for book details
							System.out.println("Enter the name of the book: ");
							name = scanner.nextLine();

							System.out.println("Enter the authors name: ");
							author = scanner.nextLine();

							System.out.println("Enter publishers name: ");
							publisher = scanner.nextLine();

							System.out.println("Enter publishing date: ");
							publishDate = scanner.nextLine();

							// creates new book
							createBook(books, isbn, name, author, publisher, publishDate, bookQty, false, false);
							System.out.println("Book \"" + name + "\" added in library.");
						} // end if
					} // end else
				} // end if
				else {
					// error message is shown if wrong number entered for how many books to add
					System.out.println("Please enter only positive numbers!");
					addBooks(books, members, userBorrowed, scanner);
				} // end else

				// catch statement to catch wrong data type entered
			} catch (InputMismatchException e) {
				System.out.println("Incorrect data type entered!");
				// clears the scanner buffer
				scanner.nextLine();

				addBooks(books, members, userBorrowed, scanner);
			} // end catch
		} while (bookQty < 0);

		// go back to menu after book added
		showMenu(books, members, userBorrowed, scanner);
	}// end addBooks

	public static void createBook(HashSet<Book> books, long isbn, String title, String author, String publisher,
			String publishDate, int qty, boolean suspended, boolean borrowed) {
		// create new book object
		Book book = new Book(isbn, title, author, publisher, publishDate, qty, suspended, borrowed);

		// add created book to HashSet books
		books.add(book);
	}// end createBook
}// end class
