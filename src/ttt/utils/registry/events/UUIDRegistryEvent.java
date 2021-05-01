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
package ttt.utils.registry.events;

import java.util.ArrayList;
import ttt.utils.registry.UUIDRegistry;
import ttt.utils.registry.abstracts.UUIDRegistrableEntry;

/**
 * Classe che scatena gli eventi riguardanti il registro.
 *
 * @author TTT
 */
public final class UUIDRegistryEvent {

    private static final UUIDRegistryEvent re = new UUIDRegistryEvent();
    private final ArrayList<UUIDRegistryListener> listeners;

    private UUIDRegistryEvent() {
        listeners = new ArrayList<>();
    }

    /**
     * Restituisce l'unica istanza di questa classe.
     *
     * @return L'istanza di questa classe.
     */
    public static UUIDRegistryEvent getInstance() {
        return re;
    }

    /**
     * Aggiungi un {@link RegistryListener}.
     *
     * @param rl Listener
     */
    public void addListener(UUIDRegistryListener rl) {
        if (rl != null) {
            listeners.add(rl);
        }
    }

    /**
     * Rimuovi un {@link RegistryListener}.
     *
     * @param rl Listener
     */
    public void removeListener(UUIDRegistryListener rl) {
        if (rl != null) {
            listeners.remove(rl);
        }
    }

    /**
     * Avverte tutti i listeners in ascolto che un elemento è stato registrato.
     *
     * @param re L'elemento aggiunto.
     * @param r L'istanza del registro
     */
    public void elementRegistered(UUIDRegistrableEntry re, UUIDRegistry r) {
        if (r != null && r.onCall()) {
            listeners.forEach(l -> l.onElementRegistered(re));
        }
    }

    /**
     * Avverte tutti i listeners in ascolto che un elemento è stato rimosso dal
     * registro.
     *
     * @param re L'elemento rimosso.
     * @param r L'istanza del registro
     */
    public void elementRemoved(UUIDRegistrableEntry re, UUIDRegistry r) {
        if (r != null && r.onCall()) {
            listeners.forEach(l -> l.onElementRemoved(re));
        }
    }
}
