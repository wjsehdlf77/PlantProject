package com.example.plantproject
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plantproject.databinding.ActivityRegisterBinding
var isExistBlank = false
var isPWSame = false
var isExist = false
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var localDB: LocalDB
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)    // 뷰 바인딩
        val view = binding.root
        setContentView(view)


        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION) // SQLite 모듈 생성

        binding.btnRegister.setOnClickListener { view ->
            if (binding.editId.text.isEmpty() || binding.editPw.text.isEmpty() || binding.editPwRe.text.isEmpty()) {// 값이 전부 입력되지 않은경우
                Toast.makeText(this, "값을 전부 입력해주세요..", Toast.LENGTH_LONG).show()
            } else {
                if (binding.editPw.text.toString()
                        .equals(binding.editPwRe.text.toString())
                ) {//패스워드/패스워드 확인이 일치
                    if (localDB.checkIdExist(binding.editId.text.toString())) {// 아이디 중복 확인
                        Toast.makeText(this, "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show()
                        isExist = false
                    } else {// 존재하는 아이디
                        localDB.registerUser(
                            binding.editId.text.toString(),
                            binding.editPw.text.toString()
                        )
                        isExist = true
                    }
                } else { // 패스워드/패스워드 확인이 일치하지 않음
                    Toast.makeText(this, "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show()
                }
            }
            if (binding.editId.text.isEmpty() || binding.editPw.text.isEmpty() || binding.editPwRe.text.isEmpty()) {
                isExistBlank = true
            } else {
                if (binding.editPw.text.toString() == binding.editPwRe.text.toString()) {
                    isPWSame = true
                }
            }
            if (!isExistBlank && isPWSame && isExist) {
                Toast.makeText(this, "회원가입에 성공하셨습니다..", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

