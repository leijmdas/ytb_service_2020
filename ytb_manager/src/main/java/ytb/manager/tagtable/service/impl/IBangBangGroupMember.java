package ytb.manager.tagtable.service.impl;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IBangBangGroupMember {

    //userId群主
    void exportMember(int userId, int groupId, List<Integer> userIds) throws NoSuchMethodException, IOException,
            InvocationTargetException  ;
}
