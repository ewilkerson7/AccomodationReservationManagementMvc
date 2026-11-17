package com.example.AccomodationReservationManagementMvc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AccomodationReservationManagementMvcApplication {


	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AccomodationReservationManagementMvcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccomodationReservationManagementMvcApplication.class, args);


	}

	@GetMapping("/hello")
	public String hello(@RequestParam(defaultValue = "AccomodationReservationManagementMvcApplication World") String name) {
		return String.format("Hello %s!", name);
	}

	/*

	    public static  void main(String[] args) {
        /*

         * I should be able to run all methods at once or select a single method to run by commenting out calls to the other methods.

         * test case to test writing and loading AddressBooks files
         *  1. set() - create AddressBooks with multiple addressBook.
         *  2. testcase1() - get and print each AddressBook and AddressBooks price
         *  3. testcase2() - add and check another AddressBook
         *  4. testcase3() - save AddressBooks to file
         *  5. testcase4() - save AddressBooks to file and then read it back into another Account object
         *  6. testcase5() - load AddressBooks and then edit and check AddressBook
         *  7. testcase6() - load AddressBooks and then delete and check AddressBook
         *  8. testcase7() - try to add duplicate object AddressBook
         *  9. testcase8() - try to add illegal load AddressBook
         * 10. testcase9() - try to add illegal operation AddressBook
         * 11. testcase10() - try to add illegal operation AddressBook
         *
         * Other tests:
         * 1. file does not exists() on loading
         * 2. file has data in incorrect format
         * 3. file is empty
         * 4. write() - test writing AddressBooks files
         * 5. read() - test read AddressBooks files



        logger.info("SWEN 656 - Earl L. Wilkerson Jr.");
        logger.info("Welcome Address Books!!!");

        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("SWEN 656 - Earl L. Wilkerson Jr.");
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Welcome Address Books!!!");
        System.out.println("------------------------------------");
        System.out.println();

	write();
	read();
	testcase1();
	testcase2();
	testcase3();
	testcase4();
	testcase5();
	testcase6();
	testcase7();
	testcase8();
	testcase9();
	testcase10();
}

public static String ew_filename() throws IOException {

	final String _fileName = "ew-file-test.json";
	final String _folder = "\\src\\main\\java\\FileJSON\\";
	final Path currentDir = Paths.get(".",_folder);

	if (!Files.isDirectory(currentDir)) {
		Files.createDirectory(currentDir);
	}
	String currDir = currentDir.toString();

	Path fileName = Paths.get(currDir,_fileName);

	if(!Files.exists(fileName)) {
		Files.createFile(fileName);
	}

	return fileName.toString();
}

// create AddressBooks with multiple reservations
private static AddressBooks setup() {
	AddressBooks addressBooks = null;
	try {

		// create AddressBooks objects

		Address addr = new Address("123 Main St", "Cooksville", "MD", "20876");
		Address addr2 = new Address("1 Here Dr", "Rockville", "MD", "11111");
		Address addr3 = new Address("333 Elm St", "Largo", "MD", "21726");
		Address addr4 = new Address("1133 Jockey Ct", "Henrico", "VA", "23231");

		User user = new User(LocalDate.of(1970, Month.JANUARY, 1),"Bill Smith", addr,  "301-555-1234","billsmith@gmail.com");
		User user2 = new User(LocalDate.of(1971, Month.JANUARY, 11),"Sally Jones", addr2, "240-888-3400","sallyjone@gmail.com");
		User user3 = new User(LocalDate.of(1972, Month.JANUARY, 21),"Mohamad Smith", addr3, "240-777-9999","mohamadsmith@gmail.com");
		User user4 = new User(LocalDate.of(1973, Month.JANUARY, 31),"Earl Wilkerson", addr4,  "804-222-4012","ewilkerson@gmail.com");

		AddressBook addressBook = new AddressBook(user);
		AddressBook addressBook2 = new AddressBook(user2);
		AddressBook addressBook3 = new AddressBook(user3);
		AddressBook addressBook4 = new AddressBook(user4);

		addressBooks = new AddressBooks(addressBook);
		addressBooks.addAddressBooks(addressBook2);
		addressBooks.addAddressBooks(addressBook3);
		addressBooks.addAddressBooks(addressBook4);

	} catch (Exception e) {
		String error = "Setup failed: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}

	return addressBooks;
}

public static void write() {
	System.out.println("**************************************");
	System.out.println("Write:");
	System.out.println("**************************************");
	System.out.println();

	try {

		AddressBooks setAddressBooks = setup();
		List<AddressBook> list = setAddressBooks.getAddressBooks();

		// print each object's data using toString()
		for (AddressBook addressBook : list) {
			System.out.println(addressBook.getUser().toString());
		}

		// print Account data using toString()
		System.out.println();
		System.out.println(setAddressBooks);

		// print Account data formatted
		System.out.println();
		System.out.println(setAddressBooks.toStringFormatted());

		// write trip to file
		setAddressBooks.saveToFile(ew_filename());
		System.out.println("Saving...");

	} catch (RuntimeException e)
	{
		String error = "Write setup failed: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	} catch (FileNotFoundException e) {
		logger.error("FileNotFoundException: ", e);
	} catch (IOException e) {
		logger.error("IOException: ", e);
	}

	System.out.println();

}

public static void read() {
	System.out.println("**************************************");
	System.out.println("Read:");
	System.out.println("**************************************");
	System.out.println();

	try {

		ManageAddressBooks org = new ManageAddressBooks();
		org.openFromFile(ew_filename());  // loads trip

		AddressBooks addressBooks = org.getManageAddressBooks();
		System.out.println();
		System.out.println(addressBooks.toString());

		// print Trip data formatted
		System.out.println();
		System.out.println(addressBooks.toStringFormatted());

	}catch (Exception e) {
		String error = "Read failed: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}

	System.out.println();

}

// get and print each AddressBook and AddressBooks
public static void testcase1()
{
	System.out.println("**************************************");
	System.out.println("Test Case 1: each AddressBook");
	System.out.println("**************************************");

	try
	{
		AddressBooks addressBooks = setup();

		List<AddressBook> list = addressBooks.getAddressBooks();
		for (AddressBook addressBook : list) System.out.println(addressBook);
		System.out.println();

	} catch (Exception e)
	{
		String error = "Test Case 1 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();
}

// add and check another AddressBook
public static void testcase2()
{
	System.out.println("**************************************");
	System.out.println("Test Case 2: Add AddressBook");
	System.out.println("**************************************");

	try
	{
		AddressBooks addressBooks = setup();

		Address addr = new Address("1133 Jockey Ct.", "Henrico", "VA", "23231");
		User user = new User(LocalDate.of(1976, Month.JANUARY, 31),"Tara Wilkerson", addr,  "804-222-4012","1bailey2@gmail.com");
		AddressBook addressBook = new AddressBook(user);
		addressBooks.addAddressBooks(addressBook);

		List<AddressBook> list = addressBooks.getAddressBooks();
		for (AddressBook book : list) System.out.println(book);
		System.out.println();

	} catch (Exception e)
	{
		String error = "Test Case 2 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();
}

// save Account to file
public static void testcase3()
{
	System.out.println("**************************************");
	System.out.println("Test Case 3: save to file");
	System.out.println("**************************************");

	try
	{
		AddressBooks addressBooks = setup();
		addressBooks.saveToFile(ew_filename());

		List<AddressBook> list = addressBooks.getAddressBooks();
		for (AddressBook addressBook : list) System.out.println(addressBook);

		System.out.println();

	} catch (RuntimeException e)
	{
		String error = "Test Case 3 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	} catch (FileNotFoundException e) {
		logger.error("FileNotFoundException: ", e);
	} catch (IOException e) {
		logger.error("IOException: ", e);
	}

	System.out.println();
}

// save AddressBooks to file and then read it back into another AddressBooks object
public static void testcase4()
{
	System.out.println("**************************************");
	System.out.println("Test Case 4: load from file");
	System.out.println("**************************************");

	try
	{
		// create and save file
		AddressBooks setAddressBooks = setup();
		setAddressBooks.saveToFile(ew_filename());

		// load
		AddressBooks addressBooks = new AddressBooks(ew_filename());

		List<AddressBook> list = addressBooks.getAddressBooks();
		for (AddressBook addressBook : list) System.out.println(addressBook);
		System.out.println();

	} catch (RuntimeException e)
	{
		String error = "Test Case 4 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	} catch (FileNotFoundException e) {
		logger.error("FileNotFoundException: ", e);
	} catch (IOException e) {
		logger.error("IOException: ", e);
	}

	System.out.println();
}

// load AddressBooks and then edit and check AddressBook
public static void testcase5()
{
	System.out.println("**************************************");
	System.out.println("Test Case 5: edit AddressBook");
	System.out.println("**************************************");

	try
	{
		AddressBooks setAddressBooks = setup();
		setAddressBooks.saveToFile(ew_filename());

		List<AddressBook> list = setAddressBooks.getAddressBooks();

		for (AddressBook book : list) {
			System.out.println(book);
		}

		System.out.println();
		System.out.println("Start Edit");
		System.out.println();

		// load
		AddressBooks addressBooks = new AddressBooks(ew_filename());

		Address addr = new Address("123 Main St", "Cooksville", "MD", "20871");
		User user = new User(LocalDate.of(1970, Month.JANUARY, 1),"William Smith", addr,  "301-555-1234","billsmith@gmail.com");

		AddressBook addressBook = new AddressBook(user);
		addressBooks.updateAddressBooks(addressBook);

		List<AddressBook>  listUpdate = addressBooks.getAddressBooks();
		for (AddressBook book : listUpdate) {
			System.out.println(book);
		}

	} catch (RuntimeException e)
	{
		String error = "Test Case 5 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	} catch (FileNotFoundException e) {
		logger.error("FileNotFoundException: ", e);
	} catch (IOException e) {
		logger.error("IOException: ", e);
	}

	System.out.println();
}

// load AddressBooks and then delete and check AddressBook
public static void testcase6()
{
	System.out.println("**************************************");
	System.out.println("Test Case 6: delete AddressBook");
	System.out.println("**************************************");

	try
	{
		ArrayList<LocalDate> conflist = new ArrayList<>();

		AddressBooks setAddressBooks = setup();
		setAddressBooks.saveToFile(ew_filename());

		// load
		AddressBooks addressBooks = new AddressBooks(ew_filename());

		List<AddressBook> list = addressBooks.getAddressBooks();
		for (AddressBook addressBook : list) {
			System.out.println(addressBook);
			conflist.add(addressBook.getUser().getDateOfBirth());
		}

		System.out.println();
		System.out.println("Start Deletes");
		System.out.println();

		while (!conflist.isEmpty()) {
			addressBooks.deleteAddressBooks(conflist.get(0));
			System.out.println(conflist.get(0) + " delete.");
			conflist.remove(0);

			System.out.println();

			List<AddressBook> list2 = addressBooks.getAddressBooks();
			for (AddressBook addressBook : list2) {
				System.out.println("   " + addressBook);
			}
			System.out.println();
		}

		if (addressBooks.getAddressBooks().isEmpty()) {
			System.out.println("All addressBook deleted");
		} else {
			System.out.println("AddressBook left=" + addressBooks.getAddressBooks().size());
		}

		System.out.println();

	} catch (RuntimeException e)
	{
		String error = "Test Case 6 Error: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	} catch (FileNotFoundException e) {
		logger.error("FileNotFoundException: ", e);
	} catch (IOException e) {
		logger.error("IOException: ", e);
	}

	System.out.println();
}

// try to add duplicate object AddressBook
public static void testcase7()
{
	System.out.println("**************************************");
	System.out.println("Test Case 7: AddressBook exists");
	System.out.println("**************************************");

	AddressBooks addressBooks;
	try
	{
		Address addr = new Address("1133 Jockey Ct.", "Henrico", "VA", "23231");

		User user = new User(LocalDate.of(1973, Month.JANUARY, 31),"Earl Wilkerson", addr,  "804-222-4012","ewilkerson@gmail.com");
		User user2 = new User(LocalDate.of(1973, Month.JANUARY, 31),"Tara Wilkerson", addr,  "804-222-4012","ewilkerson@gmail.com");

		AddressBook addressBook = new AddressBook(user);
		AddressBook addressBook2 = new AddressBook(user2);

		addressBooks = new AddressBooks(addressBook);
		addressBooks.addAddressBooks(addressBook2);

	} catch (Exception e)
	{
		String error = "Test Case 7: caught exception as expected: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();
}

// try to add illegal load AddressBook
public static void testcase8()
{
	System.out.println("**************************************");
	System.out.println("Test Case 8: AddressBook is illegal load");
	System.out.println("**************************************");

	try
	{
		String fileName = "";

		// create and save file
		AddressBooks setAddressBooks = setup();
		setAddressBooks.saveToFile(fileName);

	} catch (Exception e)
	{
		String error = "Test Case 8: caught exception as expected: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();
}

// try to add illegal operation AddressBook
public static void testcase9()
{
	System.out.println("**************************************");
	System.out.println("Test Case 9: AddressBook is illegal operation");
	System.out.println("**************************************");

	try
	{
		Address addr = new Address("1133 Jockey Ct.", "Henrico", "VA", "23231");


		User user = new User(LocalDate.of(1973, Month.JANUARY, 1),"Earl Wilkerson", addr,  "","ewilkerson@gmail.com");

		AddressBook addressBook = new AddressBook(user);

		AddressBooks addressBooks = new AddressBooks(addressBook);

	} catch (Exception e)
	{
		String error = "Test Case 9: caught exception as expected: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();
}

// try to add throwing operation AddressBook
public static void testcase10()
{
	System.out.println("**************************************");
	System.out.println("Test Case 10: AddressBook is throwing operation");
	System.out.println("**************************************");

	try
	{
		//----------------------------------------------
		//AddressBooksAspect - @AfterThrowing
		//----------------------------------------------

		Address addr = new Address("1133 Jockey Ct.", "Henrico", "VA", "23231");

		User user = new User(LocalDate.of(1973, Month.JANUARY, 1),"Earl Wilkerson", addr,  "804-222-4012","ewilkerson@gmail.com");

		AddressBook addressBook = new AddressBook(user);

		AddressBooks addressBooks = new AddressBooks(addressBook);
		addressBooks.throwingAddressBooks();

		//----------------------------------------------

	} catch (Exception e)
	{
		String error = "Test Case 10: caught exception as expected: ";
		System.out.println(error + e.getMessage());
		logger.error(error, e);
		System.out.println();
	}
	System.out.println();

}




	 */
}
