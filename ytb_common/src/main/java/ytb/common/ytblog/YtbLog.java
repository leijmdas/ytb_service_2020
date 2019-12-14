package ytb.common.ytblog;


import ch.qos.logback.classic.Logger;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.YtbContext;


public final class YtbLog {

    public static YtbLogger loggerImpl = new YtbLogger();

    public static Logger getRootLogger() {
        return rootLogger;
    }

    public static void setRootLogger(Logger rootLogger) {
        YtbLog.rootLogger = rootLogger;
    }

    public static ch.qos.logback.classic.Logger rootLogger = loggerImpl.getRootLogger();

    //和开源软件一起存日志
    public static void logService(Object title, Object msg) {
        loggerImpl.logService(title, msg);
    }

    public static void logService(IUserContext context, Object title, Object msg) {
        logService(context, title, msg);
    }

    public static void logRun(Object msg) {
        loggerImpl.logDebug(msg);
        loggerImpl.logRun(msg);
    }

    public static void logRun(String title, Object msg) {
        loggerImpl.logDebug(title, msg);
        loggerImpl.logRun(title, msg);
    }

    public static void logError(Object msg) {
        loggerImpl.logError(msg);
    }

    public static void logError(Object title, Object msg) {
        loggerImpl.logError(title, msg);
    }

    public static void logError(IUserContext context, Object title, Object msg) {
        loggerImpl.logError(context, title, msg);

    }

    public  static  boolean isDebug() {
        return YtbContext.getYtb_context().getConfig_value("SWITCH_DEBUG").equals("true");
    }
    public static void logDebug(Object msg) {
        loggerImpl.logDebug(msg);

    }

    public static void logDebug(String title, Object msg) {
        loggerImpl.logDebug(title, msg);

    }

    public static void logJtest(Object msg) {
        loggerImpl.logJtest(msg==null?"null":msg);

    }

    public static void logJtest(String title, Object msg) {
        loggerImpl.logJtest("logJtest "+title, msg==null?"null":msg);

    }

}