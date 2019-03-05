package example1;

public class LinkedList<T> {
    class Link<T> {
        private T element;

        private Link next;

        public Link(T element) {
            this.element = element;
        }

        public Link<T> getNext() {
            return next;
        }

        public void setNext(Link<T> next) {
            this.next = next;
        }

        public T getValue() {
            return element;
        }

    }

    private Link first;

    public LinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(T link) {
        Link<T> l = new Link<>(link);
        l.setNext(first);
        this.first = l;
    }

    public Link<T> delete() {
        Link<T> temp = first;
        first = first.getNext();
        return temp;
    }

    public Link<T> delete(T value){
        Link<T> current = first;
        Link<T> previous = first;
        while(!current.getValue().equals(value)){
            if(current.next == null)
                return null;
            else{
                previous = current;
                current = current.next;
            }
        }
        if(current == first)
            first = first.next;
        else
            previous.next = current.next;
        return current;
    }

    public void display() {
        Link<T> current = first;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

    public T find(T searchNode) {
        Link<T> findNode = new Link<>(searchNode);
        Link<T> current = first;
        while (current != null) {
            if (current.getValue().equals(findNode.getValue())) {
                return findNode.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.insert("Artem");
        list.insert("Roman");

        System.out.println(list.find("Artem"));

        LinkedList<Person> peopleList = new LinkedList<>();
        peopleList.insert(new Person("Artem", 22));
        peopleList.insert(new Person("Roman", 18));

        System.out.println(peopleList.find(new Person("Artem", 22)).toString());
    }

}
