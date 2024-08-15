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
