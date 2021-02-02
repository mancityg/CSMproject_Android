package com.suwonccc.csmproject.firstpage_fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.fragment_login_profile.*
import kotlinx.android.synthetic.main.fragment_login_profile_change.view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LoginProfile : Fragment() {

    lateinit var navController: NavController
    lateinit var currentPhotoPath: String
    private val REQUEST_CAMERA = 0
    private val REQUEST_READ_EXTERMAL_STORAGE = 1
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        /* 프로필 사진 변경 버튼 클릭했을 때 */
        profile_camera_image.setOnClickListener {
            val btnsheet = layoutInflater.inflate(R.layout.fragment_login_profile_change, null)
            val dialog = BottomSheetDialog(this.requireContext())
            dialog.setContentView(btnsheet)

            btnsheet.profile_option1_btn.setOnClickListener {
                pickGallery()
                dialog.dismiss()
            }
            btnsheet.profile_option2_btn.setOnClickListener {
                changeToBasic()
                dialog.dismiss()
            }
            btnsheet.profile_option3_btn.setOnClickListener {
                captureCamera()
                dialog.dismiss()
            }
            dialog.show()
        }





        /* 성별 및 멘토, 멘티 버튼 클릭 */
        male_btn.setOnClickListener {
            Toast.makeText(getActivity(), "남자 클릭", Toast.LENGTH_SHORT).show()
            male_btn.isSelected = true
            female_btn.isSelected = false
            female_btn.isPressed = false
        }

        female_btn.setOnClickListener {
            Toast.makeText(getActivity(), "여자 클릭", Toast.LENGTH_SHORT).show()
            female_btn.isSelected = true
            male_btn.isSelected = false
            male_btn.isPressed = false
        }

        mentor_btn.setOnClickListener {
            Toast.makeText(getActivity(), "멘토 클릭", Toast.LENGTH_SHORT).show()
            mentor_btn.isSelected = true
            mentee_btn.isSelected = false
            mentee_btn.isPressed = false
        }

        mentee_btn.setOnClickListener {
            Toast.makeText(getActivity(), "멘티 클릭", Toast.LENGTH_SHORT).show()
            mentee_btn.isSelected = true
            mentor_btn.isSelected = false
            mentor_btn.isPressed = false
        }





        /* 입력창 공백 체크 */
        next_btn.setOnClickListener {
            next_btn.isSelected = true

            if (TextUtils.isEmpty(name_text.text) ||
                TextUtils.isEmpty(address_text.text) ||
                TextUtils.isEmpty(email_text.text) ||
                (!male_btn.isSelected && !female_btn.isSelected) ||
                (!mentor_btn.isSelected && !mentee_btn.isSelected)
            ) {
                Toast.makeText(getActivity(), "선택하지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getActivity(), "모든 항목 선택 완료", Toast.LENGTH_SHORT).show()
                if (mentor_btn.isSelected) {
                    navController.navigate(R.id.action_loginProfile_to_loginMentor)
                } else {
                    navController.navigate(R.id.action_loginProfile_to_loginMentee)
                }

            }
        }
    } //onViewCreated





    /* 팝업창 옵션1 선택했을 때 */
    fun pickGallery() {
        /* 갤러리 권한 부여 */
        var permission=ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(requireActivity(), "프로필 사진을 설정하기 위해서는 갤러리 권한이 요구됩니다.", Toast.LENGTH_SHORT).show()
                // 권한 재요청
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERMAL_STORAGE)
            } else {
                // 권한 최초 요청
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERMAL_STORAGE)
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(getActivity(), "갤러리 권한 이미 소지, 갤러리 실행", Toast.LENGTH_SHORT).show()
            dispatchPickGalleryIntent()
        }
    }





    /* 팝업창 옵션2 선택했을 때 */
    fun changeToBasic() {
        profile_image.setImageResource(R.drawable.profile_basic)
    }





    /* 팝업창 옵션3 선택했을 때 */
    fun captureCamera() {
        /* 카메라 권한 부여 */
        var permission=ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(requireActivity(), "프로필 사진을 설정하기 위해서는 카메라 권한이 요구됩니다.", Toast.LENGTH_SHORT).show()
                // 권한 재요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            } else {
                // 권한 최초 요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(getActivity(), "카메라 권한 이미 소지, 카메라 실행", Toast.LENGTH_SHORT).show()
            dispatchTakePictureIntent()
        }
    }





    /* 권한 요청 함수 */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 사용자가 카메라 권한 동의
                    dispatchTakePictureIntent()
                } else {
                    // 사용자가 카메라 권한 거절
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            REQUEST_READ_EXTERMAL_STORAGE -> {
                Toast.makeText(requireActivity(), "grantResults", Toast.LENGTH_SHORT).show()
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 사용자가 갤러리 권한 동의
                    dispatchPickGalleryIntent()
                } else {
                    // 사용자가 갤러리 권한 거절
                }
            }

            else -> {
                // Ignore all other requests.
                Toast.makeText(requireActivity(), "권한 예외 처리", Toast.LENGTH_SHORT).show()
            }
        }
    }




    /* 촬영된 이미지 가져오기 */
    private fun dispatchTakePictureIntent() {
        // 저장하지 않고 미리보기만 하고 싶을 때
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_CAMERA)
//            }
//        }
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.suwonccc.csmproject",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_CAMERA)
                }
            }
        }
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            // 카메라로 찍은 사진을 갤러리에 저장
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            requireActivity().sendBroadcast(mediaScanIntent)

            // 카메라로 찍은 사진이 -90도 회전되는 오류 수정
            val imagePath: String = Uri.fromFile(f).getPath()
            var image: Bitmap = BitmapFactory.decodeFile(imagePath)
            val exif = ExifInterface(imagePath)
            val exifOrientation: Int = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            val exifDegree: Int = exifOrientationToDegrees(exifOrientation)
            val matrix = Matrix()
            if (exifOrientation != 0) {
                matrix.preRotate(exifDegree.toFloat())
            }
            var rotatedImage = Bitmap.createBitmap(image, 0, 0, image.width, image.height, matrix, true)

            // 카메라로 찍은 사진이 프로필 사진으로 업로드
            profile_image.setImageBitmap(rotatedImage)
        }
    }

    private fun exifOrientationToDegrees(exifOrientation: Int): Int {
        when (exifOrientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                return 90
            }

            ExifInterface.ORIENTATION_ROTATE_180 -> {
                return 180
            }

            ExifInterface.ORIENTATION_ROTATE_270 -> {
                return 270
            }

            else -> {
                return 0
            }
        }
    }





    /* 대상 뷰의 크기에 맞게 조정한 JPEG를 메모리 배열로 확장하여 동적 힙 크기 축소 */
    /* 메모리 문제가 생기면 추가(일단 보류) */
//    private fun setPic() {
//        // Get the dimensions of the View
//        val targetW: Int = profile_image.width
//        val targetH: Int = profile_image.height
//
//        val bmOptions = BitmapFactory.Options().apply {
//            // Get the dimensions of the bitmap
//            inJustDecodeBounds = true
//
//            val photoW: Int = outWidth
//            val photoH: Int = outHeight
//
//            // Determine how much to scale down the image
//            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)
//
//            // Decode the image file into a Bitmap sized to fill the View
//            inJustDecodeBounds = false
//            inSampleSize = scaleFactor
//            inPurgeable = true
//        }
//        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
//            profile_image.setImageBitmap(bitmap)
//        }
//    }





    /* 갤러리에서 이미지 가져오기 */
    private fun dispatchPickGalleryIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_READ_EXTERMAL_STORAGE)
    }





    /* 접근 처리 함수 */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            // 카메라 접근 처리
            galleryAddPic()
        } else if (requestCode == REQUEST_READ_EXTERMAL_STORAGE && resultCode == RESULT_OK) {
            // 갤러리 접근 처리
            imageUri = data?.data
            profile_image.setImageURI(imageUri)
        } else if (resultCode != RESULT_OK) {
            // 카메라로 촬영한 후 저장하지 않고 뒤로 가기를 한 경우 & 갤러리로 이동한 후 선택하지 않고 뒤로 가기를 한 경우
            Toast.makeText(requireActivity(), "취소 되었습니다.", Toast.LENGTH_SHORT).show()
            /* 임시 파일 삭제 해야함(일단 보류) */
//            if (tempFile != null) {
//                if (tempFile.exists()) {
//                    if (tempFile.delete()) {
//                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
//                        tempFile = null;
//                    }
//                }
//            }
        }
    }
}