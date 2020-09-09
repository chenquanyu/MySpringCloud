package com.krain.springcloud.dao;

import com.krain.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentDao {

    @Insert("insert into payment(serial) values (#{serial})")
    public int create(Payment payment);

    @Select("select id, serial from payment where id = #{id}")
    public Payment getByPaymentId(Long id);
}
