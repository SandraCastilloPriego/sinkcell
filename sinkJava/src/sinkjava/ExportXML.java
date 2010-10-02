/*
 * Copyright 2008-2010 The sinkCell Development Team
 *
 * This file is part of sinkCell.
 *
 * sinkCell is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * sinkCell is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * sinkCell; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package sinkjava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author sandra
 */
public class ExportXML {

    private String fileName;

    public ExportXML(Parameters parameters) {
        try {
            fileName = new java.io.File("").getAbsolutePath()
                    + "/config.xml";

            Element newElement;
            Document document = DocumentFactory.getInstance().createDocument();
            Element saveRoot = document.addElement("configuration");
            // <general_variables>
            newElement = saveRoot.addElement("general_variables");
            // <cicles>
            newElement.addElement("cicles").addText(String.valueOf(parameters.cicles));

            // <population>
            newElement.addElement("population").addText(String.valueOf(parameters.population));

            // <mortality>
            newElement.addElement("mortality").addText(String.valueOf(parameters.mortality));

            // <patch>
            newElement.addElement("patch").addText(String.valueOf(parameters.patch));

            // <size>
            newElement.addElement("size").addText(String.valueOf(parameters.size));

            // <internal_variables>
            newElement = saveRoot.addElement("internal_variables");

            // <var1>
            newElement.addElement("var1").addText(String.valueOf(parameters.var1));

            // <var2>
            newElement.addElement("var2").addText(String.valueOf(parameters.var2));

            // <var3>
            newElement.addElement("var3").addText(String.valueOf(parameters.var3));

            // <var4>
            newElement.addElement("var4").addText(String.valueOf(parameters.var4));

            // write the saving file

            File outputFile = new File(fileName);
            System.out.println(outputFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(outputFile);
            OutputStream finalStream = fos;
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(finalStream, format);
            writer.write(document);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
