package com.example.demo.model;

public class Area {
    private Integer id;

    private String areaName;

    private String remarks;

    private String areaWide;

    private Integer clockId;

    private String actionType;

    private String z;

    private String averageFiltering;

    private String kalman;

    private Integer anchorId;

    private String useAverageFilter;

    private String useBoundaryValue;

    private String useKalmanFilter;

    private String action;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getAreaWide() {
        return areaWide;
    }

    public void setAreaWide(String areaWide) {
        this.areaWide = areaWide == null ? null : areaWide.trim();
    }

    public Integer getClockId() {
        return clockId;
    }

    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z == null ? null : z.trim();
    }

    public String getAverageFiltering() {
        return averageFiltering;
    }

    public void setAverageFiltering(String averageFiltering) {
        this.averageFiltering = averageFiltering == null ? null : averageFiltering.trim();
    }

    public String getKalman() {
        return kalman;
    }

    public void setKalman(String kalman) {
        this.kalman = kalman == null ? null : kalman.trim();
    }

    public Integer getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }

    public String getUseAverageFilter() {
        return useAverageFilter;
    }

    public void setUseAverageFilter(String useAverageFilter) {
        this.useAverageFilter = useAverageFilter == null ? null : useAverageFilter.trim();
    }

    public String getUseBoundaryValue() {
        return useBoundaryValue;
    }

    public void setUseBoundaryValue(String useBoundaryValue) {
        this.useBoundaryValue = useBoundaryValue == null ? null : useBoundaryValue.trim();
    }

    public String getUseKalmanFilter() {
        return useKalmanFilter;
    }

    public void setUseKalmanFilter(String useKalmanFilter) {
        this.useKalmanFilter = useKalmanFilter == null ? null : useKalmanFilter.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }
}