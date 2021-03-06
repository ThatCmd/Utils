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
package ttt.utils.registry.interfaces;

import ttt.utils.registry.IDRegistry;

/**
 * Interfaccia per il registro ID {@link IDRegistry}.
 *
 * @author TTT
 */
public interface IndexID {

    /**
     * Ritorna il precedente ID ({@link Long}) che è stato utilizzato.
     *
     * @return Il precedente ID utilizzato.
     */
    public long getLastID();
}
