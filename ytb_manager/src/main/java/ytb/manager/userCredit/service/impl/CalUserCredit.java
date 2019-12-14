package ytb.manager.userCredit.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

public class CalUserCredit {
        public Integer calTotalUserQ() {
            StringBuilder sql=new StringBuilder();
            sql.append("call spCalTotalUserQ()");
            return YtbSql.selectOne(sql,Integer.class);
        }

}
