package cn.duizhang.finance;

import cn.duizhang.finance.client.BaseClient;
import cn.duizhang.finance.client.BithumbClient;
import cn.duizhang.finance.client.OkexClient;
import cn.duizhang.finance.exception.NoThisCoinException;
import cn.duizhang.finance.util.DateUtil;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlinli on 2018/1/9.
 */
public class BackupData implements Runnable{
    private BaseClient client;
    private String outPutDir;
    private String dt;
    private List<CoinType> backupCoinTypes;
//    private Map<CoinType,FileOutputStream> outputStreamMap;
    private Map<CoinType,FileWriter> writerMap;

    public BackupData(BaseClient client,String outPutDir,List<CoinType> backupCoinTypes){
        this.client = client;
        this.outPutDir = outPutDir;
        this.backupCoinTypes = backupCoinTypes;
        dt="";
    }

    @Override
    public void run() {
        try {
            checkNewDay();
            for (CoinType coinType:backupCoinTypes) {
                String tick = client.getCurrentTickString(coinType);
                if (tick!=null){
                    writerMap.get(coinType).write(tick+"\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NoThisCoinException e) {
            e.printStackTrace();
        }
    }
    private void   checkNewDay() throws IOException, NoThisCoinException {
        if (!dt.equals(DateUtil.getNowDtString())){
            dealNewDay(DateUtil.getNowDtString());
        }
    }

    private void dealNewDay(String newDt) throws IOException, NoThisCoinException {
        for (Map.Entry<CoinType,FileWriter> entry:writerMap.entrySet()) {
            FileWriter writer = entry.getValue();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
//        for (Map.Entry<CoinType,FileOutputStream> entry:outputStreamMap.entrySet()) {
//            FileOutputStream outputStream = entry.getValue();
//            if (outputStream != null) {
//                outputStream.close();
//            }
//        }
        dt = newDt;
        for (CoinType coinType:backupCoinTypes){
            String filePath = outPutDir+"/"+client.getCoinTypeString(coinType)+"/"+client.getCoinTypeString(coinType)+"-data-"+newDt+".txt";
//            FileOutputStream outputStream = new FileOutputStream(filePath);
//            outputStreamMap.put(coinType,outputStream);
            FileWriter writer = new FileWriter(filePath);
            writerMap.put(coinType,writer);
        }
    }

    public static void main(String args[]){
        if (args.length!=2){
            System.err.println("Usage: clintname,outputDir");
        }
        BaseClient baseClient = null;
        List<CoinType> coinTypes = new ArrayList<>();
        if (args[0].equals("bithumb")){
            baseClient = new BithumbClient();
        }else if (args[0].equals("okex")){
            baseClient = new OkexClient();
        }
        BackupData backupData = new BackupData(baseClient,args[1],coinTypes);
    }
}
