package com.example.oespartner.WebService;


import com.example.oespartner.Model.AddAuthorizedSignatoryModel;
import com.example.oespartner.Model.AddMaterialGatePassModel;
import com.example.oespartner.Model.AddPartnerPersonModel;
import com.example.oespartner.Model.AddVehicleNo;
import com.example.oespartner.Model.AddVisitorGatePassModel;
import com.example.oespartner.Model.AddWorkGatePassModel;
import com.example.oespartner.Model.AuthorizedSignatoryModel;
import com.example.oespartner.Model.LoginResult;
import com.example.oespartner.Model.MaterialGatePassModel;
import com.example.oespartner.Model.OtpModel;
import com.example.oespartner.Model.PartnerPersonModel;
import com.example.oespartner.Model.TransportModel;
import com.example.oespartner.Model.VehicleNoModel;
import com.example.oespartner.Model.VisitorGatePassModel;
import com.example.oespartner.Model.WorkGatePassModel;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApi {

    @POST("send_sms")
    @FormUrlEncoded
    Call<OtpModel> sendOtp(@Field("content") String content, @Field("contact") int contact);

    @POST("login")
    @FormUrlEncoded
    Call<LoginResult> login(@Field("role") String role, @Field("phone") String phone, @Field("pin") String pin);

    @POST("registration/add_info")
    Call<String> registration(@Body RequestBody body);


    @FormUrlEncoded
    @POST("visitor_gatepass")
    Call<List<VisitorGatePassModel>> VisitorGatePass(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("work_gatepass")
    Call<List<WorkGatePassModel>> WorkGatePass(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("authorised_signatory")
    Call<List<AuthorizedSignatoryModel>> AuthorizedSignatory(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("partner_person")
    Call<List<PartnerPersonModel>> PartnerPerson(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("transport")
    Call<List<TransportModel>> Transport(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("material_list")
    Call<List<MaterialGatePassModel>> MaterialGatePass(
            @Field("email") String email,
            @Field("role") String role);

    @FormUrlEncoded
    @POST("get_vehicle")
    Call<List<VehicleNoModel>> VehicleNo(
            @Field("email") String email,
            @Field("role") String role);


    @FormUrlEncoded
    @POST("add_material_gatepass")
    Call<AddMaterialGatePassModel> AddMaterialGatePass(
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("gate_pass_type") String gate_pass_type,
            @Field("partner_code") String partner_code,
            @Field("partner_name") String partner_name,
            @Field("vehicle_no") String vehicle_no,
            @Field("vehicle_load") String vehicle_load,
            @Field("reason") String reason,
            @Field("belong_to") String belong_to,
            @Field("returnable_nonreturnable") String returnable_nonreturnable,
            @Field("date_time") String date_time);


    @FormUrlEncoded
    @POST("add_visitor_gatepass")
    Call<AddVisitorGatePassModel> AddVisitorGatePass(
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("person_name") String person_name,
            @Field("firm_name") String firm_name,
            @Field("designation") String designation,
            @Field("approval") String approval,
            @Field("person_visited") String person_visited,
            @Field("reason") String reason,
            @Field("visit_date") String visit_date,
            @Field("visit_time") String visit_time,
            @Field("declaration") String declaration,
            @Field("person_id") String personId);


    @FormUrlEncoded
    @POST("add_work_gatepass")
    Call<AddWorkGatePassModel> AddWorkGatePass(
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("person_name") String person_name,
            @Field("person_id") String person_id,
            @Field("designation") String designation,
            @Field("work_reference_no") String work_reference_no,
            @Field("work_description") String work_description,
            @Field("work_valid_upto") String work_valid_upto,
            @Field("police_verify") String police_verify,
            @Field("visa_validity") String visa_validity,
            @Field("declaration") String declaration,
            @Field("j_declaration") String j_declaration,
            @Field("h_declaration") String h_declaration,
            @Field("stakeholder_id") String stakeholder_id,
            @Field("p_valid_upto") String p_valid_upto,
            @Field("id") String id);


    @FormUrlEncoded
    @POST("add_authorised_signatory")
    Call<AddAuthorizedSignatoryModel> AddAuthorizedSignatory(
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("person_name") String person_name,
            @Field(" reference_no") String reference_no,
            @Field("designationnn") String designationnn,
            @Field("description") String description,
            @Field("valid_upto") String valid_upto);

    @POST("add_transport")
    Call<String> AddTransport(@Body RequestBody body);

    @Multipart
    @POST("add_person_master")
    Call<AddPartnerPersonModel> AddPartnerPerson(
            @Part("email") RequestBody session_email,
            @Part("role") RequestBody role,
            @Part("title") RequestBody title,
            @Part("person_name") RequestBody person_name,
            @Part("phone") RequestBody phone,
            @Part("email1") RequestBody email,
            @Part("photo\"; filename=\"photo.jpg") RequestBody photo,
            @Part("father_name") RequestBody father_name,
            @Part("dob") RequestBody dob,
            @Part("age") RequestBody age,
            @Part("gender") RequestBody gender,
            @Part("blood_group") RequestBody blood_group);

    @FormUrlEncoded
    @POST("update_work_gatepass")
    Call<AddWorkGatePassModel> UpdateWorkGatePass(
            @Field("id") String id,
            @Field("email") String email,
            @Field("client") String client,
            @Field("role") String role,
            @Field("branch") String branch,
            @Field(" person_name") String person_name,
            @Field("designation") String designation,
            @Field("license_valid_upto") String license_valid_upto,
            @Field("stakeholder_id") String stakeholder_id,
            @Field("person_id") String person_id,
            @Field("work_reference_no") String work_reference_no,
            @Field("work_description") String work_description,
            @Field("work_valid_upto") String work_valid_upto,
            @Field("visa_validity") String visa_validity,
            @Field("declaration") String declaration,
            @Field("j_declaration") String j_declaration,
            @Field("h_declaration") String h_declaration,
            @Field("p_valid_upto") String p_valid_upto);

    @FormUrlEncoded
    @POST("add_vehicle")
    Call<AddVehicleNo> AddVehicleNo(
            @Field("email") String email,
            @Field("role") String role,
            @Field("vehicle_no") String vehicle_no,
            @Field("vehicle_type") String vehicle_type
    );


    @FormUrlEncoded
    @POST("delete_visitor_gatepass")
    Call<VisitorGatePassModel> DeleteVisitorGatePass(
            @Field("id") String person_id);


    @FormUrlEncoded
    @POST("delete_work_gatepass")
    Call<WorkGatePassModel> DeleteWorkGatePass(
            @Field("id") String person_id);


    @FormUrlEncoded
    @POST("delete_material_gatepass")
    Call<MaterialGatePassModel> DeleteMaterialGatePass(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("delete_authorised")
    Call<AuthorizedSignatoryModel> DeleteAuthorized(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("delete_Person_master")
    Call<PartnerPersonModel> DeletePartnerPerson(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("delete_transport")
    Call<TransportModel> DeleteTransport(
            @Field("id") String id);

//    @FormUrlEncoded
//    @POST("get_work_gatepass")
//    Call<AddWorkGatePassModel> EditWorkGatePass(
//            @Field("id") String id);

    @FormUrlEncoded
    @POST("update_visitor_gatepass")
    Call<AddVisitorGatePassModel> UpdateVisitorGatePass(
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("person_name") String person_name,
            @Field("firm_name") String firm_name,
            @Field("designation") String designation,
            @Field("approval") String approval,
            @Field("person_visited") String person_visited,
            @Field("reason") String reason,
            @Field("visit_date") String visit_date,
            @Field("visit_time") String visit_time,
            @Field("declaration") String declaration,
            @Field("person_id") String personId,
            @Field("id") String id);

    @FormUrlEncoded
    @POST("update_vehicle")
    Call<AddVehicleNo> UpdateVehicleNo(
            @Field("id") String id,
            @Field("email") String email,
            @Field("role") String role,
            @Field("vehicle_no") String vehicle_no,
            @Field("vehicle_type") String vehicle_type);

    @FormUrlEncoded
    @POST("update_material_gatepass")
    Call<AddMaterialGatePassModel> UpdateMaterialGatePass(
            @Field("id") String id,
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("gate_pass_type") String gate_pass_type,
            @Field("partner_code") String partner_code,
            @Field("partner_name") String partner_name,
            @Field("vehicle_no") String vehicle_no,
            @Field("vehicle_load") String vehicle_load,
            @Field("reason") String reason,
            @Field("belong_to") String belong_to,
            @Field("returnable_nonreturnable") String returnable_nonreturnable,
            @Field("date_time") String date_time);

    @POST("update_transport")
    Call<String> UpdateTransport(@Body RequestBody body);

    @FormUrlEncoded
    @POST(" update_authorised_signatory")
    Call<AddAuthorizedSignatoryModel> UpdateAuthorizedSignatory(
            @Field("id") String id,
            @Field("email") String email,
            @Field("role") String role,
            @Field("client") String client,
            @Field("branch") String branch,
            @Field("person_name") String person_name,
            @Field(" reference_no") String reference_no,
            @Field("designationnn") String designationnn,
            @Field("description") String description,
            @Field("valid_upto") String valid_upto);

    @FormUrlEncoded
    @POST("get_material_gatepass")
    Call<AddMaterialGatePassModel> EditMaterialGatePass(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("get_visitor_gatepass")
    Call<AddVisitorGatePassModel> EditVisitorGatePass(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("edit_vehicle")
    Call<AddVehicleNo> EditVehicleNo(
            @Field("id") String id);


}