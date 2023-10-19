package org.java.app.api.email.db.repo;

import org.java.app.api.email.db.pojo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Email, Integer> {

}
