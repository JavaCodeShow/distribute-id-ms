package com.jf.ids.controller;

import com.jf.common.aspect.log.RpcApi;
import com.jf.common.utils.id.IdGenerator;
import com.jf.distribute.ids.api.IdsApi;
import com.jf.ids.service.IdsService;
import com.jf.model.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 *
 * @author: 江峰
 * @create: 2021-03-25 4:17
 * @since: 2.22.1
 */
@Api(value = "分布式id服务controller", tags = {"分布式id"})
@RestController
@Slf4j
public class IdsController implements IdsApi {

    @Autowired
    private IdsService idsService;

    @ApiOperation("获取一个id")
    @RpcApi(apiId = "6221deeb0a849a5acc9cb183")
    public CommonResult<Long> getId() {
        log.info("还不错啊");
        long id = idsService.getId();
        return CommonResult.success(id);
    }

    @Override
    @ApiOperation("获取count个id")
    @RpcApi(apiId = "6221deeb0a849a5acc9cb184")
    public CommonResult<List<Long>> batchGetId(@PathVariable Integer count) {

        List<Long> idList = idsService.batchGetId(count);
        return CommonResult.success(idList);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            String id = IdGenerator.generateId();
            System.out.println(id);
        }
    }
}
