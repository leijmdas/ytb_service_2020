package ytb.project.common;

import ytb.common.context.model.YtbError;

public class ProjectYtbError extends YtbError {
    public static String CODE_PROJECT_LASTSTAGE_BIGCHANGE= "PROJECT_LASTSTAGE_BIGCHANGE";

    public ProjectYtbError(String code) {
        super(code);

    }
}

