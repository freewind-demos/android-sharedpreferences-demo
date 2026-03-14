# android-sharedpreferences-demo

## 简介

本 demo 展示 Android 中 SharedPreferences 的基本用法，演示如何存储和读取简单的键值对数据。

## 基本原理

SharedPreferences 是 Android 提供的轻量级存储机制，用于保存简单的键值对数据。它本质上是 XML 文件，存储在应用的私有目录中。

主要特点：
- 适合存储少量数据，如用户设置、登录状态等
- 数据以 XML 格式存储在应用的私有目录
- 支持多种数据类型：String、int、long、float、boolean、Set<String>
- 是同步操作，不适合存储大量数据

## 启动和使用

### 环境要求
- Android Studio 3.0+
- JDK 1.8+
- Android SDK 28

### 安装和运行
1. 用 Android Studio 打开此项目
2. 连接 Android 设备或启动模拟器
3. 点击 Run 运行项目

## 教程

### 什么是 SharedPreferences？

SharedPreferences 是 Android 提供的用于存储少量数据的机制。它是一种轻量级的键值对存储方式，非常适合保存用户设置、应用状态等小数据。

### 获取 SharedPreferences 实例

```kotlin
// 方法1：指定文件名
val prefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

// 方法2：使用默认偏好设置（活动级别）
val prefs = PreferenceManager.getDefaultSharedPreferences(this)
```

MODE_PRIVATE 表示只有当前应用可以访问这些数据。

### 存储数据

```kotlin
val editor = sharedPreferences.edit()
editor.putString("name", "张三")
editor.putInt("age", 25)
editor.putBoolean("enabled", true)

// apply() - 异步保存，不返回结果（推荐）
editor.apply()

// commit() - 同步保存，返回是否成功
val success = editor.commit()
```

### 读取数据

```kotlin
val name = sharedPreferences.getString("name", "默认值")
val age = sharedPreferences.getInt("age", 0)
val enabled = sharedPreferences.getBoolean("enabled", false)
```

读取时需要提供默认值，如果 key 不存在则返回默认值。

### 清除数据

```kotlin
val editor = sharedPreferences.edit()
editor.clear()
editor.apply()
```

### 常用方法

| 方法 | 说明 |
|------|------|
| putString(key, value) | 存储字符串 |
| putInt(key, value) | 存储整数 |
| putLong(key, value) | 存储长整数 |
| putFloat(key, value) | 存储浮点数 |
| putBoolean(key, value) | 存储布尔值 |
| putStringSet(key, value) | 存储字符串集合 |
| getString(key, default) | 读取字符串 |
| getInt(key, default) | 读取整数 |
| clear() | 清除所有数据 |
| remove(key) | 移除指定 key |

### 注意事项

1. **不要存储敏感信息**：SharedPreferences 数据虽然只有本应用能访问，但 ROOT 设备可以读取
2. **不要存储大量数据**：适合存储少量配置数据，大量数据请使用数据库
3. **apply vs commit**：apply 是异步的，不阻塞主线程；commit 是同步的，会阻塞主线程
4. **多进程访问**：SharedPreferences 不支持多进程访问，需要使用 ContentProvider 或其他机制
