package com.edubridge.contact_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.contact_app.model.Contact;
import com.edubridge.contact_app.util.DBUtils;

public class ContactDaoImpl implements ContactDao {

	@Override
	public List<Contact> getAllContacts() {
		//
		String SELECT = "select * from contact";

		Connection con = DBUtils.getConnection();

		List<Contact> contacts = new ArrayList<Contact>();

		try {
			PreparedStatement ps = con.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			System.out.println("Contacts List are : ");
			while (rs.next()) {
				Contact c = new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setMobile(rs.getLong("mobile"));
				contacts.add(c); // we have to add
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contacts;
	}

	// ------------------------------------------------------------------------------

	@Override
	public Contact getContact(String name) {
		// to search the Contact details using NAME.
		Connection con = DBUtils.getConnection();
		Contact c = null;
		String select = "select * from contact where name=?";

		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setMobile(rs.getLong("mobile"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	// ------------------------------------------------------------------------------

	@Override
	public int addContact(Contact c) {
		// Build Connection with MySQL using DBUtils Class.
		// We need to assign Values to three ?,?,?.
		String INSERT = "insert into  contact(id, name, email, mobile) values(?,?,?,?)";

		Connection con = DBUtils.getConnection();

		int status = 0;

		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setString(3, c.getEmail());
			ps.setLong(4, c.getMobile());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}

		return status;
	}

	// ------------------------------------------------------------------------------

	@Override
	public int updateContact(Contact c) {
		// Updating the Contact Record in table.
		String update = "update contact set name=?, email=?, mobile=? where id=?";
		Connection con = DBUtils.getConnection();
		int status = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getId());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	// ------------------------------------------------------------------------------

	@Override
	public int deleteContact(Contact c) {
		String delete = "delete from contact where id = ?";

		Connection con = DBUtils.getConnection();

		int status = 0;

		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, c.getId());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}

		return status;
	}

	// ------------------------------------------------------------------------------

	@Override
	public int deleteAllContacts() {
		// TODO Auto-generated method stub
		String delete_all = "delete from contact";

		Connection con = DBUtils.getConnection();

		int status = 0;

		try {
			PreparedStatement ps = con.prepareStatement(delete_all);
//			ps.setInt(1, c.getId());			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}
		return status;

	}

	// ------------------------------------------------------------------------------

	@Override
	public Contact searchContact(int id) {
		// to search the Contact details using ID.
		Connection con = DBUtils.getConnection();
		Contact c = null;
		String select = "select * from contact where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setMobile(rs.getLong("mobile"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	@Override
	public int truncateContact() {
		// TODO Auto-generated method stub.
		String truncate = "truncate table contact";

		Connection con = DBUtils.getConnection();

		int status = 0;

		try {
			PreparedStatement ps = con.prepareStatement(truncate);
//			ps.setInt(1, c.getId());			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<Contact> describeContact() {
		// TODO Auto-generated method stub
		String describe = "describe contact";

		Connection con = DBUtils.getConnection();

		List<Contact> contacts = new ArrayList<Contact>();

		try {
			PreparedStatement ps = con.prepareStatement(describe);
			ResultSet rs = ps.executeQuery();
			System.out.println("Description of Contact : ");
			while (rs.next()) {
				Contact c = new Contact();
				c.setName(rs.getString("Field"));
				c.setName(rs.getString("Type"));
				c.setName(rs.getString("Null"));
				c.setName(rs.getString("Default"));
				c.setName(rs.getString("Extra"));
				contacts.add(c); // we have to add.
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}

}
