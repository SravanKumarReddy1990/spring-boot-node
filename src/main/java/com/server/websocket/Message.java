package com.server.websocket;

/**
 * Created by Arip Hidayat on 21/02/2016.
 */
public class Message {

    private String from;
    private String to;
    private String content;
    private String status;
    private String contentType;
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [from=" + from + ", to=" + to + ", content=" + content + ", status=" + status + ", contentType="
				+ contentType + "]";
	}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
