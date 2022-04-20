package com.guko.assigment_8.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.guko.assigment_8.R
import com.guko.assigment_8.crud.User
import com.guko.assigment_8.data.Client
import com.guko.assigment_8.databinding.FragmentUpdateBinding
import com.guko.assigment_8.retrofit.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFragment : Fragment() {
    private lateinit var binding : FragmentUpdateBinding
    private var username = ""
    private var email = ""
    private var password = ""
    private var gender = "male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.btnUpdateUser.setOnClickListener {
            validate()
        }
        return binding.root
    }
    private fun updateUser(username: String, email: String, gender: String){
        val username = binding.etUpdateUsername.text.toString()
        val email = Client(requireContext()).getUserEmail().toString()
        val gender = binding.etUpdateGender.text.toString()

        NetworkHelper().userService?.updateUser(username,email,gender)
            ?.enqueue(object : Callback<com.guko.assigment_8.crud.User>{
                override fun onResponse(
                    call: Call<com.guko.assigment_8.crud.User>,
                    response: Response<com.guko.assigment_8.crud.User>
                ) {
                    Toast.makeText(requireContext(),"Successfully Updated.",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
                }

                override fun onFailure(
                    call: Call<com.guko.assigment_8.crud.User>,
                    t: Throwable
                ) {
                    Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun validate(){
        username = binding.etUpdateUsername.text.toString()
        email = binding.etUpdateEmail.text.toString()


        if(TextUtils.isEmpty(username)){
            binding.etUpdateUsername.error = "Enter name"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etUpdateEmail.error = "Invalid email format"
        } else {
            updateUser(username,email,gender)
        }


    }
    private fun deleteUser(){
        val email = Client(requireContext()).getUserEmail().toString()

        NetworkHelper().userService?.deleteUser(email)
            ?.enqueue(object : Callback<com.guko.assigment_8.crud.User>{
                override fun onResponse(
                    call: Call<com.guko.assigment_8.crud.User>,
                    response: Response<com.guko.assigment_8.crud.User>
                ) {
                    Toast.makeText(requireContext(),"Account Deleted Successfully",Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(
                    call: Call<com.guko.assigment_8.crud.User>,
                    t: Throwable
                ) {
                    Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
                    Log.e("ERRORRRRR",t.message.toString())
                }

            })
    }


}