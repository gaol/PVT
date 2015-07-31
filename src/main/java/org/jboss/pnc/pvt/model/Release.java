package org.jboss.pnc.pvt.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.jboss.pnc.pvt.config.PVTStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:yyang@redhat.com">Yong Yang</a>
 */
@JsonAutoDetect
public class Release implements Serializable {

    private String id = UUID.randomUUID().toString();

    // name of product
    private String productName;

    // release name ex: 7.0.0.DR1
    private String name;

    // the distribution zips, urls to download,
    // ex: http://download.devel.redhat.com/devel/candidates/JBEAP/JBEAP-7.0.0.DR6/jboss-eap-7.0.0.DR6.zip
    private List<String> distributions = new ArrayList<>();

    // The jobs applied to this release
    private List<Job> jobs = new ArrayList<>();

    private String description;

    private PVTStatus status;

    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<String> distributions) {
        this.distributions = distributions;
    }

    public PVTStatus getStatus() {
        return status;
    }

    public void setStatus(PVTStatus status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
