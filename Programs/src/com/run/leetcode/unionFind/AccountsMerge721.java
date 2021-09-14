package com.run.leetcode.unionFind;

import java.util.*;

/***
 * 
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * @author Home-Laptop
 *
 */
public class AccountsMerge721 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> t1 = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
		List<String> t2 = Arrays.asList("John", "johnnybravo@mail.com");
		List<String> t3 = Arrays.asList("John", "john_newyork@mail.com","johnsmith@mail.com");
		List<String> t4 = Arrays.asList("Mary", "mary@mail.com");
		List<List<String>> t = new ArrayList<>();
		t.add(t1);
		t.add(t2);
		t.add(t3);
		t.add(t4);
		
		AccountsMerge721 am = new AccountsMerge721();
		List<List<String>> result = am.accountsMerge2(t);
		result.stream()
				.forEach(System.out :: println);
	}
/**
 * Bes method
 *
 * Time complexity is sum of AilogAi  where Ai is each account and this time is for sorting.
 * Since union by rank and path compression we will have amortized time complexity
 */
public List<List<String>> accountsMerge(List<List<String>> accounts) {

    List<Account> accts = new ArrayList<>();
    Map<String, Account> mapMailToAccount = new HashMap<>();
    for (List<String> account : accounts) {
        String name = account.get(0);
        Account acct = new Account(name);
        accts.add(acct);
        for(int i=1;i<account.size();i++){
            String mail = account.get(i);
            Account mappedAcct = mapMailToAccount.get(mail);
            if (mappedAcct == null) {
                mapMailToAccount.put(mail, acct);
                acct.mails.add(mail);
            } else {
                union(acct, mappedAcct);
            }
        }
    }
    for (Account acct : accts) {
        if (acct.parent != acct) {
            acct.parent = find(acct);
            acct.parent.mails.addAll(acct.mails);
        }
    }
    List<List<String>> mergedAccounts = new ArrayList<>();
    for (Account acct : accts) {
        if (acct.parent == acct) {
            mergedAccounts.add(acct.convertToList());
        }
    }
    return mergedAccounts;
}
    private void union(Account acctA, Account acctB) {
        Account parentA = find(acctA);
        Account parentB = find(acctB);
        if (parentA == parentB) {
            return;
        }
        parentA.parent = parentB;// This can be viceversa too
    }
    private Account find(Account acct) {
        Account x = acct;
        while (x != x.parent) {
            x = x.parent;
        }
        while (x != acct.parent) {
            Account tmp = acct.parent;
            acct.parent = x;
            acct = tmp;
        }
        return x;
    }
    private class Account {
        private Account parent;
        private String name;
        private List<String> mails;
        private Account(String name) {
            this.parent = this;
            this.name = name;
            this.mails = new ArrayList<>();
        }
        private List<String> convertToList() {
            Collections.sort(mails);
            List<String> converted = new ArrayList<>();
            converted.add(name);
            converted.addAll(mails);
            return converted;
        }
    }



    /**
     * 18-19 ms  path compression okok
     */
    class Node {
        private String name;
        private List<String> emails;
        Node parent;
        public Node(String name_) {
            this.name = name_;
            this.emails = new ArrayList<>();
            this.parent = this;
        }
    }
    /**   private Node findP2(Node node) {
     while(node.parent != node) {
     node.parent = node.parent.parent;
     node = node.parent;
     }
     return node;
     }**/

    private Node findP(Node node) {
        Node p = node;
        while(node.parent != node) {
            //node.parent = node.parent.parent;
            node = node.parent;
        }
        Node root = node;
        //path compression
        while(p.parent != node)
        {
            p.parent = node;
            p = p.parent;
        }

        return root;
    }

    public List<List<String>> accountsMerge5(List<List<String>> accounts) {
        Map<String, Node> map = new HashMap<>();
        List<Node> all = new ArrayList<>();
        for (List<String> acc:accounts) {
            String name = acc.get(0);
            Node node = new Node(name);
            for(int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                if(!map.containsKey(email)) {
                    map.put(email, node);
                    node.emails.add(email);
                }
                else {
                    // update parent
                    Node node_p = findP(node);
                    Node exist_p = findP(map.get(email));
                    if(node_p != exist_p) {
                        node_p.parent = exist_p;
                    }
                }
            }
            all.add(node);
        }

        //add all emails to parent node
        for(Node node:all) {
            if(node.parent != node) {
                Node node_p = findP(node);
                node_p.emails.addAll(node.emails);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for(Node node:all) {
            if(node.parent == node) {
                Collections.sort(node.emails);
                node.emails.add(0, node.name);
                res.add(node.emails);
            }
        }
        return res;
    }

    /**
     *
     * @param acts
     * @return
     */
	public List<List<String>> accountsMerge2(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }
}
