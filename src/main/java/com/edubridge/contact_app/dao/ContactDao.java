package com.edubridge.contact_app.dao;



import java.util.List;

import com.edubridge.contact_app.model.Contact;

public interface ContactDao {
	
	
	List<Contact> getAllContacts();
	
	Contact getContact(String name);
	
	int addContact(Contact c);
	
	Contact searchContact(int id);
	
	int updateContact(Contact c);
	
	int deleteContact(Contact c);
	
	int deleteAllContacts();
	
	int truncateContact();
	
	List<Contact> describeContact();
	
}
