/**
 * 
 */
package com.partsshop.web.datatable;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class DataTableColumnSpecs.
 *
 * @author pavan.solapure
 */
public class DataTableColumnSpecs {
	
	/** The index. */
	private int index;
	
	/** The data. */
	private String data;
	
	/** The name. */
	private String name;
	
	/** The searchable. */
	private boolean searchable;
	
	/** The orderable. */
	private boolean orderable;
	
	/** The search. */
	private Search search;
	
	/** The regex. */
	private boolean regex;
	
	/** The sort dir. */
	private String sortDir;
	
	
	public DataTableColumnSpecs() {
		
	}
	
	/**
	 * Instantiates a new data table column specs.
	 *
	 * @param request the request
	 * @param i the i
	 */
	
	

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if is searchable.
	 *
	 * @return the searchable
	 */
	public boolean isSearchable() {
		return searchable;
	}

	/**
	 * Sets the searchable.
	 *
	 * @param searchable the searchable to set
	 */
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	/**
	 * Checks if is orderable.
	 *
	 * @return the orderable
	 */
	public boolean isOrderable() {
		return orderable;
	}

	/**
	 * Sets the orderable.
	 *
	 * @param orderable the orderable to set
	 */
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * Sets the search.
	 *
	 * @param search the search to set
	 */
	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * Checks if is regex.
	 *
	 * @return the regex
	 */
	public boolean isRegex() {
		return regex;
	}

	/**
	 * Sets the regex.
	 *
	 * @param regex the regex to set
	 */
	public void setRegex(boolean regex) {
		this.regex = regex;
	}

	/**
	 * Gets the sort dir.
	 *
	 * @return the sortDir
	 */
	public String getSortDir() {
		return sortDir;
	}

	/**
	 * Sets the sort dir.
	 *
	 * @param sortDir the sortDir to set
	 */
	public void setSortDir(String sortDir) {
		this.sortDir = (null != sortDir) ? sortDir.toUpperCase() : sortDir;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
}
