package com.run.leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * Example 4:
 *
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class SimplifyPath71 {

    /**


     Algorithm

     Initialize a stack, S that we will be using for our implementation.
     Split the input string using / as the delimiter. This step is really important because no matter what, the given input is a valid path and we simply have to shorten it. So, that means that whatever we have between two / characters is either a directory name or a special character and we have to process them accordingly.
     Once we are done splitting the input path, we will process one component at a time.
     If the current component is a . or an empty string, we will do nothing and simply continue. Well if you think about it, the split string array for the string /a//b would be [a,,b]. yes, that's an empty string in between a and b. Again, from the perspective of the overall path, it doesn't mean anything.
     If we encounter a double-dot .., we have to do some processing. This simply means go one level up in the current directory path. So, we will pop an entry from our stack if it's not empty.
     Finally, if the component we are processing right now is not one of the special characters, then we will simply add it to our stack because it's a legitimate directory name.
     Once we are done processing all the components, we simply have to connect all the directory names in our stack together using / as the delimiter and we will have our shortest path that leads us to the same directory as the one provided as an input.

     Time and space is O(N)
     **/

    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        int i = 0;
        while (i < path.length()) {
            char c = path.charAt(i);
            if (c == '/') {
                i++;
                continue;
            } else {
                // start from i, we need to find the current folder name
                int j = i;
                while (j < path.length() && path.charAt(j) != '/') {
                    j++;
                }
                // now we have path.substring(i, j) for the folder name
                String folder = path.substring(i, j);
                i = j;
                if (folder.equals("..")) {
                    if (!stack.isEmpty()) {
                        // stack.pollFirst();
                        stack.pollLast();
                    }
                } else if (!folder.equals(".")) {
                    //  stack.offerFirst(folder);
                    stack.offerLast(folder);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append('/');
            // sb.append(stack.pollLast());
            sb.append(stack.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
    public String simplifyPath2(String path) {

        // Initialize a stack
        Stack<String> stack = new Stack<String>();
        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {

            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {

                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {

                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.add(directory);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/" ;
    }
}
