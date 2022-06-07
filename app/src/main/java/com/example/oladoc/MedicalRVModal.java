package com.example.oladoc;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicalRVModal implements Parcelable {
    // creating variables for our different fields.
    private String patientName;
    private String prescription;
    private String date;
    private String docName;
    private String patientId;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // creating an empty constructor.
    public MedicalRVModal() {

    }

    protected MedicalRVModal(Parcel in) {
        patientName = in.readString();
        patientId = in.readString();
        prescription = in.readString();
        date = in.readString();
        docName = in.readString();

    }

    public static final Creator<MedicalRVModal> CREATOR = new Creator<MedicalRVModal>() {
        @Override
        public MedicalRVModal createFromParcel(Parcel in) {
            return new MedicalRVModal(in);
        }

        @Override
        public MedicalRVModal[] newArray(int size) {
            return new MedicalRVModal[size];
        }
    };



    public MedicalRVModal(String patientId, String patientName, String prescription, String date, String docName) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.prescription = prescription;
        this.date = date;
        this.docName = docName;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patientName);
        dest.writeString(patientId);
        dest.writeString(prescription);
        dest.writeString(date);
        dest.writeString(docName);
    }
}

