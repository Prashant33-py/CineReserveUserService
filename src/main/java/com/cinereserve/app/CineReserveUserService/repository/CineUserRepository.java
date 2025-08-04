package com.cinereserve.app.CineReserveUserService.repository;

import com.cinereserve.app.CineReserveUserService.model.CineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CineUserRepository extends JpaRepository<CineUser, Integer> {
    CineUser findByUserName(String userName);

}
