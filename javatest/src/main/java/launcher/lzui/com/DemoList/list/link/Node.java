package launcher.lzui.com.DemoList.list.link;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: launcher.lzui.com.DemoList.list.link
 * ProjectName: ProjectTest01
 * Date: 2019/4/16 17:16
 */
public class Node<E> {

    Node prev;
    Node next;
    E e;

    public Node(Node prev, E e, Node next) {
        this.prev = prev;
        this.next = next;
        this.e = e;
    }




}
