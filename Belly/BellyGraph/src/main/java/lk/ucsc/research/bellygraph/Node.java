package lk.ucsc.research.bellygraph;

/**
 *
 * @author agentmilindu
 */
public class Node {

    private String url;

    public Node(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Node.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Node other = (Node) obj;

        return this.url.equals(other.url);
    }

}
