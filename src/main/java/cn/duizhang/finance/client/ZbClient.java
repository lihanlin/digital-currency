package cn.duizhang.finance.client;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.exception.NoThisCoinException;
import cn.duizhang.finance.exchange.zb.ZbStockRestApi;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.List;

public class ZbClient extends BaseClient{

    String url_prex = "http://api.zb.com";
    private ZbStockRestApi stockGet;

    public ZbClient(){
        stockGet = new ZbStockRestApi(url_prex);
    }
    @Override
    public String getExchangeName() {
        return "zb";
    }

    @Override
    public List<CoinType> getSupportCoinTypes() {
        return null;
    }

    @Override
    public String getCoinTypeString(CoinType coinType) throws NoThisCoinException {
        return null;
    }

    @Override
    public String getCurrentTickString(CoinType coinType) {
        try {
            String response = stockGet.ticker(coinType.name().toLowerCase()+"_usd");
            return response;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public float getCurrentUsdPrice(CoinType coinType) {
        try {
            String response = stockGet.ticker(coinType.name().toLowerCase()+"_usdt");
            //System.out.println(response);
            JSONObject tickObject = JSONObject.parseObject(response);
            if (tickObject.containsKey("ticker")) {
                JSONObject ticker = tickObject.getJSONObject("ticker");
                float last = ticker.getFloat("last");
                return last;
            }

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String args[]){
        BaseClient zbClient = new ZbClient();
        for (CoinType coinType:CoinType.values()) {
            System.out.println(coinType.name()+":"+zbClient.getCurrentUsdPrice(coinType));
        }

    }
}
