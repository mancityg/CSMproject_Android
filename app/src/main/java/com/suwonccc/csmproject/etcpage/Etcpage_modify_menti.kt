package com.suwonccc.csmproject.etcpage

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_modify_menti.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Etcpage_modify_menti : AppCompatActivity()  {
    lateinit var currentPhotoPath: String
    private val REQUEST_CAMERA = 0
    private val REQUEST_READ_EXTERMAL_STORAGE = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_modify_menti)

        //modify btn 눌렀을 때, 수정 가능하게 구현하기
        val name = "홍길동"
        val age = "20"
        val school = "성균관대학교"
        val major = "소프트웨어학과"
        val addr = "수원시"
        val email = "0000@gmail.com"
        val birth = "2000.01.01"
        val msg = "안녕하세요:)"

        edit_text1.setText(name)
        edit_text2.setText(age)
        edit_text3.setText(school)
        edit_text4.setText(major)
        edit_text5.setText(addr)
        edit_text6.setText(email)
        edit_text7.setText(birth)
        edit_text8.setText(msg)
        disabled()
        profile_camera_image.setClickable(false)

        save_btn.setVisibility(View.INVISIBLE)

        return_btn.setOnClickListener{
            this.finish()
        }
        modify_btn.setOnClickListener{
            enabled()
            save_btn.setVisibility(View.VISIBLE)
            modify_btn.setVisibility(View.INVISIBLE)
            profile_camera_image.setClickable(true)
        }

        save_btn.setOnClickListener{
            val name = edit_text1.getText().toString()
            disabled()
            save_btn.setVisibility(View.INVISIBLE)
            modify_btn.setVisibility(View.VISIBLE)
            profile_camera_image.setClickable(false)
        }

        //profile camera 구현하기
        profile_camera_image.setOnClickListener {
            Toast.makeText(this@Etcpage_modify_menti, "프로필 사진 변경 버튼 클릭", Toast.LENGTH_SHORT).show()

            /* 팝업 메뉴 생성 */
            var popUpMenu = PopupMenu(this@Etcpage_modify_menti, profile_camera_image)

            popUpMenu.menu.add(Menu.NONE, 0, 0, "앨범에서 사진 선택")
            popUpMenu.menu.add(Menu.NONE, 1, 1, "기본 이미지로 변경")
            popUpMenu.menu.add(Menu.NONE, 2, 2, "카메라 촬영하기")
            popUpMenu.setOnMenuItemClickListener { menuItem ->
                val id = menuItem.itemId

                if (id == 0) {
                    Toast.makeText(this@Etcpage_modify_menti, "옵션1 클릭", Toast.LENGTH_SHORT).show()
                    pickGallery()
                } else if (id == 1) {
                    Toast.makeText(this@Etcpage_modify_menti, "옵션2 클릭", Toast.LENGTH_SHORT).show()
                    changeToBasic()
                } else if (id == 2) {
                    Toast.makeText(this@Etcpage_modify_menti, "옵션3 클릭", Toast.LENGTH_SHORT).show()
                    captureCamera()
                }
                false
            }
            popUpMenu.show()
        }


    }
    fun enabled(){
        edit_text1.isEnabled = true
        edit_text1.setTextColor(Color.parseColor("#666666"))
        edit_text2.isEnabled = true
        edit_text2.setTextColor(Color.parseColor("#666666"))
        edit_text3.isEnabled = true
        edit_text3.setTextColor(Color.parseColor("#666666"))
        edit_text4.isEnabled = true
        edit_text4.setTextColor(Color.parseColor("#666666"))
        edit_text5.isEnabled = true
        edit_text5.setTextColor(Color.parseColor("#666666"))
        edit_text6.isEnabled = true
        edit_text6.setTextColor(Color.parseColor("#666666"))
        edit_text7.isEnabled = true
        edit_text7.setTextColor(Color.parseColor("#666666"))
        edit_text8.isEnabled = true
        edit_text8.setTextColor(Color.parseColor("#666666"))
    }

    fun disabled(){
        edit_text1.isEnabled = false
        edit_text1.setTextColor(Color.parseColor("#333333"))
        edit_text2.isEnabled = false
        edit_text2.setTextColor(Color.parseColor("#333333"))
        edit_text3.isEnabled = false
        edit_text3.setTextColor(Color.parseColor("#333333"))
        edit_text4.isEnabled = false
        edit_text4.setTextColor(Color.parseColor("#333333"))
        edit_text5.isEnabled = false
        edit_text5.setTextColor(Color.parseColor("#333333"))
        edit_text6.isEnabled = false
        edit_text6.setTextColor(Color.parseColor("#333333"))
        edit_text7.isEnabled = false
        edit_text7.setTextColor(Color.parseColor("#333333"))
        edit_text8.isEnabled = false
        edit_text8.setTextColor(Color.parseColor("#333333"))
    }

    /* 팝업창 옵션1 선택했을 때 */
    fun pickGallery() {
        /* 갤러리 권한 부여 */
        var permission= ContextCompat.checkSelfPermission(this@Etcpage_modify_menti, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@Etcpage_modify_menti, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(this@Etcpage_modify_menti, "프로필 사진을 설정하기 위해서는 갤러리 권한이 요구됩니다.", Toast.LENGTH_SHORT).show()
                // 권한 재요청
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERMAL_STORAGE)
            } else {
                // 권한 최초 요청
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERMAL_STORAGE)
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(this@Etcpage_modify_menti, "갤러리 권한 이미 소지, 갤러리 실행", Toast.LENGTH_SHORT).show()
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
        var permission= ContextCompat.checkSelfPermission(this@Etcpage_modify_menti, Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@Etcpage_modify_menti, Manifest.permission.CAMERA)) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(this@Etcpage_modify_menti, "프로필 사진을 설정하기 위해서는 카메라 권한이 요구됩니다.", Toast.LENGTH_SHORT).show()
                // 권한 재요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            } else {
                // 권한 최초 요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(this@Etcpage_modify_menti, "카메라 권한 이미 소지, 카메라 실행", Toast.LENGTH_SHORT).show()
            dispatchTakePictureIntent()
        }
    }

    /* 권한 요청 함수 */
    @SuppressLint("MissingSuperCall")
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
                Toast.makeText(this@Etcpage_modify_menti, "grantResults", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@Etcpage_modify_menti, "권한 예외 처리", Toast.LENGTH_SHORT).show()
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
            takePictureIntent.resolveActivity(this@Etcpage_modify_menti.packageManager)?.also {
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
                        this@Etcpage_modify_menti,
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
        val storageDir: File = this@Etcpage_modify_menti.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
            this@Etcpage_modify_menti.sendBroadcast(mediaScanIntent)

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

    /* 갤러리에서 이미지 가져오기 */
    private fun dispatchPickGalleryIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_READ_EXTERMAL_STORAGE)
    }

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
            Toast.makeText(this@Etcpage_modify_menti, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
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
        super.onActivityResult(requestCode, resultCode, data)
    }
}