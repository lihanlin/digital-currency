package cn.duizhang.finance.client;

import cn.duizhang.finance.CoinType;

public abstract class BaseClient {

    public abstract float getCurrentUsdPrice(CoinType coinType);
}
