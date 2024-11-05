package com.practica.controller.dao.services;

import com.practica.controller.dao.GeneradorDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Generador;

public class GeneradorServices {
    private GeneradorDao obj;

    public GeneradorServices(){
        this.obj = new GeneradorDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }

    public LinkedList listAll() throws Exception{
        return obj.getListAll();
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setGenerador(Generador generador){
        obj.setGenerador(generador);
    }

    public Generador getGeneradorByIndex(Integer index) throws Exception{
        return obj.getGeneradorByIndex(index);
    }

    public String getGeneradorJsonByIdIndex(Integer index) throws Exception{
        return obj.getGeneradorJsonByIdIndex(index);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }
}
