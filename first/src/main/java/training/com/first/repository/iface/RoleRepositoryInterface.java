package training.com.first.repository.iface;

import org.springframework.data.repository.CrudRepository;

import training.com.first.model.UserModel;

public interface RoleRepositoryInterface extends CrudRepository<UserModel, Integer>{
	UserModel findByUsername(String username);
	
	UserModel findById(Integer id);
}