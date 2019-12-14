package ytb.project.service.template;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;
import ytb.common.ytblog.YtbLog;
import ytb.project.model.projectFolderView.projectTemplate.TitleInfo;
import ytb.project.service.impl.flow.ProjectFileServiceImpl;
import java.util.stream.Stream;

public class VersionRule extends ProjectFileServiceImpl {
    public final static byte COPY_TITLE_SAME = 0;
    public final static byte COPY_TITLE_SIMPLE = 1;
    public final static byte COPY_TITLE_UPGRADE = 2;

    //设置版本号
    public String setVersion(String docVer, int status) {

        String[] s = docVer.split("\\.");
        if (s.length > 2){//进行中
            if(status == 0){
                s[0] = String.valueOf(Integer.parseInt(s[0])+1);//a
            }else if(status == 1){
                s[1] = String.valueOf(Integer.parseInt(s[1])+1);//p
            }else if(status == 2){
                s[2] = String.valueOf(Integer.parseInt(s[2])+1);//m
            }else{
                s[3] = String.valueOf(Integer.parseInt(s[3])+1);//n
            }
        }else{  //洽谈文档
            s[1] = String.valueOf(Integer.parseInt(s[1])+1);
        }
        StringBuilder sb = new StringBuilder();
        Stream<String> stream = Stream.of(s);
        stream.forEach(p->{
            sb.append(p).append(".");
        });
        String str = new String(sb);
        str = str.substring(0,str.lastIndexOf("."));
        return str;
    }


    //设置版本号
    public  String getVersion (String docVer){

        String[] s = docVer.split("\\.");

        return s[0];
    }



    public static StringBuilder buildFirstTemplateTitle(TitleInfo info) {
        return buildFirstTemplateTitle(info.getStatus(),info.getTitle(),info.getProjectName(),info.getTaskName());
    }

    public static StringBuilder buildFirstTemplateTitle(byte status, String title, String projectName,
                                                        String taskName) {
        StringBuilder st = new StringBuilder(128);

        title = title.split("V")[0].trim();
        if (status == 1) {
            if (title.split("【").length > 1) {
                int s = title.indexOf("【");
                int e = title.lastIndexOf("】");
                st.append(title.substring(0, s)).append(projectName);
                st.append("-").append(taskName);
                st.append(title.substring(e + 1, title.length()));
                YtbLog.logDebug(st);
            } else {
                st.append(projectName).append("-").append(taskName);
                st.append("-").append(title);
            }
        }  else if (status == 2) {
            st.append(projectName).append("-").append(title);
        }  else {
            st.append(title);
        }

        return st;
    }


}
