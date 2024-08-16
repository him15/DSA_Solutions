//  12. Write a linear time iterative algorithm to reverse a linked list.

public ListNode reverseList(ListNode head) {
        ListNode prev = head;
        ListNode curr = head;
        if(curr == null || curr.next == null) return head;
        curr = curr.next;

        while(curr != null){
            ListNode nxt = curr.next;
            curr.next = prev;
            if(head == prev) prev.next = null;
            prev = curr;
            curr = nxt;
        }
        return prev;

}



// 13. Write a linear time algorithm to decide if a linked list contains a cycle or not.

public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
}


// 14. Given a linked list containing a cycle, write a linear time algorithm to delete the cycle.

public ListNode removeCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast !=  null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)break;
        }
        while(slow != null){
            head = head.next;
            if(slow.next == head){
                slow.next = null;
                return;
            }
            slow = slow.next;
        }
}





// 15. Design a linear time algorithm to decide if a given sequence of numbers is a stack sequence.

public static boolean validateStackSequences(String str) {
        Stack<Integer> st = new Stack<>();
        int n = str.length();
        int remNum = 1;
        for(int i = 0; i < str.length(); i++){
            int num = str.charAt(i) - '0';
            if(st.size() != 0 && st.peek() == num ){
                st.pop();
            }else{
                while(remNum <= n){
                    if(num == remNum){
                        remNum++;
                        break;
                    }else{
                        st.push(remNum++);
                    }
                }
            }
        }
        if(st.size() == 0) return true;
        return false;
}






// 16. You are given an array of integers, there is a sliding window of size at most k which is moving from left to right.
     //You can only see at most k numbers in the window. Each time the sliding window moves right by one position. Design an linear time
     // algorithm to compute the maximum in each window.



public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int ans[] = new int[n - k + 1];
        int idx = 0;
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < nums.length ; i++){
            // remove element from dequeu which are out of the window from front
            while(dq.size() != 0 && dq.getFirst() < i - k + 1){
                dq.removeFirst();
            }

            // remove all the element which are smaller than current element
            while(dq.size() != 0 && nums[dq.getLast()] <= nums[i] ){
                dq.removeLast();
            }
            dq.addLast(i);
            if(i >= k - 1){
                ans[idx++] = nums[dq.getFirst()];
            }

        }
        return ans;
}



// 25. Given a sequence of n numbers design a linear time algorithm to compute the length of the maximum sum sub array

// Kandane Algorithm  Little Modification
public int maxSubArray(int[] nums) {
        int sum = 0;
        int idx = 0;
        int maxLen = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length ; i++){
            if(sum < 0) {
                sum = 0;
                idx = i;
            }
            sum += nums[i];
            if(max <= sum){
                max = sum;
                maxLen = Math.max(maxLen , i - idx + 1);
            }
        }
        return maxLen;
}
