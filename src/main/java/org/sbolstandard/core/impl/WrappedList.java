/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class over {@link WrappedValue wrapped values} that will behave as if it is a list of unwrapped values.
 * Values are wrapped automatically upon addition and unwrapped before retrieval so to the outside world this does not
 * behave any different than a normal list.
 * 
 * @author Evren Sirin
 */
public class WrappedList<T, W extends WrappedValue<T>> extends AbstractList<T> implements List<T> {
	private final Class<W> wrapperCls;
	private final List<W> base;

	public WrappedList(Class<W> wrapperCls) {
		this(wrapperCls, new ArrayList<W>());
	}

	public WrappedList(Class<W> wrapperCls, List<W> base) {
		this.wrapperCls = wrapperCls;
		this.base = base;
	}

	protected W wrap(T element) {
		if (element == null) {
			throw new NullPointerException("This list does not accept null values");
		}

		try {
			W wrapped = wrapperCls.newInstance();
			wrapped.setValue(element);
			return wrapped;
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to create an instance of " + wrapperCls);
		}
	}

	protected T unwrap(W wrapper) {
		return wrapper == null ? null : wrapper.getValue();
	}

	@Override
	public void add(int index, T element) {
		base.add(index, wrap(element));
	}

	@Override
	public T remove(int index) {
		return unwrap(base.remove(index));
	}

	@Override
	public T set(int index, T element) {
		return unwrap(base.set(index, wrap(element)));
	}

	@Override
	public boolean remove(Object o) {
		int index = (o == null) ? -1 : indexOf(o);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	protected void removeRange(int fromIndex, int toIndex) {
		base.subList(fromIndex, toIndex).clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index) {
		return unwrap(base.get(index));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return base.size();
	}
}
