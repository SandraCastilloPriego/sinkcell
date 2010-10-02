/*
 * Copyright 2006-2010 The sinkCell Development Team
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
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author sandra
 */
public class ImportXML extends DefaultHandler {

    private StringBuffer charBuffer;
    private Parameters parameters;

    public ImportXML(String fileName) {
        // Use the default (non-validating) parser
        parameters = new Parameters();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            File originalFile = new File(fileName);

            if ((!originalFile.exists()) || (!originalFile.canRead())) {
                throw new SAXException(
                        "Parsing Cancelled, file does not exist or is not readable");
            }

            FileInputStream fis = new FileInputStream(fileName);
            InputStream finalStream = fis;

            charBuffer = new StringBuffer();

            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(finalStream, this);

        } catch (Throwable e) {
            return;
        }

    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String namespaceURI, String lName, // local name
            String qName, // qualified name
            Attributes attrs) throws SAXException {
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String namespaceURI, String sName, // simple name
            String qName // qualified name
            ) throws SAXException {
        
        if (qName.equals("size")) {
            try {
                parameters.size = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.size = 1000;
            }
        }

        if (qName.equals("cicles")) {
            try {
                parameters.cicles = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.cicles = 10000;
            }
        }
        if (qName.equals("population")) {
            try {
                parameters.population = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.population = 40;
            }
        }
        if (qName.equals("mortality")) {
            try {
                parameters.mortality = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.mortality = 40;
            }
        }
        if (qName.equals("patch")) {
            try {
                parameters.patch = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.patch = 40;
            }
        }
       
        if (qName.equals("var1")) {
            try {
                parameters.var1 = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.var1 = 4;
            }
        }
        if (qName.equals("var2")) {
            try {
                parameters.var2 = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.var2 = 2;
            }
        }
        if (qName.equals("var3")) {
            try {
                parameters.var3 = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.var3 = 2;
            }
        }
        if (qName.equals("var4")) {
            try {
                parameters.var4 = Integer.valueOf(getTextOfElement());
            } catch (Exception exception) {
                parameters.var4 = 2;
            }
        }

    }

    public Parameters getParameters() {
        return parameters;
    }

    /**
     * characters()
     *
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char buf[], int offset, int len) throws SAXException {
        charBuffer = charBuffer.append(buf, offset, len);
    }

    /**
     * Return a string without tab an EOF characters
     *
     * @return String element text
     */
    private String getTextOfElement() {
        String text = charBuffer.toString();
        text = text.replaceAll("[\n\r\t]+", "");
        text = text.replaceAll("^\\s+", "");
        charBuffer.delete(0, charBuffer.length());
        return text;
    }
}
