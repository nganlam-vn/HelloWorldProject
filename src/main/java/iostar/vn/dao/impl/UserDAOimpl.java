package iostar.vn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import iostar.vn.configs.DBConnectMySQL;
import iostar.vn.dao.IUserDAO;
import iostar.vn.models.UserModel;

public class UserDAOimpl extends DBConnectMySQL implements IUserDAO {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UserModel> findAll() {
		String sql = "select * from users";
		List<UserModel> list = new ArrayList(); //tao 1 list de truyen du lieu
		
		try {
			conn = super.getConnection(); //ket noi db
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next() /*Next: tung dong toi cuoi bang*/) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("fullName"), rs.getString("password")));//add vao
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findByID(int id) {
		String sql = "select * from users where users.id = ?";
				
				try {
					conn = super.getConnection();
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						return new UserModel(	
								rs.getInt("id"),
								rs.getString("username"),
								rs.getString("fullName"),
								rs.getString("password")
								);
								
					}
					
				} catch (Exception e){
					e.printStackTrace();
				}
				return null;
	}
	
	public UserModel findByUsername(String username) {
		String sql = "select * from users where users.username = ?";
				
				try {
					conn = super.getConnection();
					ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						return new UserModel(	
								rs.getInt("id"),
								rs.getString("username"),
								rs.getString("fullName"),
								rs.getString("password")
								);
								
					}
					
				} catch (Exception e){
					e.printStackTrace();
				}
				return null;
	}
	


	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUsername(username);
		if(user != null && (username.equals(user.getUsername())) && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public void register(UserModel user) {
		String sql = "INSERT INTO users(fullName, username, password, email, avatar, phoneNumber, createdDate) VALUES ( ?, ?, ?, ?, ?, ? ,?)";
		try {
			conn = super.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String[] args) {
		UserDAOimpl userDAO = new UserDAOimpl();
		
		//REGISTER
//		userDAO.register(new UserModel(3, "abc", "abcusername", "abcpassword"));
		
		//LOGIN
		System.out.println(userDAO.login("nganne", "123"));
		
		
		List<UserModel> list = userDAO.findAll();
		for (UserModel user : list) {
			System.out.print(user);
		}
	}
	

}
