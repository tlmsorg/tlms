package com.tlms.core.domain;

public class SysGroup {
    private Integer id;

    private String groupname;

    private Integer state;

    private Integer overgroupid;

    private Integer queuetype;

    private String dutytelno;

    private String groupnamerem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOvergroupid() {
        return overgroupid;
    }

    public void setOvergroupid(Integer overgroupid) {
        this.overgroupid = overgroupid;
    }

    public Integer getQueuetype() {
        return queuetype;
    }

    public void setQueuetype(Integer queuetype) {
        this.queuetype = queuetype;
    }

    public String getDutytelno() {
        return dutytelno;
    }

    public void setDutytelno(String dutytelno) {
        this.dutytelno = dutytelno;
    }

    public String getGroupnamerem() {
        return groupnamerem;
    }

    public void setGroupnamerem(String groupnamerem) {
        this.groupnamerem = groupnamerem;
    }
}