package com.shopme.admin.user;

import com.shopme.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from User u where u.email = :email")
    User getUserByEmail(@Param("email") String email);

    public Long countById(Integer id);


    @Query("update  User u set u.enable=:enable where u.id=:id")
    @Modifying
    public void updateEnableStatus(boolean enable, Integer id);

   /* @Query("update  User u set u.enable=?1 where u.id=?2")
    @Modifying
    public void updateEnblStatus(boolean enable, Integer id);*/
}
