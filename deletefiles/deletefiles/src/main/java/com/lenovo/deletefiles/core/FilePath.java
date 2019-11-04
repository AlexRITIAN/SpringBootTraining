package com.lenovo.deletefiles.core;

import org.springframework.beans.factory.annotation.Value;

/**
 * FilePath
 */
public class FilePath {

    private String activeLogPath;
    private String startDate;
    private String endDate;
    private String storePath;

    /**
     * @param activeLogPath the activeLogPath to set
     */
    public void setActiveLogPath(String activeLogPath) {
        this.activeLogPath = activeLogPath;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @param storePath the storePath to set
     */
    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    /**
     * @return the activeLogPath
     */
    public String getActiveLogPath() {
        return activeLogPath;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return the storePath
     */
    public String getStorePath() {
        return storePath;
    }
}