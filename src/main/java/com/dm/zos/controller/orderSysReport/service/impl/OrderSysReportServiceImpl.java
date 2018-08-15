package com.dm.zos.controller.orderSysReport.service.impl;

import com.dm.zos.controller.orderSysReport.dao.OrderSysReportDao;
import com.dm.zos.controller.orderSysReport.entity.BBill;
import com.dm.zos.controller.orderSysReport.service.OrderSysReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderSysReportServiceImpl implements OrderSysReportService {

	@Autowired
	private OrderSysReportDao orderSysReportDao;

	@Override
	public List<BBill> queryList(Map <String, Object> map) {
		return orderSysReportDao.queryList(map);
	}

}
