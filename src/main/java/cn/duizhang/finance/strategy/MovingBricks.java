package cn.duizhang.finance.strategy;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.client.BaseClient;
import cn.duizhang.finance.client.BithumbClient;
import cn.duizhang.finance.client.OkexClient;

public class MovingBricks {
    private BaseClient client1;
    private BaseClient client2;

    public MovingBricks(BaseClient client1,BaseClient client2){
        this.client1 =client1;
        this.client2 = client2;
    }

    public void run(){
        for (CoinType coinType:CoinType.values()) {
            try {
                getPercentageOfPriceDifference(coinType);
            } catch (Exception e) {
            }
        }
    }

    private float getPercentageOfPriceDifference(CoinType coinType) throws Exception {
        float price1 = client1.getCurrentUsdPrice(coinType);
        float price2 = client2.getCurrentUsdPrice(coinType);
        if (price1 == 0 || price2 ==0){
            throw new Exception("no "+coinType.name()+"price");
        }
        float result = (price2-price1)/price1;
        System.out.println(coinType+"差价："+result*100+"%");
        return result;
    }

    public static void main(String args[]){
        MovingBricks movingBricks = new MovingBricks(new OkexClient(),new BithumbClient());
        movingBricks.run();
    }
}
