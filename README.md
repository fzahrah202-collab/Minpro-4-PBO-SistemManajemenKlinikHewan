# MINPRO 4 PBO – SISTEM MANAJEMEN KLINIK HEWAN
Nama: Fatimatuzzahrah

Nim: 2509116020

## 1. Deskripsi Program

Sistem Manajemen Klinik Hewan merupakan program berbasis Java yang digunakan untuk mengelola data hewan pada sebuah klinik hewan.

Program dapat digunakan untuk menambahkan, menampilkan, mencari, mengubah, dan menghapus data hewan. Data yang disimpan meliputi data hewan, pemilik, pemeriksaan, serta informasi vaksin untuk hewan tertentu.

## 2. Struktur Package

Program menggunakan beberapa package untuk mengatur class berdasarkan fungsinya.

<img width="525" height="1141" alt="image" src="https://github.com/user-attachments/assets/bf98d594-8c71-4d60-87f0-816449577bff" />

Keterangan package dan class yang digunakan:

- `main`: berisi class `Main` sebagai titik awal program.
- `model`: berisi class untuk merepresentasikan data, seperti `Hewan`, `Kucing`, `Anjing`, `HewanLain`, `Pemilik`, dan `Pemeriksaan`, serta `HewanDAO` untuk mengakses database.
- `view`: berisi class `KlinikView` untuk menampilkan informasi kepada pengguna.
- `controller`: berisi class `Service` untuk mengatur proses program.
- `config`: berisi class `Koneksi` untuk menghubungkan program dengan database serta `JPAUtil` untuk pengelolaan koneksi ORM.
- `src/main/resources/META-INF`: berisi file `persistence.xml` untuk konfigurasi ORM.

## 3. Alur Program

### 3.1 Menu Utama

<img width="554" height="419" alt="image" src="https://github.com/user-attachments/assets/98617260-0599-41d0-bade-0e52df41054e" />

Saat program dijalankan, pengguna dapat memilih menu berikut:

1. Tambah Data Hewan
2. Tampilkan Data Hewan
3. Cari Data Hewan
4. Update Data Hewan
5. Hapus Data Hewan
6. Keluar

Pengguna dapat memilih menu sesuai kebutuhan untuk mengelola data hewan.

### 3.2 Tambah Data Hewan

<img width="566" height="901" alt="image" src="https://github.com/user-attachments/assets/de59953c-2a40-47fc-82e7-c61858552114" />


Menu tambah data digunakan untuk memasukkan data hewan, pemilik, dan pemeriksaan. Data yang dimasukkan akan diproses oleh program dan disimpan ke database MySQL menggunakan JDBC.

### 3.3 Tampilkan Data Hewan

<img width="528" height="1316" alt="image" src="https://github.com/user-attachments/assets/a9982966-28db-4bfb-ad90-d2741a69cbc9" />

Menu ini digunakan untuk menampilkan data hewan yang tersimpan di database. Data hewan ditampilkan bersama informasi pemilik dan pemeriksaan yang berkaitan.

### 3.4 Cari Data Hewan

<img width="510" height="638" alt="image" src="https://github.com/user-attachments/assets/6e6f8c1d-c902-4563-9a87-266e2b4a904d" />

Menu pencarian digunakan untuk menemukan data hewan berdasarkan kriteria pencarian yang tersedia pada program.

### 3.5 Update Data Hewan

<img width="513" height="907" alt="image" src="https://github.com/user-attachments/assets/27bb017f-ad7f-4aed-9979-84598d814b82" />

Menu update digunakan untuk mengubah data yang telah tersimpan di database. Perubahan dilakukan melalui proses pembaruan data menggunakan JDBC.

### 3.6 Hapus Data Hewan

<img width="515" height="485" alt="image" src="https://github.com/user-attachments/assets/ff3ee91e-61f9-44c0-8f8c-d8cf9b2d36f3" />

Menu hapus digunakan untuk menghapus data hewan yang dipilih beserta data terkait sesuai dengan proses penghapusan yang diterapkan pada program.

### 3.7 Keluar

<img width="521" height="312" alt="image" src="https://github.com/user-attachments/assets/b55a62c7-4b1f-4127-ac6a-3a44bd274f35" />

Menu keluar digunakan untuk menghentikan program.

## 4. Konsep PBO yang Diterapkan

### 4.1 Encapsulation

Encapsulation diterapkan dengan menggunakan access modifier seperti `private` pada atribut class serta method getter dan setter untuk mengakses atau mengubah data sesuai kebutuhan.

<img width="521" height="158" alt="image" src="https://github.com/user-attachments/assets/fdb18b3b-7b33-46a9-9f61-c454824f7722" />
.
<img width="531" height="546" alt="image" src="https://github.com/user-attachments/assets/80a5201f-e5c4-4fb0-ab20-25ab013ac8c2" />

### 4.2 Inheritance

Inheritance diterapkan melalui class `Hewan` sebagai superclass dan class `Kucing`, `Anjing`, serta `HewanLain` sebagai subclass.

<img width="519" height="68" alt="image" src="https://github.com/user-attachments/assets/b6aad70b-22b3-46f1-bf42-bb496828ea8f" />
.
<img width="519" height="55" alt="image" src="https://github.com/user-attachments/assets/e409d8ee-da86-461e-9bd5-e7c97430681a" />
.
<img width="513" height="57" alt="image" src="https://github.com/user-attachments/assets/41e8d19c-764b-4c7a-bcd7-5134830d8f30" />

Dengan inheritance, subclass dapat menggunakan atribut dan method yang diwariskan dari superclass.

### 4.3 Polymorphism

Polymorphism diterapkan melalui method overriding pada method `tampilkanInfo()` yang diimplementasikan sesuai dengan jenis hewan. 

<img width="528" height="109" alt="image" src="https://github.com/user-attachments/assets/d3fa3318-6f78-4498-81cd-38cb5c1c628f" />

Method overloading juga diterapkan melalui method dengan nama sama tetapi parameter berbeda, sesuai implementasi pada program.

<img width="523" height="456" alt="image" src="https://github.com/user-attachments/assets/414de8ea-7fe6-4c21-a1bd-97ea4f2b8916" />

### 4.4 Abstraction

Abstraction diterapkan dengan menjadikan class `Hewan` sebagai abstract class yang memiliki abstract method `tampilkanInfo()`. Setiap subclass mengimplementasikan method tersebut sesuai dengan karakteristiknya.

<img width="519" height="59" alt="image" src="https://github.com/user-attachments/assets/9bc9a6de-3b9c-43c7-8df9-d6e32b63dcd9" />
.
<img width="516" height="57" alt="image" src="https://github.com/user-attachments/assets/e4488618-7465-40cf-bd22-1b9243f6c0b2" />


## 5. Penerapan JDBC dan DAO

### 5.1 Koneksi Database

Program menggunakan JDBC untuk menghubungkan aplikasi Java dengan database MySQL bernama `klinik_hewan`. Pengaturan koneksi terdapat pada class `Koneksi`.

<img width="526" height="288" alt="image" src="https://github.com/user-attachments/assets/47d89731-7405-47c0-8176-f7a8aaaf8977" />

### 5.2 Data Access Object (DAO)

Class `HewanDAO` digunakan untuk memisahkan proses akses database dari bagian program lainnya. Class ini menangani operasi tambah, tampil, update, dan hapus data.

<img width="516" height="81" alt="image" src="https://github.com/user-attachments/assets/791f11d1-e27d-4b02-ae46-de7d0aeb5fab" />
.
<img width="717" height="44" alt="image" src="https://github.com/user-attachments/assets/d01bf02c-9b02-4345-a86d-0a4264ca278d" />
.
<img width="722" height="52" alt="image" src="https://github.com/user-attachments/assets/47f16896-21c9-4ce8-a8d3-c3ba919527d9" />
.
<img width="708" height="44" alt="image" src="https://github.com/user-attachments/assets/74548a0b-5b6a-40e4-8185-f2d8fe98464e" />
.
<img width="714" height="59" alt="image" src="https://github.com/user-attachments/assets/a342e21c-3078-49e6-afa3-fc01141cc6c2" />

### 5.3 PreparedStatement

`PreparedStatement` digunakan untuk menjalankan perintah SQL dengan parameter. Penggunaannya membantu menyusun query secara terstruktur saat melakukan operasi CRUD.

<img width="541" height="125" alt="image" src="https://github.com/user-attachments/assets/19adf4af-3e6e-4b0d-b5a2-eea6fc5ee779" />

## 6. Penerapan ORM sebagai Nilai Tambah

ORM (*Object-Relational Mapping*) diterapkan menggunakan Hibernate/JPA untuk menghubungkan objek Java dengan tabel database. Pada program ini, ORM digunakan pada class `PemilikORM`.

### 6.1 Konfigurasi ORM

Konfigurasi ORM terdapat pada file `persistence.xml`, sedangkan class `JPAUtil` digunakan untuk membantu pengelolaan `EntityManagerFactory`.

<img width="545" height="445" alt="image" src="https://github.com/user-attachments/assets/3f0f578b-cc07-4e48-9729-bf0a8498f8e0" />
.

### 6.2 Pengujian Operasi ORM

ORM digunakan untuk menguji operasi baca, tambah, update, dan hapus data pemilik melalui Hibernate/JPA.

<img width="541" height="211" alt="image" src="https://github.com/user-attachments/assets/6bd79420-fc4c-4b07-9b58-e713c1abcae1" />
.
<img width="556" height="126" alt="image" src="https://github.com/user-attachments/assets/59fb3c05-3794-4ea6-8b97-29d96450ad67" />
.
<img width="819" height="158" alt="image" src="https://github.com/user-attachments/assets/1d3d5ec7-618d-4383-922a-a95813797097" />
.
<img width="821" height="153" alt="image" src="https://github.com/user-attachments/assets/832ce7d2-350b-48c3-9846-390a0cabbce5" />

JDBC dan ORM merupakan dua pendekatan akses database yang diterapkan pada program ini. JDBC digunakan dalam proses pengelolaan data utama melalui DAO, sedangkan ORM menjadi nilai tambah untuk menunjukkan pengelolaan data menggunakan pemetaan objek ke tabel database.

## 7. File Database SQL

File SQL disertakan dalam repository untuk menyediakan struktur tabel dan data awal yang dibutuhkan oleh program.

<img width="513" height="164" alt="image" src="https://github.com/user-attachments/assets/b9f13312-402f-4f7f-a3b8-5f3e0e21e24c" />

Database `klinik_hewan` terdiri dari tabel:

- `pemilik`: menyimpan data pemilik hewan.
- `hewan`: menyimpan data hewan dan informasi vaksin.
- `pemeriksaan`: menyimpan data pemeriksaan hewan.

File SQL dapat digunakan untuk membuat tabel dan memasukkan data awal ke database sebelum program dijalankan.
