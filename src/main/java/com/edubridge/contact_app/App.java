package com.edubridge.contact_app;

import java.util.List;
import java.util.Scanner;

import com.edubridge.contact_app.dao.ContactDao;
import com.edubridge.contact_app.dao.ContactDaoImpl;
import com.edubridge.contact_app.model.Contact;

/**
 * Contact Application module.
 * 
 * unable to close Scanner class. System.exit(0) is successfully implemented.
 * 
 * DELETE cases are yet to complete.
 *
 */
public class App {
	public static void main(String[] args) {
		ContactDao dao = new ContactDaoImpl();
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("\nMY  CONTACT APP");
			System.out.println("----------------");
			System.out.println("1. ADD CONTACT");
			System.out.println("2. VIEW CONTACT");
			System.out.println("3. SEARCH CONTACT(using Id)");
			System.out.println("4. GET CONTACT(using Name)");
			System.out.println("5. UPDATE CONTACT");
			System.out.println("6. DELETE CONTACT");
			System.out.println("7. DELETE ALL CONTACTS");
			System.out.println("8. TRUNCATE CONTACT");
			System.out.println("9. DESCRIBE CONTACT");
			System.out.println("0. EXIT");

			System.out.println("PLEASE CHOOSE OPTION  : ");
			int option = in.nextInt();

			switch (option) {

			case 1:
				System.out.println("Enter the Contact ID : ");
				int id = in.nextInt();
				System.out.println("Enter the Contact name : ");
				String name = in.next();
				System.out.println("Enter Contact Email : ");
				String email = in.next();
				System.out.println("Enter Contact MobileNumber : ");
				long mobile = in.nextLong();

				Contact newContact = new Contact();
				newContact.setId(id);
				newContact.setName(name);
				newContact.setEmail(email);
				newContact.setMobile(mobile);

				int status = dao.addContact(newContact);
				if (status >= 1) {
					System.out.println("New Contact Added Successfully!!");
				} else {
					System.out.println("Something Went Wrong!!");
				}

				break;

			case 2:
				List<Contact> contacts = dao.getAllContacts();

				if (contacts.size() != 0) {
					for (Contact c : contacts) {
						System.out.println(c.getId() + "\t" + c.getName() + "\t" + c.getEmail() + "\t" + c.getMobile());
					}
				} else {
					System.out.println("no contacts exist!!");
				}
				break;

			case 3: // System.out.println("search contact using ID");

				System.out.println("Please Enter ID to search : ");
				int searchId = in.nextInt();
				Contact contact = dao.searchContact(searchId);
				if (contact != null) {
					{
						System.out.println(contact.getId() + "\t" + contact.getName() + "\t" + contact.getEmail() + "\t"
								+ contact.getMobile());
					}
				} else {
					System.out.println("No contacts found");
				}
				break;

			case 4: // System.out.println("get contact using Name");

				System.out.println("Please Enter NAME to get contact details : ");
				String searchName = in.next();

				Contact contact1 = dao
						.getContact(searchName.substring(0, 1).toUpperCase() + searchName.substring(1).toLowerCase());
				if (contact1 != null) {
					{
						System.out.println(contact1.getId() + "\t" + contact1.getName() + "\t" + contact1.getEmail()
								+ "\t" + contact1.getMobile());
					}
				} else {
					System.out.println("No contacts found");
				}
				break;

			case 5:
				System.out.println("Please Enter the ID to UPDATE the Record in Contact Table : ");
				int existId = in.nextInt();
				Contact existingContact = dao.searchContact(existId);
				System.out.println("ExistingContact : " + existingContact);
				System.out.println(existingContact.getEmail());

				if (existingContact != null) {

					System.out.println("Enter New Name : ");
					String newName = in.next();

					System.out.println("Enter New Email ID : ");
					String newMail = in.next();

					System.out.println("Enter New Mobile Number : ");
					long newMobile = in.nextLong();

					existingContact.setName(newName);
					existingContact.setEmail(newMail);
					existingContact.setMobile(newMobile);

					int updateQueryStatus = dao.updateContact(existingContact);
					System.out.println("Status of Update : " + updateQueryStatus);

					if (updateQueryStatus >= 1) {
						System.out.println("Contact Record Updated Successsfully!!");
					} else {
						System.out.println("Contact Record update Failed!!");
					}
				}
//    				else {
//    					System.out.println("Contact Not Found!!");
//    				}

				break;

			case 6:
				System.out.println("Please Enter the ID to DELETE the Record from Contact Table : ");
				int get_id = in.nextInt();

				Contact get_Contact = dao.searchContact(get_id);

				if (get_Contact != null) {

					int updatedQueryStatus = dao.deleteContact(get_Contact);

					if (updatedQueryStatus >= 1) {
						System.out.println("Contact Record Deleted Successsfully!!");
					} else {
						System.out.println("Contact Record deletion Failed!!");
					}
				} else {
					System.out.println("Record Details Not Found with the requested ID From Contact Table!");
				}

				break;

			case 7:
				System.out.println("Caution : \"Complete Table Will Be Removed!\"");
				System.out.println("Are you sure to DELETE contact table! \nReply With \"Y\" or \"y\" : ");
				String req = in.next();
				if (req.toLowerCase().equals("y")) {

					int updatedQueryStatus = dao.deleteAllContacts();

					if (updatedQueryStatus >= 1) {
						System.out.println("Contact Table Deleted Successsfully!!");
					} else {
						System.out.println("Contact Table deletion Failed!!");
					}
				}

				// System.out.println("Please Enter the ID to DELETE ALL the Record from Contact
				// Table : ");
				// int exit = in.nextInt();
				// if(exit == 404) {
				// continue;
				// }
				break;

			case 8:
				System.out.println("Caution : \"Table Data Will Be Erased Completely!\"");
				System.out.println("Are you sure to TRUNCATE contact table! \nReply With \"Y\" or \"y\" : ");
				String reply = in.next();
				if (reply.toLowerCase().equals("y")) {

					int updatedQueryStatus = dao.truncateContact();

					if (updatedQueryStatus >= 1) {
						System.out.println("Contact Table Truncated Successsfully!!");
					} else {
						System.out.println("Contact Table truncate Failed!!");
					}
				}

				// System.out.println("Please Enter the ID to DELETE ALL the Record from Contact
				// Table : ");
				// int exit = in.nextInt();
				// if(exit == 404) {
				// continue;
				// }
				break;

			case 9:
				List<Contact> cont = dao.describeContact();

				if (cont.size() != 0) {
					for (Contact c : cont) {
						System.out.println(c.getName() + "\t" + c.getName() + "\t" + c.getName() + "\t" + c.getName());
					}
				} else {
					System.out.println("no contact description exist!!");
				}
				break;
			case 0:
				System.out.println("Bye! for now!! \t Thankyou for reaching us....");
				System.exit(0);

			default:
				System.out.println("Invalid Option !!Please select correct option!!");

			}
		}

		// in.close();

	}
}
