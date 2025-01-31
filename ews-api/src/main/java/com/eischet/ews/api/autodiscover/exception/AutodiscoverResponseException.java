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

package com.eischet.ews.api.autodiscover.exception;

import com.eischet.ews.api.autodiscover.enumeration.AutodiscoverErrorCode;
import com.eischet.ews.api.core.exception.service.remote.ServiceRemoteException;

/**
 * Represents an exception from an autodiscover error response.
 */
public class AutodiscoverResponseException extends ServiceRemoteException {

    /**
     * Constant serialized ID used for compatibility.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Error code when Autodiscover service operation failed remotely.
     */
    private final AutodiscoverErrorCode errorCode;

    /**
     * Initializes a new instance of the class.
     *
     * @param errorCode the error code
     * @param message   the message
     */
    public AutodiscoverResponseException(AutodiscoverErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Gets the ErrorCode for the exception.
     *
     * @return the error code
     */
    public AutodiscoverErrorCode getErrorCode() {
        return this.errorCode;
    }
}
