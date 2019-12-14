//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import com.ccb.ccbnetpay.util.CcbPayUtil;
//import com.ccb.ccbnetpay.util.CcbSdkLogUtil;
//import com.example.constants.Constants;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
///**
// * 微信支付结果接收类
// */
//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
//
//	private static final String TAG = "WXPayEntryActivity";
//    private IWXAPI api;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//		//setContentView(R.layout.pay_result);
//    	api = WXAPIFactory.createWXAPI(this, null);
//		api.registerApp(Constants.APP_ID);
//        api.handleIntent(getIntent(), this);
//
//    }
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		setIntent(intent);
//        api.handleIntent(intent, this);
//	}
//
//	@Override
//	public void onReq(BaseReq req) {
//	}
//
//	@Override
//	public void onResp(BaseResp resp) {
//		CcbSdkLogUtil.i(TAG, "---onResp errCode = " + resp.errCode+
//				"---onResp type = " + resp.getType());
//		CcbPayUtil.getInstance().wxPayResultReq(WXPayEntryActivity.this, resp);
//	}
//}