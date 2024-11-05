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

import com.practica.controller.dao.services.GeneradorServices;
import com.practica.controller.excepcion.ListEmptyException;

@Path("/generador")
public class GeneradorApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllGenerador() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        try {
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con éxito.");
            res.put("data", gs.listAll().toArray());
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
    public Response getGeneradorById(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        try {
            String jsonResponse = gs.getGeneradorJsonByIdIndex(id);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response saveGenerador(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        try {
            // Validación de campos obligatorios
            if (map.get("precio") == null || map.get("consumoPorHora") == null || map.get("energiaGenerada") == null) {
                throw new IllegalArgumentException("Campos obligatorios no proporcionados.");
            }
            
            gs.getGenerador().setPrecio(Float.parseFloat(map.get("precio").toString()));
            gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
            gs.getGenerador().setEnergiaGenerada(Float.parseFloat(map.get("energiaGenerada").toString()));
            gs.getGenerador().setUso(map.get("uso") != null ? map.get("uso").toString() : "No especificado");
            
            gs.save();
            res.put("estado", "success");
            res.put("data", "Generador guardado con éxito.");
            return Response.ok(res).build();
        } catch (IllegalArgumentException e) {
            res.put("estado", "error");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response deleteGenerador(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        try {
            gs.getGenerador().setId(String.valueOf(id));
            gs.delete();
            res.put("estado", "success");
            res.put("data", "Generador eliminado con éxito.");
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
    public Response updateGenerador(HashMap<String, Object> map, @PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        try {
            // Validación de parámetros de entrada
            if (map == null || !map.containsKey("precio") || !map.containsKey("consumoPorHora") || !map.containsKey("energiaGenerada")) {
                res.put("estado", "error");
                res.put("data", "Parámetros insuficientes.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }
            
            // Asignación de valores
            gs.getGenerador().setId(String.valueOf(id));
            gs.getGenerador().setPrecio(Float.parseFloat(map.get("precio").toString()));
            gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
            gs.getGenerador().setEnergiaGenerada(Float.parseFloat(map.get("energiaGenerada").toString()));
            gs.getGenerador().setUso(map.get("uso") != null ? map.get("uso").toString() : "No especificado");

            // Actualización del generador
            gs.update();
            res.put("estado", "success");
            res.put("data", "Generador actualizado con éxito.");
            return Response.ok(res).build();
        } catch (NumberFormatException e) {
            res.put("estado", "error");
            res.put("data", "Error en el formato de los datos: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
