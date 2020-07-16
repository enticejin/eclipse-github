package com.example.draw.rtle.planmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/** 
* @version 创建时间：2020年7月14日 上午10:07:42
*/
@Entity
@Table(name = "plan_map",indexes = {@Index(name="id", columnList = "id", unique = true),
									@Index(name = "plan_map_name",columnList = "plan_map_name")})
public class PlanMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="plan_map_name", nullable = false)
	String planMapName;
	@Column(name="plan_map_id", nullable = false)
	String planMapId;
	@Column(name="image_type", nullable = false)
	int imageType;
	@Column(name="logitude", nullable = false)
	float longitude;
	@Column(name="latitude", nullable = false)
	float latitude;
	@Column(name="remarks")
	String remarks;
	@Column(name="transparency", nullable = false)
	float transparency;
	@Column(name="rotation_angle", nullable = false)
	float rotationAngle;
	@Column(name="zoom_x", nullable = false)
	float zoomX;
	@Column(name="zoom_y", nullable = false)
	float zoomY;
	@Column(name="file_name", nullable = false)
	String fileName;
	@Column(name="flag", nullable = false)
	int flag;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlanMapName() {
		return planMapName;
	}
	public void setPlanMapName(String planMapName) {
		this.planMapName = planMapName;
	}
	public String getPlanMapId() {
		return planMapId;
	}
	public void setPlanMapId(String planMapId) {
		this.planMapId = planMapId;
	}
	public int getImageType() {
		return imageType;
	}
	public void setImageType(int imageType) {
		this.imageType = imageType;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public float getTransparency() {
		return transparency;
	}
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}
	public float getRotationAngle() {
		return rotationAngle;
	}
	public void setRotationAngle(float rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	public float getZoomX() {
		return zoomX;
	}
	public void setZoomX(float zoomX) {
		this.zoomX = zoomX;
	}
	public float getZoomY() {
		return zoomY;
	}
	public void setZoomY(float zoomY) {
		this.zoomY = zoomY;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "PlanMap [id=" + id + ", planMapName=" + planMapName + ", planMapId=" + planMapId + ", imageType="
				+ imageType + ", longitude=" + longitude + ", latitude=" + latitude + ", remarks=" + remarks
				+ ", transparency=" + transparency + ", rotationAngle=" + rotationAngle + ", zoomX=" + zoomX
				+ ", zoomY=" + zoomY + ", fileName=" + fileName + ", flag=" + flag + "]";
	}
	
}
