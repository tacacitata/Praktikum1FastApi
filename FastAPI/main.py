from fastapi import FastAPI
from pydantic import BaseModel
from typing import List

app = FastAPI()

class Mahasiswa(BaseModel):
    nim: str
    nama: str
    kelas: str

data_mahasiswa: List[Mahasiswa] = []

@app.post("/mahasiswa/")
def tambah(mahasiswa: Mahasiswa):
    data_mahasiswa.append(mahasiswa)
    return {"pesan": "Berhasil ditambahkan"}

@app.get("/mahasiswa/", response_model=List[Mahasiswa])
def get_data():
    return data_mahasiswa


# NIM   : 10122199
# Nama  : Rucita
# Kelas : PA
