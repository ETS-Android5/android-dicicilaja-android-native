package com.dicicilaja.app.Activity.Addon

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import com.dicicilaja.app.API.Client.RetrofitClient
import com.dicicilaja.app.API.Interface.InterfacePengajuanAxi
import com.dicicilaja.app.API.Item.AreaRequest.CompleteDataUpdate
import com.dicicilaja.app.Activity.*
import com.dicicilaja.app.R
import com.dicicilaja.app.Remote.ApiUtils
import com.dicicilaja.app.Remote.UserService
import com.dicicilaja.app.Session.SessionManager
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_complete_phone_email.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompletePhoneEmailActivity : AppCompatActivity() {

    var inUserId: String? = null
    var inPhone: String? = null
    var inEmail: String? = null
    var inRole: String? = null

//    var loader: ProgressBar? = null
    val loader = findViewById<ProgressBar>(R.id.progressBar)
    var phoneUpdate: Boolean = true
    var emailUpdate: Boolean = true

    internal lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_phone_email)

        session = SessionManager(this)

        toolbar.title = "Lengkapi Data"



        cardCompletePhone.visibility = View.GONE
        cardCompleteEmail.visibility = View.GONE

        // Get instance variable
        inUserId = intent.getStringExtra("USER_ID")
        inPhone = intent.getStringExtra("USER_PHONE")
        inEmail = intent.getStringExtra("USER_EMAIL")
        inRole = intent.getStringExtra("USER_ROLE")

        // Show if phone blank or empty
        if( inPhone.isNullOrBlank() || inPhone.isNullOrEmpty() ) {
            cardCompletePhone.visibility = View.VISIBLE
            phoneUpdate = false
        }

        // Show if email blank or empty
        if( inEmail.isNullOrBlank() || inEmail.isNullOrEmpty() ) {
            cardCompleteEmail.visibility = View.VISIBLE
            emailUpdate = false
        }

        // Set event listener
        btnSavePhone.setOnClickListener { savePhone() }
        btnSaveEmail.setOnClickListener { saveEmail() }
    }

    private fun savePhone() {
        if( validatePhoneNumber(inputPhone.text.toString()) ) {
            showLoader()

            val userService = RetrofitClient.getClient().create(UserService::class.java)
            val call: Call<CompleteDataUpdate> = userService.updatePhoneNumber(inUserId, inputPhone.text.toString())

            call.enqueue(object: Callback<CompleteDataUpdate> {
                override fun onFailure(call: Call<CompleteDataUpdate>, t: Throwable) {
                    hideLoader()

                    showFailed(1)
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<CompleteDataUpdate>, response: Response<CompleteDataUpdate>) {
                    hideLoader()

                    showSuccessPhone()
                }

            })
        } else {
            val alert: AlertDialog.Builder = AlertDialog.Builder(this).setCancelable(false)
            alert.setMessage("Harap isikan nomor telepon yang valid")
            alert.setPositiveButton("OK", object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    inputPhone.requestFocus()
                }
            })
            alert.show()
        }
    }

    private fun saveEmail() {
        if( validateEmail(inputEmail.text.toString()) ) {
            showLoader()

            val userService = RetrofitClient.getClient().create(UserService::class.java)
            val call: Call<CompleteDataUpdate> = userService.updateEmail(inUserId, inputEmail.text.toString())

            call.enqueue(object: Callback<CompleteDataUpdate> {
                override fun onFailure(call: Call<CompleteDataUpdate>, t: Throwable) {
                    hideLoader()

                    showFailed(2)
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<CompleteDataUpdate>, response: Response<CompleteDataUpdate>) {
                    hideLoader()

                    showSuccessEmail()
                }

            })
        } else {
            val alert: AlertDialog.Builder = AlertDialog.Builder(this).setCancelable(false)
            alert.setMessage("Harap isikan alamat email yang valid")
            alert.setPositiveButton("OK", object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    inputEmail.requestFocus()
                }
            })
            alert.show()
        }
    }

    private fun showSuccessPhone() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this).setCancelable(false)
        alert.setMessage("Nomor handphone berhasil disimpan")
        alert.setPositiveButton("OK", object:DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                session.phone = inputPhone.text.toString()
                txtPhone.text = inputPhone.text

                layoutSuccessPhone.visibility = View.VISIBLE
                inputPhone.visibility = View.GONE
                btnSavePhone.visibility = View.GONE

                phoneUpdate = true

                showNextActivity()
            }
        })
        alert.show()
    }

    private fun showSuccessEmail() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this).setCancelable(false)
        alert.setMessage("Email berhasil disimpan")
        alert.setPositiveButton("OK", object:DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                session.email = inputEmail.text.toString()
                txtEmail.text = inputEmail.text

                layoutSuccessEmail.visibility = View.VISIBLE
                inputEmail.visibility = View.GONE
                btnSaveEmail.visibility = View.GONE

                emailUpdate = true

                showNextActivity()
            }
        })
        alert.show()
    }

    private fun showFailed(type: Int) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this).setCancelable(false)
        alert.setMessage("Gagal menyimpan data, silahkan coba lagi")
        alert.setNegativeButton("Batal", object:DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })
        alert.setPositiveButton("Coba Lagi", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (dialog != null) {
                    dialog.dismiss()
                }
                if( type.equals(1) )
                    savePhone()
                else
                    saveEmail()
            }
        })

        alert.show()
    }

    private fun showLoader() {
        loader.visibility = View.VISIBLE;
    }

    private fun hideLoader() {
        loader.visibility = View.GONE;
    }

    private fun showNextActivity() {
        if( phoneUpdate && emailUpdate ) {
//            var role: String? = inRole?.toLowerCase()
            when( inRole ) {
                "axi" -> intent = Intent(baseContext, AxiDashboardActivity::class.java)
                "channel" -> intent = Intent(baseContext, MaxiDashboardActivity::class.java)
                "crh" -> intent = Intent(baseContext, EmployeeDashboardActivity::class.java)
                "cro" -> intent = Intent(baseContext, EmployeeDashboardActivity::class.java)
                "tc" -> intent = Intent(baseContext, EmployeeDashboardActivity::class.java)
                "admin" -> intent = Intent(baseContext, EmployeeDashboardActivity::class.java)
                "spg" -> intent = Intent(baseContext, SPGDashboardActivity::class.java)
                "bm" -> intent = Intent(baseContext, SPGDashboardActivity::class.java)
                "mm" -> intent = Intent(baseContext, SPGDashboardActivity::class.java)
                "ho" -> intent = Intent(baseContext, SPGDashboardActivity::class.java)
                "basic" -> intent = Intent(baseContext, MarketplaceActivity::class.java)
                else -> intent = Intent(baseContext, MarketplaceActivity::class.java)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun validatePhoneNumber(txt: String?): Boolean {
        var ret = false
        if( !txt.isNullOrEmpty() || !txt.isNullOrBlank() ) {
            return Patterns.PHONE.matcher(txt).matches()
        }
        return ret
    }

    private fun validateEmail(txt: String?): Boolean {
        var ret = false
        if( !txt.isNullOrEmpty() || !txt.isNullOrBlank() ) {
            return Patterns.EMAIL_ADDRESS.matcher(txt).matches()
        }

        return ret
    }
}
