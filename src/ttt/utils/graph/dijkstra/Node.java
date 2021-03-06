package ttt.utils.graph.dijkstra;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Classe per la rappresentazione di un nodo.
 * @param <G> Il valore che contiene.
 * @param <T> Il tipo di distanza tra i nodi.
 */
public class Node<G, T> {

    private final G value;
    private final HashMap<Node<G, T>, T> links;

    public Node(G value) {
        this.value = value;
        this.links = new HashMap<>();
    }

    /**
     * Ritorna il contenuto del nodo.
     * @return Contenuto del nodo.
     */
    public G getValue() {
        return value;
    }

    /**
     * Ritorna i nodi a cui è collegato.
     * @return Set di nodi a cui è collegato.
     */
    public Set<Node<G, T>> getLinks() {
        return links.keySet();
    }

    /**
     * Aggiunge un nodo.
     * @param node Nodo da aggiungere.
     * @param value Distanza dal nodo da aggiungere.
     */
    public void addNode(Node<G, T> node, T value) {
        links.put(node, value);
    }

    /**
     * Rimuove il nodo dalla lista dei nodi collegati.
     * @param node Nodo da rimuovere.
     */
    public void removeNode(Node<G, T> node) {
        links.remove(node);
    }

    /**
     * Ritorna la distanza dal nodo passato per parametro se presente,
     * altrimenti nullo.
     * @param node Nodo
     * @return Distanza
     */
    public T getLinkValue(Node<G, T> node) {
        return links.get(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(value, node.value); // non controlla i collegamenti perche potrebbero essere aggiunti dopo
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
