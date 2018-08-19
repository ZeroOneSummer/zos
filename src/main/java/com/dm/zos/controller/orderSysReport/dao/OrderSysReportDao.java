package com.dm.zos.controller.orderSysReport.dao;

import com.dm.zos.controller.orderSysReport.entity.BBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品信息-----B系统表名为：symbol_Info
 *
 * @author
 * @email @danpacmall.com
 * @date 2017-11-28 13:15:36
 */
@Mapper
public interface OrderSysReportDao{

    List<BBill> queryList(Map<String, Object> map);

    void saveBatch(List<BBill> bBill);
}
