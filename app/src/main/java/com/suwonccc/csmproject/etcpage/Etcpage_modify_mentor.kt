package com.suwonccc.csmproject.etcpage

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.suwonccc.csmproject.R
import kotlinx.android.synthetic.main.activity_etcpage_modify_mentor.*
import kotlinx.android.synthetic.main.fragment_login_profile_change_dialog.view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Etcpage_modify_mentor : AppCompatActivity()  {
    lateinit var currentPhotoPath: String
    private val REQUEST_CAMERA = 0
    private val REQUEST_READ_EXTERMAL_STORAGE = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etcpage_modify_mentor)

        //modify btn 눌렀을 때, 수정 가능하게 구현하기
        val name = "홍길동"
        val age = "20"
        val school = "성균관대학교"
        val major = "소프트웨어학과"
        val addr = "수원시"
        val email = "0000@gmail.com"
        val birth = "2000년 1월 1일"
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

        return_btn.setOnClickListener {
            this.finish()
        }

        /* 생년월일 선택했을 때 */
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                edit_text7.text = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            }, year, month, day
        )

        val birthday_datepicker = findViewById<View>(R.id.edit_text7) as TextView

        birthday_datepicker.setOnClickListener {
            if (birthday_datepicker.isClickable) {
                datePicker.show()
            }
        }

        modify_btn.setOnClickListener {
            modify_btn.isSelected = true
            Toast.makeText(this, "수정 가능합니다", Toast.LENGTH_SHORT).show()
            enabled()

            val handler = Handler()
            handler.postDelayed(Runnable {
                save_btn.setVisibility(View.VISIBLE)
                modify_btn.setVisibility(View.INVISIBLE)
                profile_camera_image.setClickable(true)
            }, 1000)
        }

        save_btn.setOnClickListener {
            val name = edit_text1.getText().toString()
            save_btn.isSelected = true
            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
            disabled()

            val handler = Handler()
            handler.postDelayed(Runnable {
                save_btn.setVisibility(View.INVISIBLE)
                modify_btn.setVisibility(View.VISIBLE)
                profile_camera_image.setClickable(false)
            }, 1000)
        }

        //profile camera 구현하기
        profile_camera_image.setOnClickListener {
            val btnsheet_camera =
                layoutInflater.inflate(R.layout.fragment_login_profile_change_dialog, null)
            val cameraPopupWindow = PopupWindow(btnsheet_camera, 550, 450)
            cameraPopupWindow.setOutsideTouchable(true)
            cameraPopupWindow.setFocusable(true)
            cameraPopupWindow.showAsDropDown(profile_camera_image)

            val container = cameraPopupWindow.contentView.rootView
            val context = cameraPopupWindow.contentView.context
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val p = container.layoutParams as WindowManager.LayoutParams
            p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            p.dimAmount = 0.3f
            wm.updateViewLayout(container, p)

            btnsheet_camera.profile_option1_btn.setOnClickListener {
                pickGallery()
                cameraPopupWindow.dismiss()
            }
            btnsheet_camera.profile_option2_btn.setOnClickListener {
                changeToBasic()
                cameraPopupWindow.dismiss()
            }
            btnsheet_camera.profile_option3_btn.setOnClickListener {
                captureCamera()
                cameraPopupWindow.dismiss()
            }
        }
    }

    /* 바탕화면 클릭 시 키보드 사라지게 */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass
                .getName().startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x: Float = ev.rawX + view.getLeft() - scrcoords[0]
            val y: Float = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }

    fun enabled() {
        edit_text1.isEnabled = true
        edit_text1.setTextColor(Color.parseColor("#777777"))
        edit_text2.isEnabled = true
        edit_text2.setTextColor(Color.parseColor("#777777"))
        edit_text3.isEnabled = true
        edit_text3.setTextColor(Color.parseColor("#777777"))
        edit_text4.isEnabled = true
        edit_text4.setTextColor(Color.parseColor("#777777"))
        edit_text5.isEnabled = true
        edit_text5.setTextColor(Color.parseColor("#777777"))
        edit_text6.isEnabled = true
        edit_text6.setTextColor(Color.parseColor("#777777"))
        edit_text7.isEnabled = true
        edit_text7.isClickable = true
        edit_text7.setTextColor(Color.parseColor("#777777"))
        edit_text8.isEnabled = true
        edit_text8.setTextColor(Color.parseColor("#777777"))

        val sv: ScrollView = findViewById<View>(R.id.ScrollView1) as ScrollView
        sv.post(Runnable { sv.fullScroll(ScrollView.FOCUS_UP) })

        modify_btn.isSelected = false
    }

    fun disabled() {
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
        edit_text7.isClickable = false
        edit_text7.setTextColor(Color.parseColor("#333333"))
        edit_text8.isEnabled = false
        edit_text8.setTextColor(Color.parseColor("#333333"))

        val sv: ScrollView = findViewById<View>(R.id.ScrollView1) as ScrollView
        sv.post(Runnable { sv.fullScroll(ScrollView.FOCUS_UP) })

        save_btn.isSelected = false
    }

    /* 팝업창 옵션1 선택했을 때 */
    fun pickGallery() {
        /* 갤러리 권한 부여 */
        var permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(
                    this,
                    "프로필 사진을 설정하기 위해서는 갤러리 권한이 요구됩니다.",
                    Toast.LENGTH_SHORT
                ).show()
                // 권한 재요청
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE
                )
            } else {
                // 권한 최초 요청
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE
                )
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(this, "갤러리 권한 이미 소지, 갤러리 실행", Toast.LENGTH_SHORT)
                .show()
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
        var permission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permission == PackageManager.PERMISSION_DENIED) {
            // 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                // 사용자가 이전에 권한을 실수로 취소시킨 경우
                Toast.makeText(
                    this,
                    "프로필 사진을 설정하기 위해서는 카메라 권한이 요구됩니다.",
                    Toast.LENGTH_SHORT
                ).show()
                // 권한 재요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            } else {
                // 권한 최초 요청
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
            }
        } else {
            // 권한이 있을 경우
            Toast.makeText(this, "카메라 권한 이미 소지, 카메라 실행", Toast.LENGTH_SHORT)
                .show()
            dispatchTakePictureIntent()
        }
    }

    /* 권한 요청 함수 */
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
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
                Toast.makeText(this, "grantResults", Toast.LENGTH_SHORT).show()
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // 사용자가 갤러리 권한 동의
                    dispatchPickGalleryIntent()
                } else {
                    // 사용자가 갤러리 권한 거절
                }
            }

            else -> {
                // Ignore all other requests.
                Toast.makeText(this, "권한 예외 처리", Toast.LENGTH_SHORT).show()
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
            takePictureIntent.resolveActivity(this.packageManager)?.also {
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
                        this,
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
        val storageDir: File =
            this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
            this.sendBroadcast(mediaScanIntent)

            // 카메라로 찍은 사진이 -90도 회전되는 오류 수정
            val imagePath: String = Uri.fromFile(f).getPath()
            var image: Bitmap = BitmapFactory.decodeFile(imagePath)
            val exif = ExifInterface(imagePath)
            val exifOrientation: Int = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            val exifDegree: Int = exifOrientationToDegrees(exifOrientation)
            val matrix = Matrix()
            if (exifOrientation != 0) {
                matrix.preRotate(exifDegree.toFloat())
            }
            var rotatedImage =
                Bitmap.createBitmap(image, 0, 0, image.width, image.height, matrix, true)

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
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
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



