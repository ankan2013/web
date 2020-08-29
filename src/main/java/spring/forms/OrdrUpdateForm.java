package spring.forms;

public class OrdrUpdateForm {

    private int ordrId;
    private int clientId;
    private int diskId;
    private String requestTime;
    private String returnTime;

    public int getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(int orderId) {
        this.ordrId = orderId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDiskId() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }

}
