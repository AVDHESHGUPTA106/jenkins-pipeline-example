package com.mycompany.app;

public class TestModel {
    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    private String publicIp;

    public String getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    private String awsRegion;

    @Override
    public String toString() {
        return "TestModel{" +
                "publicIp='" + publicIp + '\'' +
                ", awsRegion='" + awsRegion + '\'' +
                '}';
    }
}
