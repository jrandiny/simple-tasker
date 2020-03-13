# simple-tasker

## Deskripsi Aplikasi

Simple tasker merupakan sebuah aplikasi android IFTTW (IF This Then What). Pengguna dapat menambahkan sebuah rutin di dalam aplikasi. Rutin terdiri dari sebuah kondisi dan aksi. Kondisi merupakan syarat dari sebuah aksi untuk terjadi. Aksi merupakan hal yang terjadi ketika kondisi tercapai.

## Cara Kerja

Terdapat 5 modul yang dibagi menjadi 2 kondisi dan 3 aksi.
#### Kondisi :
- Sensor
Syarat yang dihasilkan dari kondisi minimum atau maksimum dari sensor. Ada 2 sensor dalam aplikasi, magnet dan akselerometer. Pengguna dapat memasukkan nilai maksimum atau minimum untuk sensor. 
- Timer
Syarat yang dihasilkan dari kondisi batas waktu. Pengguna dapat menambahkan waktu yang kan menjadi syarat untuk dilewati jika aksi ingin terjadi.
#### Aksi :
- External API
Aksi yang mengirimkan sebuah request pada External API. External API nya merupakan smarthome sonoff. Membutuhkan masukan berupa username, password, dan ip address.
- Notify
Aksi yang mengirimkan sebuah notification jika sebuah kondisi terpenuhi. Membutuhkan input judul dan isi notifikasi.
- Wifi
Aksi yang mengubah status dari wifi smartphone pengguna. Pengguna dapat memilih untuk mengaktifkan atau menonaktifkan wifi jika suatu kondisi telah tercapai.

## Library :
- androidx.core
Digunakan untuk memperbolehkan develop ke banyak versi API
- androidx.activity
Digunakan untuk memperbolehkan develop ke banyak versi API
- androidx.appcompat
Digunakan untuk memperbolehkan develop ke banyak versi API
- androidx.constraintlayout
Digunakan untuk memperbolehkan develop ke banyak versi API
- androidx.fragment
Digunakan untuk memperbolehkan develop ke banyak versi API
- androidx.room
Digunakan untuk pengaturan database.
- androidx.lifecycle
Mengatur tindakan yang merupakan respons dari perubahan status siklus.
- com.google.code.gson
Menyimpan map di database.
- com.android.volley
Untuk mengirim request ke External API.
- materialDayPicker
Untuk memberikan tampilan dari day picker.

## Screenshot :
- homepage
![homepage][simple-tasker/screenshot/home.jpg]
