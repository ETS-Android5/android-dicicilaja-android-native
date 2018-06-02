package com.dicicilaja.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

public class DeskripsiLengkapActivity extends AppCompatActivity {

    WebView deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_lengkap);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        deskripsi = findViewById(R.id.deskripsi);
        String content = "<p><b>RINGKASAN INFORMASI PRODUK&nbsp;<span style=\"font-size: 1rem;\">ASURANSI HOSPITAL CASH PLAN 5 DISEASES&nbsp;</span></b></p><p>Asuransi Hospital Cash Plan (5 Diseases) adalah produk asuransimilik PT Asuransi Adira Dinamika yang memberikan manfaatsantunan rawat inap apabila tertanggung terdiagnosis salah satupenyakit yaitu Demam Berdarah, Tipus, Pneumonia, Meningitisdan Difteri.</p><p><b>ARTI ISTILAH</b>&nbsp;</p><ol><li>Penanggung adalah PT Asuransi Adira Dinamika.&nbsp;</li><li>Tertanggung adalah Customer yang telah mengajukan aplikasiasuransi tipus dan berusia antara 6 (enam) bulan sampaidengan maksimal 65 (enam puluh lima) tahun.</li><li>Ahli Waris adalah ahli waris Tertanggung menurut ketentuanhukum yang berlaku.</li><li>Premi yang berarti sejumlah uang yang harus dibayar olehTertanggung sebagai imbalan atas jasa pertanggungan atauasuransi yang diberikan oleh Penanggung.</li><li>Demam Berdarah Dengue atau disingkat DBD adalah suatupenyakit yang disebabkan oleh virus dengue yang dibawa olehnyamuk aedes aegypti betina lewat air liur gigitan saatmenghisap darah manusia yang harus dibuktikan dengan hasildiagnosa dokter dan dengan hasil pemeriksaan laboratoriumyang menunjukkan jumlah trombosit kurang dari 150.000(seratus lima puluh ribu).</li><li>Tipus atau demam tifoid merupakan infeksi berat pada salurancerna yang disebabkan oleh bakteri Salmonella typhi yangharus dibuktikan dengan pemeriksaan laboratorium yangmenunjukkan widal minimal 1/640 atau ditemukannyaSalmonella typhi di feses atau Anti-Salmonella typhi IgM 6 -10dan hasil diagnosa dokter yang menyatakan bahwatertanggung menderita Tipus dan harus dirawat inap.</li><li>Pneumonia adalah penyakit infeksi yang menyebabkan paruparumeradang. Penyakit Pneumonia harus dibuktikan denganpemeriksaan XRay yang menyatakan bahwa Tertanggungmenderita Pneumonia (tanda/in_ltrat pada Paru-paru).</li><li>Meningitis adalah infeksi pada meninges (selaput pelindung)yang menyelimuti otak dan saraf tulang belakang. PenyakitMeningitis harus dibuktikan dengan pemeriksaan lumbalpungsi yang menunjukkan peningkatan lebih dari 10 sel limfositatau granulosit atau peningkatan kadar protein cairan otak.</li><li>Difteri adalah suatu infeksi akut pada saluran pernafasan.Penyakit Difteri harus dibuktikan dengan pemeriksaanlaboratorium dari swab tenggorokan yang menunjukkanditemukannya Corynebacterium Diphteriae.</li></ol><p><b>PREMI</b></p><p>Tertanggung wajib membayar Premi kepada Penanggung sebesarRp 150.000.- (Seratus lima puluh ribu rupiah).</p><p><b>MASA PERTANGGUNGAN</b>&nbsp;</p><p>Perlindungan ini akan berlaku selama 1 (satu) tahun sejak tanggal&nbsp;Tertanggung melakukan pembayaran dengan masa tungguselama 14 (empat belas) hari sejak tanggal tersebut.</p><p><b>LINGKUP PERTANGGUNGAN</b></p><p>Polis ini akan memberikan santunan kepada Tertanggung atauAhli Warisnya yang sah sebesar yang tertera dalam Ikhtisar Polis,jika Tertanggung terdiagnosis salah satu penyakit infeksi dari 5(lima) penyakit infeksi (Demam Berdarah, Tipus, Pneumonia,Meningitis, atau Difteri) dan dirawat di Rumah Sakit selamaberlakunya Polis ini.Tertanggung wajib membaca dan memahami ketentuanmengenai hal-hal yang dikecualikan sebagaimana yang diaturdalam polis.</p><p><b>MANFAAT ASURANSI&nbsp;</b></p><p>Santunan rawat inap sebesar Rp 500.000,- (lima ratus ribu rupiah)perhari maksimal Rp5.000.000 (lima juta rupiah) atau maksimalselama 10 (sepuluh) hari untuk 5 (lima) penyakit. Denganmaksimal klaim 2 (dua) kali dalam setahun.</p><p><b>CARA MENGAJUKAN KLAIM&nbsp;</b></p><p>Dalam hal terjadi suatu kerugian yang dijamin dalam Polis ini,maka:</p><ol><li>Dokumen klaim diterima oleh Penanggung dari Tertanggungdalam waktu maksimal 30 (tiga puluh) hari kalender terhitungsejak keluar dari rumah sakit.</li><li>Apabila terdapat kekurangan dokumen/informasi klaim dariTertanggung, maka kekurangan tersebut dapat dikirimkankembali dan diterima oleh Penanggung selambat-lambatnya14 (empat belas) hari kalender sejak tanggal Informasi Tundadikeluarkan oleh Penanggung.</li><li>Dokumen klaim yang harus dilengkapi :</li><ul><li>Formulir klaim asuransi Hospital Cash Plan (5 Disease) yangtelah diisi dengan lengkap;</li><li>Fotokopi identitas diri Tertanggung (KTP/ SIM/ Paspor) yangmasih berlaku atau Fotokopi Kartu Keluarga (apabilaTertanggung dibawah 17 (tujuh belas) tahun);</li><li>Fotokopi legalisir resume medis yang dilengkapi denganketerangan lama rawat inap;</li><li>Fotokopi legalisir hasil pemeriksaan laboratorium/penunjanglainnya dan resep obat yaitu :</li><ul type=\"a\"><li>Demam Berdarah Dengue<br>Dengan hasil diagnosa dokter dan dengan hasilpemeriksaan laboratorium yang menunjukkan jumlahtrombosit kurang dari 150.000 (seratus lima puluh ribu).</li><li>Tipus<br>Dengan hasil pemeriksaan laboratorium yangmenunjukkan widal minimal 1/640 atau ditemukannyaSalmonella typhi di feses atau Anti-Salmonella typhi IgM 6-10.</li><li>Pneumonia<br>Dengan hasil pemeriksaan XRay yang menyatakan bahwaTertanggung menderita Pneumonia (tanda/in_ltrat padaParu-paru).</li><li>Meningitis<br>Dengan pemeriksaan lumbal pungsi yang menunjukkanpeningkatan lebih dari 10 sel limfosit atau&nbsp;granulositatau peningkatan kadar protein cairan otak.</li><li>Difteri<br>Dengan pemeriksaan laboratorium dari swabtenggorokan yang menunjukkan ditemukannyaCorynebacterium Diphteriae.<br></li></ul><li>Dokumen tambahan lainnya yang relevan, jika diperlukan.Apabila Tertanggung meninggal, maka ahli waris dapatmengajukan klaim dengan melengkapi :</li><ul><li>Fotokopi identitas diri Ahli Waris (KTP/ SIM/ Paspor) yangmasih berlaku;</li><li>Fotokopi Kartu Keluarga;</li><li>Surat Keterangan atas Kematian dari Rumah Sakit/InstansiPemerintah Terkait. Bila kewajiban-kewajiban yang tersebutdiatas tidak dipenuhi oleh Tertanggung atau wakilnya yangsah, maka Penanggung berhak untuk menolak memberikansantunan atau penggantian biaya kepada Tertanggung.</li></ul></ul></ol><p><b>BERAKHIRNYA PERTANGGUNGAN&nbsp;</b></p><p>Pertanggungan ini akan berakhir dalam hal-hal sebagai berikut :&nbsp;</p><ol><li>Berakhirnya jangka waktu Polis.</li><li>Pembatalan Polis.</li><li>Jika Tertanggung meninggal dunia.</li><li>Jika Tertanggung dikenakan tahanan / hukuman penjara.</li><li>Tertanggung dan Penanggung sepakat untuk mengesampingkanketentuan Pasal 1266 Kitab Undang-Undang HukumPerdata Indonesia sejauh suatu penetapan pengadilandiperlukan untuk mengakhiri Polis ini.</li></ol><p>Ringkasan ini dirancang untuk menjelaskan Program Asuransitersebut diatas secara singkat. Penafsiran terhadap setiappersyaratan-persyaratan dan ketentuan-ketentuan mengenaiProgram Asuransi tersebut harus dilakukan berdasarkanpersyaratan-persyaratan dan ketentuan-ketentuan yangtercantum dalam Polis yang diterbitkan Penanggung.Polis dan form aplikasi asuransi hospital cash plan 5 diseases yangdisampaikan oleh Tertanggung kepada Penanggung merupakanbukti perjanjian asuransi yang sah dan bersifat sebagai satukesatuan serta mencakup seluruh perjanjian antara Tertanggungdengan Penanggung.</p><p><br></p><p><br></p><p>*) Penjelasan ini hanya memuat informasi umum mengenai produk Asuransi Hospital Cash Plan 5 Diseases PT Asuransi Adira Dinamikadan bukan merupakan kontrak/perjanjian asuransi. Rincian mengenai kondisi pertanggungan dan pengecualiannya dapat dilihat di dalam polis.<br></p>";
        deskripsi.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
