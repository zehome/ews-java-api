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

package com.eischet.ews.api.core.enumeration.dns;

/**
 * DNS record types.
 */
enum DnsRecordType {
    // RFC 1034/1035 Address Record
    /**
     * The A.
     */
    A(0x0001),

    // Canonical Name Record
    /**
     * The CNAME.
     */
    CNAME(0x0005),

    // / Start of Authority Record
    /**
     * The SOA.
     */
    SOA(0x0006),

    // / Pointer Record
    /**
     * The PTR.
     */
    PTR(0x000c),

    // / Mail Exchange Record
    /**
     * The MX.
     */
    MX(0x000f),

    // / Text Record
    /**
     * The TXT.
     */
    TXT(0x0010),

    // / RFC 1886 (IPv6 Address)
    /**
     * The AAAA.
     */
    AAAA(0x001c),

    // / Service location - RFC 2052
    /**
     * The SRV.
     */
    SRV(0x0021);

    /**
     * The dns record.
     */
    private final int dnsRecord;

    /**
     * Instantiates a new dns record type.
     *
     * @param dnsRecord the dns record
     */
    DnsRecordType(int dnsRecord) {
        this.dnsRecord = dnsRecord;
    }
}
