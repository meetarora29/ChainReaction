import java.util.Stack;

/**
 * myStack class is a custom class inherited from the Stack class
 * that limits the size of the stack to the defined maximum size.
 *
 * @param <T> is the generic type the stack will be based on
 * @author Meet Arora
 */
class myStack<T> extends Stack<T> {
    private int max_size;

    /**
     * Constructor based on the maximum size for the stack.
     *
     * @param size is the maximum size for the stack
     */
    myStack(int size) {
        super();
        max_size=size;
    }

    /**
     * Pushes an item on to the stack while maintaining the maximum size.
     * Whenever pushing an item would cause the stack to cross the
     * maximum limit, the last item ie the oldest item is removed to
     * make space for the incoming item.
     *
     * @param item is the generic type item that is to be added to the stack
     * @return item that was added
     */
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
