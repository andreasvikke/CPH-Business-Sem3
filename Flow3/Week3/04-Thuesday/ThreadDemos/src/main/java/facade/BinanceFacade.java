package facade;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreas
 */
public class BinanceFacade {
    private final String targetURL = "https://api.binance.com/api";
    private Gson  gson = new Gson();
    
    private SymbolPrice getPriceOfSymbol(String symbol) throws MalformedURLException, ProtocolException, IOException {
        String json = request(targetURL + "/v3/avgPrice?symbol=" + symbol);
        SymbolPrice sp = gson.fromJson(json, SymbolPrice.class);
        sp.symbol = symbol;
        return sp;
    }
    
    private String request(String urlIn) throws MalformedURLException, ProtocolException, IOException {
        HttpURLConnection con = null;
        
        URL url = new URL(urlIn);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        InputStream inStream = con.getInputStream();
        String json = new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
        con.disconnect();

        return json;
    }
    
    public Map<String, Double> getMap() throws InterruptedException, ExecutionException, MalformedURLException, ProtocolException, IOException {
        String all = request(targetURL + "/v1/ticker/allPrices");
        
        SymbolPrice[] sp = gson.fromJson(all, SymbolPrice[].class);
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        List<Future<SymbolPrice>> futures = new ArrayList();
        
        for (int i = 0; i < sp.length; i++) {
            final SymbolPrice symbol = sp[i];

            futures.add(executor.submit(new Callable<SymbolPrice>() {
                @Override
                public SymbolPrice call() throws Exception {
                    return getPriceOfSymbol(symbol.symbol);
                }
            }));
        }
        
        Map<String, Double> map = new HashMap<String, Double>();
        for(Future<SymbolPrice> f : futures) {
            map.put(f.get().symbol, f.get().price);
        }
        
        executor.shutdown();
        return map;
    }
    
    class SymbolPrice {
        public String symbol;
        public Double price;
        
        public SymbolPrice(String symbol, Double price) {
            this.symbol = this.symbol;
            this.price = price;
        }
    }
}
