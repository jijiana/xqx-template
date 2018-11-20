package com.xqx.user.data.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xqx.user.data.pojo.entity.UserDO;

@Repository
public interface ICashBackRepository extends JpaRepository<UserDO,Long> {
	
	
	@Modifying
	@Transactional
    @Query(value = "update u_user set cashback_status = true,cashback_number=?1 where cashback_status = false",nativeQuery = true)
	public void updateCashBackInfo(Long number);
}
