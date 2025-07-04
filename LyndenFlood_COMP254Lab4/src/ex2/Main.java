package ex2;

public class Main {

    public static void main(String[]args){


        Stack<String> taskStack = new ArrayStack<>();
        taskStack.push("Read chapter");
        taskStack.push("Write summary");
        taskStack.push("Submit assignment");

        System.out.println("Before clearing:");
        System.out.println(taskStack);

        clearStack(taskStack);

        System.out.println("After clearing:");
        System.out.println(taskStack.isEmpty() ? "Stack is empty" : "Stack is not empty");


    }

    public static <E> void  clearStack(Stack<E> stack){

        if(stack.isEmpty())
            return;
        stack.pop();
        clearStack(stack);
    }
}
