package cn.duizhang.finance.exchange.zb;

import cn.duizhang.finance.exchange.okcoin.HttpUtilManager;
import cn.duizhang.finance.exchange.okcoin.StringUtil;
import org.apache.http.HttpException;

import java.io.IOException;

public class ZbStockRestApi {
    private String secret_key;

    private String api_key;

    private String url_prex;

    public ZbStockRestApi(String url_prex,String api_key,String secret_key){
        this.api_key = api_key;
        this.secret_key = secret_key;
        this.url_prex = url_prex;
    }

    public ZbStockRestApi(String url_prex){
        this.url_prex = url_prex;
    }

    /**
     * 现货行情URL
     */
    private final String TICKER_URL = "/data/v1/ticker?";

    public String ticker(String symbol) throws HttpException, IOException {
        cn.duizhang.finance.exchange.okcoin.HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String param = "";
        if(!StringUtil.isEmpty(symbol )) {
            if (!param.equals("")) {
                param += "&";
            }
            param += "market=" + symbol;
        }
        String result = httpUtil.requestHttpGet(url_prex, TICKER_URL, param);
        return result;
    }
}
