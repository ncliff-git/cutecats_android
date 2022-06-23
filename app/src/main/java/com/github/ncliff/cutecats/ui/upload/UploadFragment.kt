package com.github.ncliff.cutecats.ui.upload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.github.ncliff.cutecats.databinding.FragmentUploadBinding
import com.github.ncliff.cutecats.ui.viewmodel.SharedCatApiViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class UploadFragment : Fragment() {
    private lateinit var _binding: FragmentUploadBinding
    private var imageUri: Uri? = null
    private val catViewModel: SharedCatApiViewModel by lazy {
        ViewModelProvider(this).get(
            SharedCatApiViewModel::class.java
        )
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.also { selectedImage ->
                    imageUri = selectedImage
                    setImageUi()
                }
            }
        }

    private fun getRealPathFromUri(context: Context, contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
        cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)?.also {
            cursor.moveToFirst()
            val name = cursor.getString(it)
            cursor.close()
            return name
        }
        return ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        pickImageFromCard()
        deleteImage()
        uploadImage()
        return _binding.root
    }

    private fun uploadImage() = with(_binding) {
        btnUpload.setOnClickListener {
            imageUri?.also { path ->
                val file = File(getRealPathFromUri(requireContext(), path))
                val builder = MultipartBody.Builder()
                builder.setType(MultipartBody.FORM)
                builder.addFormDataPart("title", "title_${file.name}")
                builder.addFormDataPart("description", "description_${file.name}")
                builder.addFormDataPart("file", file.name, file.asRequestBody("multipart/form-data".toMediaTypeOrNull()))
                Log.d("FileName", "file: ${file.name}")
                val requestBody = builder.build()
                catViewModel.postCatUpload(requestBody) {}
            }
            resetUi()
        }
    }

    private fun deleteImage() = with(_binding) {
        btnUploadDel.setOnClickListener {
            resetUi()
        }
    }

    private fun setImageUi() = with(_binding) {
        ivUpload.load(imageUri)
        clUpload.isVisible = false
        btnUpload.isVisible = true
        btnUploadDel.isVisible = true
    }

    private fun resetUi() = with(_binding) {
        imageUri = null
        ivUpload.load("")
        clUpload.isVisible = true
        btnUpload.visibility = View.GONE
        btnUploadDel.visibility = View.GONE
    }

    private fun pickImageFromCard() {
        _binding.clUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getContent.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imageUri = null
    }
}