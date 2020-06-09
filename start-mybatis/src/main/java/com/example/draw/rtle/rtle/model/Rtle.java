package com.example.draw.rtle.rtle.model;



/** 
* @version 创建时间：2020年6月9日 上午10:29:30
*/
public class Rtle {
    private Integer id;

    private String logitude;

    private String latitude;

    private String showRangeMessage;

    private String showClockMessage;

    private String listenForTagMessage;

    private String clockSynchorizationMessage;

    private String listenForRangeMessage;

    private String useAlgorithm;

    private String delayVote;

    private String anchorOnlineThreshold;

    private String tagOnlineThreshold;

    private String debugArea;

    private String debugAnchor;

    private String debugTag;

    private String votingMechanism;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude == null ? null : logitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getShowRangeMessage() {
        return showRangeMessage;
    }

    public void setShowRangeMessage(String showRangeMessage) {
        this.showRangeMessage = showRangeMessage == null ? null : showRangeMessage.trim();
    }

    public String getShowClockMessage() {
        return showClockMessage;
    }

    public void setShowClockMessage(String showClockMessage) {
        this.showClockMessage = showClockMessage == null ? null : showClockMessage.trim();
    }

    public String getListenForTagMessage() {
        return listenForTagMessage;
    }

    public void setListenForTagMessage(String listenForTagMessage) {
        this.listenForTagMessage = listenForTagMessage == null ? null : listenForTagMessage.trim();
    }

    public String getClockSynchorizationMessage() {
        return clockSynchorizationMessage;
    }

    public void setClockSynchorizationMessage(String clockSynchorizationMessage) {
        this.clockSynchorizationMessage = clockSynchorizationMessage == null ? null : clockSynchorizationMessage.trim();
    }

    public String getListenForRangeMessage() {
        return listenForRangeMessage;
    }

    public void setListenForRangeMessage(String listenForRangeMessage) {
        this.listenForRangeMessage = listenForRangeMessage == null ? null : listenForRangeMessage.trim();
    }

    public String getUseAlgorithm() {
        return useAlgorithm;
    }

    public void setUseAlgorithm(String useAlgorithm) {
        this.useAlgorithm = useAlgorithm == null ? null : useAlgorithm.trim();
    }

    public String getDelayVote() {
        return delayVote;
    }

    public void setDelayVote(String delayVote) {
        this.delayVote = delayVote == null ? null : delayVote.trim();
    }

    public String getAnchorOnlineThreshold() {
        return anchorOnlineThreshold;
    }

    public void setAnchorOnlineThreshold(String anchorOnlineThreshold) {
        this.anchorOnlineThreshold = anchorOnlineThreshold == null ? null : anchorOnlineThreshold.trim();
    }

    public String getTagOnlineThreshold() {
        return tagOnlineThreshold;
    }

    public void setTagOnlineThreshold(String tagOnlineThreshold) {
        this.tagOnlineThreshold = tagOnlineThreshold == null ? null : tagOnlineThreshold.trim();
    }

    public String getDebugArea() {
        return debugArea;
    }

    public void setDebugArea(String debugArea) {
        this.debugArea = debugArea == null ? null : debugArea.trim();
    }

    public String getDebugAnchor() {
        return debugAnchor;
    }

    public void setDebugAnchor(String debugAnchor) {
        this.debugAnchor = debugAnchor == null ? null : debugAnchor.trim();
    }

    public String getDebugTag() {
        return debugTag;
    }

    public void setDebugTag(String debugTag) {
        this.debugTag = debugTag == null ? null : debugTag.trim();
    }

    public String getVotingMechanism() {
        return votingMechanism;
    }

    public void setVotingMechanism(String votingMechanism) {
        this.votingMechanism = votingMechanism == null ? null : votingMechanism.trim();
    }
}
