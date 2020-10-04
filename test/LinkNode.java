package test;

class LinkNode<E> {
    E data;
    LinkNode<E> next;

    public LinkNode(E d) {
        this.data = d;
        this.next = null;
    }

    public LinkNode() {
        this.next = null;
    }

    public LinkNode(E data, LinkNode<E> next) {
        this.next = next;
        this.data = data;
    }
}
