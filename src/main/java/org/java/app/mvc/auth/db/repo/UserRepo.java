package org.java.app.mvc.auth.db.repo;

import org.java.app.mvc.auth.db.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
