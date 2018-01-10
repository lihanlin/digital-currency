package cn.duizhang.finance.strategy;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.client.BaseClient;
import cn.duizhang.finance.client.BithumbClient;
import cn.duizhang.finance.client.OkexClient;
import cn.duizhang.finance.client.ZbClient;

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
        System.out.println(coinType+":"+price1+"--"+price2+" 差价："+result*100+"%");
        return result;
    }

    public static void main(String args[]){
        System.out.println("okex to bithumb");
        MovingBricks movingBricks1 = new MovingBricks(new OkexClient(),new BithumbClient());
        movingBricks1.run();

        System.out.println("zb to bithumb");
        MovingBricks movingBricks2 = new MovingBricks(new ZbClient(),new BithumbClient());
        movingBricks2.run();

        System.out.println("zb to okex");
        MovingBricks movingBricks3 = new MovingBricks(new OkexClient(),new ZbClient());
        movingBricks3.run();
    }
}
