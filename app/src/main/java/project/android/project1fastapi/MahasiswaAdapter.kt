package project.android.project1fastapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.android.project1fastapi.databinding.ItemMahasiswaBinding
import project.android.project1fastapi.model.Mahasiswa

class MahasiswaAdapter(private val listMahasiswa: List<Mahasiswa>) :
    RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    inner class MahasiswaViewHolder(val binding: ItemMahasiswaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMahasiswaBinding.inflate(inflater, parent, false)
        return MahasiswaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = listMahasiswa[position]
        with(holder.binding) {
            tvNim.text = "NIM: ${mahasiswa.nim}"
            tvNama.text = "Nama: ${mahasiswa.nama}"
            tvKelas.text = "Kelas: ${mahasiswa.kelas}"
        }
    }

    override fun getItemCount(): Int = listMahasiswa.size
}



