# 🕌 TaubatKu - Aplikasi Pengingat Sholat Islami

## 📱 Tentang Aplikasi

TaubatKu adalah aplikasi pengingat sholat Islami yang komprehensif, dikembangkan sebagai bagian dari proyek akhir mata kuliah Pemrograman Bergerak (PBM). Aplikasi ini hadir sebagai sahabat spiritual Anda, membantu menjaga kedisiplinan ibadah harian dan memperkuat perjalanan spiritual Anda.

## ✨ Fitur Unggulan

### 🕌 Jadwal Sholat

- Pembaruan waktu sholat secara real-time ⏰
- Jadwal sholat berdasarkan lokasi terkini 📍
- Penanda dinamis untuk waktu sholat yang sedang berlangsung
- Tampilan kalender jadwal sholat bulanan 📅

### 📖 Konten Islami

- Hadits harian untuk inspirasi spiritual 💫
- Kalimatullah sebagai renungan harian 🤲
- Pengingat-pengingat Islami yang bermakna ✨

### 📝 Jurnal Pribadi

- Fitur jurnal Islami untuk refleksi diri 💭
- Catatan renungan harian yang personal
- Dokumentasi perjalanan spiritual Anda 📔

### ⚙️ Fitur Tambahan

- Autentikasi pengguna dengan Firebase Authentication 🔐
- Layanan berbasis lokasi yang akurat 🌍
- Pengaturan yang dapat disesuaikan 🛠️
- Desain antarmuka bergradien yang memukau 🎨

## 🔧 Detail Teknis

### Dibangun Dengan

- **Native Android** (Kotlin) - Performa optimal dan native experience
- **Firebase Authentication** - Keamanan login yang terpercaya 🔒
- **Firebase Firestore** - Database cloud yang responsif ☁️
- **Material Design Components** - UI/UX yang modern dan intuitif 🎯
- **Prayer Times API** - Data waktu sholat yang akurat 🕐
- **Google Location Services** - Deteksi lokasi yang presisi 📲

### Arsitektur Aplikasi

- **MVVM Pattern** (Model-View-ViewModel) - Struktur kode yang terorganisir 🏗️
- **Repository Pattern** - Manajemen data yang efisien 📊
- **LiveData** - Update UI yang reaktif dan responsif 🔄
- **Coroutines** - Operasi asinkron yang smooth ⚡

### Persyaratan Sistem

- Android 6.0 (API level 23) atau lebih tinggi 📱
- Google Play Services terinstall ✅
- Koneksi internet aktif 🌐
- Layanan lokasi diaktifkan 📍

## 🧪 Testing Otomatis & Quality Assurance

### Framework Testing yang Digunakan

Implementasi testing otomatis menggunakan teknologi terdepan:

- **Appium** - Framework testing mobile yang powerful 🚀
- **TestNG** - Framework testing yang comprehensive 🎯
- **Java** - Bahasa pemrograman yang robust ☕
- **Selenium WebDriver** - Automasi yang handal 🤖
- **UIAutomator2** - Testing Android native yang precise ⚡

### Persyaratan Environment Testing

- **JDK 8** atau versi lebih tinggi ☕
- **Appium Server** - Engine testing mobile 🔧
- **Android SDK** - Development toolkit Android 📱
- **Maven** - Build automation tool 🛠️
- **Android Emulator** atau perangkat fisik 📲

### Struktur Testing Komprehensif

Test suite (`Base.java`) mencakup skenario testing menyeluruh:

#### 1. **Onboarding & Flow Registrasi** 🚪

- Verifikasi splash screen yang menarik
- Navigasi welcome screen yang smooth
- Registrasi pengguna dengan kredensial random
- Validasi halaman sukses registrasi ✨

#### 2. **Testing Fitur Jadwal Sholat** 🕌

- Verifikasi elemen home screen
- Validasi kartu waktu sholat
- Testing fitur hadits harian yang inspiratif 💫

#### 3. **Testing Fitur Jurnal Spiritual** 📖

- Pembuatan jurnal baru
- Validasi input konten
- Penghapusan jurnal
- Verifikasi operasi CRUD yang complete 🔄

#### 4. **Testing Kalender Bulanan** 📅

- Navigasi kalender yang intuitif
- Seleksi tanggal yang responsive
- Verifikasi jadwal sholat per tanggal 📍

#### 5. **Testing Pengaturan & Autentikasi** ⚙️

- Verifikasi pengaturan profil
- Fungsionalitas logout yang aman
- Manajemen sesi yang robust 🔐

### Menjalankan Testing Suite

#### 1. **Setup Prerequisites** 🛠️

```bash
# Memulai Appium Server
appium

# Install dependencies yang diperlukan
mvn clean install
```

#### 2. **Eksekusi Testing** 🚀

```bash
# Menjalankan semua test
mvn test

# Menjalankan test class spesifik
mvn test -Dtest=Base
```

### Konfigurasi Testing yang Optimal

Test suite menggunakan capabilities terbaik:

```java
{
    "deviceName": "TaubatKu_TestDevice",
    "automationName": "uiautomator2",
    "platformName": "Android",
    "app": "/path/to/taubatku.apk"
}
```

### Best Practices yang Diimplementasikan 🎯

- **Dynamic Waits** - Menunggu elemen dengan patience yang optimal ⏱️
- **Random Data Generation** - Test data yang selalu fresh dan realistic 🎲
- **Resource Management** - Cleanup yang proper dan manajemen memori 🧹
- **Error Handling** - Penanganan error yang comprehensive dan informatif 🛡️
- **Modular Structure** - Struktur test yang maintainable dan scalable 🏗️

### Laporan Testing yang Detail 📊

Eksekusi testing menghasilkan laporan mendalam meliputi:

- Status test case (Pass/Fail) dengan akurasi tinggi ✅❌
- Waktu eksekusi yang terukur precis ⏰
- Log error yang informatif (jika ada) 📝
- Screenshot otomatis saat failure 📸
- Coverage report yang comprehensive 📈

## 🤝 Kontribusi & Kolaborasi

Kami menyambut kontribusi dari developer lain! Cara berkontribusi:

1. **Fork repository** ini ke akun GitHub Anda 🍴
2. **Buat feature branch** dengan nama yang deskriptif 🌟
3. **Commit perubahan** Anda dengan pesan yang jelas 💾
4. **Push ke branch** yang telah dibuat 🚀
5. **Buat Pull Request** dengan deskripsi detail 📬

### Guidelines Kontribusi 📋

- Ikuti coding standards yang telah ditetapkan
- Pastikan semua test case berjalan dengan baik ✅
- Dokumentasikan perubahan yang significant
- Gunakan commit message yang informatif dan terstruktur

## 🏆 Tim Pengembang

<div align="center">
   <img src="public/readme/profile.jpg" alt="Developer Profile" width="150" style="border-radius: 50%; border: 4px solid #fff; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
   
   ### 👨‍💻 **Hidayat Nur Hakim**
   <sub>🎓 **NIM:** 2208107010063</sub>
   
   [![GitHub](https://img.shields.io/badge/GitHub-Follow-1DA1F2?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Hidayattt24)
   [![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/hidayat-nur-hakim/)
   
   <p align="center">
      <img src="https://img.shields.io/badge/Android-Development-3DDC84?style=flat-square&logo=android&logoColor=white"/>
      <img src="https://img.shields.io/badge/Firebase-Expert-FFCA28?style=flat-square&logo=firebase&logoColor=white"/>
      <img src="https://img.shields.io/badge/UI/UX-Design-FF4088?style=flat-square&logo=material-design&logoColor=white"/>
   </p>

> _"Crafting digital solutions with Islamic values at heart"_ 🌟

_"Mengembangkan teknologi yang membawa manfaat untuk umat"_ 🌟

---

**🎓 Role & Expertise:**

- **Full-Stack Mobile Developer** - Android Native Development
- **UI/UX Enthusiast** - Material Design & User Experience
- **Firebase Specialist** - Authentication & Cloud Database
- **Testing Engineer** - Automation Testing & Quality Assurance

**💡 Passion:**
Menggabungkan passion teknologi mobile dengan nilai-nilai Islami untuk menciptakan aplikasi yang bermanfaat bagi kehidupan spiritual umat Muslim.

**🛠️ Tech Stack:**
`Kotlin` • `Java` • `Firebase` • `Android SDK` • `Material Design` • `Appium` • `TestNG`

**📫 Connect:**

- 🌐 Portfolio: https://portofolio-hidayat-phi.vercel.app/
- 📧 Email: hidayat.22@mhs.usk.ac.id
- 💼 LinkedIn: https://www.linkedin.com/in/hidayat-nur-hakim/
</div>

---

### 🎯 **Visi Pengembangan**

Aplikasi TaubatKu dikembangkan dengan visi menciptakan ekosistem digital yang mendukung kehidupan spiritual umat Muslim di era modern. Setiap fitur dirancang dengan pertimbangan mendalam terhadap kebutuhan ibadah harian dan kemudahan akses informasi keagamaan.

### 🌟 **Dedikasi & Komitmen**

Proyek ini merupakan manifestasi dari dedikasi tinggi seorang mahasiswa Pemrograman Bergerak yang berkomitmen menghadirkan solusi teknologi berkualitas tinggi dengan sentuhan nilai-nilai Islami yang autentik.

## 📄 Lisensi

Proyek ini dilisensikan di bawah **MIT License** - lihat file LICENSE untuk detail lengkap.

---

_"Dan ingatlah, hanya dengan mengingat Allah-lah hati menjadi tenteram"_ - QS. Ar-Ra'd: 28 🤲

**TaubatKu** - _Sahabat Spiritual di Genggaman Anda_ 📱✨
