package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.ConsumerView;
import vn.mvv.xconnect.models.enums.ClientType;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 16/06/2016.
 */
public class ConsumerApi extends BaseApi<ConsumerView> {
    private static ConsumerApi sInstance;
    public static ConsumerApi getInstance() {
        if (sInstance == null) sInstance = new ConsumerApi();
        return sInstance;
    }

    public ConsumerApi() {
        super("api/consumer");
    }

    public String loginApiUrl(String email, String password, ClientType clientType) {
        return String.format(AppConstants.API_CONSUMER_LOGIN_PATTERN, this.getBaseUrl(), "?email=" + email, "&password="+ password, "&clientType="+ clientType);
    }
}
