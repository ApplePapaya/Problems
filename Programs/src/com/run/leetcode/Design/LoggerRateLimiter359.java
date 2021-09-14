package com.run.leetcode.Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 *     Logger() Initializes the logger object.
 *     bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * Output
 * [null, true, true, false, false, false, true]
 *
 * Explanation
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
 *
 *
 *
 * Constraints:
 *
 *     0 <= timestamp <= 109
 *     Every timestamp will be passed in non-decreasing order (chronological order).
 *     1 <= message.length <= 30
 *     At most 104 calls will be made to shouldPrintMessage.
 */
public class LoggerRateLimiter359 {
    /**
     * This is onehashmap solution. Although very simple,since this keeps adding the message without removing,
     * if there are lots of unique messages it will blow up the memory.
     */
    HashMap<String, Integer> map = new HashMap<>();
    public boolean shouldPrintMessage(int timestamp, String message) {

        if(!map.containsKey(message) || timestamp - map.get(message) >= 10){
            map.put(message, timestamp);
            return true;
        }

        return false;
    }

}

/**
 * Map solution is simply and nice but this version allows us to think about potential follow ups based on different conditions, especially when its described as stream of messages. Took several looks to understand how it actually works.
 *
 *     Use bucket to store timestamp at seconds granularity.
 *
 *         int idx = timestamp % 10;
 *         if (timestamp != buckets[idx]) {
 *
 * We check if time in the bucket and current timestamp is different, it means for given second, previous record is older than 10 seconds, therefore we erase all message that we no longer need. This allows to flush streams of log data that is no longer relevant while not eagerly cleaning old data. Also it makes math work nicely for logs that occur after exactly 10 seconds (e.g. 1, and 11)
 *
 * We go through all the buckets and see if current message has been posted.
 *
 *             if (timestamp - buckets[i] < 10) {
 *
 * If (current timestamp - some previous time) given from bucket is less than 10, it means there could be message within 10 seconds that has been posted. We check set to see if that message is present, if so we return false.
 * Also note that if for some reason if bucket X has not been updated for a long time, for example, current timestamp is 100 but bucket[X] = 50, it still works nicely since 100- 50 is not less than 10, we ignore such case.
 *
 * Now we know that there has been no message printed in last 10 seconds, we add the message in the set (remember in step 1, bucket is already updated with current timestamp).
 *
 *         sets[idx].add(message);
 */
class Logger {
    private int[] buckets;
    private Set[] sets;
    /** Initialize your data structure here. */
    public Logger() {
        buckets = new int[10];
        sets = new Set[10];
        for (int i = 0; i < sets.length; ++i) {
            sets[i] = new HashSet<String>();
        }
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int idx = timestamp % 10;
        if (timestamp != buckets[idx]) {
            sets[idx].clear();
            buckets[idx] = timestamp;
        }
        for (int i = 0; i < buckets.length; ++i) {
            if (timestamp - buckets[i] < 10) {
                if (sets[i].contains(message)) {
                    return false;
                }
            }
        }
        sets[idx].add(message);
        return true;
    }
}
//2 HashMap solution
class Logger2 {
    private Map<String, Integer> cacheOld;
    private Map<String, Integer> cacheNew;
    private int latest;

    /** Initialize your data structure here. */
    public Logger2() {
        this.cacheOld = new HashMap<String, Integer>();
        this.cacheNew = new HashMap<String, Integer>();

        this.latest = 0;
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(timestamp >= latest + 20){
            cacheOld.clear();
            cacheNew.clear();
            latest = timestamp;
        }else if(timestamp >= latest + 10){
            cacheOld = new HashMap<>(cacheNew);
            cacheNew.clear();
            latest = timestamp;
        }

        if(cacheNew.containsKey(message)) return false;

        if(cacheOld.containsKey(message)){
            int last = cacheOld.get(message);
            if(last + 10 > timestamp) return false;
        }

        cacheNew.put(message, timestamp);
        return true;
    }
}
class Logger2HashMap {
    private Map<String, Integer> cacheOld;
    private Map<String, Integer> cacheNew;
    private int latest;

    /** Initialize your data structure here. */
    public Logger2HashMap() {
        this.cacheOld = new HashMap<String, Integer>();
        this.cacheNew = new HashMap<String, Integer>();

        this.latest = 0;
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(timestamp >= latest + 20){
            cacheOld.clear();
            cacheNew.clear();
            latest = timestamp;
        }else if(timestamp >= latest + 10){
            cacheOld = new HashMap<>(cacheNew);
            cacheNew.clear();
            latest = timestamp;
        }

        if(cacheNew.containsKey(message)) return false;

        if(cacheOld.containsKey(message)){
            int last = cacheOld.get(message);
            if(last + 10 > timestamp) return false;
        }

        cacheNew.put(message, timestamp);
        return true;
    }
}