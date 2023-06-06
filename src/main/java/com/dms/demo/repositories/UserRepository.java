package com.dms.demo.repositories;

import com.dms.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String userEmail);

    List<User> findAllByFirstName(String firstName);
}