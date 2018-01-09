package cn.duizhang.finance.client;

import cn.duizhang.finance.CoinType;
import cn.duizhang.finance.exception.NoThisCoinException;

import java.util.List;

public abstract class BaseClient {

    public abstract String getExchangeName();

    public abstract List<CoinType> getSupportCoinTypes();

    public abstract String getCoinTypeString(CoinType coinType) throws NoThisCoinException;

    public abstract String getCurrentTickString(CoinType coinType);

    public abstract float getCurrentUsdPrice(CoinType coinType);
}
