package com.practica.controller.dao;

import com.google.gson.Gson;
import com.practica.controller.dao.implement.AdapterDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList<Familia> listAll;
    private Gson g = new Gson();

    public FamiliaDao() {
        super(Familia.class);
    }

    public Familia getFamilia() {
        if (familia == null) {
            familia = new Familia();
        }
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public LinkedList<Familia> getListAll() throws Exception {
        if (listAll == null) {
            this.listAll = listAll(); 
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize() + 1;
        getFamilia().setId(String.valueOf(id));
        this.persist(this.familia);
        this.listAll = listAll(); 
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(familia, Integer.valueOf(familia.getId())); 
        this.listAll = listAll(); 
        return true;
    }

    public Boolean delete() throws Exception {
        if (listAll == null) {
            listAll = listAll(); 
        }
        this.delete(Integer.valueOf(familia.getId())); 
        reindexIds(); 
        return true;
    }

    public void reindexIds() throws Exception {
        LinkedList<Familia> listAll = listAll(); 
        for (int i = 0; i < listAll.getSize(); i++) {
            Familia fa = listAll.get(i);
            fa.setId(String.valueOf(i + 1)); 
            this.merge(fa, i + 1); 
        }
    }

    public Familia getFamiliaByIndex(Integer index) throws Exception {
        return get(index);
    }

    public String getFamiliaJsonByIdIndex(Integer index) throws Exception {
        return g.toJson(get(index));
    }

    public String toJsonAll() throws Exception {
        return g.toJson(getListAll());
    }

    public void setListAll(LinkedList<Familia> listAll) {
        this.listAll = listAll;
    }
}
