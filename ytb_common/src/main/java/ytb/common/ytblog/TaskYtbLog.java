package ytb.common.ytblog;

import ytb.common.context.service.IUserContext;

public final class TaskYtbLog  {

         public static YtbLogger loggerImpl = new YtbLogger("tasklog");

         //和开源软件一起存日志
         public static void logService(Object title, Object msg) {
             loggerImpl.logService(title, msg);
         }

         public static void logService(IUserContext context, Object title, Object msg) {
             logService(context, title, msg);
         }

         public static void logRun(Object msg) {
             loggerImpl.logRun(msg);
         }

         public static void logRun(String title, Object msg) {
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


         public static void logDebug(Object msg) {
             loggerImpl.logDebug(msg);

         }

         public static void logDebug(String title, Object msg) {
             loggerImpl.logDebug(title, msg);

         }

         public static void logJtest(Object msg) {
             loggerImpl.logJtest(msg);

         }

         public static void logJtest(String title, Object msg) {
             loggerImpl.logJtest(title, msg);

         }

     }
