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
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("name"), rs.getString("password")));//add vao
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
								rs.getString("name"),
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
	public UserModel login(int id, String username, String password) {
		UserModel user = this.findByID(id);
		if(user != null && (id == user.getId()) && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public void register(UserModel user) {
		String sql = "INSERT INTO users(name, username, password) VALUES ( ?, ?, ?)";
		try {
			conn = super.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
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
		userDAO.register(new UserModel(3, "abc", "abcusername", "abcpassword"));
		
		//LOGIN
		System.out.println(userDAO.login(2, "abc", "abcpassword"));
		
		
		List<UserModel> list = userDAO.findAll();
		for (UserModel user : list) {
			System.out.print(user);
		}
	}
	

}
