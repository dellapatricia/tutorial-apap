# Tutorial APAP
## Authors
* **Della Patricia Siregar** - *1906399436* - *C*
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
