public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}

// single pass leading and lagging pointer approach
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // still want dummy for head deletion
        dummy.next = head;
        ListNode leading = dummy;
        ListNode lagging = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) { // need n+1 hops ahead to get node before n.  n = 2. excutes for 1, 2, 3.
            leading = leading.next;
        }
        // Move first to the end, maintaining the gap
        while (leading != null) {
            leading = leading.next;
            lagging = lagging.next;
        }
        lagging.next = lagging.next.next;
        return dummy.next;
    }
}

// 2 pass approach: O(2L) = O(L)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // create dummy node for instances where head is deleted or tail deleted
        dummy.next = head; // linked to true head
        int length = 0;
        ListNode first = head;
        while (first != null) { //  found length
            length++;
            first = first.next;
        }
        length -= n; // iterating to length-1. if n = length edge casee of heead deleeetiono handled  since lngth == 0
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next; // in case of tail removal, tail pointer defaults to null so new tail points to null. no tail dummy needed
        return dummy.next;
    }
}



// my solution, works and good space efficincy, but sloppy with handlng edge cases. needs dummy node for two pass optimized
//class Solution {
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        int length = 1;
//        ListNode temp = head;
//
//        while (temp.next != null) { // O(n) cost time. find length
//            temp = temp.next;
//            length++;
//        }
//        if (length == 1) {
//            return null;
//        } else if (n == length) {
//            temp = head;
//            head = head.next;
//            temp.next = null;
//        } else{
//            temp = head;
//
//            for (int i = 1; i < length - n; i++) { // retrieve node prior to node to be deleted
//                temp = temp.next;
//            }
//
//            ListNode delete = temp.next; // delete the node
//            if (n == 1) {
//                temp.next = null;
//            } else {
//                temp.next = temp.next.next;
//                delete.next = null;
//            }
//        }
//
//        return head;
//    }
//}
//
//
//
