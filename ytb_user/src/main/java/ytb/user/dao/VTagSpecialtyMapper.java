package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.VTagSpecialtyModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2018/9/27 13:55
 */
public interface VTagSpecialtyMapper {

    //获取一级专业能力类别
    List<VTagSpecialtyModel> getVTagSpecialList(@Param("tagId") Integer tagId,@Param("tagType") Integer tagType);

}
