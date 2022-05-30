package com.example.uasmobile_psikisku;

public class Obat {
    private long _id;
    private String _namabarang;
    private String _qtybarang;
    private long _hargabarang;


    public Obat()
    {
    }


    public void setID(long id)
    {
        this._id=id;
    }


    public long getID()
    {
        return this._id;
    }


    public void setNamaBarang(String namaBarang)
    {
        this._namabarang=namaBarang;
    }


    public void setQTYBarang(String qtyBarang)
    {
        this._qtybarang=qtyBarang;
    }

    public void setHargaBarang(long HargaBarang)
    {
        this._hargabarang=HargaBarang;
    }



    @Override
    public String toString()
    {
        return "Nama Obat\t\t\t\t: "+ _namabarang + " \nHarga Barang\t: "+ _hargabarang+ " \nQTY\t\t\t\t:" + _qtybarang;
    }

}
