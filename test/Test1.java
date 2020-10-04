package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test1 {
    public static final int POW10_6 = 1000000;

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<Integer> list = new ArrayList<>();
            System.out.println("输入exit退出");
            while (true) {
                String input_int = scanner.next();
                if (input_int.equals("exit")) {
                    break;
                }
                list.add(Integer.valueOf(input_int));
            }
            Integer[] array = list.toArray(new Integer[0]);
            testRemoveAllMaxNode(array);
    }
    public static void testRemoveAllMaxNode(Integer[] cases) {

        IntegerLinkListClass<Integer> linkList1 = new IntegerLinkListClass<>(cases);
        long preMills = System.nanoTime();
        removeAllMaxNodeCommon(linkList1);
        System.out.println("耗时" + (double) (System.nanoTime() - preMills) / POW10_6 + "毫秒");
    }
    static class removeActionUnit {
        public LinkNode<Integer> maxPtr;
        public LinkNode<Integer> maxPrePtr;

        public removeActionUnit() {
        }

        public removeActionUnit(LinkNode<Integer> maxPtr, LinkNode<Integer> maxPrePtr) {
            this.maxPtr = maxPtr;
            this.maxPrePtr = maxPrePtr;
        }

        public LinkNode<Integer> getMaxPtr() {
            return maxPtr;
        }

        public void setMaxPtr(LinkNode<Integer> maxPtr) {
            this.maxPtr = maxPtr;
        }

        public LinkNode<Integer> getMaxPrePtr() {
            return maxPrePtr;
        }

        public void setMaxPrePtr(LinkNode<Integer> maxPrePtr) {
            this.maxPrePtr = maxPrePtr;
        }


    }

    public static void removeAllMaxNodeCommon(IntegerLinkListClass<Integer> list) {
        LinkNode<Integer> p = list.head.next;
        Integer maxValue = p.data;
        while (p.next != null) {
            if (p.next.data > maxValue) {
                maxValue = p.next.data;
            }
            p = p.next;
        }
        LinkNode<Integer> preNode = list.head;
        p = preNode.next;
        while(p != null){
            if (maxValue.equals(p.data)) {
                preNode.next = p.next;
            }else{
                preNode =  preNode.next;
            }
            p = preNode.next;
        }
        System.out.println("--" + list.toString());
    }

    public static void removeAllMaxNodeUseArray(IntegerLinkListClass<Integer> list) {
        removeActionUnit[] removeActionUnits = new removeActionUnit[list.size()];
        int actionCount = 0;
        LinkNode<Integer> p = list.head;
        LinkNode<Integer> maxPtr = p.next;
        LinkNode<Integer> maxPrePtr = p;
        int count = 0;
        while (p.next != null) {
            if (p.next.data > maxPtr.data) {
                maxPtr = p.next;
                maxPrePtr = p;
                removeActionUnits = null;
                removeActionUnits = new removeActionUnit[list.size()];
                count = 1;
                actionCount = 0;
            } else if (p.next.data.equals(maxPtr.data)) {
                maxPtr = p.next;
                maxPrePtr = p;
                count++;
            } else if (p.next.data < maxPtr.data) {
                count = 0;
            }

            if (count == 1) {
                removeActionUnit tmp = new removeActionUnit();
                tmp.maxPtr = maxPtr;
                tmp.maxPrePtr = maxPrePtr;
                removeActionUnits[actionCount] = tmp;
                ++actionCount;
            } else if (count != 0) {
                removeActionUnits[actionCount - 1].setMaxPtr(maxPtr);
            }
            p = p.next;
        }
        for (int i = 0; i < actionCount; i++) {
            removeActionUnits[i].maxPrePtr.next = removeActionUnits[i].maxPtr.next;
        }

        System.out.println("--" + list.toString());
    }
    public static void removeAllMaxNodeUseArrayList(IntegerLinkListClass<Integer> list) {
        List<removeActionUnit> removeActionUnits = new ArrayList<>();
        LinkNode<Integer> p = list.head;
        LinkNode<Integer> maxPtr = p.next;
        LinkNode<Integer> maxPrePtr = p;
        int count = 0;
        while (p.next != null) {
            if (p.next.data > maxPtr.data) {
                maxPtr = p.next;
                maxPrePtr = p;
                removeActionUnits.clear();
                count = 1;
            } else if (p.next.data.equals(maxPtr.data)) {
                maxPtr = p.next;
                maxPrePtr = p;
                count++;
            } else if (p.next.data < maxPtr.data) {
                count = 0;
            }
            if (count == 1) {
                removeActionUnit tmp = new removeActionUnit();
                tmp.maxPtr = maxPtr;
                tmp.maxPrePtr = maxPrePtr;
                removeActionUnits.add(tmp);
            } else if (count != 0) {
                removeActionUnits.get(removeActionUnits.size() - 1).setMaxPtr(maxPtr);
            }
            p = p.next;
        }
        for (removeActionUnit r : removeActionUnits) {
            r.maxPrePtr.next = r.maxPtr.next;
        }
        System.out.println("--" + list.toString());
    }
}

