package com.xqx.user.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xqx.user.data.pojo.entity.UserMoney;
@Repository
public interface IReturnMoneyRepository extends JpaRepository<UserMoney,Long> {
	
	@Modifying
    @Query(value = "insert into user_money(u_id,money) values(?1,?2)",nativeQuery = true)
    void insertNameAndPassword(@Param("u_id")String uId,@Param("money")String money);
}
