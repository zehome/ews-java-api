/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.eischet.ews.api.autodiscover.configuration.outlook;

import com.eischet.ews.api.attribute.EditorBrowsable;
import com.eischet.ews.api.autodiscover.IFunc;
import com.eischet.ews.api.autodiscover.enumeration.UserSettingName;
import com.eischet.ews.api.autodiscover.response.GetUserSettingsResponse;
import com.eischet.ews.api.core.EwsXmlReader;
import com.eischet.ews.api.core.ILazyMember;
import com.eischet.ews.api.core.LazyMember;
import com.eischet.ews.api.core.XmlElementNames;
import com.eischet.ews.api.core.enumeration.attribute.EditorBrowsableState;
import com.eischet.ews.api.core.enumeration.misc.XmlNamespace;
import com.eischet.ews.api.security.XmlNodeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents the user Outlook configuration settings apply to.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class OutlookUser {

    /**
     * Converters to translate Outlook user settings.
     * Each entry maps to a lambda expression used to
     * get the matching property from the OutlookUser instance.
     */
    private static final LazyMember<Map<UserSettingName, IFunc<OutlookUser, String>>>
            converterDictionary =
            new LazyMember<Map<UserSettingName, IFunc<OutlookUser, String>>>(
                    new ILazyMember<Map<UserSettingName, IFunc<OutlookUser, String>>>() {
                        public Map<UserSettingName, IFunc<OutlookUser, String>> createInstance() {
                            Map<UserSettingName, IFunc<OutlookUser, String>> results =
                                    new HashMap<UserSettingName, IFunc<OutlookUser, String>>();
                            results.put(UserSettingName.UserDisplayName,
                                    new IFunc<OutlookUser, String>() {
                                        public String func(OutlookUser arg) {
                                            return arg.displayName;
                                        }
                                    });
                            results.put(UserSettingName.UserDN,
                                    new IFunc<OutlookUser, String>() {
                                        public String func(OutlookUser arg) {
                                            return arg.legacyDN;
                                        }
                                    });
                            results.put(UserSettingName.UserDeploymentId,
                                    new IFunc<OutlookUser, String>() {
                                        public String func(OutlookUser arg) {
                                            return arg.deploymentId;
                                        }
                                    });
                            return results;
                        }
                    });

    /**
     * The display name.
     */
    private String displayName;

    /**
     * The legacy dn.
     */
    private String legacyDN;

    /**
     * The deployment id.
     */
    private String deploymentId;

    /**
     * Initializes a new instance of the OutlookUser class.
     */
    protected OutlookUser() {
    }

    /**
     * Load from XML.
     *
     * @param reader The reader.
     * @throws Exception the exception
     */
    protected void loadFromXml(EwsXmlReader reader) throws Exception {

        do {
            reader.read();

            if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
                if (reader.getLocalName().equals(XmlElementNames.DisplayName)) {
                    this.displayName = reader.readElementValue();
                } else if (reader.getLocalName().equals(
                        XmlElementNames.LegacyDN)) {
                    this.legacyDN = reader.readElementValue();
                } else if (reader.getLocalName().equals(
                        XmlElementNames.DeploymentId)) {
                    this.deploymentId = reader.readElementValue();
                } else {
                    reader.skipCurrentElement();

                }
            }
        } while (!reader.isEndElement(XmlNamespace.NotSpecified,
                XmlElementNames.User));
    }

    /**
     * Convert OutlookUser to GetUserSettings response.
     *
     * @param requestedSettings The requested settings.
     * @param response          The response.
     */
    protected void convertToUserSettings(
            List<UserSettingName> requestedSettings,
            GetUserSettingsResponse response) {
        // In English: collect converters that are
        //contained in the requested settings.
        Map<UserSettingName, IFunc<OutlookUser, String>>
                converterQuery = new HashMap<UserSettingName,
                IFunc<OutlookUser, String>>();
        for (Entry<UserSettingName, IFunc<OutlookUser, String>> map : converterDictionary.getMember()
                .entrySet()) {
            if (requestedSettings.contains(map.getKey())) {
                converterQuery.put(map.getKey(), map.getValue());
            }
        }

        for (Entry<UserSettingName, IFunc<OutlookUser, String>> kv : converterQuery.entrySet()) {
            String value = kv.getValue().func(this);
            if (!(value == null || value.isEmpty())) {
                response.getSettings().put(kv.getKey(), value);
            }
        }
    }

    /**
     * Gets the available user settings.
     *
     * @return The available user settings.
     */
    protected static Iterable<UserSettingName> getAvailableUserSettings() {
        return converterDictionary.getMember().keySet();
    }
}
