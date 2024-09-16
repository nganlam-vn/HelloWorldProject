package iostar.vn.dao;
import java.util.List;

import iostar.vn.models.*;

public interface IUserDAO {
	List<UserModel> findAll();
	
	UserModel findByID(int id);
	
	void insert(UserModel user);

	UserModel login(String username, String password);
	
	void register(UserModel user);
	
	boolean checkExistUsername(String username);
	
	UserModel findByUsername(String username);
	
	

}
