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

import ttt.utils.registry.abstracts.RegistrableEntry;

/**
 * Semplice Listener del registro, utile per essere segnalati di eliminazioni o
 * aggiunte di elementi nel registro.
 *
 * @author TTT
 */
public interface RegistryListener {

    /**
     * Viene chiamato ogni volta che un elemento viene registrato.
     *
     * @param re L'elemento registrato.
     */
    public void onElementRegistered(RegistrableEntry re);

    /**
     * Viene chiamato ogni volta che un elemento viene rimosso dal registro.
     *
     * @param re L'elemento rimosso.
     */
    public void onElementRemoved(RegistrableEntry re);
}
