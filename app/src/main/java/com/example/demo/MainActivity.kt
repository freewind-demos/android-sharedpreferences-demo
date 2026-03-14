package com.example.demo

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // SharedPreferences 文件名
    private val PREFS_NAME = "my_prefs"

    // Key 常量
    private val KEY_NAME = "name"
    private val KEY_AGE = "age"
    private val KEY_ENABLED = "enabled"

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var textViewResult: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取 SharedPreferences 实例
        // MODE_PRIVATE 表示只有本应用可以访问
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        textViewResult = findViewById(R.id.textViewResult)

        // 加载保存的数据并显示
        loadData()

        // 保存按钮点击事件
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            saveData()
        }

        // 读取按钮点击事件
        findViewById<Button>(R.id.buttonLoad).setOnClickListener {
            loadData()
        }

        // 清除按钮点击事件
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            clearData()
        }
    }

    // 保存数据
    private fun saveData() {
        val name = editTextName.text.toString()
        val age = editTextAge.text.toString().toIntOrNull() ?: 0

        // 使用 SharedPreferences.Editor 进行写入
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.putInt(KEY_AGE, age)
        editor.putBoolean(KEY_ENABLED, true)

        // apply 是异步保存，不返回结果
        // commit 是同步保存，返回是否成功
        editor.apply()

        textViewResult.text = "数据已保存!\n姓名: $name\n年龄: $age"
    }

    // 读取数据
    private fun loadData() {
        // 使用默认值读取，如果 key 不存在返回默认值
        val name = sharedPreferences.getString(KEY_NAME, "未设置") ?: "未设置"
        val age = sharedPreferences.getInt(KEY_AGE, 0)
        val enabled = sharedPreferences.getBoolean(KEY_ENABLED, false)

        editTextName.setText(name)
        editTextAge.setText(age.toString())

        textViewResult.text = "读取到的数据:\n姓名: $name\n年龄: $age\n启用: $enabled"
    }

    // 清除数据
    private fun clearData() {
        val editor = sharedPreferences.edit()
        editor.clear()  // 清除所有数据
        editor.apply()

        editTextName.setText("")
        editTextAge.setText("")
        textViewResult.text = "数据已清除"
    }
}
