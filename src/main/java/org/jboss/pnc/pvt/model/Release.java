package org.jboss.pnc.pvt.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;

/**
 * @author <a href="mailto:yyang@redhat.com">Yong Yang</a>
 */
@JsonAutoDetect
public class Release implements Serializable {

    private static final long serialVersionUID = 6927098718846881811L;

    private String id = UUID.randomUUID().toString();

    // id of product
    private String productId;

    // release name ex: 7.0.0.DR1
    private String name;

    private String referenceReleaseId;

    // the distribution zips, urls to download, separate by space
    // ex: http://download.devel.redhat.com/devel/candidates/JBEAP/JBEAP-7.0.0.DR6/jboss-eap-7.0.0.DR6.zip
    private String distributions;

    // The tools applied to this release
    private List<String> tools = new ArrayList<>();

    // Runtime verification, {toolId => verificationId}
    private Map<String, String> verifications = new HashMap<>();

    private String description;

    private Status status = Status.NEW;

    private long createTime = System.currentTimeMillis();

    // use to detect if Tools need to run again if release updated
    private long updateTime = System.currentTimeMillis();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistributions() {
        return distributions;
    }

    @JsonIgnore
    public String[] getDistributionArray(){
        if(distributions != null) {
            return distributions.split("\\r\\n");
        }
        else {
            return new String[0];
        }
    }

    public void setDistributions(String distributions) {
        this.distributions = distributions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public Map<String, String> getVerifications() {
        return verifications;
    }

    public void setVerifications(Map<String, String> verifications) {
        this.verifications=verifications;
    }

    public String getVerificationIdByToolId(String toolId){
        return verifications.get(toolId);
    }

    public void addVerification(String toolId, String verificationId) {
        verifications.put(toolId, verificationId);
    }

    public String getReferenceReleaseId() {
        return referenceReleaseId;
    }

    public void setReferenceReleaseId(String referenceReleaseId) {
        this.referenceReleaseId = referenceReleaseId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Release other = (Release) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Release [productId=" + productId + ", name=" + name + ", referenceReleaseId=" + referenceReleaseId + "]";
    }

    /**
     * Created by yyang on 4/24/15.
     */
    @JsonAutoDetect
    public static enum Status {
        NEW, VERIFYING, NEED_INSPECT, PASSED, REJECTED
    }
}
