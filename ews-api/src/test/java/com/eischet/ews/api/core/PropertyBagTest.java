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

package com.eischet.ews.api.core;

import com.eischet.ews.api.core.enumeration.misc.ExchangeVersion;
import com.eischet.ews.api.core.exception.misc.ArgumentException;
import com.eischet.ews.api.core.exception.service.local.ServiceObjectPropertyException;
import com.eischet.ews.api.core.service.ServiceObject;
import com.eischet.ews.api.core.service.item.Item;
import com.eischet.ews.api.misc.OutParam;
import com.eischet.ews.api.property.definition.IntPropertyDefinition;
import com.eischet.ews.api.property.definition.RecurrencePropertyDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PropertyBagTest {

    /**
     * Calling tryGetPropertyType with invalid data.
     * Expecting exception
     *
     * @throws Exception
     */
    @Test(expected = ArgumentException.class)
    public void tryGetPropertyType() throws Exception {
        PropertyBag pb = createPropertyBag();
        pb.tryGetPropertyType(String.class, new RecurrencePropertyDefinition("test", "none", null, ExchangeVersion.Exchange2010_SP2), new OutParam<String>());
    }

    @Test(expected = ServiceObjectPropertyException.class)
    public void testGetObjectFromPropertyDefinition() throws Exception {
        PropertyBag pb = createPropertyBag();
        pb.getObjectFromPropertyDefinition(new IntPropertyDefinition("", "none", ExchangeVersion.Exchange2007_SP1));
    }


    private PropertyBag createPropertyBag() throws Exception {
        ExchangeService es = new ExchangeService(null);
        ServiceObject owner = new Item(es);
        return new PropertyBag(owner);
    }

}
