package com.xqx.user.data.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xqx.user.data.pojo.entity.UserDO;

@Repository
public interface IRegisterRepository extends JpaRepository<UserDO,Long> {
	
	@Modifying
	@Transactional
    @Query(value = "insert into u_user(name,password) values(?1,?2)",nativeQuery = true)
    void insertNameAndPassword(@Param("name")String name,@Param("password")String password);

}
