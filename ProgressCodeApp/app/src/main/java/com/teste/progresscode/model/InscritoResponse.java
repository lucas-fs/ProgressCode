package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lucas on 11/10/16.
 */

public class InscritoResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Inscrito> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Inscrito> getResults() {
        return results;
    }

    public void setResults(List<Inscrito> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
