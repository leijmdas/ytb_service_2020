package ytb.user.dao;

import ytb.user.model.StudentPaperModel;

import java.util.List;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/5 13:30
 */
public interface StudentPaperMapper {
    //获取用户的学生论文
    List<StudentPaperModel> getStudentPaperListById(int userId);

    //添加学生论文
    void addStudentPaper(StudentPaperModel studentPaperModel);

    //删除学生论文
    void deleteStudentPaper(int paperId,int userId);

    //修改学生论文信息
    void updateStudentPaper(StudentPaperModel studentPaperModel);


}
