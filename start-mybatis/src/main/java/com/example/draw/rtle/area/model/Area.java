package com.example.draw.rtle.area.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name="area",indexes = {@Index(name="id", columnList = "id" , unique = true),
		@Index(name="area_name", columnList = "area_name", unique = true)})
public class Area {
	@Id//主键ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(name = "area_name" ,nullable = false)
    private String areaName;
	@Column(name="remarks", nullable = false)
    private String remarks;
	@Column(name="area_wide", nullable = false)
    private String areaWide;
	@Column(name="clock_name", nullable = false)
    private String clockName;
	@Column(name="action_type", nullable = false)
    private String actionType;
	@Column(name="z", nullable = false)
    private String z;
	@Column(name="average_filtering", nullable = false)
    private String averageFiltering;
	@Column(name="kalman", nullable = false)
    private String kalman;
	@Column(name="anchor_id", nullable = false)
    private String anchorId;
	@Column(name="use_average_filter", nullable = false)
    private String useAverageFilter;
	@Column(name="use_boundary_value", nullable = false)
    private String useBoundaryValue;
	@Column(name="use_kalman_filter", nullable = false)
    private String useKalmanFilter;
	@Column(name="action", nullable = false)
    private String action;
	@Column(name = "flag")
	private Integer flag=0;
	
    public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

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

    public String getClockName() {
        return clockName;
    }

    public void setClockName(String clockName) {
        this.clockName = clockName == null ? null : clockName.trim();
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

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId == null ? null : anchorId.trim();
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