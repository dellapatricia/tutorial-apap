# Tutorial APAP
## Authors
* **Della Patricia Siregar** - *1906399436* - *C*
---
## Tutorial 3
### What I have learned today
Saya telah mempelajari tentang Java Persistence API (JPA) lalu juga berkenalan dengan XAMPP, Apache, MySql PHPmyadmin. Saya juga mempelajari relasi database pada spring boot dimana. 

### Pertanyaan
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
@AllArgsConstructor akan mengenerate sebuah constructor dengan 1 parameter sedangkan @NoArgsConstructor akan mengenerate sebuah constructor tanpa parameter. Untuk @Getter dan @Setter, keduanya akan mengenerate getter/setter secara otomatis pada setiap field yang ada pada kelas. Lalu @Entity adalah anotasi JPA yang memiliki fungsi untuk menganotasikan sebuah kelas sebagai suatu JPA entity class. Terakhir adalah @Table dimana anotasi tersebut memiliki fungsi untuk menentukan detail yang lebih jelas terkait tabel yang berhubungan dengan kelas.

2. Pada class BioskopDB, terdapat method findByNoBioskop, apakah kegunaan dari method tersebut?
Kegunaan dari method adalah untuk mencari BioskopModel pada database Bioskop berdasarkan nomor bioskop.

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn 
@JoinTable akan menyimpan hubungan antara suatu tabel a dan tabel b dalam suatu tabel yang baru sedangkan @JoinColumn akan menyimpan hubungan kedua tabel dalam suatu kolom baru. @JoinColumn akan memberikan entitas yang berhubungan dengan entitas lain suatu foreign key sedangkan @JoinTable akan menyajikan seluruh hubungan antar entitas. 

4.  Pada class PenjagaModel, digunakan anotasi @JoinColumn pada atribut bioskop, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull
Kegunaan "name" adalah sebagai foreignkey pada sebuah kolom. Sedangan "referencedColumnName" akan mendefine nama untuk suatu kolom pada tabel yang kita refer. Nullable akan menentukan apakah suatu kolom bisa mengandung nilai null atau tidak. Perbedaanya dengan @NotNull adalah anotasi @NotNull akan langsung menentukan bahwa suatu metode tidak boleh return null.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType EAGER!
FetchType.EAGER digunakan saat kita ingin mendapatkan seluruh elemen suatu relasi dimana sintaks ini dapat memperlambat jalannya aplikasi karena ia akan mengambil data-data yang juga tidak diperlukan ketika dipanggil pada suatu kasus. Sedangkan FetchType.LAZY digunakan saat kita ingin mendapatkan elemen yang kita butuhkan saja sehingga aplikasi akan berjalan lebih cepat dibandingkan dengan FetchType.EAGER. Untuk CascadeType.ALL adalah suatu set operasi cascadable untuk entitas yang saling berhubungan dimana set operasi ini akan perform beberapa operasi seperti PERSIST, MERGE, REMOVE, DETACH, REFRESH untuk entitas parent.

### What I did not understand
Terlalu banyak materi yang diserap sehingga setiap ilmu yang saya dapatkan belum terlalu saya pahami tetapi saya bisa mengikutinya seiring pengerjaan walau memerlukan wakt yang cukup lama.

---
## Tutorial 2
### What I have learned today
Saya jadi lebih memahami PathVariable dan RequestParam. Pemahaman saya saat PPW dan juga DDP terpakai disini. Saya juga semakin memahami alur dari Springboot karena tutorial ini.

### Github
1. Pertanyaan 1: Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut:
   ```
   http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx&jumlahStudio=10
   ```
   Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Yang terjadi adalah Whitelabel Error Page dengan error 500. Path name /bioskop/add akan memanggil addBioskop method dimana method tersebut akan return suatu template. Namun, page html dengan nama add-bioskop belum dibuat sehingga menimbulkan error.

2. Pertanyaan 2: Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat. 

@Autowired ditaruh di atas properti dimana anotasi ini akan mengeliminasi kebutuhan properti untuk menciptakan getter setter. Spring akan otomatis injection dengan anotasi tersebut karena anotasi ini adalah fitur utama dari Dependency Injection. Walaupun masih sedikit dampaknya, namun implementasi anotasi ini bisa mempermudah kita dalam penulisan kode.

3. Pertanyaan 3: Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut:
   ```
   http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx 
   ```
   Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Yang terjadi adalah Whitelabel Error Page dengan error 400 yaitu Bad Request. Yang terjadi adalah pada URL, kurang query pada query parameters dimana jika mengakses path /bioskop/add dibutuhkan 5 query tetapi hanya 4 query yang ada pada query parameter.
   
4. Pertanyaan 4: Jika Papa APAP ingin melihat Bioskop dengan nama Bioskop Maung, link apa yang harus diakses?

Jika belum menambahkan nama Bioskop Maung, maka harus menambahkan Bioskop Maung terlebih dahulu dengan:
   ```
   http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Maung&alamat=Fasilkom%20UI&noTelepon=021450XXX&jumlahStudio=5
   ```
   
Kemudian akses Bioskop tersebut dengan:
   ```
   http://localhost:8080/bioskop/view?idBioskop=1
   ```

6. Pertanyaan 5: Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

Saya menambahkan:
   - idBioskop : 3
   - nama Bioskop : Della
   - alamat rumah : Della
   - notelepon : 0813xxx
   - jumlah studio : 8 

Saya tambahkan dengan link ini:
   ```
   http://localhost:8080/bioskop/add?idBioskop=3&namaBioskop=Della&alamat=Rumah%20Della&noTelepon=0813xxx&jumlahStudio=8
   ```
   
   Lalu ketika view all, terlihat bioskop custom yang saya buat di paling bawah.
   ![](Gambar_No-5.png)

### What I did not understand
Saya memahami lab kali ini walaupun sedikit-sedikit lupa dengan materi PPW dan DDP. Tetapi dengan melihat beberapa dokumen Springboot dan tutorial sebelumnya, saya jadi lebih familiar. Namun pemakaian @Override, @Autowired dan lainnya masih kurang bisa dipahami dan tidak familiar.


---
## Tutorial 1
### What I have learned today
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?

   Issue Tracker adalah fitur yang dimiliki GitHub untuk membuat ataupun mengetahui masalah apa yang terjadi selama proses pengerjaan. Dengan adanya fitur ini, akan lebih mudah untuk bisa melacak apakah ada sesuatu yang tidak bekerja, memberitahu perubahan yang dilakukan, meminta bantuan, ataupun masukan yang harus dilakukan dalam pekerjaan yang ada di repository ini. Komunikasi antar anggota dalam mengerjakan proyek juga akan semakin efisien dengan adanya fitur issue tracker ini.

2. Apa perbedaan dari git merge dan git merge --squash?

   Git merge adalah aktivitas yang dilakukan git untuk mengambil konten dari suatu branch dan menyatukannya dengan branch yang ditargetkan dimana akan terbentuk suatu commit baru yang memiliki dua parent yaitu kedua branch yang digabungkan. Sedangkan git merge --squash adalah suatu opsi merge yang akan menggabungkan kedua branch namun commit baru akan hanya memiliki satu parent yaitu parent dari master.

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
   suatu aplikasi?
- Dengan menggunakan version control system, **_dokumentasi_** dari pekerjaan bisa
  dijabarkan dengan mendetail.
- Akan lebih mudah untuk bisa **_melacak_** dokumen apa saja yang mengalami perubahan, dokumen yang dihapus, dan seluruh aktivitas jika menggunakan Version Control System seperi Git.
- Juga untuk pekerjaan yang dilakukan bersamaan, seluruh anggota bisa dengan **_bebas_** melakukan tugasnya masing-masing dan diakhir pekerjaan, seluruh anggota dapat menyatukan kembali pekerjaan yang telah dilakukan masing-masing anggotanya dengan mudah.


### Spring
4. Apa itu library & dependency?

   **Library** adalah kumpulan-kumpulan kode yang bisa digunakan oleh kita dalam membangun suatu projek. Sedangkan **Dependency** adalah hubungan antara proyek kita dengan beberapa library yang kita pilih untuk berkolaborasi dalam membangun proyek kita. Kita tidak bisa mengatakan bahwa semua library saling bergantung atau _dependen_ tetapi ketika kita memilih beberapa library untuk membangun proyek kita, maka kumpulan library tersebut disebut _dependency_.

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?

   Maven adalah sebuah tools untuk membangun projek, membuat laporan, sampai dokumentasi yang berbasis kosnep project object model (POM). Kita menggunakan Maven karena Maven memiliki _dependency management_ jadi kita bisa hanya mendefinisikan dependency yang kita perlukan dalam sebuah file POM dan Maven akan otomatis mengunduhnya. Juga yang paling terpenting adalah karena kita akan bekerja dalam tim untuk membangun suatu aplikasi, besar kemungkinan bahwa anggota-anggota tim akan memakan IDE favorit mereka dan setiap IDE tentunya memiliki struktur yang berbeda. Namun dengan Maven, akan ada hanya satu struktur yang bisa diadaptasikan oleh setiap IDEnya sehingga anggota tim bisa bekerja dengan IDE yang disukai. Beberapa alternatif lain Maven adalah Gradle, GNU Make, CMake, Meson dan SCons.

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
   framework?

   Selain untuk pengembangan web, Spring dapat membantu dalam:
- Pemrosesan beberapa data tanpa adanya interupsi eksternal
- Pengaksesan data dan integrasi
- Pembangunan aplikasi Enterprise

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
   menggunakan @RequestParam atau @PathVariable?

   @RequestParam digunakan untuk get parameter yang di request dari sebuah URL atau dikenal dengan query parameter sedangkan @PathVariable mengekstraksi value dari URI.

   @RequestParam digunakan saat sebuah URL memiliki key value setelah tanda '?' yang biasa dikenal dengan query paramaeter dan kita ingin mengambil data key value tersebut.
   Contoh:
   http://localhost:8080/npm?id=della berarti bisa mendapatkan nilai nama yaitu della dengan
    ```
    @GetMapping("/foos")
    @ResponseBody
    public String getNpmByIdUsingQueryParam(@RequestParam String id) {
      return "ID: " + id;
    }
    ```
    Untuk @PathVariable digunakan saat ingin mengambil data langsung dari URI templatenya.
    Contoh:
    http://localhost:8080/npm/della bisa mendapatkan nilai nama yaitu della dengan 
    ```
    @GetMapping("/npm/{id}")
    @ResponseBody
    public String getNpmById(@PathVariable String id) {
      return "NPM: " + id;
    }
    ```

### What I did not understand
Saya sebenarnya masih kurang memahami sepenuhnya dengan proses yang terjadi dalam tutorial ini dan mungkin masih kurang menangkap apa tujuan yang mau kita capai pada pengerjaan tutorial ini. Lalu, terlalu banyak informasi baru yang saya dapatkan dalam satu kali tutorial ini sehingga masih banyak hal yang baru saya pahami hanya kulitnya saja. Seperti contohnya, saya mengerti analogi dari git merge dan git merge --squash tetapi saya kurang paham realitanya seperti apa. 
