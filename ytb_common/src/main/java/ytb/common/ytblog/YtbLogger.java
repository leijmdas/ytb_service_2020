package ytb.common.ytblog;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import ytb.common.context.model.Ytb_Model;
import ytb.common.context.service.IUserContext;
import ytb.common.context.service.impl.YtbContext;

//logger.setLevel();

public final class YtbLogger extends Ytb_Model implements IYtbLogger {

    public String getSubsys() {
        return subsys;
    }

    public void setSubsys(String subsys) {
        this.subsys = subsys;
    }

    String subsys = "common";

    public YtbLogger(String subsys) {
        this.subsys = subsys;
         loggerRun = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(subsys);
    }

    public YtbLogger() {

    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public void setRootLogger(Logger rootLogger) {
        this.rootLogger = rootLogger;
    }

    ch.qos.logback.classic.Logger rootLogger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getILoggerFactory().getLogger(
            "ROOT");
    //错误日志
    private ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(YtbLogger.class);
    private ch.qos.logback.classic.Logger loggerError = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ytberror");
    //运行日志
    private ch.qos.logback.classic.Logger loggerRun = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ytbrun");

    //调试日志
    private ch.qos.logback.classic.Logger loggerDebug = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ytbdebug");
    //jtest测试日志
    private  ch.qos.logback.classic.Logger loggerJtest = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("jtest");

    public  boolean isDebug() {
        return YtbContext.getYtb_context().getConfig_value("SWITCH_DEBUG").equals("true");
    }
    public  ch.qos.logback.classic.Logger getLogger() {
        return logger;
    }

    public  ch.qos.logback.classic.Logger getLoggerError() {
        return loggerError;
    }

    public  ch.qos.logback.classic.Logger getLoggerRun() {
        return loggerRun;
    }

    public  ch.qos.logback.classic.Logger getLoggerDebug() {
        return loggerDebug;
    }

    public  ch.qos.logback.classic.Logger getLoggerJtest() {
        return loggerJtest;
    }

    //和开源软件一起存日志
    public void logService(Object title, Object msg) {
        logger.debug(title.toString() + ":\r\n" + msg.toString());
    }

    public   void logService(IUserContext context, Object title, Object msg) {
        logger.debug("loginSso:\r\n" + context.getLoginSso().getLoginSsoJson().toString());
        logger.debug(title.toString() + ":\r\n" + msg.toString());
    }

    public   void logRun(Object msg) {

        loggerRun.info(msg.toString());
    }

    public   void logRun(String title, Object msg) {
        loggerRun.info(title.toString() + ":\r\n" + msg.toString());
    }

    public   void logError(Object msg) {
        loggerError.error(msg.toString());
    }

    public   void logError(Object title, Object msg) {
        loggerError.error(title.toString() + ":\r\n" + msg.toString());
    }

    public   void logError(IUserContext context, Object title, Object msg) {
        loggerError.error("loginSso:\r\n" + context.getLoginSso().getLoginSsoJson().toString());
        loggerError.error(title.toString() + ":\r\n" + msg.toString());
    }


    public   void logDebug(Object msg) {
        if (isDebug()) {
            loggerDebug.debug(msg.toString());
        }
    }

    public   void logDebug(String title, Object msg) {

        if (isDebug()) {
            loggerDebug.debug("Begin " + title + ":\r\n" + msg.toString() + "\r\nEnd " + title);
        }
    }

    public   void logJtest(Object msg) {
        System.out.println(msg.toString());
        loggerJtest.debug(msg.toString());

    }

    public   void logJtest(String title, Object msg) {
        System.out.println(title + ":\r\n" + msg.toString());
        loggerJtest.debug("Begin " + title + ":\r\n" + msg.toString() + "\r\nEnd " + title);

    }



}