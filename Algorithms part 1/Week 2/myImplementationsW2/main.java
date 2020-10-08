package myImplementationsW2;

public class main {

    public static void main(String[] args) {
//        queueLinkedTest();
        test1();
//        test2();
    }

    private static void queueLinkedTest() {
        MyQueueLinkedOne queue = new MyQueueLinkedOne();
        queue.push("hello");
        System.out.println(queue);
        for (int i = 0; i < 16; i++) {
            queue.push(String.valueOf(i));
            if (i == 5 || i == 7) queue.pop();
            System.out.println(queue);
        }
        queue.push("100");
        System.out.println(queue);
        for (int i = 0; i < 30; i++) {
            queue.pop();
            System.out.println(queue);
        }
        queue.push("5");
        System.out.println(queue);
    }

    private static void queueArrayTest() {
        MyQueueArray queue = new MyQueueArray();
        for (int i = 0; i < 16; i++) {
            queue.push(String.valueOf(i));
            if (i == 5 || i == 7) queue.pop();
            System.out.println(queue);
        }
        queue.push("100");
        System.out.println(queue);
        for (int i = 0; i < 30; i++) {
            queue.pop();
            System.out.println(queue);
        }
        queue.push("5");
        System.out.println(queue);

    }

    private static void test1() {
        MyStackArray myStackArray = new MyStackArray();
        myStackArray.push("1");
        myStackArray.push("2");
        myStackArray.push("3");
        myStackArray.push("4");
        myStackArray.push("5");
        myStackArray.push("6");
        myStackArray.push("7");
        myStackArray.push("8");
        System.out.println(myStackArray);
        myStackArray.pop();
        System.out.println(myStackArray);
        myStackArray.pop();
        System.out.println(myStackArray);
        myStackArray.pop();
        System.out.println(myStackArray);
        myStackArray.pop();
        System.out.println(myStackArray);
        myStackArray.pop();
        myStackArray.pop();
        System.out.println(myStackArray);
    }

    public static void test2() {
        MyStackList myStackList = new MyStackList();
        myStackList.pop();
        myStackList.push("hello");
        System.out.println(myStackList);
        myStackList.push("hello");
        System.out.println(myStackList);
        myStackList.push("hello");
        myStackList.push("hello");
        myStackList.push("1");
        myStackList.push("2");
        myStackList.push("3");
        myStackList.push("-");
        myStackList.push("4");
        myStackList.push("5");
        System.out.println(myStackList);
        myStackList.pop();
        System.out.println(myStackList);
        myStackList.pop();
        System.out.println(myStackList);
        myStackList.pop();
        System.out.println(myStackList);
        myStackList.pop();
        System.out.println(myStackList);
    }
}
