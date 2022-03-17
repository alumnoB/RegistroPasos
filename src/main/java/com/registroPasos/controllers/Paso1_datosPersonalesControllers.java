package com.registroPasos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.registroPasos.colecciones.Colecciones;
import com.registroPasos.entities.Paso1_datosPersonales;
import com.registroPasos.entities.Paso2_datosProfesionales;
import com.registroPasos.entities.Paso3_datosBancarios;
import com.registroPasos.service.IDatosBancariosService;
import com.registroPasos.service.IDatosPersonalesService;
import com.registroPasos.service.IDatosProfesionalesService;


@Controller
@RequestMapping("/acceso")
public class Paso1_datosPersonalesControllers {
	
	@Autowired
	private IDatosPersonalesService datosPersonalesService;
	
	@Autowired
	private IDatosProfesionalesService datosProfesionalesService;
	
	@Autowired
	private IDatosBancariosService DatosBancariosService;
	
	@GetMapping("/datosPersonales")
	public String datosPersonales(Model model) {
		model.addAttribute("datosPersonales", new Paso1_datosPersonales());
		model.addAttribute("listaGenerosOrdenada", Colecciones.listaGenerosOrdenada);
		model.addAttribute("listaParejaOrdenada", Colecciones.listaParejaOrdenada);
		model.addAttribute("listaNacionalidadesOrdenada", Colecciones.listaNacionalidadesOrdenada);
		model.addAttribute("datosNavegacion", Colecciones.datosNavegacion);
		
		return "datosPersonales";
	}
	
	
		
	@PostMapping("/save-datosPersonales") 
	public String saveActors(Paso1_datosPersonales datosPersonales,RedirectAttributes redirect, Model model) {
				
		datosPersonalesService.saveDatosPersonales(datosPersonales);
		redirect.addFlashAttribute("datosPersonalesGuardado","Los Datos Personales se han guardado");

		return "redirect:/acceso/datosProfesionales";
	}
	
	 @GetMapping("/datosProfesionales") 
	 public String actorsForm(Paso2_datosProfesionales datoProfesionale, Model
			  model) {
				  
		 model.addAttribute("datosProfesionales", new Paso2_datosProfesionales()); 
		 model.addAttribute("listaDepertamentosOrdenada", Colecciones.listaDepertamentosOrdenada);		  
		 model.addAttribute("datosNavegacion", Colecciones.datosNavegacion);  
		 return "datosProfesionales"; 
	}
	 
	 @PostMapping("/save-datosProfesionales") 
	 public String saveActors(Paso2_datosProfesionales datoProfesionale,RedirectAttributes redirect, Model model, Paso1_datosPersonales datosPersonales) {
		
		datosProfesionalesService.saveDatosProfesionales(datoProfesionale);
		redirect.addFlashAttribute("datosProfesionalesGuardado","Los Datos Profesionales se han guardado");

		return "redirect:/acceso/datosBancarios-form"; 
	}

	@GetMapping("/datosBancarios") 
	 public String actorsForm(Paso3_datosBancarios datosBancarios, Model
			  model) {
				  
		 model.addAttribute("datosBancarios", new Paso3_datosBancarios()); 	  
		 model.addAttribute("datosNavegacion", Colecciones.datosNavegacion);	  
		 return "datosBancarios"; 
	}
	
	@PostMapping("/save-datosBancarios") 
	 public String saveActors(Paso3_datosBancarios datosBancarios,RedirectAttributes redirect, Model model, Paso1_datosPersonales datosPersonales) {
		
		DatosBancariosService.saveDatosBancarios(datosBancarios);
		redirect.addFlashAttribute("datosBancariosGuardado","Los Datos Bancarios se han guardado");

		return "redirect:/acceso/resumen"; 
	}
}
