package com.dm.zos.controller.orderSysReport.service;

import java.util.List;
import java.util.Map;

public interface OrderSysReportService {

	List<com.dm.zos.controller.orderSysReport.entity.BBill> queryList(Map<String, Object> map);
}
