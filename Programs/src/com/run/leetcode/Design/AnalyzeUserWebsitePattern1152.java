package com.run.leetcode.Design;

import java.util.*;

/**
 * You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
 *
 * A pattern is a list of three websites (not necessarily distinct).
 *
 *     For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 *
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 *
 *     For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 *     Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 *     Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 *
 * Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 *
 *
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation: The tuples in this example are:
 * ["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
 * The pattern ("home", "about", "career") has score 2 (joe and mary).
 * The pattern ("home", "cart", "maps") has score 1 (james).
 * The pattern ("home", "cart", "home") has score 1 (james).
 * The pattern ("home", "maps", "home") has score 1 (james).
 * The pattern ("cart", "maps", "home") has score 1 (james).
 * The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
 *
 * Example 2:
 *
 * Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
 * Output: ["a","b","a"]
 *
 *
 *
 * Constraints:
 *
 *     3 <= username.length <= 50
 *     1 <= username[i].length <= 10
 *     timestamp.length == username.length
 *     1 <= timestamp[i] <= 109
 *     website.length == username.length
 *     1 <= website[i].length <= 10
 *     username[i] and website[i] consist of lowercase English letters.
 *     It is guaranteed that there is at least one user who visited at least three websites.
 *     All the tuples [username[i], timestamp[i], website[i]] are unique.
 */
public class AnalyzeUserWebsitePattern1152 {

    private Map<String, Integer> results = new HashMap<>();
    private String result = "";
    private int resultCount = 0;

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        List<Record> records = new ArrayList<>(username.length);
        for (int i = 0; i < username.length; i++) {
            records.add(new Record(username[i], timestamp[i], website[i]));
        }
        Collections.sort(records, new Comparator<Record>(){
            @Override
            public int compare(Record o1, Record o2) {
                if (o1.username.equals(o2.username)) {
                    return o1.timestamp - o2.timestamp;
                } else {
                    return o1.username.compareTo(o2.username);
                }
            }
        });

        // Generate all triplets and calculate the counts
        String user = records.get(0).username;
        int start = 0;
        int end = 0;
        for (int i = 0; i < records.size(); i++) {
            if (!records.get(i).username.equals(user)) {
                end = i;
                process(records, start, end);
                user = records.get(i).username;
                start = i;
            }
        }
        process(records, start, records.size());

        // Turn the concatenated string to list of string for returning
        return Arrays.asList(result.split(" "));
    }
    private void process(List<Record> records, int start, int end) {

        if (end - start < 3) return;

        Set<String> visited = new HashSet<>();

        for (int i = start; i < end - 2; i++) {
            for (int j = i + 1; j < end - 1; j++) {
                for (int k = j + 1; k < end; k++) {
                    String s = new StringBuilder()
                            .append(records.get(i).website).append(" ")
                            .append(records.get(j).website).append(" ")
                            .append(records.get(k).website).toString();
                    if (!visited.contains(s)) {
                        visited.add(s);
                        int count = results.getOrDefault(s, 0) + 1;
                        results.put(s, count);
                        if (result.isEmpty() || count > resultCount || (count == resultCount && s.compareTo(result) < 0)) {
                            result = s;
                            resultCount = count;
                        }
                    }
                }
            }
        }
    }

    private class Record {
        String username;
        int timestamp;
        String website;

        public Record(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append(username).append(" ")
                    .append(timestamp).append(" ")
                    .append(website).toString();
        }
    }

}

class Solution1 {
    static class Visit{
        String userName;
        int timestamp;
        String website;

        Visit(String u,int t, String w){
            userName=u;
            timestamp=t;
            website=w;
        }
        Visit(){}
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        // Convert all the entry as visit object to ease of understand
        List<Visit> visitList = new ArrayList<>();
        for(int i=0;i<username.length;i++){

            visitList.add(new Visit(username[i],timestamp[i],website[i]));
        }

        // Sort all the visit entries using their timestamp
        Comparator<Visit> cmp = (v1,v2)->{return v1.timestamp-v2.timestamp;};
        Collections.sort(visitList,cmp);

        //Collect list of websites for each user
        Map<String,List<String>> userWebSitesMap= new HashMap<>();
        for(Visit v: visitList){
            userWebSitesMap.putIfAbsent(v.userName, new ArrayList<>());
            userWebSitesMap.get(v.userName).add(v.website);
        }

        Map<List<String>,Integer> seqUserFreMap = new HashMap<>();
        // Now get all the values of all the users
        for(List<String> websitesList:userWebSitesMap.values())
        {
            if(websitesList.size() < 3) continue; // no need to consider less than 3 entries of web site visited by user
            Set<List<String>> sequencesSet= generate3Seq(websitesList);
            // Now update the frequency of the sequence ( increment by 1 for 1 user)
            for(List<String> seq: sequencesSet)
            {
                seqUserFreMap.putIfAbsent(seq, 0);
                seqUserFreMap.put(seq, seqUserFreMap.get(seq)+1);
            }
        }

        List<String> res= new ArrayList<>();
        int MAX=0;
        for(Map.Entry<List<String>,Integer> entry:seqUserFreMap.entrySet()){
            if(entry.getValue() > MAX){
                MAX=entry.getValue();
                res=entry.getKey();
            }
            else if(entry.getValue() == MAX){
                if(entry.getKey().toString().compareTo(res.toString()) < 0){
                    res=entry.getKey();
                }
            }
        }
        return res;
    }

    // It will not return duplicate seq for each user that why we are using Set
    private Set<List<String>> generate3Seq(List<String> websitesList) {
        Set<List<String>> setOfListSeq= new HashSet<>();
        for(int i=0;i<websitesList.size();i++){
            for(int j=i+1;j<websitesList.size();j++){
                for(int k=j+1;k<websitesList.size();k++){
                    List<String> list = new ArrayList<>();
                    list.add(websitesList.get(i));
                    list.add(websitesList.get(j));
                    list.add(websitesList.get(k));
                    setOfListSeq.add(list);
                }
            }
        }
        return setOfListSeq;
    }
    /**
     *
     */
    static class Visit{
        String userName;
        int timestamp;
        String website;

        Visit(String u,int t, String w){
            userName=u;
            timestamp=t;
            website=w;
        }
        Visit(){}
    }

    public List<String> mostVisitedPattern2(String[] username, int[] timestamp, String[] website) {

        // Convert all the entry as visit object to ease of understand
        List<Visit> visitList = new ArrayList<>();
        for(int i=0;i<username.length;i++){

            visitList.add(new Visit(username[i],timestamp[i],website[i]));
        }

        // Sort all the visit entries using their timestamp
        Comparator<Visit> cmp = (v1,v2)->{return v1.timestamp-v2.timestamp;};
        Collections.sort(visitList,cmp);

        //Collect list of websites for each user
        Map<String,List<String>> userWebSitesMap= new HashMap<>();
        for(Visit v: visitList){
            userWebSitesMap.putIfAbsent(v.userName, new ArrayList<>());
            userWebSitesMap.get(v.userName).add(v.website);
        }

        Map<List<String>,Integer> seqUserFreMap = new HashMap<>();
        // Now get all the values of all the users
        for(List<String> websitesList:userWebSitesMap.values())
        {
            if(websitesList.size() < 3) continue; // no need to consider less than 3 entries of web site visited by user
            Set<List<String>> sequencesSet= generate3Seq(websitesList);
            // Now update the frequency of the sequence ( increment by 1 for 1 user)
            for(List<String> seq: sequencesSet)
            {
                seqUserFreMap.putIfAbsent(seq, 0);
                seqUserFreMap.put(seq, seqUserFreMap.get(seq)+1);
            }
        }

        List<String> res= new ArrayList<>();
        int MAX=0;
        for(Map.Entry<List<String>,Integer> entry:seqUserFreMap.entrySet()){
            if(entry.getValue() > MAX){
                MAX=entry.getValue();
                res=entry.getKey();
            }
            else if(entry.getValue() == MAX){
                if(entry.getKey().toString().compareTo(res.toString()) < 0){
                    res=entry.getKey();
                }
            }
        }
        return res;
    }

    // It will not return duplicate seq for each user that why we are using Set
    private Set<List<String>> generate3Seq(List<String> websitesList) {
        Set<List<String>> setOfListSeq= new HashSet<>();
        for(int i=0;i<websitesList.size();i++){
            for(int j=i+1;j<websitesList.size();j++){
                for(int k=j+1;k<websitesList.size();k++){
                    List<String> list = new ArrayList<>();
                    list.add(websitesList.get(i));
                    list.add(websitesList.get(j));
                    list.add(websitesList.get(k));
                    setOfListSeq.add(list);
                }
            }
        }
        return setOfListSeq;
    }
}
