/* WrappedClosableIterator
 *
 * $Id$
 *
 * Created on 2:16:56 PM Jun 5, 2008.
 *
 * Copyright (C) 2008 Internet Archive.
 *
 * This file is part of wayback.
 *
 * wayback is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * any later version.
 *
 * wayback is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with wayback; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.archive.wayback.util;

import java.io.IOException;
import java.util.Iterator;

/**
 * Simple wrapper around a normal Iterator which allows use of the close().
 *
 * @author brad
 * @version $Date$, $Revision$
 */
public class WrappedCloseableIterator<E> implements CloseableIterator<E> {

	private Iterator<E> inner = null;
	
	public WrappedCloseableIterator(Iterator<E> inner) {
		this.inner = inner;
	}

	public boolean hasNext() {
		return inner.hasNext();
	}

	public E next() {
		return inner.next();
	}

	public void remove() {
		inner.remove();
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	public void close() throws IOException {
		// NO-OP
	}
	
}