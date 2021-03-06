/*
 * The MIT License
 *
 * Copyright 2014 CMU ABLE Group.
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
package org.sa.rainbow.model.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.sa.rainbow.core.error.RainbowModelException;
import org.sa.rainbow.core.models.ModelsManager;
import org.sa.rainbow.core.models.commands.ModelCommandFactory;

public class UtilityHistoryCommandFactory extends ModelCommandFactory<UtilityHistory> {

    public static UtilityHistoryLoadModelCommand loadCommand (ModelsManager mm,
            String modelName,
            InputStream stream,
            String source) {
        return new UtilityHistoryLoadModelCommand (modelName, mm, stream, source);
    }

    public UtilityHistoryCommandFactory (UtilityHistoryModelInstance model) {
        super (UtilityHistoryModelInstance.class, model);
    }

    @Override
    protected void fillInCommandMap () {
        m_commandMap.put ("addUtilityMeasure".toLowerCase (), AddUtilityMeasureCmd.class);
    }

    public AddUtilityMeasureCmd addUtilityMeasureCmd (String utilityKey, double utility) {
        return new AddUtilityMeasureCmd ("addUtilityMeasure", (UtilityHistoryModelInstance )m_modelInstance,
                utilityKey,
                Double.toString (utility));
    }

    @Override
    public SaveUtilityHistoryCmd saveCommand (String location) throws RainbowModelException {
        try {
            FileOutputStream os = new FileOutputStream (location);
            return new SaveUtilityHistoryCmd ("SaveUtilityHistoryCmd", null, location, os,
                    m_modelInstance.getOriginalSource ());
        }
        catch (FileNotFoundException e) {
            return null;
        }

    }

}
