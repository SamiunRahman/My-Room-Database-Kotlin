package com.samiun.myroom

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samiun.myroom.data.Account
import com.samiun.myroom.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.InputStream


class AddFragment : Fragment() {

    private lateinit var viewModel: AccountViewModel
    private lateinit var uri : Uri
    var temp = 0

    val getArgs : AddFragmentArgs by navArgs()
    //val ima

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        val getAccount = getArgs.account
        if (getAccount != null) {
            firstname_et.setText(getAccount.firstName)
            lastname_et.setText(getAccount.lastName)
            phone_et.setText(getAccount.phoneNumber.toString())
            password_et.setText(getAccount.password.toString())

            add_button.setText("Update Account")


            add_button.setOnClickListener {
                updateUserIntoDatabase()
            }

        }
        else{

            add_button.setOnClickListener {
                val firstname = firstname_et.text.toString()
                val lastName = lastname_et.text.toString()
                val phone = phone_et.text.toString()




                val password = password_et.text.toString()
                if(!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(lastName) && temp>0){


                    val contentResolver = requireContext().contentResolver
                    val inputStream: InputStream? = contentResolver.openInputStream(uri)
                    val image: ByteArray = inputStream?.readBytes() ?: byteArrayOf()

                    val account = Account(phone.toInt(),image,password.toInt(),firstname,lastName,1000.0)
                    viewModel.addAccount(account)
                    Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT).show()
                    val action = AddFragmentDirections.actionAddFragmentToHomeFragment(account)
                    findNavController().navigate(action)
                }
            }


        }

        add_image.setOnClickListener{

            val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission, so prompt the user
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 23)
            }
            else {

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                startActivityForResult(intent,22)
                val uri = intent.data
                onActivityResult(22,Activity.RESULT_OK,intent)
            }
        }




    }

    private fun updateUserIntoDatabase() {
        val firstname = firstname_et.text.toString()
        val lastName = lastname_et.text.toString()
        val phone = phone_et.text.toString()



        val password = password_et.text.toString()
        if(!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(lastName)&& temp>0){

            val contentResolver = requireContext().contentResolver
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val image: ByteArray = inputStream?.readBytes() ?: byteArrayOf()
            val account = Account(phone.toInt(),image,password.toInt(),firstname,lastName, 1000.0)
            viewModel.updateUser(account)
            val action = AddFragmentDirections.actionAddFragmentToHomeFragment(account)
            findNavController().navigate(action)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 22 && resultCode == Activity.RESULT_OK) {
            temp++

            val imageUri: Uri? = data?.data
            add_image.setImageURI(imageUri)

            uri= data?.data ?: return
            Log.v("Add Fragment $temp", uri.toString())
            // Do something with the image Uri (e.g. open an input stream and read the image data)
        }
    }


}