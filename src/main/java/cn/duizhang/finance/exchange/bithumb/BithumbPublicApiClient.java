package cn.duizhang.finance.exchange.bithumb;

import cn.duizhang.finance.exchange.okcoin.HttpUtilManager;
import cn.duizhang.finance.exchange.okcoin.StringUtil;
import org.apache.http.HttpException;

import java.io.IOException;

public class BithumbPublicApiClient {
    protected String api_url = "https://api.bithumb.com";

    public BithumbPublicApiClient(){}



    /**
     * 现货行情URL
     */
    private final String TICKER_URL = "/public/ticker/";

    public String ticker(String symbol) throws HttpException, IOException {
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpGet(api_url, TICKER_URL+symbol, "");
        return result;
    }
}
