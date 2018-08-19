package com.dm.zos.controller.orderSysReport.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dm.zos.controller.orderSysReport.entity.BBill;
import com.dm.zos.controller.orderSysReport.service.OrderSysReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订货系统订单报表（上传-下载）
 * @author jp
 * @date 2018-08-15 15:11:36
 */

@RestController
@RequestMapping("orderSysReport")
public class OrderSysReportController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderSysReportService orderSysReportService;

	@Value("${rptFilePath}")
	private String rptFilePath;

	@RequestMapping("/download")
//	@RequiresPermissions("orderSysReport:download")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		logger.info("orderSysReport-download请求参数：{}", params);
		String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
		String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");

		//参数校验
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			throw new RuntimeException("导出失败，参数缺失");
		}

		String fileName = startTime.replace("-","") + "_" + endTime.replace("-","") + "订单信息查询.txt";
		String filePath = rptFilePath + fileName;
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

		logger.info("orderSysReport-download开始下载文件");
		downFile2(filePath, response);
		logger.info("orderSysReport-download下载文件完成");
	}

	/*
	 *下载文件方式1
	 **/
	/*@RequestMapping("/downFile")
	public void downFile(HttpServletResponse response) throws Exception {
		File file = new File(rptFilePath);
		InputStream is = null;
		OutputStream os = null;
		is = new FileInputStream(file);
		int num = 0;
		byte[] buff = new byte[1024];
		os = response.getOutputStream();
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode("我是谁.txt","utf-8"));
		while ((num=is.read(buff)) != -1){
			os.write(buff,0,num);
		}
		os.close();
		is.close();
	}*/

	/*
	 *下载文件方式2
	 **/
	public void downFile2(String filePath, HttpServletResponse response) throws Exception {
		File file = new File("D:/20171201_20180430订单信息查询.txt");
		InputStream is = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode("我是谁.txt","utf-8"));
		IoUtil.copy(is,os);
	}

	/*
	* 上传文件，读取文件流插入DB表中
	* */
	@RequestMapping("/import")
	public Object importData(MultipartFile file){
		//响应参数
		JSONObject data = new JSONObject();
		data.put("code", 200);
		data.put("msg", "success");
		data.put("data", null);

		try {
			InputStream is = null;
			is = file.getInputStream();
			List list = new ArrayList();
			IoUtil.readUtf8Lines(is,list);
			List<BBill> list2 = new ArrayList<>();

			list.stream()
					.forEach(bill -> {
						BBill bBill = new BBill();
						bBill = JSONUtil.toBean((String) bill,BBill.class);
						list2.add(bBill);
					});

			//有数据时
			if (list2.size() > 0){
				int total = list2.size(); //总记录数
				int size = 100; //每次插入条数
				int num = total%size == 0 ? total/size : total/size + 1; //共插入次数
				int start = 0;

				logger.info("--> 开始插入数据，数据总量{}，插入容量{}/次, 需插入次数{}", total, size, num);
				for (int i = 1; i <= num; i++){ //共插入次数
					start++; //位移标记
					List<BBill> tempList = new ArrayList <>();

					if (start != num){ //非最后一次
						for(int j = (start-1)*size; j <= (size*start-1); j++){
							tempList.add(list2.get(j)); //分批取出list2数据到tempList
						}
					} else {//最后一次 <= size的数据
						for(int j = (start-1)*size; j < total; j++){
							tempList.add(list2.get(j)); //分批取出list2数据到tempList
						}
					}

					logger.info("--> 第{}次开始插入", i);
					orderSysReportService.saveBatch(tempList);
					logger.info("--> 第{}次插入完成", i);
				}
				logger.info("--> 数据全部插入完成，共计{}条", total);

			}
		} catch (Exception e) {
			e.printStackTrace();
			data.put("code", 500);
			data.put("msg", "上传失败，数据导入失败");
			logger.info("--> 数据导入失败", e);
			return data;
		}

		return data;
	}


	/*
	* 根据日期创建不同目录
	* */
	public static void creatFile() throws Exception {
		File file = new File("d:/data/2018-08-17");
		if (!file.exists()){
			file.mkdirs();
		}
		File file2 = new File(file.getPath(),"aa1.txt");
		file2.createNewFile();
	}

}
