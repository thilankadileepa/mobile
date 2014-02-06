package com.dushantha.util;

/**
 * composite return data object
 * purpose is to return a status and a data from a method
 * 
 * @author Thilanka
 *
 * @param <T>
 */
public class ReturnData<T> {
	private boolean sucsess;
	private T data;
	
	
	public ReturnData() {
		super();
	}

	public ReturnData(boolean sucsess, T data) {
		super();
		this.sucsess = sucsess;
		this.data = data;
	}

	public boolean isSucsess() {
		return sucsess;
	}

	public void setSucsess(boolean sucsess) {
		this.sucsess = sucsess;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
