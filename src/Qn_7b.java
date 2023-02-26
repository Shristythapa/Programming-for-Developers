import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.util.ArrayList;
/*Write multithreaded web crawler*/

class WebCrawler implements Runnable{
    /*this code demonstrates a basic implementation of a multithreaded web
    crawler that uses the Jsoup library to make HTTP requests and parse HTML documents.*/

    /*multithreaded web crawler independently download and analyze web pages
    * allows us to handle multiple request at same time*/
    private static final int MAX_DEPTH=3;
    private Thread thread;
    private String first_link;

    /*keep track of visited links*/
    private ArrayList<String> visitedLinks= new ArrayList<String>();

    /*to keep track of the bot that crawl through the web*/
    private int ID;

    public WebCrawler(String link, int num){
        first_link=link;
        ID=num;
        /*thread to run current class*/
        thread=new Thread(this);
        /*start when the object is created*/
        thread.start();
    }


    @Override
    public void run() {
        /*call the crawl from first link*/
        crawl(1, first_link);

    }

    private void crawl(int level, String url){


        if(level<MAX_DEPTH){
            /* makes an HTTP request to a given URL using the Jsoup library to get the HTML document of the page.*/
            Document document =request(url);

            if(document!=null){
                for(Element link: document.select("a[href]")){
                    String next_link = link.absUrl("href");

                    /*check if website*/
                    if(!visitedLinks.contains(next_link)){
                        crawl(level++,next_link);
                    }
                }
            }
        }
    }

    private Document request(String url){
        try{
            Connection conn = Jsoup.connect(url);
            Document doc = conn.get();
            if(conn.response().statusCode()==200){
                System.out.println("\n**BOT ID: "+ID+" Received WebPage at "+url);
                String title = doc.title();
                System.out.println(title);
                visitedLinks.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public Thread getThread(){
        return  this.thread;
    }

}
class Main{
    public static void main(String[] args) {
        ArrayList<WebCrawler> bots = new ArrayList<>();
        bots.add(new WebCrawler("https://www.geeksforgeeks.org/understanding-time-complexity-simple-examples/",1));
      bots.add(new WebCrawler("https://www.enjoyalgorithms.com/blog/trapping-rain-water",2));
      bots.add(new WebCrawler("https://www.codesdope.com/course/algorithms-dynamic-programming/",3));


        for(WebCrawler w: bots){
            try{
                /*join waits for one thread to complete before another is started*/
                w.getThread().join();
            } catch (InterruptedException e) {
                /*join method throws interrupted exception*/
                e.printStackTrace();
            }
        }
    }
}
