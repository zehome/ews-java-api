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

package com.eischet.ews.api.core.enumeration.misc;

/**
 * Defines supported Id formats in ConvertId operations.
 */
public enum IdFormat {

    // The EWS Id format used in Exchange 2007 RTM.
    /**
     * The Ews legacy id.
     */
    EwsLegacyId,

    // The EWS Id format used in Exchange 2007 SP1 and above.
    /**
     * The Ews id.
     */
    EwsId,

    // The base64-encoded PR_ENTRYID property.
    /**
     * The Entry id.
     */
    EntryId,

    // The hexadecimal representation of the PR_ENTRYID property.
    /**
     * The Hex entry id.
     */
    HexEntryId,

    // The Store Id format.
    /**
     * The Store id.
     */
    StoreId,

    // The Outlook Web Access Id format.
    /**
     * The Owa id.
     */
    OwaId
}
