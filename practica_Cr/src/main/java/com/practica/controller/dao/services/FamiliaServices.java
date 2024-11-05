package com.practica.controller.dao.services;

import com.practica.controller.dao.FamiliaDao;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;

public class FamiliaServices {
    private FamiliaDao obj;
    
    public FamiliaServices(){
        this.obj = new FamiliaDao();
    }

    public Boolean  save() throws Exception{
        return obj.save();
    }

    public LinkedList listAll() throws Exception{
        return obj.getListAll();
    }

    public Familia getFamilia(){
        return obj.getFamilia();
    }

    public void setFamilia(Familia familia){
        obj.setFamilia(familia);
    }

    public Familia getFamiliaByIndex(Integer index)throws Exception{
        return obj.getFamiliaByIndex(index);
    }

    public String getFamiliaJsonByIdIndex(Integer index)throws Exception{
        return obj.getFamiliaJsonByIdIndex(index);
    }
    public Boolean update() throws Exception{
        return obj.update();
    }
    
    public Boolean delete() throws Exception{
        return obj.delete();
    }

}
