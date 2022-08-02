package bg.lease.model;

import java.io.Serializable;

public class LeaseDetailId implements Serializable {

    private String contractNo;
    private int lineNo;

    public LeaseDetailId(String contractNo, int lineNo) {
        this.contractNo = contractNo;
        this.lineNo = lineNo;
    }

    public LeaseDetailId() {
    }
}
