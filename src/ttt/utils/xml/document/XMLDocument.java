/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.utils.xml.document;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.stream.events.XMLEvent;
import ttt.utils.xml.engine.interfaces.IXMLElement;

/**
 * Rappresenta il documento base. Questa è la root per tutti gli elementi di un
 * documento. La creazione coincide con l'evento
 * {@link XMLEvent#START_DOCUMENT}.<br>
 * Un documento è considerato esso stesso un elemento XML per poter facilitare i
 * compiti d'accesso, di scrittura e di lettura.
 *
 * @author TTT
 */
public class XMLDocument extends XMLElement {

    private final ArrayList<IXMLElement> elements = new ArrayList<>();
    private final File file;

    /**
     * Crea un nuovo documento.
     *
     * @param file Il file associato, non è necessario che il file esista sul
     * disco.
     */
    public XMLDocument(File file) {
        super(file != null ? file.getName() : "XMLDocument-generated.xml");
        this.file = file;
    }

    /**
     * Ritorna il file associato a questo documento.
     *
     * @return Il file associato.
     */
    public File getSourceFile() {
        return file;
    }

    /**
     * Ritorna la lista non modificabile di elementi contenuti nel documento .
     *
     * @return Lista di elementi contenuti nel documento.
     */
    @Override
    public List<IXMLElement> getElements() {
        elements.clear();
        elements.addAll(super.getElements());
        return Collections.unmodifiableList(elements);
    }
}
