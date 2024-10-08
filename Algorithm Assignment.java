// Q.1 
// O(logN)
class Solution {
    void multiply(vector<vector<int>> &M ,vector<vector<int>> &A){
        int M11 = M[0][0] * A[0][0] + M[0][1] * A[1][0];
        int M12 = M[0][0] * A[0][1] + M[0][1] * A[1][1];
        int M21 = M[1][0] * A[0][0] + M[1][1] * A[1][0];
        int M22 = M[1][0] * A[0][1] + M[1][1] * A[1][1];
        
        M[0][0] = M11 , M[0][1] =M12 , M[1][0] =M21 , M[1][1] = M22;   
    }
    void power(vector<vector<int>> &M , int k){  
        if(k==0 or k==1)
            return ;
        power(M , k/2);
        multiply(M,M);
        if(k&1){
            vector<vector<int> > A {{1,1},{1,0}};
            multiply(M, A);
        }
    }
public:
    int fib(int n) {
       if(n==0) return  0 ;
        vector<vector<int> > M {{1,1},{1,0}};
        power(M , n-1);
        return M[0][0];
    }
};






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




// 26. Given a sequence of n numbers and an integer k, design a linear time algorithm to compute
      // the maximum sum sub array , whos length is exactly k.


public long maximumSubarraySum(int[] nums, int k) {
        long max = 0;
        long sum = 0;

        // find sum of th<e window
        for(int i = 0; i < k ; i++){
            sum += nums[i];
        }
        max = sum;

        for(int i = k; i < nums.length ; i++){
            sum -= nums[i - k];
            sum += nums[i];
            max = Math.max(max, sum);
        }
        return max;
}
