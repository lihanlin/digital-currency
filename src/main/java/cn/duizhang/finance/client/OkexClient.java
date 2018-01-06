package cn.duizhang.finance.client;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.exchange.okcoin.stock.IStockRestApi;
import cn.duizhang.finance.exchange.okcoin.stock.impl.StockRestApi;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;

import java.io.IOException;

public class OkexClient extends BaseClient {

    String url_prex = "https://www.okcoin.com";
    private IStockRestApi stockGet;

    public OkexClient(){
        stockGet = new StockRestApi(url_prex);
    }
    @Override
    public float getCurrentUsdPrice(CoinType coinType) {
        try {
            String response = stockGet.ticker(coinType.name().toLowerCase()+"_usd");
            System.out.println(response);
            JSONObject tickObject = JSONObject.parseObject(response);
            JSONObject ticker = tickObject.getJSONObject("ticker");
            float last = ticker.getFloat("last");
            return last;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String args[]){
        BaseClient okClient = new OkexClient();
        for (CoinType coinType:CoinType.values()) {
            System.out.println(coinType.name()+":"+okClient.getCurrentUsdPrice(coinType));
        }

    }
}
