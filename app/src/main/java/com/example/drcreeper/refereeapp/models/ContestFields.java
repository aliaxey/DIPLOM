
package com.example.drcreeper.refereeapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestFields {

    @SerializedName("headers")
    @Expose
    private List<Header> headers = null;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
