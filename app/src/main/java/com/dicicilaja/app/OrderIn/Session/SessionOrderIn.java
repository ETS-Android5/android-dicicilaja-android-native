package com.dicicilaja.app.OrderIn.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionOrderIn {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "OrderInPref";

    public SessionOrderIn(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createOrderInSession(String jenis_pengajuan,
                                     String program_id,
                                     String product_id,
                                     String qty,
                                     String agen_id,
                                     String agen_name,
                                     String amount,
                                     String ktp_image,
                                     String bpkb,
                                     String vehicle_id,
                                     boolean status_data_calon,
                                     String name,
                                     String no_ktp,
                                     String email,
                                     String no_hp,
                                     String province_id,
                                     String province,
                                     String city_id,
                                     String city,
                                     String district_id,
                                     String district,
                                     String village_id,
                                     String village,
                                     String address,
                                     String postal_code,
                                     boolean status_informasi_jaminan,
                                     String jaminan_id,
                                     String jaminan,
                                     String area_id,
                                     String area,
                                     String objek_brand_id,
                                     String brand,
                                     String objek_model_id,
                                     String model,
                                     String year,
                                     String tenor_simulasi,
                                     String tipe_asuransi_id,
                                     String tipe_asuransi,
                                     String jenis_angsuran_id,
                                     String jenis_angsuran,
                                     String branch_id,
                                     String branch,
                                     String branch_address,
                                     String branch_district){
        editor.putString("jenis_pengajuan", jenis_pengajuan);
        editor.putString("program_id", program_id);
        editor.putString("product_id", product_id);
        editor.putString("qty", qty);
        editor.putString("agen_id", agen_id);
        editor.putString("agen_name", agen_name);
        editor.putString("amount", amount);
        editor.putString("ktp_image", ktp_image);
        editor.putString("bpkb", bpkb);
        editor.putString("vehicle_id", vehicle_id);
        editor.putBoolean("status_data_calon", status_data_calon);
        editor.putString("name", name);
        editor.putString("no_ktp", no_ktp);
        editor.putString("email", email);
        editor.putString("no_hp", no_hp);
        editor.putString("province_id", province_id);
        editor.putString("province", province);
        editor.putString("city_id", city_id);
        editor.putString("city", city);
        editor.putString("district_id", district_id);
        editor.putString("district", district);
        editor.putString("village_id", village_id);
        editor.putString("village", village);
        editor.putString("address", address);
        editor.putString("postal_code", postal_code);
        editor.putBoolean("status_informasi_jaminan", status_informasi_jaminan);
        editor.putString("jaminan_id", jaminan_id);
        editor.putString("jaminan", jaminan);
        editor.putString("area_id", area_id);
        editor.putString("area", area);
        editor.putString("objek_brand_id", objek_brand_id);
        editor.putString("brand", brand);
        editor.putString("objek_model_id", objek_model_id);
        editor.putString("model", model);
        editor.putString("year", year);
        editor.putString("tenor_simulasi", tenor_simulasi);
        editor.putString("tipe_asuransi_id", tipe_asuransi_id);
        editor.putString("tipe_asuransi", tipe_asuransi);
        editor.putString("jenis_angsuran_id", jenis_angsuran_id);
        editor.putString("jenis_angsuran", jenis_angsuran);
        editor.putString("branch_id", branch_id);
        editor.putString("branch", branch);
        editor.putString("branch_address", branch_address);
        editor.putString("branch_district", branch_district);
        editor.commit();
    }

    public void editDataCalonPeminjam(boolean status_data_calon,
                                      String name,
                                      String no_ktp,
                                      String email,
                                      String no_hp,
                                      String province_id,
                                      String province,
                                      String city_id,
                                      String city,
                                      String district_id,
                                      String district,
                                      String village_id,
                                      String village,
                                      String address,
                                      String postal_code){
        editor.putBoolean("status_data_calon", status_data_calon);
        editor.putString("name", name);
        editor.putString("no_ktp", no_ktp);
        editor.putString("email", email);
        editor.putString("no_hp", no_hp);
        editor.putString("province_id", province_id);
        editor.putString("province", province);
        editor.putString("city_id", city_id);
        editor.putString("city", city);
        editor.putString("district_id", district_id);
        editor.putString("district", district);
        editor.putString("village_id", village_id);
        editor.putString("village", village);
        editor.putString("address", address);
        editor.putString("postal_code", postal_code);
        editor.commit();
    }

    public void editInformasiJaminan(boolean status_informasi_jaminan,
                                     String jaminan_id,
                                     String jaminan,
                                     String area_id,
                                     String area,
                                     String objek_brand_id,
                                     String brand,
                                     String objek_model_id,
                                     String model,
                                     String year,
                                     String tenor_simulasi,
                                     String tipe_asuransi_id,
                                     String tipe_asuransi,
                                     String jenis_angsuran_id,
                                     String jenis_angsuran){
        editor.putBoolean("status_informasi_jaminan", status_informasi_jaminan);
        editor.putString("jaminan_id", jaminan_id);
        editor.putString("jaminan", jaminan);
        editor.putString("area_id", area_id);
        editor.putString("area", area);
        editor.putString("objek_brand_id", objek_brand_id);
        editor.putString("brand", brand);
        editor.putString("objek_model_id", objek_model_id);
        editor.putString("model", model);
        editor.putString("year", year);
        editor.putString("tenor_simulasi", tenor_simulasi);
        editor.putString("tipe_asuransi_id", tipe_asuransi_id);
        editor.putString("tipe_asuransi", tipe_asuransi);
        editor.putString("jenis_angsuran_id", jenis_angsuran_id);
        editor.putString("jenis_angsuran", jenis_angsuran);
        editor.commit();
    }

    public void editInformasiJaminanMaxi(boolean status_informasi_jaminan,
                                     String jaminan_id,
                                     String jaminan,
                                     String area_id,
                                     String area,
                                     String objek_brand_id,
                                     String brand,
                                     String objek_model_id,
                                     String model,
                                     String year,
                                     String tipe_asuransi_id,
                                     String tipe_asuransi,
                                     String jenis_angsuran_id,
                                     String jenis_angsuran){
        editor.putBoolean("status_informasi_jaminan", status_informasi_jaminan);
        editor.putString("jaminan_id", jaminan_id);
        editor.putString("jaminan", jaminan);
        editor.putString("area_id", area_id);
        editor.putString("area", area);
        editor.putString("objek_brand_id", objek_brand_id);
        editor.putString("brand", brand);
        editor.putString("objek_model_id", objek_model_id);
        editor.putString("model", model);
        editor.putString("year", year);
        editor.putString("tipe_asuransi_id", tipe_asuransi_id);
        editor.putString("tipe_asuransi", tipe_asuransi);
        editor.putString("jenis_angsuran_id", jenis_angsuran_id);
        editor.putString("jenis_angsuran", jenis_angsuran);
        editor.commit();
    }

    public String getJenis_pengajuan() {
        return pref.getString("jenis_pengajuan", "1");
    }

    public void setJenis_pengajuan(String jenis_pengajuan) {
        editor.putString("jenis_pengajuan", jenis_pengajuan);
        editor.commit();
    }

    public String getProgram_id() {
        return pref.getString("program_id", "1");
    }

    public void setProgram_id(String program_id) {
        editor.putString("program_id", program_id);
        editor.commit();
    }

    public String getProduct_id() {
        return pref.getString("product_id", "1");
    }

    public void setProduct_id(String product_id) {
        editor.putString("product_id", product_id);
        editor.commit();
    }

    public String getQty() {
        return pref.getString("qty", "1");
    }

    public void setQty(String qty) {
        editor.putString("qty", qty);
        editor.commit();
    }

    public String getAgen_id() {
        return pref.getString("agen_id", "");
    }

    public void setAgen_id(String agen_id) {
        editor.putString("agen_id", agen_id);
        editor.commit();
    }

    public String getAgen_name() {
        return pref.getString("agen_name", null);
    }

    public void setAgen_name(String agen_name) {
        editor.putString("agen_name", agen_name);
        editor.commit();
    }

    public String getAmount() {
        return pref.getString("amount", "");
    }

    public void setAmount(String amount) {
        editor.putString("amount", amount);
        editor.commit();
    }

    public String getKtp_image() {
        return pref.getString("ktp_image", "");
    }

    public void setKtp_image(String ktp_image) {
        editor.putString("ktp_image", ktp_image);
        editor.commit();
    }

    public String getBpkb() {
        return pref.getString("bpkb", "");
    }

    public void setBpkb(String bpkb) {
        editor.putString("bpkb", bpkb);
        editor.commit();
    }

    public String getVehicle_id() {
        return pref.getString("vehicle_id", null);
    }

    public void setVehicle_id(String vehicle_id) {
        editor.putString("vehicle_id", vehicle_id);
        editor.commit();
    }

    public String getVoucher_code_id() {
        return pref.getString("voucher_code_id", "");
    }

    public void setVoucher_code_id(String voucher_code_id) {
        editor.putString("voucher_code_id", voucher_code_id);
        editor.commit();
    }

    public String getVoucher_code() {
        return pref.getString("voucher_code", null);
    }

    public void setVoucher_code(String voucher_code) {
        editor.putString("voucher_code", voucher_code);
        editor.commit();
    }

    public boolean getStatus_data_calon() {
        return pref.getBoolean("status_data_calon", false);
    }

    public void setStatus_data_calon(boolean status_data_calon) {
        editor.putBoolean("status_data_calon", status_data_calon);
        editor.commit();
    }

    public String getName() {
        return pref.getString("name", null);
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public String getNo_ktp() {
        return pref.getString("no_ktp", null);
    }

    public void setNo_ktp(String no_ktp) {
        editor.putString("no_ktp", no_ktp);
        editor.commit();
    }

    public String getNo_hp() {
        return pref.getString("no_hp", null);
    }

    public void setNo_hp(String no_hp) {
        editor.putString("no_hp", no_hp);
        editor.commit();
    }

    public String getProvince_id() {
        return pref.getString("province_id", null);
    }

    public void setProvince_id(String province_id) {
        editor.putString("province_id", province_id);
        editor.commit();

    }

    public String getProvince() {
        return pref.getString("province", null);
    }

    public void setProvince(String province) {
        editor.putString("province", province);
        editor.commit();
    }

    public String getCity_id() {
        return pref.getString("city_id", null);
    }

    public void setCity_id(String city_id) {
        editor.putString("city_id", city_id);
        editor.commit();
    }

    public String getCity() {
        return pref.getString("city", null);
    }

    public void setCity(String city) {
        editor.putString("city", city);
        editor.commit();
    }

    public String getDistrict_id() {
        return pref.getString("district_id", null);
    }

    public void setDistrict_id(String district_id) {
        editor.putString("district_id", district_id);
        editor.commit();
    }

    public String getDistrict() {
        return pref.getString("district", null);
    }

    public void setDistrict(String district) {
        editor.putString("district", district);
        editor.commit();
    }

    public String getVillage_id() {
        return pref.getString("village_id", null);
    }

    public void setVillage_id(String village_id) {
        editor.putString("village_id", village_id);
        editor.commit();
    }

    public String getVillage() {
        return pref.getString("village", null);
    }

    public void setVillage(String village) {
        editor.putString("village", village);
        editor.commit();
    }

    public String getAddress() {
        return pref.getString("address", null);
    }

    public void setAddress(String address) {
        editor.putString("address", address);
        editor.commit();
    }

    public String getPostal_code() {
        return pref.getString("postal_code", null);
    }

    public void setPostal_code(String postal_code) {
        editor.putString("postal_code", postal_code);
        editor.commit();
    }

    public boolean getStatus_informasi_jaminan() {
        return pref.getBoolean("status_informasi_jaminan", false);
    }

    public void setStatus_informasi_jaminan(boolean status_informasi_jaminan) {
        editor.putBoolean("status_informasi_jaminan", status_informasi_jaminan);
        editor.commit();
    }

    public String getJaminan_id() {
        return pref.getString("jaminan_id", null);
    }

    public void setJaminan_id(String jaminan_id) {
        editor.putString("jaminan_id", jaminan_id);
        editor.commit();
    }

    public String getJaminan() {
        return pref.getString("jaminan", null);
    }

    public void setJaminan(String jaminan) {
        editor.putString("jaminan", jaminan);
        editor.commit();
    }

    public String getArea_id() {
        return pref.getString("area_id", null);
    }

    public void setArea_id(String area_id) {
        editor.putString("area_id", area_id);
        editor.commit();
    }

    public String getArea() {
        return pref.getString("area", null);
    }

    public void setArea(String area) {
        editor.putString("area", area);
        editor.commit();
    }

    public String getObjek_brand_id() {
        return pref.getString("objek_brand_id", null);
    }

    public void setObjek_brand_id(String objek_brand_id) {
        editor.putString("objek_brand_id", objek_brand_id);
        editor.commit();
    }

    public String getBrand() {
        return pref.getString("brand", null);
    }

    public void setBrand(String brand) {
        editor.putString("brand", brand);
        editor.commit();
    }

    public String getObjek_model_id() {
        return pref.getString("objek_model_id", null);
    }

    public void setObjek_model_id(String objek_model_id) {
        editor.putString("objek_model_id", objek_model_id);
        editor.commit();
    }

    public String getModel() {
        return pref.getString("model", null);
    }

    public void setModel(String model) {
        editor.putString("model", model);
        editor.commit();
    }

    public String getYear() {
        return pref.getString("year", null);
    }

    public void setYear(String year) {
        editor.putString("year", year);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString("email", null);
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getTenor_simulasi() {
        return pref.getString("tenor_simulasi", null);
    }

    public void setTenor_simulasi(String tenor_simulasi) {
        editor.putString("tenor_simulasi", tenor_simulasi);
        editor.commit();
    }

    public String getTipe_asuransi_id() {
        return pref.getString("tipe_asuransi_id", "1");
    }

    public void setTipe_asuransi_id(String tipe_asuransi_id) {
        editor.putString("tipe_asuransi_id", tipe_asuransi_id);
        editor.commit();
    }

    public String getTipe_asuransi() {
        return pref.getString("tipe_asuransi", "1");
    }

    public void setTipe_asuransi(String tipe_asuransi) {
        editor.putString("tipe_asuransi", tipe_asuransi);
        editor.commit();
    }

    public String getJenis_angsuran_id() {
        return pref.getString("jenis_angsuran_id", "1");
    }

    public void setJenis_angsuran_id(String jenis_angsuran_id) {
        editor.putString("jenis_angsuran_id", jenis_angsuran_id);
        editor.commit();
    }

    public String getJenis_angsuran() {
        return pref.getString("jenis_angsuran", "1");
    }

    public void setJenis_angsuran(String jenis_angsuran) {
        editor.putString("jenis_angsuran", jenis_angsuran);
        editor.commit();
    }

    public String getBranch_id() {
        return pref.getString("branch_id", null);
    }

    public void setBranch_id(String branch_id) {
        editor.putString("branch_id", branch_id);
        editor.commit();
    }

    public String getBranch() {
        return pref.getString("branch", null);
    }

    public void setBranch(String branch) {
        editor.putString("branch", branch);
        editor.commit();
    }

    public String getBranch_address() {
        return pref.getString("branch_address", null);
    }

    public void setBranch_address(String branch_address) {
        editor.putString("branch_address", branch_address);
        editor.commit();
    }

    public String getBranch_district() {
        return pref.getString("branch_district", null);
    }

    public void setBranch_district(String branch_district) {
        editor.putString("branch_district", branch_district);
        editor.commit();
    }

    public String getGambar() {
        return pref.getString("gambar", null);
    }

    public void setGambar(String gambar) {
        editor.putString("gambar", gambar);
        editor.commit();
    }

    public String getTitle() {
        return pref.getString("title", null);
    }

    public void setTitle(String title) {
        editor.putString("title", title);
        editor.commit();
    }

    public String getMitra() {
        return pref.getString("mitra", null);
    }

    public void setMitra(String mitra) {
        editor.putString("mitra", mitra);
        editor.commit();
    }

    public String getHarga() {
        return pref.getString("harga", null);
    }

    public void setHarga(String harga) {
        editor.putString("harga", harga);
        editor.commit();
    }

    /**
     * Clear session details
     * */
    public void clearOrderIn(){
        editor.clear();
        editor.commit();
    }
}
