package com.dm.zos.controller.orderSysReport.service;

import com.dm.zos.controller.orderSysReport.entity.BBill;

import java.util.List;
import java.util.Map;

public interface OrderSysReportService {

	List<BBill> queryList(Map<String, Object> map);

	void saveBatch(List<BBill> bBill);
}
