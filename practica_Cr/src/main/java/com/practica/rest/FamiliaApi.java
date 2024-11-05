package com.practica.rest;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.practica.controller.dao.services.FamiliaServices;
import com.practica.controller.tda.list.LinkedList;
import com.practica.models.Familia;

@Path("/familia")
public class FamiliaApi {

    private FamiliaServices familiaService = new FamiliaServices();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllFamilia() {
        HashMap<String, Object> res = new HashMap<>();
        try {
            LinkedList<Familia> familias = familiaService.listAll();
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con éxito.");
            res.put("data", familias.toArray());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getFamiliaByIndex(@PathParam("id") Integer id) {
        try {
            String jsonResponse = familiaService.getFamiliaJsonByIdIndex(id);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

  /*  @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            if (!map.containsKey("nombre") || map.get("nombre").toString().isEmpty()) {
                throw new IllegalArgumentException("El nombre es obligatorio.");
            }
            
            Familia familia = familiaService.getFamilia();
            familia.setNombre(map.get("nombre").toString());
            familia.setTieneGenerador(Boolean.parseBoolean(map.get("tieneGenerador").toString()));
            familia.setPresupuesto(Float.parseFloat(map.get("presupuesto").toString()));
            familia.setCostoGenerador(Float.parseFloat(map.get("costoGenerador").toString()));
            familia.setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
            familia.setEnergiaGenerada(Float.parseFloat(map.get("energiaGenerada").toString()));
            familia.setUsoGenerador(map.get("usoGenerador").toString());

            familiaService.save();

            res.put("estado", "success");
            res.put("data", "Registro guardado con éxito.");
            return Response.ok(res).build();
        } catch (IllegalArgumentException e) {
            res.put("estado", "error");
            res.put("data", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }*/

    @POST
@Produces(MediaType.APPLICATION_JSON)
@Path("/save")
public Response save(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();
    try {
        // Validación del nombre
        if (!map.containsKey("nombre") || map.get("nombre").toString().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        // Validación del presupuesto
        float presupuesto = 0;
        if (map.containsKey("presupuesto") && !map.get("presupuesto").toString().isEmpty()) {
            presupuesto = Float.parseFloat(map.get("presupuesto").toString());
        }
        if (presupuesto <= 0 && Boolean.parseBoolean(map.get("tieneGenerador").toString())) {
            throw new IllegalArgumentException("No se puede obtener un generador sin un presupuesto válido.");
        }

        // Asignación de valores a la familia
        Familia familia = familiaService.getFamilia();
        familia.setNombre(map.get("nombre").toString());
        familia.setTieneGenerador(Boolean.parseBoolean(map.get("tieneGenerador").toString()));
        familia.setPresupuesto(presupuesto);
        familia.setCostoGenerador(Float.parseFloat(map.get("costoGenerador").toString()));
        familia.setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
        familia.setEnergiaGenerada(Float.parseFloat(map.get("energiaGenerada").toString()));
        familia.setUsoGenerador(map.get("usoGenerador").toString());

        familiaService.save();

        res.put("estado", "success");
        res.put("data", "Registro guardado con éxito.");
        return Response.ok(res).build();
    } catch (IllegalArgumentException e) {
        res.put("estado", "error");
        res.put("data", e.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
        res.put("estado", "error");
        res.put("data", "Error interno del servidor: " + e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            Familia familia = familiaService.getFamilia();
            familia.setId(String.valueOf(id));
            familiaService.delete();
            res.put("estado", "success");
            res.put("data", "Registro eliminado con éxito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response update(HashMap<String, Object> map, @PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            Familia familia = familiaService.getFamilia();
            familia.setId(String.valueOf(id));
            
            familia.setNombre(map.get("nombre").toString());
            familia.setTieneGenerador(Boolean.parseBoolean(map.get("tieneGenerador").toString()));
            familia.setPresupuesto(Float.parseFloat(map.get("presupuesto").toString()));
            familia.setCostoGenerador(Float.parseFloat(map.get("costoGenerador").toString()));
            familia.setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
            familia.setEnergiaGenerada(Float.parseFloat(map.get("energiaGenerada").toString()));
            familia.setUsoGenerador(map.get("usoGenerador").toString());

            familiaService.update();

            res.put("estado", "success");
            res.put("data", "Registro actualizado con éxito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
