package com.faceyspacies.TyRC.Containers;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscussionsFeedEntry {
	
	public enum EntryType {
		DISCUSSION_THREAD,
		DISCUSSION_REPLY,
		DISCUSSION_REPORT
	}
	
	public enum Actions {
		CREATED,
		DELETED,
		UNDELETED,
		MOVED,
		MODIFIED
	}
	
	private EntryType type;
	private Actions  action;
	private String user;
	private String url;
	private String snippet;
	private String title;
	private int size;
	private String category;
	

	public DiscussionsFeedEntry(String json) throws JSONException, Exception {
		JSONObject jsonObject = new JSONObject(json);
		
		switch (jsonObject.getString("type")) {
		case "discussion-thread":
			setType(EntryType.DISCUSSION_THREAD);
			try {
				setTitle(jsonObject.getString("title"));
			} catch (JSONException e) {
				setTitle("");
			}
			break;
			
		case "discussion-post":
			setType(EntryType.DISCUSSION_REPLY);
			break;
			
		case "discussion-report":
			setType(EntryType.DISCUSSION_REPORT);
			break;
			
		default:
			throw new Exception("Unknown Discussion type \"" + jsonObject.getString("type") + "\"");
			
		}
		
		switch(jsonObject.getString("action")) {
		case "created":
			setAction(Actions.CREATED);
			break;
			
		case "deleted":
			setAction(Actions.DELETED);
			break;
			
		case "un-deleted":
			setAction(Actions.UNDELETED);
			break;
			
		case "moved":
			setAction(Actions.MOVED);
			break;
			
		case "modified":
			setAction(Actions.MODIFIED);
			break;
			
		default:
			throw new Exception("Unknown Discussion action \"" + jsonObject.getString("action") + "\"");
		}
		
		setUser(jsonObject.getString("userName"));
		setUrl(jsonObject.getString("url"));
		setSize(jsonObject.getInt("size"));
		setCategory(jsonObject.getString("category"));
		
		setSnippet(jsonObject.getString("snippet"));
		
		
	}

	/**
	 * @return the type
	 */
	public EntryType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EntryType type) {
		this.type = type;
	}


	/**
	 * @return the action
	 */
	public Actions getAction() {
		return action;
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(Actions action) {
		this.action = action;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the snippet
	 */
	public String getSnippet() {
		return snippet;
	}


	/**
	 * @param snippet the snippet to set
	 */
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param i the size to set
	 */
	public void setSize(int i) {
		this.size = i;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
