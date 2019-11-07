package dk.andreasvikke.threadtest;

/*
 * Code taken from
 * http://crunchify.com/how-to-get-ping-status-of-any-http-end-point-in-java/
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Hello {

    public static void main(String args[]) throws Exception {

        String[] hostList = {"http://crunchify.com", "http://yahoo.com",
            "http://www.ebay.com", "http://google.com",
            "http://www.example.com", "https://paypal.com",
            "http://bing.com/", "http://techcrunch.com/",
            "http://mashable.com/", "http://thenextweb.com/",
            "http://wordpress.com/", "http://cphbusiness.dk/",
            "http://example.com/",
            "http://ebay.co.uk/", "http://google.co.uk/",
            "http://www.wikipedia.org/",
            "http://dr.dk", "http://pol.dk", "https://www.google.dk",
            "http://phoronix.com", "http://www.webupd8.org/",
            "https://studypoint-plaul.rhcloud.com/", "http://stackoverflow.com",
            "http://docs.oracle.com", "https://fronter.com",
            "http://imgur.com/", "http://www.imagemagick.org"
        };
        long start = System.nanoTime();
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<String>> futures = new ArrayList();

        for (int i = 0; i < hostList.length; i++) {

            String url = hostList[i];
            String status = getStatus(url);

            System.out.println(status);
        }
        long first = System.nanoTime()-start;
        start = System.nanoTime();
        for (int i = 0; i < hostList.length; i++) {
            final String URL = hostList[i];

            futures.add(executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return getStatus(URL);
                }
            }));
        }
        
        for(Future<String> future : futures) {
            System.out.println(future.get());
        }
        long stop = System.nanoTime();
        System.out.println("First Run (No-Thread): " + TimeUnit.NANOSECONDS.toSeconds(first));
        System.out.println("Second Run (Thread): " + TimeUnit.NANOSECONDS.toSeconds(stop - start));
        executor.shutdown();
    }

    public static String getStatus(String url) throws IOException {

        String result = "Error";
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                result = "Green";
            }
            if (code == 301) {
                result = "Redirect";
            }
        } catch (Exception e) {
            result = "->Red<-";
        }
        return (url + "\t\tStatus:" + result);
    }
}
