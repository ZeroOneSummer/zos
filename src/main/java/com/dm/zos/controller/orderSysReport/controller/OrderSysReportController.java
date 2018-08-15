package com.dm.zos.controller.orderSysReport.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.dm.zos.controller.orderSysReport.entity.BBill;
import com.dm.zos.controller.orderSysReport.service.OrderSysReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 订货系统订单报表
 * @author jp
 * @date 2018-08-15 15:11:36
 */

@RestController
@RequestMapping("orderSysReport")
public class OrderSysReportController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderSysReportService orderSysReportService;

	/*@Value("${xxx.xxx.xxx}")
	private String rptFilePath;*/

	@RequestMapping("/download")
//	@RequiresPermissions("orderSysReport:download")
	public void download(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("orderSysReport-download请求参数：{}", params);
		String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
		String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");

		//参数校验
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			throw new RuntimeException("导出失败，参数缺失");
		}

		String fileName = startTime.replace("-","") + "_" + endTime.replace("-","") + "订单信息查询.txt";
		String filePath = "D:/" + fileName; //rptFilePath
		List<BBill> list= null;
		PageInfo<BBill> pageInfo =null;
		//删除原文件
		File file = new File(filePath);
		if(file.exists()) file.delete();

		while (pageInfo == null || pageInfo.isHasNextPage()){
			PageHelper.startPage(pageInfo == null ? 1 : pageInfo.getNextPage(),50); //页面容量
			list = orderSysReportService.queryList(params);
			pageInfo = new PageInfo <>(list); //重新赋上分页信息
			//写入文件中
			logger.info("第" + pageInfo.getPageNum() + "页数据开始写入");
			FileUtil.appendLines(list, filePath,"UTF-8");
			logger.info("第" + pageInfo.getPageNum() + "页数据写入完成");
		}

		logger.info("orderSysReport-download文件全部写入完成，文件路径:{}", filePath);
	}


}
