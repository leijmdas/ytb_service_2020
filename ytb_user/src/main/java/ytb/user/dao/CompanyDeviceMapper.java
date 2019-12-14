package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyDeviceModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/10 13:01
 * Copyright: Copyright (c) 2018
 */
public interface CompanyDeviceMapper {
    //获取公司设备列表
    List<CompanyDeviceModel> getCompanyDeviceListById(int companyId);

    //添加公司设备信息
    void addCompanyDevice(CompanyDeviceModel companyDeviceModel);

    //删除公司设备信息
    void deleteCompanyDevice(@Param("companyId") int companyId ,@Param("deviceId") int deviceId);

    //修改公司设备信息
    void updateCompanyDevice(CompanyDeviceModel companyDeviceModel);
}
