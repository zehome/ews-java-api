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

package com.eischet.ews.api.core.response;

import com.eischet.ews.api.core.EwsServiceXmlReader;
import com.eischet.ews.api.core.EwsUtilities;
import com.eischet.ews.api.core.XmlAttributeNames;
import com.eischet.ews.api.core.XmlElementNames;
import com.eischet.ews.api.core.enumeration.misc.XmlNamespace;
import com.eischet.ews.api.core.exception.service.local.ServiceLocalException;
import com.eischet.ews.api.property.complex.Attachment;

/**
 * Represents the response to an individual attachment deletion operation.
 */
public final class DeleteAttachmentResponse extends ServiceResponse {

    /**
     * The attachment.
     */
    private final Attachment attachment;

    /**
     * Initializes a new instance of the DeleteAttachmentResponse class.
     *
     * @param attachment the attachment
     */
    public DeleteAttachmentResponse(Attachment attachment) {
        super();
        EwsUtilities.ewsAssert(attachment != null, "DeleteAttachmentResponse.ctor", "attachment is null");

        this.attachment = attachment;
    }

    /**
     * Reads response elements from XML.
     *
     * @param reader the reader
     * @throws ServiceLocalException the service local exception
     * @throws Exception             the exception
     */
    @Override
    protected void readElementsFromXml(EwsServiceXmlReader reader)
            throws ServiceLocalException, Exception {
        super.readElementsFromXml(reader);

        reader.readStartElement(XmlNamespace.Messages,
                XmlElementNames.RootItemId);

        String changeKey = reader
                .readAttributeValue(XmlAttributeNames.RootItemChangeKey);
        if (!(null == changeKey || changeKey.isEmpty())) {
            this.attachment.getOwner().getRootItemId().setChangeKey(changeKey);
        }
        reader.readEndElement(XmlNamespace.Messages,
                XmlElementNames.RootItemId);
    }

    /**
     * Gets the attachment that was deleted.
     *
     * @return the attachment
     */
    public Attachment getAttachment() {
        return this.attachment;
    }
}
