package cn.duizhang.finance.client;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.exchange.bithumb.BithumbPublicApiClient;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;

import java.io.IOException;

public class BithumbClient  extends BaseClient {

    private BithumbPublicApiClient stockApiClient;
    private int rate =1066;

    public BithumbClient(){
        stockApiClient = new BithumbPublicApiClient();
    }
    @Override
    public float getCurrentUsdPrice(CoinType coinType) {
        try {
            String response = stockApiClient.ticker(coinType.name());
            System.out.println(response);
            JSONObject tickObject = JSONObject.parseObject(response);
            JSONObject ticker = tickObject.getJSONObject("data");
            float last = ticker.getFloat("closing_price")/rate;
            return last;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
        }
        return 0;
    }

    public static void main(String args[]){
        BaseClient bithumbClient = new BithumbClient();
        for (CoinType coinType:CoinType.values()) {
            System.out.println(coinType.name()+":"+bithumbClient.getCurrentUsdPrice(coinType));
        }

    }
}
