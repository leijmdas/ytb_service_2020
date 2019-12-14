package ytb.common.basic.userid.service;

import ytb.common.basic.userid.model.BBNoModel;

/**
 * Package: ytb.common.basic.userid.service
 * Author: ZCS
 * Date: Created in 2018/10/16 19:01
 */
public interface BBNoService {
    //添加信息
    void addBBNOInfo(BBNoModel bbNoModel);

    //获取
    BBNoModel getBBNoByUUId(String UUId);
}
