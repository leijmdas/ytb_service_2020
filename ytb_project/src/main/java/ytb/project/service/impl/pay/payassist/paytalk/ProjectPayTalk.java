package ytb.project.service.impl.pay.payassist.paytalk;


import ytb.project.context.UserProjectContext;
import ytb.project.model.ProjectModel;
import ytb.project.model.ProjectTradeModel;
import ytb.project.model.UserAssistModel;
import ytb.project.service.impl.pay.payassist.IProjectPayAssist;
import ytb.project.service.impl.pay.payassist.ProjectPayAssist;
import ytb.account.wallet.model.AccountTradeProject;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.common.context.model.YtbError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//洽谈感谢金
public class ProjectPayTalk extends ProjectPayAssist implements IProjectPayAssist {

    //洽谈支付 感谢金
    public ProjectPayTalk() {
        serviceType = TradeConst.SERVICE_TYPE_thank;
    }

    public List<AccountTradeProject> payAssistConfrim(UserProjectContext context, int preTradeId,
                                                       List<UserAssistModel> monies) throws UnsupportedEncodingException {
        return payAssistConfrim(context, 0, preTradeId,  monies);
    }


    public List<UserAssistModel> queryAssistMoneies(UserProjectContext context) throws UnsupportedEncodingException {
        ProjectModel pm = context.getProjectModel();
        List<ProjectTradeModel>  models = getPayTradeService().selectListByProject(getServiceType(), pm.getProjectId());
        List<UserAssistModel> result=new ArrayList<>();
        for(ProjectTradeModel model:models) {
            List<UserAssistModel> assistModels =  queryAssistMoneies(model.getTradeId());
            result.addAll(assistModels);
        }
        return result;
    }

    //包括了tradeId
    public UserAssistModel queryAssistMoneyByTalk(UserProjectContext context, int talkId) throws UnsupportedEncodingException {

        List<UserAssistModel> result = queryAssistMoneies(context);
        for (UserAssistModel model : result) {
            if (model.getTalkId() == talkId) {
                return model;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD, "UserAssistModel");
    }
}
