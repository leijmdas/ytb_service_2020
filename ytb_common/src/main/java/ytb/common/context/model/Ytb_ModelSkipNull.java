package ytb.common.context.model;

import ytb.common.utils.YtbUtils;

public class Ytb_ModelSkipNull extends Ytb_Model {
    public String toString(){
        return YtbUtils.toJSONStringSkipNull(this);

    }
}
