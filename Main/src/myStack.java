import java.util.Stack;

class myStack<T> extends Stack<T> {
    private int max_size;

    myStack(int size) {
        super();
        max_size=size;
    }

    @Override
    public T push(T item) {
        if(this.size()>=max_size) {
            Stack<T> temp=new Stack<>();
            while(!this.isEmpty())
                temp.push(this.pop());
            while (temp.size()>=max_size)
                temp.pop();
            while (!temp.isEmpty())
                this.push(temp.pop());
        }
        super.push(item);
        return item;
    }
}
