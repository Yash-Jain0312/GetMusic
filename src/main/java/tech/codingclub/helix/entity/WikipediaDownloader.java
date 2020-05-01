package tech.codingclub.helix.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.helix.global.HttpUrlConnectionExample;

public class WikipediaDownloader {
    private String keyword;

    public WikipediaDownloader(String keyword) {
        this.keyword=keyword;
    }

    public WikipediaDownloader() {

    }

    public WikiResult getResult() {
        String response="";
        String image_url="";
        if(this.keyword==null || this.keyword.length()==0) {
            return null;
        }
        this.keyword=this.keyword.trim().replaceAll("[ ]+", "_");
        String wikiUrl=getWikipediaUrlForQuery(this.keyword);
        try {
            String wikipediaResponse= HttpUrlConnectionExample.sendGet(wikiUrl);
            Document document=Jsoup.parse(wikipediaResponse, "https://en.wikipedia.org/wiki/");
            Elements childElements=document.body().select(".mw-parser-output > *");
            int state=0;
            for(Element childElement: childElements) {
                if (state == 0) {
                    if (childElement.tagName().equals("table")) {
                        state = 1;
                    }
                } else if (state == 1) {
                    if (childElement.tagName().equals("p") && !childElement.hasClass(".mw-empty-elt")) {
                        response = childElement.text();
                        break;
                    }
                }
            }
            try {
                image_url=document.body().select(".infobox img").get(0).attr("src");
            } catch(Exception e) {

            }
        } catch (Exception e) {

        }
        WikiResult wikiResult=new WikiResult(this.keyword, response, image_url);
        return wikiResult;
}

    private String getWikipediaUrlForQuery(String cleanKeyword) {
        return "https://en.wikipedia.org/wiki/"+cleanKeyword;
    }
}
