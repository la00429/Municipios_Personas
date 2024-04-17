package co.edu.uptc.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree<T> {
    private NodeDouble<T> root;
    private Comparator<T> comparator;


    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(T value) {
        insert(this.root, value);
    }

    public void insert(NodeDouble<T> nodeCurrent, T value) {
        NodeDouble<T> node = new NodeDouble<T>(value);
        if (isEmpty()) {
            root = node;
        } else {
            NodeDouble<T> aux = nodeCurrent;
            NodeDouble<T> father = null;
            while (aux != null) {

                father = aux;
                if (comparator.compare(value, aux.getData()) > 0) {
                    aux = aux.getRight();
                } else {
                    aux = aux.getLeft();
                }
            }
            if (comparator.compare(value, father.getData()) > 0) {
                father.setRight(node);
            } else {
                father.setLeft(node);
            }
        }
    }

    public T searchData(T value) {
        return findData(root, value);
    }

    public T findData(NodeDouble<T> current, T value) {
        NodeDouble<T> nodeFound = new NodeDouble<T>(null);
        T dataFound = nodeFound.getData();
        if (current == null) {
            dataFound = null;
        }

        if (comparator.compare(value, current.getData()) == 0) {
            dataFound = current.getData();
        } else {
            if (comparator.compare(value, current.getData()) > 0) {
                dataFound = findData(current.getRight(), value);
            } else {
                dataFound = findData(current.getLeft(), value);
            }
        }
        return dataFound;
    }

    public void remove(T value) throws Exception{
        this.root = remove(this.root, value);
    }

    public NodeDouble<T> remove(NodeDouble<T> current, T value)  {

        if (current == null) {
            throw new RuntimeException("Element not found");
        }

        if (comparator.compare(value, current.getData()) > 0) {
            current.setRight(remove(current.getRight(), value));
        } else if (comparator.compare(value, current.getData()) < 0) {
            current.setLeft(remove(current.getLeft(), value));
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                NodeDouble<T> aux = remplace(current);
                if (current.equals(root)) {
                    root = aux;
                }
            }
        }
        return current;
    }

    private NodeDouble<T> remplace(NodeDouble<T> current) {
        NodeDouble<T> father = current;
        NodeDouble<T> aux = current.getLeft();
        while (aux.getRight() != null) {
            father = aux;
            aux = aux.getRight();
        }
        current.setData(aux.getData());
        if (father.equals(current)) {
            father.setLeft(aux.getLeft());
        } else {
            father.setRight(aux.getLeft());
        }
        return aux;
    }

    public List<T> inOrder() {
        List<T> list = new ArrayList<T>();
        return inOrder(this.root, list);
    }

    private List<T> inOrder(NodeDouble<T> node, List<T> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getData());
            inOrder(node.getRight(), list);
        }
        return list;
    }


    public List<T> preOrder() {
        List<T> list = new ArrayList<T>();
        return preOrder(this.root, list);
    }

    public List<T> preOrder(NodeDouble<T> node, List<T> list) {

        if (node != null) {
            list.add(node.getData());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
        return list;
    }

    public List<T> postOrder() {
        List<T> list = new ArrayList<T>();
        return postOrder(this.root, list);
    }

    public List<T> postOrder(NodeDouble<T> node, List<T> list) {
        if (node != null) {
            postOrder(node.getLeft(), list);
            postOrder(node.getRight(), list);
            list.add(node.getData());
        }
        return list;
    }
}
