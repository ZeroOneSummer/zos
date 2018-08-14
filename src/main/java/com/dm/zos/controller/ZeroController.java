package com.dm.zos.controller;

import com.dm.zos.common.Dev;
import com.dm.zos.common.R;
import com.dm.zos.common.ZeroException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Description: zero接口
 * @date 2018/8/14 18:40
 */
@Api(value = "zero的接口", description = "zero的接口")
@RestController
@RequestMapping("zos")
public class ZeroController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Dev(value = "annotation msg")
    private String msg;

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R getList(@ApiParam(name = "name", value = "name", defaultValue = "my-name", required = true)
                     @RequestParam("name") String name) {

        logger.info("------------into api -> zos/list----------,annotation-" + this.msg);
        List rs = Arrays.asList("1", "2", "3", "4", "5").stream().collect(Collectors.toList());
        if (rs != null) throw new ZeroException(301,"这是个自定义错误");
        logger.info("------------out api -> zos/list----------");

        return R.success(rs);

    }

}
