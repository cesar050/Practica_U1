package com.practica.controller.dao;

import com.google.gson.Gson;
import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Generador;

public class GeneradorDao  extends AdapterDao<Generador>{
    private Generador generador;
    private LinkedList<Generador> listAll;
    private Gson g = new Gson();

    public GeneradorDao(){
        super(Generador.class);
    }

    public Generador getGenerador(){
        if(generador == null){
            generador = new Generador();
        }
        return generador;
    }

    public void setGenerador(Generador generador){
        this.generador = generador;
    }

    public LinkedList<Generador> getListAll() throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize() +1;
        getGenerador().setId(String.valueOf(id));
        this.persist(this.generador);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception{
        this.merge(generador, Integer.valueOf(generador.getId()));
        this.listAll = listAll();
        return true;
    }

    public Boolean delete() throws Exception{
        if(listAll == null){
            listAll = listAll();
        }
        this.delete(Integer.valueOf(generador.getId()));
        reindexIds();
        return true;
    }


    public void reindexIds()throws Exception{
        LinkedList<Generador> listAll = listAll();
        for(int i = 0; i < listAll.getSize(); i++){
            Generador ge = listAll.get(i);
            ge.setId(String.valueOf(i + 1));
            this.merge(ge, i +1);
        }
    
    }

    public Generador getGeneradorByIndex(Integer index) throws Exception{
        return get(index);
    }

    public String getGeneradorJsonByIdIndex(Integer index) throws Exception{
        return g.toJson(get(index));
    }

    public String toJsonAll()throws Exception{
        return g.toJson(getListAll());
    }

    public void setListAll(LinkedList<Generador> listAll){
        this.listAll = listAll;
    }
}