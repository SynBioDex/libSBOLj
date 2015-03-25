package org.sbolstandard.core2.util;

import java.net.URI;

public class SBOLPair
{


	private URI left;
	private URI right;

	public SBOLPair(URI left, URI right)
	{
		this.left = left;
		this.right = right;
	}

	public URI getLeft() {
		return left;
	}

	public void setLeft(URI left) {
		this.left = left;
	}

	public URI getRight() {
		return right;
	}

	public void setRight(URI right) {
		this.right = right;
	}
}