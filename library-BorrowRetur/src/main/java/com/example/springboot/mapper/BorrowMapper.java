package com.example.springboot.mapper;

import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.entity.Borrow;
import com.example.springboot.api.entity.Retur;
import com.example.springboot.po.BorrowReturCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {

    List<Borrow> list();

    List<Borrow> listByCondition(BaseRequest baseRequest);

    List<Retur> listReturByCondition(BaseRequest baseRequest);

    void save(Borrow obj);

    void saveRetur(Retur obj);

    Borrow getById(Integer id);

    void updateById(Borrow user);

    void deleteById(Integer id);

    void deleteReturById(Integer id);

    void updateStatus(String status, Integer id);

    List<BorrowReturCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);  // 1 borrow  2 return
}
