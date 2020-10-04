package test;

public class LinkListClass<E> {
    LinkNode<E> head;
    LinkNode<E> point;
    private int size;

    public LinkListClass() {
        head = new LinkNode<E>();
        head.next = null;
        size = 0;
    }

    public LinkListClass(E[] array) {
        head = new LinkNode<E>();
        head.next = null;
        addArray(array, null);
    }

    public int size() {
        return this.size;
    }

    public void add(LinkNode<E> node) {
        if (head.next == null) {
            this.head.next = node;
            this.point = this.head.next;
        } else {
            this.point.next = node;
            this.point = node;
        }
        size++;
    }

    public interface addNodeListener<E> {
        void onNodeAdded(E node);
    }

    ;

    public void addArray(E[] array) {
        for (E e : array) {
            add(new LinkNode<>(e));
        }
    }

    public void addArray(E[] array, addNodeListener<E> addNodeListener) {
        for (E e : array) {
            add(new LinkNode<>(e));
            if (addNodeListener != null) {
                addNodeListener.onNodeAdded(e);
            }
        }
    }

    public void addArraySafe(E[] array, addNodeListener<E> addNodeListener, Class<?> clazz) {
        for (E e : array) {
            add(new LinkNode<>(e));
            if (addNodeListener != null) {
                if (clazz.isInstance(e)) {
                    addNodeListener.onNodeAdded(e);
                }
            }
        }
    }


    public LinkNode<E> get(int index) {
        if (index > size - 1) {
            throw new IllegalArgumentException("超过链表长度");
        }
        return getNodeByIndex(head.next, 0, index);
    }

    private LinkNode<E> getNodeByIndex(LinkNode<E> node, int point, int index) {
        if (point == index) {
            return node;
        }
        return getNodeByIndex(node.next, ++point, index);
    }

    public void remove(int index) {
        if (index > size - 1) {
            throw new IllegalArgumentException("超过链表长度");
        }
        removeNodeByIndex(this.head, 0, index);
    }

    private void removeNodeByIndex(LinkNode<E> node, int point, int index) {
        if (index == 0) {
            this.head = this.head.next;
            this.size--;
            return;
        }
        if (point == index - 1) {
            node.next = node.next.next;
            this.size--;
            return;
        }
        removeNodeByIndex(node.next, ++point, index);
    }

    public void removeAllNodeByData(E data) {
        removeNodeByData(this.head, data);
    }

    private LinkNode<E> removeNodeByData(LinkNode<E> head, E data) {
        if (head == null) {
            return null;
        }
        LinkNode<E> node = removeNodeByData(head.next, data);
        if (data.equals(head.data)) {
            this.size--;
            return node;
        } else {
            head.next = node;
            return head;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        LinkNode<E> p = head;
        while (p.next != null) {
            sb.append(" ");
            sb.append(p.next.data.toString());
            sb.append(" ");
            p = p.next;
        }
        sb.append("}");
        return sb.toString();
    }
}
