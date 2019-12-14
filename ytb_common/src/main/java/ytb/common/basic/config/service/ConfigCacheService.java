package ytb.common.basic.config.service;
/**
 * Package: ytb.common.testcase.service.impl
 * Author: leijming
 * Date: Created in 2018/10/12
 */

import org.springframework.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ytb.common.ehcache.CacheService.ICacheService;
import ytb.common.ehcache.EhcacheContext;
import ytb.common.utils.YtbCache;

import ytb.common.context.model.YtbError;
import ytb.common.basic.config.model.Dict_ConfigModel;
import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.basic.config.model.SubSysDictModel;

import java.util.List;

@Service
public final class ConfigCacheService extends ConfigDAOService implements ICacheService {
    RestTemplate restTemplate = new RestTemplate();

    public static String CFG_ITENM_USER_FILE_PATH_PIC = "USER_FILE_PATH_PIC";
    static ConfigCacheService configService = new ConfigCacheService();
    public static ConfigCacheService getConfigService() {
        return configService;
    }

    static String KEY_getError_msg = "ConfigCacheService::getError_msg";
    static String KEY_getConfig_value = "ConfigCacheService::getConfig_value";
    static String KEY_SubSysDictModel = "subsys_dict";


    @Override
    public Cache getCache() {
        return EhcacheContext.getEhcacheContext().getCache();
    }

    @Override
    public void refresh() {
        getCache().evict(KEY_getError_msg);
        getCache().evict(KEY_getConfig_value);
    }

    public void refreshErrorCode() {
        getCache().evict(KEY_getError_msg);
    }

    public void refreshConfig() {
        getCache().evict(KEY_getConfig_value);
    }


    public Dict_ErrorCode getError_msg(String error_code) {
        List<Dict_ErrorCode> vw = YtbCache.getSysCache(KEY_getError_msg,List.class);
        if (vw == null) {
            YtbCache.putSysCache(KEY_getError_msg, this.getDictErrorCode());
        }
        vw = YtbCache.getSysCache(KEY_getError_msg,List.class);
        for (Dict_ErrorCode dec : vw) {
            if (dec.getErrorCode().equals(error_code)) {
              return dec;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }


    public String getConfig_value(String config_item) {

        List<Dict_ConfigModel> vw = YtbCache.getSysCache(KEY_getConfig_value, List.class);
        if (vw == null) {
            List<Dict_ConfigModel> lst = this.getDictConfig();
            YtbCache.putSysCache(KEY_getConfig_value, lst);
        }
        vw = YtbCache.getSysCache(KEY_getConfig_value, List.class);
        for (Dict_ConfigModel dcm : vw) {
            if (dcm.getConfigItem().equals(config_item)) {
                return dcm.getConfigValue();

            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }

    //ip port
    public SubSysDictModel getSubsys_address(String no) {
        for (SubSysDictModel s : getSubSysDictModel()) {
            if (s.getSubsysNo().equals(no)) {
                return s;
            }
        }
        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }
    public List<SubSysDictModel> getSubSysDictModel() {
        List<SubSysDictModel> vw = YtbCache.getSysCache(KEY_SubSysDictModel,List.class);
        if (vw == null) {
            YtbCache.putSysCache(KEY_SubSysDictModel, this.getSubSysDict());

        }
        return YtbCache.getSysCache(KEY_SubSysDictModel,List.class);

    }

    public SubSysDictModel getSubsys_address(int id) {
        //JSONObject jsonObject = new JSONObject();
        for (SubSysDictModel s : getSubSysDictModel()) {
            if (s.getSubsysId()==id ) {
                return s;
            }
        }

        throw new YtbError(YtbError.CODE_NOTEXISTS_RECORD);
    }

    String getBaseUrl(String sub_sysNo){
        SubSysDictModel json=getSubsys_address(sub_sysNo);
        return "http://"+json.getIp()+":"+json.getPort() ;
    }
    String getBaseUrl(int subsysId){
        SubSysDictModel json=getSubsys_address(subsysId);
        return "http://"+json.getIp()+":"+json.getPort() ;
    }
    //String url="http://admin.youtobon.com/rest/sysuser"; rest
    /*
     * sub_sysNo=ISubSystem.SS_Project
     * url=rest/common
     * data="{...}"
     * */
    public ResponseEntity<String> postForEntity(String sub_sysNo, String url, String data) {
        return restTemplate.postForEntity(getBaseUrl(sub_sysNo) + url, data, String.class);
    }

    public ResponseEntity<String> postForEntity(int subsysId, String url, String data) {
//        System.out.println(getBaseUrl(subsysId) + url);
//        System.out.println(data);
        return restTemplate.postForEntity(getBaseUrl(subsysId) + url, data, String.class);

    }

    public byte[] postForObject(String sub_sysNo, String url, String data) {
        return restTemplate.postForObject(getBaseUrl(sub_sysNo) + url, data, byte[].class);

    }

    public String postFor(String url, String data) {
        return restTemplate.postForObject(url, data, String.class);
    }
}
