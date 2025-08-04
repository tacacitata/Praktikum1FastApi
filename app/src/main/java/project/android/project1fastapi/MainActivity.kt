package project.android.project1fastapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import project.android.project1fastapi.model.Mahasiswa
import project.android.project1fastapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import project.android.project1fastapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MahasiswaAdapter
    private val listMahasiswa = mutableListOf<Mahasiswa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MahasiswaAdapter(listMahasiswa)
        binding.rvMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.rvMahasiswa.adapter = adapter

        binding.btnTambah.setOnClickListener {
            tambahMahasiswa()
        }

        getMahasiswa()
    }

    private fun tambahMahasiswa() {
        val mahasiswa = Mahasiswa(
            nim = binding.etNim.text.toString(),
            nama = binding.etNama.text.toString(),
            kelas = binding.etKelas.text.toString()
        )

        ApiClient.getApiService().tambahMahasiswa(mahasiswa)
            .enqueue(object : Callback<Map<String, String>> {
                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {
                    Toast.makeText(this@MainActivity, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    getMahasiswa()
                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gagal tambah data", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun getMahasiswa() {
        ApiClient.getApiService().getMahasiswa()
            .enqueue(object : Callback<List<Mahasiswa>> {
                override fun onResponse(
                    call: Call<List<Mahasiswa>>,
                    response: Response<List<Mahasiswa>>
                ) {
                    listMahasiswa.clear()
                    response.body()?.let { listMahasiswa.addAll(it) }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<Mahasiswa>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            })
    }
}

// NIM   : 10122199
// Nama  : Rucita
// Kelas : PA3