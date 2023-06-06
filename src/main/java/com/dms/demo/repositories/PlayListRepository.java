package com.dms.demo.repositories;

import com.dms.demo.models.entities.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, String> {
}