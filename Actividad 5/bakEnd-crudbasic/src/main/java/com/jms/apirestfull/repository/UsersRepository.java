package com.jms.apirestfull.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.jms.apirestfull.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	
	@Transactional(readOnly = true)
	Optional<Users> findByUsername(String username);
	
	

}
