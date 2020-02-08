package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.employee.common.constant.EmpInfoConstant;
import cn.edu.scau.employee.common.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.request.UserDetailQueryRequest;
import cn.edu.scau.employee.common.response.UserDetailResponse;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.UserDetail;
import cn.edu.scau.employee.service.UserDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 员工信息Service接口实现类
 * @date 2020/2/2 15:59
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public CommonResult findByName(UserDetailQueryRequest request) {
        logger.info("员工信息查询  请求参数: {}", request.toString());
        PageHelper.startPage(request.getPage().getCurrentPage(),
                request.getPage().getPageSize());
        List<UserDetail> userDetails = userDetailDao.findByName(request.getName());
        List<UserDetailResponse> responses = userDetails.stream().map(userDetail -> {
            UserDetailResponse response = ConvertUtil.convert(userDetail, UserDetailResponse.class);
            return response;
        }).collect(Collectors.toList());
        PageInfo<UserDetail> pageInfo = new PageInfo<>(userDetails);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Override
    public CommonResult add(UserDetailAddRequest request) {
        logger.info("添加员工信息  请求参数: {}", request.toString());
        UserDetail userDetail = ConvertUtil.convert(request, UserDetail.class);
        Long empNo = generateEmpNo();
        userDetail.setEmpNo(empNo);
        Long id = userDetailDao.add(userDetail);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("empNo", empNo);
        resultMap.put("id", id);
        return CommonResult.success(resultMap);
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        logger.info("删除员工信息  请求参数: {}", Arrays.toString(ids.toArray()));
        int rows = userDetailDao.deleteByIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult updateById(Long id, UserDetailAddRequest request) {
        logger.info("更新员工信息  请求参数: {}", request.toString());
        UserDetail userDetail = ConvertUtil.convert(request, UserDetail.class);
        userDetail.setId(id);
        userDetailDao.updateById(userDetail);
        return CommonResult.success();
    }

    private Long generateEmpNo() {
        RedisAtomicLong counter = new RedisAtomicLong(EmpInfoConstant.EMP_INDEX, redisTemplate.getConnectionFactory());
        long number = counter.incrementAndGet();
        number += EmpInfoConstant.MAX_NUMBER;
        String prefix = DateUtil.dateToStr(new Date(), "yyyyMMdd");
        String suffix = (number + "").substring(1);
        String empNo = prefix + suffix;
        return Long.valueOf(empNo);
    }
}
