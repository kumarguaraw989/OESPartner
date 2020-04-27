package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("person_name")
    @Expose
    private String personName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("wear_glass")
    @Expose
    private String wearGlass;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("post_office")
    @Expose
    private String postOffice;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("pin_no")
    @Expose
    private String pinNo;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("whether_staying")
    @Expose
    private String whetherStaying;
    @SerializedName("p_address")
    @Expose
    private String pAddress;
    @SerializedName("p_village")
    @Expose
    private String pVillage;
    @SerializedName("p_post_office")
    @Expose
    private String pPostOffice;
    @SerializedName("p_police_station")
    @Expose
    private String pPoliceStation;
    @SerializedName("p_state")
    @Expose
    private String pState;
    @SerializedName("p_district")
    @Expose
    private Object pDistrict;
    @SerializedName("p_pin_no")
    @Expose
    private String pPinNo;
    @SerializedName("p_mobile")
    @Expose
    private String pMobile;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("particular_place")
    @Expose
    private String particularPlace;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("school_name_address")
    @Expose
    private String schoolNameAddress;
    @SerializedName("employee_detail")
    @Expose
    private Object employeeDetail;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("pf")
    @Expose
    private String pf;
    @SerializedName("reference_name1")
    @Expose
    private String referenceName1;
    @SerializedName("reference_phone1")
    @Expose
    private String referencePhone1;
    @SerializedName("reference_name2")
    @Expose
    private String referenceName2;
    @SerializedName("reference_phone2")
    @Expose
    private String referencePhone2;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("ifsci_code")
    @Expose
    private String ifsciCode;
    @SerializedName("ever_arrested")
    @Expose
    private String everArrested;
    @SerializedName("case_details")
    @Expose
    private String caseDetails;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("aadhar_no")
    @Expose
    private String aadharNo;
    @SerializedName("aadhar_copy")
    @Expose
    private String aadharCopy;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("pan_copy")
    @Expose
    private String panCopy;
    @SerializedName("valid_police")
    @Expose
    private String validPolice;
    @SerializedName("reference_no")
    @Expose
    private String referenceNo;
    @SerializedName("issuance_date")
    @Expose
    private String issuanceDate;
    @SerializedName("police_varification_upload")
    @Expose
    private String policeVarificationUpload;
    @SerializedName("passport_no")
    @Expose
    private String passportNo;
    @SerializedName("visa_no")
    @Expose
    private String visaNo;
    @SerializedName("validity_of_visa")
    @Expose
    private String validityOfVisa;
    @SerializedName("expert_field")
    @Expose
    private String expertField;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("biometric_signature")
    @Expose
    private String biometricSignature;
    @SerializedName("declaration_by_person")
    @Expose
    private String declarationByPerson;
    @SerializedName("left_eye_power")
    @Expose
    private String leftEyePower;
    @SerializedName("right_eye_power")
    @Expose
    private String rightEyePower;
    @SerializedName("lense")
    @Expose
    private Object lense;
    @SerializedName("left_eye")
    @Expose
    private String leftEye;
    @SerializedName("right_eye")
    @Expose
    private Object rightEye;
    @SerializedName("police_station")
    @Expose
    private String policeStation;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("session_email")
    @Expose
    private String sessionEmail;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("branch")
    @Expose
    private Object branch;
    @SerializedName("partner_client")
    @Expose
    private Object partnerClient;
    @SerializedName("person_id")
    @Expose
    private String personId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getWearGlass() {
        return wearGlass;
    }

    public void setWearGlass(String wearGlass) {
        this.wearGlass = wearGlass;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhetherStaying() {
        return whetherStaying;
    }

    public void setWhetherStaying(String whetherStaying) {
        this.whetherStaying = whetherStaying;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpVillage() {
        return pVillage;
    }

    public void setpVillage(String pVillage) {
        this.pVillage = pVillage;
    }

    public String getpPostOffice() {
        return pPostOffice;
    }

    public void setpPostOffice(String pPostOffice) {
        this.pPostOffice = pPostOffice;
    }

    public String getpPoliceStation() {
        return pPoliceStation;
    }

    public void setpPoliceStation(String pPoliceStation) {
        this.pPoliceStation = pPoliceStation;
    }

    public String getpState() {
        return pState;
    }

    public void setpState(String pState) {
        this.pState = pState;
    }

    public Object getpDistrict() {
        return pDistrict;
    }

    public void setpDistrict(Object pDistrict) {
        this.pDistrict = pDistrict;
    }

    public String getpPinNo() {
        return pPinNo;
    }

    public void setpPinNo(String pPinNo) {
        this.pPinNo = pPinNo;
    }

    public String getpMobile() {
        return pMobile;
    }

    public void setpMobile(String pMobile) {
        this.pMobile = pMobile;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getParticularPlace() {
        return particularPlace;
    }

    public void setParticularPlace(String particularPlace) {
        this.particularPlace = particularPlace;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSchoolNameAddress() {
        return schoolNameAddress;
    }

    public void setSchoolNameAddress(String schoolNameAddress) {
        this.schoolNameAddress = schoolNameAddress;
    }

    public Object getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(Object employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getReferenceName1() {
        return referenceName1;
    }

    public void setReferenceName1(String referenceName1) {
        this.referenceName1 = referenceName1;
    }

    public String getReferencePhone1() {
        return referencePhone1;
    }

    public void setReferencePhone1(String referencePhone1) {
        this.referencePhone1 = referencePhone1;
    }

    public String getReferenceName2() {
        return referenceName2;
    }

    public void setReferenceName2(String referenceName2) {
        this.referenceName2 = referenceName2;
    }

    public String getReferencePhone2() {
        return referencePhone2;
    }

    public void setReferencePhone2(String referencePhone2) {
        this.referencePhone2 = referencePhone2;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfsciCode() {
        return ifsciCode;
    }

    public void setIfsciCode(String ifsciCode) {
        this.ifsciCode = ifsciCode;
    }

    public String getEverArrested() {
        return everArrested;
    }

    public void setEverArrested(String everArrested) {
        this.everArrested = everArrested;
    }

    public String getCaseDetails() {
        return caseDetails;
    }

    public void setCaseDetails(String caseDetails) {
        this.caseDetails = caseDetails;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getAadharCopy() {
        return aadharCopy;
    }

    public void setAadharCopy(String aadharCopy) {
        this.aadharCopy = aadharCopy;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanCopy() {
        return panCopy;
    }

    public void setPanCopy(String panCopy) {
        this.panCopy = panCopy;
    }

    public String getValidPolice() {
        return validPolice;
    }

    public void setValidPolice(String validPolice) {
        this.validPolice = validPolice;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getPoliceVarificationUpload() {
        return policeVarificationUpload;
    }

    public void setPoliceVarificationUpload(String policeVarificationUpload) {
        this.policeVarificationUpload = policeVarificationUpload;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getVisaNo() {
        return visaNo;
    }

    public void setVisaNo(String visaNo) {
        this.visaNo = visaNo;
    }

    public String getValidityOfVisa() {
        return validityOfVisa;
    }

    public void setValidityOfVisa(String validityOfVisa) {
        this.validityOfVisa = validityOfVisa;
    }

    public String getExpertField() {
        return expertField;
    }

    public void setExpertField(String expertField) {
        this.expertField = expertField;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBiometricSignature() {
        return biometricSignature;
    }

    public void setBiometricSignature(String biometricSignature) {
        this.biometricSignature = biometricSignature;
    }

    public String getDeclarationByPerson() {
        return declarationByPerson;
    }

    public void setDeclarationByPerson(String declarationByPerson) {
        this.declarationByPerson = declarationByPerson;
    }

    public String getLeftEyePower() {
        return leftEyePower;
    }

    public void setLeftEyePower(String leftEyePower) {
        this.leftEyePower = leftEyePower;
    }

    public String getRightEyePower() {
        return rightEyePower;
    }

    public void setRightEyePower(String rightEyePower) {
        this.rightEyePower = rightEyePower;
    }

    public Object getLense() {
        return lense;
    }

    public void setLense(Object lense) {
        this.lense = lense;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public Object getRightEye() {
        return rightEye;
    }

    public void setRightEye(Object rightEye) {
        this.rightEye = rightEye;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSessionEmail() {
        return sessionEmail;
    }

    public void setSessionEmail(String sessionEmail) {
        this.sessionEmail = sessionEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }

    public Object getPartnerClient() {
        return partnerClient;
    }

    public void setPartnerClient(Object partnerClient) {
        this.partnerClient = partnerClient;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", personName='" + personName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", dob='" + dob + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", wearGlass='" + wearGlass + '\'' +
                ", mark='" + mark + '\'' +
                ", address='" + address + '\'' +
                ", village='" + village + '\'' +
                ", postOffice='" + postOffice + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pinNo='" + pinNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", whetherStaying='" + whetherStaying + '\'' +
                ", pAddress='" + pAddress + '\'' +
                ", pVillage='" + pVillage + '\'' +
                ", pPostOffice='" + pPostOffice + '\'' +
                ", pPoliceStation='" + pPoliceStation + '\'' +
                ", pState='" + pState + '\'' +
                ", pDistrict=" + pDistrict +
                ", pPinNo='" + pPinNo + '\'' +
                ", pMobile='" + pMobile + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", particularPlace='" + particularPlace + '\'' +
                ", qualification='" + qualification + '\'' +
                ", schoolNameAddress='" + schoolNameAddress + '\'' +
                ", employeeDetail=" + employeeDetail +
                ", duration='" + duration + '\'' +
                ", pf='" + pf + '\'' +
                ", referenceName1='" + referenceName1 + '\'' +
                ", referencePhone1='" + referencePhone1 + '\'' +
                ", referenceName2='" + referenceName2 + '\'' +
                ", referencePhone2='" + referencePhone2 + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", ifsciCode='" + ifsciCode + '\'' +
                ", everArrested='" + everArrested + '\'' +
                ", caseDetails='" + caseDetails + '\'' +
                ", nationality='" + nationality + '\'' +
                ", aadharNo='" + aadharNo + '\'' +
                ", aadharCopy='" + aadharCopy + '\'' +
                ", pan='" + pan + '\'' +
                ", panCopy='" + panCopy + '\'' +
                ", validPolice='" + validPolice + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                ", issuanceDate='" + issuanceDate + '\'' +
                ", policeVarificationUpload='" + policeVarificationUpload + '\'' +
                ", passportNo='" + passportNo + '\'' +
                ", visaNo='" + visaNo + '\'' +
                ", validityOfVisa='" + validityOfVisa + '\'' +
                ", expertField='" + expertField + '\'' +
                ", signature='" + signature + '\'' +
                ", biometricSignature='" + biometricSignature + '\'' +
                ", declarationByPerson='" + declarationByPerson + '\'' +
                ", leftEyePower='" + leftEyePower + '\'' +
                ", rightEyePower='" + rightEyePower + '\'' +
                ", lense=" + lense +
                ", leftEye='" + leftEye + '\'' +
                ", rightEye=" + rightEye +
                ", policeStation='" + policeStation + '\'' +
                ", role='" + role + '\'' +
                ", sessionEmail='" + sessionEmail + '\'' +
                ", status='" + status + '\'' +
                ", password=" + password +
                ", client='" + client + '\'' +
                ", branch=" + branch +
                ", partnerClient=" + partnerClient +
                ", personId='" + personId + '\'' +
                '}';
    }
}
