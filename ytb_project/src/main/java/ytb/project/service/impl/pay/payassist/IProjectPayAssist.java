package ytb.project.service.impl.pay.payassist;

import ytb.project.context.UserProjectContext;
import ytb.project.model.UserAssistModel;
import ytb.account.wallet.model.AccountTradeProject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IProjectPayAssist {
    boolean isAssist();

    boolean isGroupMember(UserAssistModel assistModel);

    List<UserAssistModel> queryAssistMoneies(UserProjectContext context, int templateId) throws UnsupportedEncodingException;

    List<UserAssistModel> queryAssistMoneies(int tradeId) throws UnsupportedEncodingException;

    int payAssistPre(UserProjectContext context, String payPassword, List<UserAssistModel> monies) throws UnsupportedEncodingException;
    //非协助传模板不传文档标识 =0
    List<AccountTradeProject> payAssistConfrim(UserProjectContext context, int preTradeId,
                                               List<UserAssistModel> monies) throws UnsupportedEncodingException;

    //协助传模板文档标识
    List<AccountTradeProject> payAssistConfrim(UserProjectContext context,int templateId, int preTradeId,
                                        List<UserAssistModel> monies) throws UnsupportedEncodingException;

    int payAssistCancel(UserProjectContext context, int preTradeId,
                        List<UserAssistModel> monies) throws UnsupportedEncodingException;


}