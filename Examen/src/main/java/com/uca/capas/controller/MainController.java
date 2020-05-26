package com.uca.capas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		return mav;
		
	}
	
	@RequestMapping("/indexCategoria")
	public ModelAndView indexCategoria() {
		Categoria categoria = new Categoria();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", categoria);
		mav.setViewName("indexCategoria");
		
		return mav;
	}
	
	
	@RequestMapping(value="/saveCategoria")
	public ModelAndView saveCategoria(Categoria categoria) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("categoria", categoria);
		try {
			
			categoriaService.insert(categoria);
			mav.setViewName("exitocategoria");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mav;
		
	}
	
	
}
