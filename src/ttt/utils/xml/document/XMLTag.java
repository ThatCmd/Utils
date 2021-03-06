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
package ttt.utils.xml.document;

import ttt.utils.xml.engine.interfaces.IXMLTag;

/**
 * Tag base di un file XML. Contiene un valore rappresentato da un nome.
 *
 * @author TTT
 */
public class XMLTag implements IXMLTag {

    private String name;
    private String value;

    /**
     * Crea una nuova Tag, deve essere associato subito ad un nome.
     *
     * @param name Il nome dell'attributo.
     */
    public XMLTag(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

}
