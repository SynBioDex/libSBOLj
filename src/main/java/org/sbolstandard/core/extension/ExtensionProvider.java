package org.sbolstandard.core.extension;

/**
 * Provides an Extension to the type E.
 *
 * @tparam E the extended type
 * @author Matthew Pocock
 */
public interface ExtensionProvider<E> {
    public ExtendedAs<E> extend(E e);
}
