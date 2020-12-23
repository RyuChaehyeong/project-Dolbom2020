package request.service;

public class SearchRequest {
	private String field;
	private String word;
	public SearchRequest(String field, String word) {
		this.field = field;
		this.word = word;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
}


