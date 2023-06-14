package com.dms.demo.repositories;

import com.dms.demo.models.entities.User;
import com.dms.demo.util.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String userEmail);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByCity(String city);

    List<User> findAllByGender(Gender gender);

    List<User> findAllByTokenExpiryTimeBefore(LocalDateTime time);
}