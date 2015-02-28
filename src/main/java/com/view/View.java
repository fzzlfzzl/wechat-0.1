package com.view;

public abstract class View {

	public abstract void render(StringBuffer sb);

	public String toString() {
		StringBuffer sb = new StringBuffer();
		render(sb);
		return sb.toString();
	}
}
