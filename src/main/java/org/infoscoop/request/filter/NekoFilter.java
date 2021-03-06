/* infoScoop OpenSource
 * Copyright (C) 2010 Beacon IT Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0-standalone.html>.
 */

package org.infoscoop.request.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.cyberneko.html.parsers.SAXParser;
import org.infoscoop.request.ProxyRequest;
import org.infoscoop.util.XHtmlWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NekoFilter extends ProxyFilter{
	private static Log log = LogFactory.getLog(NekoFilter.class);

    public NekoFilter() {
	}

	protected int preProcess(HttpClient client, HttpMethod method, ProxyRequest request) {
		return 0;
	}

	protected InputStream postProcess(ProxyRequest request, InputStream responseStream) throws IOException {
		byte[] responseBytes = process(responseStream); 
		//request.setResponseBody(new ByteArrayInputStream(responseBytes));
		
		request.putResponseHeader("Content-Length", Integer.toString(responseBytes.length));//TODO:May not be necessary
//		request.putResponseHeader("Content-Type", "text/xml; charset=\"utf-8\"");

		return new ByteArrayInputStream(responseBytes);
	}

	public static byte[] process(InputStream responseBody, String encoding) throws IOException {
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLDocumentFilter[] filters = { new XHtmlWriter(baos) };
		
        SAXParser parser = new SAXParser();
		
		try {
			parser
			.setFeature(
					"http://cyberneko.org/html/features/balance-tags/document-fragment",
					true);
			parser.setProperty("http://cyberneko.org/html/properties/filters",
					filters);
			parser.setProperty("http://cyberneko.org/html/properties/names/elems",
			"lower");
			parser.setProperty("http://cyberneko.org/html/properties/names/attrs",
			"lower");
			
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding",
			encoding);
			
//			parser.parse(new InputSource(new StringReader(sb.toString())));
			parser.parse(new InputSource(responseBody));
		} catch (SAXException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
		return baos.toByteArray();
	}
	
	public static  byte[] process(InputStream responseBody) throws IOException {
		return process(responseBody, "Windows-1252");
	}
	
}
