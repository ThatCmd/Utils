/*
 * Copyright 2021 TTT.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ttt.utils.xml.io;

import java.io.File;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttt.utils.xml.document.XMLComment;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.document.XMLTag;

/**
 * Serve per leggere un file di testo strutturato come XML.
 *
 * @author TTT
 */
public class XMLReader {

    private static final ResourceBundle read_errors = ResourceBundle.getBundle("ttt/utils/resources/i18n/xml/read_errors");

    /*
    Quando si legge un file si deve ritornare un'istanza di una classe 
    Documento (da creare).
    Per ogni tag aperta si va a creare un nuovo elemento (classe Elemento - da creare) 
    che viene completato con i dati man mano letti, e per ogni tag chiusa si va 
    a salvare quest'ultimo nel penultimo elemento aperto (Se viene chiuso il primo
    tag allora questo elemento viene salvato nella root del documento).
    
    Ogni sezione del documento letto (perciò ad ogni evento) viene chiamata la 
    classe XMLEngine che si deve occupare di completare (assegnare i valori, 
    perciò chiamando metodi o direttamente variabili) con i valori letti.
     */
    private final File f;
    private final InputStream f_strem;

    public XMLReader(File file) {
        this.f = file;
        f_strem = null;
    }

    public XMLReader(InputStream is) {
        this.f_strem = is;
        f = null;
    }

    /**
     * Legge il file associato a questo {@link XMLReader} per estrapolarne un
     * oggetto {@link XMLDocument} con i relativi {@link XMLElement} ognungo
     * completo di tags e valori.
     *
     * @return Il nuovo documento tradotto in oggetti {@link XMLElement}
     * @throws IOException Nel caso in cui il file non esiste.
     */
    public XMLDocument readDocument() throws IOException {
        if (f != null && f.exists() && f.isFile()) {
            XMLDocument document = new XMLDocument(f);
            try {
                XMLInputFactory xmlif = XMLInputFactory.newInstance();
                XMLStreamReader xmlsr = xmlif.createXMLStreamReader(f.getAbsolutePath(), new FileInputStream(f));
                parseDocument(xmlsr, document);
                xmlsr.close();
            } catch (FileNotFoundException | XMLStreamException e) {
                throw new IOException(read_errors.getString("not_found_or_incorrect"));
            }

            return document;
        } else if (f_strem != null) {
            XMLDocument document = new XMLDocument(null);
            try {
                XMLInputFactory xmlif = XMLInputFactory.newInstance();
                XMLStreamReader xmlsr = xmlif.createXMLStreamReader(f_strem);
                parseDocument(xmlsr, document);
                xmlsr.close();
            } catch (XMLStreamException e) {
                throw new IOException(read_errors.getString("not_found_or_incorrect"));
            }

            return document;
        } else {
            throw new IOException(read_errors.getString("not_found_or_incorrect"));
        }
    }

    /**
     * Esegue il parse per eventi base XML.
     *
     * @param xmlsr Lo stream di lettura XML.
     * @param d Il documento in cui vanno salvati gli elementi.
     */
    private void parseDocument(XMLStreamReader xmlsr, XMLDocument d) {
        try {
            while (xmlsr.hasNext()) {
                switch (xmlsr.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        d.getLast().addSubElement(parseElement(xmlsr));
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        d.getLast().close();
                        break;
                    case XMLStreamConstants.COMMENT:
                        d.getLast().addComment(new XMLComment(xmlsr.getText()));
                        break;
                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                        if (xmlsr.getText().trim().length() > 0) {
                            d.getLast().setValue(xmlsr.getText());
                        }
                        break;
                    default:
                        break;
                }
                xmlsr.next();
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Esegue il parse di un singolo elemento: lo crea e gli associa gli
     * attributi.
     *
     * @param xmlsr Lo stream di lettura XML.
     * @return Il nuovo oggetto {@link XMLElement}.
     */
    private XMLElement parseElement(XMLStreamReader xmlsr) {
        XMLElement element = new XMLElement(xmlsr.getLocalName());
        for (int i = 0; i < xmlsr.getAttributeCount(); i++) {
            XMLTag xmlTag = new XMLTag(xmlsr.getAttributeLocalName(i));
            xmlTag.setValue(xmlsr.getAttributeValue(i));
            element.addTag(xmlTag);
        }
        return element;
    }

}
