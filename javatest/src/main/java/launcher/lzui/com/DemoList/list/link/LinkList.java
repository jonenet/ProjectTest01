package launcher.lzui.com.DemoList.list.link;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: launcher.lzui.com.DemoList.list
 * ProjectName: ProjectTest01
 * Date: 2019/4/16 17:16
 */
public class LinkList<E> {

    transient Node<E> first;

    transient Node<E> last;

    private int size;
    private int modCount;

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) { first = newNode; } else { l.next = newNode; }
        size++;
        modCount++;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) { last = newNode; } else { f.prev = newNode; }
        size++;
        modCount++;
    }

    public E get(int index) {
        //判断index序号是否是合法的
//        checkElementIndex(index);
        return node(index).e;
    }

    Node<E> node(int index) {
        // assert isElementIndex(index);
        if (index < (size >> 1)) {//判断序号在总长度一半之前还是之后
            Node<E> x = first;
            for (int i = 0; i < index; i++) { x = x.next; }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) { x = x.prev; }
            return x;
        }
    }
}
