package myImplementationsW2;

public class main {

    public static void main(String[] args) {
//        MyStackArrayCheating myStack = new MyStackArrayCheating(3);
//        System.out.println(myStack);
//        myStack.push("hi");
//        System.out.println(myStack);
//        myStack.push("there");
//        System.out.println(myStack);
//        myStack.push("friend");
//        System.out.println(myStack);

        MyStackArray myStackArray = new MyStackArray();
        myStackArray.push("1");
        myStackArray.push("2");
        myStackArray.push("3");
        myStackArray.push("-");
        myStackArray.push("4");
        myStackArray.push("5");
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
        System.out.println("");
    }
}
