package com.worthsoln.patientview;

public class Page {

    private int page;
    private boolean currentPage;

    public Page() {
    }

    public Page(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(boolean currentPage) {
        this.currentPage = currentPage;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Page)) {
            return false;
        }
        final Page pagee = (Page) o;
        if (page != pagee.page) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return page;
    }

    public String toString() {
        return page + "";
    }
}
