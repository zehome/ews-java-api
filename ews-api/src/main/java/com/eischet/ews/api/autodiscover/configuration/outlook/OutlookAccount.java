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
import com.eischet.ews.api.autodiscover.AlternateMailbox;
import com.eischet.ews.api.autodiscover.AlternateMailboxCollection;
import com.eischet.ews.api.autodiscover.enumeration.AutodiscoverResponseType;
import com.eischet.ews.api.autodiscover.enumeration.OutlookProtocolType;
import com.eischet.ews.api.autodiscover.enumeration.UserSettingName;
import com.eischet.ews.api.autodiscover.response.GetUserSettingsResponse;
import com.eischet.ews.api.core.EwsXmlReader;
import com.eischet.ews.api.core.XmlElementNames;
import com.eischet.ews.api.core.enumeration.attribute.EditorBrowsableState;
import com.eischet.ews.api.core.enumeration.misc.XmlNamespace;
import com.eischet.ews.api.security.XmlNodeType;

import java.util.HashMap;
import java.util.List;

/**
 * Represents an Outlook configuration settings account.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class OutlookAccount {

    //region Private constants
    /**
     * The Constant Settings.
     */
    private final static String Settings = "settings";

    /**
     * The Constant RedirectAddr.
     */
    private final static String RedirectAddr = "redirectAddr";

    /**
     * The Constant RedirectUrl.
     */
    private final static String RedirectUrl = "redirectUrl";
    //endRegion

    private String accountType;
    private AutodiscoverResponseType responseType;

    //region Private fields
    /**
     * The protocols.
     */
    private final HashMap<OutlookProtocolType, OutlookProtocol> protocols;
    private final AlternateMailboxCollection alternateMailboxes;
    private String redirectTarget;
    //endRegion

    /**
     * Initializes a new instance of the OutlookAccount class.
     */
    protected OutlookAccount() {
        this.protocols = new HashMap<OutlookProtocolType, OutlookProtocol>();
        this.alternateMailboxes = new AlternateMailboxCollection();
    }

    /**
     * Parses the specified reader.
     *
     * @param reader The reader.
     * @throws Exception the exception
     */
    protected void loadFromXml(EwsXmlReader reader)
            throws Exception {

        do {
            reader.read();

            if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
                if (reader.getLocalName().equals(XmlElementNames.AccountType)) {
                    this.setAccountType(reader.readElementValue());
                } else if (reader.getLocalName().equals(XmlElementNames.Action)) {
                    String xmlResponseType = reader.readElementValue();
                    if (xmlResponseType.equals(OutlookAccount.Settings)) {
                        this.setResponseType(AutodiscoverResponseType.Success);
                    } else if (xmlResponseType
                            .equals(OutlookAccount.RedirectUrl)) {
                        this.setResponseType(AutodiscoverResponseType.
                                RedirectUrl);
                    } else if (xmlResponseType
                            .equals(OutlookAccount.RedirectAddr)) {
                        this.setResponseType(
                                AutodiscoverResponseType.RedirectAddress);
                    } else {
                        this.setResponseType(AutodiscoverResponseType.Error);
                    }

                } else if (reader.getLocalName().equals(
                        XmlElementNames.Protocol)) {
                    OutlookProtocol protocol = new OutlookProtocol();
                    protocol.loadFromXml(reader);
                    this.protocols.put(
                            protocol.getProtocolType(), protocol);
                } else if (reader.getLocalName().equals(
                        XmlElementNames.RedirectAddr)) {
                    this.setRedirectTarget(reader.readElementValue());
                } else if (reader.getLocalName().equals(
                        XmlElementNames.RedirectUrl)) {
                    this.setRedirectTarget(reader.readElementValue());
                } else if (reader.getLocalName().equals(
                        XmlElementNames.AlternateMailboxes)) {
                    AlternateMailbox alternateMailbox = AlternateMailbox.
                            loadFromXml(reader);
                    this.alternateMailboxes.getEntries().add(alternateMailbox);
                } else {
                    reader.skipCurrentElement();
                }
            }
        } while (!reader.isEndElement(XmlNamespace.NotSpecified,
                XmlElementNames.Account));
    }

    /**
     * Gets the type of the account.
     */
    protected void convertToUserSettings(List<UserSettingName> requestedSettings,
                                         GetUserSettingsResponse response) {
        for (OutlookProtocol protocol : this.protocols.values()) {
            protocol.convertToUserSettings(requestedSettings, response);
        }

        if (requestedSettings.contains(UserSettingName.AlternateMailboxes)) {
            response.getSettings().put(UserSettingName.
                    AlternateMailboxes, this.alternateMailboxes);
        }
    }

    /**
     * Gets the type of the account.
     *
     * @return the account type
     */
    protected String getAccountType() {
        return accountType;
    }

    /**
     * Gets the type of the account.
     */
    protected void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the type of the response.
     *
     * @return the response type
     */
    protected AutodiscoverResponseType getResponseType() {
        return responseType;
    }

    /**
     * Sets the response type.
     *
     * @param value the new response type
     */
    protected void setResponseType(AutodiscoverResponseType value) {
        this.responseType = value;
    }

    /**
     * Gets the redirect target.
     *
     * @return the redirect target
     */
    protected String getRedirectTarget() {
        return redirectTarget;

    }

    /**
     * Sets the redirect target.
     *
     * @param value the new redirect target
     */
    protected void setRedirectTarget(String value) {
        this.redirectTarget = value;
    }
}
