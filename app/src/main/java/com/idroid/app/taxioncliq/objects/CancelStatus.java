package com.idroid.app.taxioncliq.objects;

/**
 * Created by Aron on 20-11-2016.
 */

public class CancelStatus {
    public String status;
    public String request_type;
    public String header;
    public String text;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CancelStatus{" +
                "status='" + status + '\'' +
                ", request_type='" + request_type + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CancelStatus that = (CancelStatus) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (request_type != null ? !request_type.equals(that.request_type) : that.request_type != null)
            return false;
        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (request_type != null ? request_type.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
