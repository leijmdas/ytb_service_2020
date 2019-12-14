package ytb.manager.charges.service.impl;

import ytb.common.utils.YtbSql;

public class ThirdPartyServiceImpl {
    public void modifyStatus(int key_id, boolean is_active) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ytb_account.account_third_key ");
        sql.append(" set is_active=").append(!is_active);
        sql.append(" where key_id=").append(key_id);
        YtbSql.update(sql);

    }
}
