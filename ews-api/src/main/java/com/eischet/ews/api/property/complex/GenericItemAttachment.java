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

package com.eischet.ews.api.property.complex;

import com.eischet.ews.api.core.service.item.Item;

/**
 * Represents a strongly typed item attachment.
 *
 * @param <TItem> Item type.
 */
public final class GenericItemAttachment<TItem extends Item> extends ItemAttachment {

    /**
     * Initializes a new instance of the GenericItemAttachment class.
     *
     * @param owner the owner
     */
    protected GenericItemAttachment(Item owner) {
        super(owner);
    }

    /**
     * Gets the item associated with the attachment.
     *
     * @return the t item
     */
    public TItem getTItem() {
        return (TItem) super.getItem();
    }

    /**
     * Sets the t item.
     *
     * @param value the new t item
     */
    protected void setTItem(TItem value) {
        super.setItem(value);
    }
}
