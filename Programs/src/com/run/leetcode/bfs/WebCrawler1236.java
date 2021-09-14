package com.run.leetcode.bfs;

import java.util.*;

public class WebCrawler1236 {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String hostname = getHostname(startUrl);

        queue.offer(startUrl);
        set.add(startUrl);

        while (!queue.isEmpty()) {
            String currentUrl = queue.poll();
            for (String url : htmlParser.getUrls(currentUrl)) {
                if (url.contains(hostname) && !set.contains(url)) {
                    queue.offer(url);
                    set.add(url);
                }
            }
        }

        return new ArrayList<String>(set);
    }

    private String getHostname(String Url) {
        String[] ss = Url.split("/");
        return ss[2];
    }


}
class HtmlParser {
    public List<String> getUrls(String url) {

        return Arrays.asList("http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us");
    }
}