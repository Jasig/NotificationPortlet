/*
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.notice;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *  Simplifies the JSON output of the custom attributes to a format that is easy for javascript to work with.
 *  Collapses to an object with a series of fields with an array of values; e.g.
 *
 * <blockquote><pre>
 * "attributes": [
 *     {
 *         "name": "category",
 *         "values": ["Academic"]
 *     },
 *     {
 *         "name": "department",
 *         "values": ["Physics and Astronomy", "Earth Sciences"]
 *     }
 *  ],
 *
 * becomes
 *
 * "attributes": {
 *     "category": ["Academic"],
 *     "department": ["Physics and Astronomy", "Earth Sciences"]
 * },
 * </pre></blockquote>
 *
 * @since 2.1
 * @author James Wennmacher (jwennmacher@unicon.net)
 */
public class JsonAttributesSerializer extends JsonSerializer<List<NotificationAttribute>> {

    @Override
    public void serialize(List<NotificationAttribute> attributes, JsonGenerator gen, SerializerProvider provider)
            throws JsonGenerationException, IOException {

        gen.writeStartObject();
        for (NotificationAttribute attr : attributes) {
            gen.writeObjectField(attr.getName(), attr.getValues());
        }
        gen.writeEndObject();

    }

}
