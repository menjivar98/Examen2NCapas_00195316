package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
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
	public ModelAndView saveCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", categoria);
		
		if(result.hasErrors()) {
			mav.setViewName("indexCategoria");
		}else{
				
			try {
				
				categoriaService.insert(categoria);
				mav.setViewName("exitocategoria");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		
		return mav;
	}
	
	@RequestMapping("/indexLibro")
	public ModelAndView indexLibro() {
		ModelAndView mav = new ModelAndView();
		
		Libro libro = new Libro();
		List <Categoria> categorias = null;
		
		try {
			categorias = categoriaService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("categorias",categorias);
		mav.addObject("libro", libro);
		mav.setViewName("indexlibro");
		
		return mav;
		
		
	}
	
	@RequestMapping("/savelibro")
	public ModelAndView savelibro(@Valid @ModelAttribute Libro libro,BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			
			List<Categoria> categorias = null;
			
			try {
				
				categorias = categoriaService.findAll();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			mav.addObject("categorias", categorias);
			mav.setViewName("indexlibro");
		}else {
			try {
				libroService.insert(libro);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			libro = new Libro();
			mav.setViewName("exitolibro");
		}
		
		
		return mav;
	}
	
	@RequestMapping("/listalibros")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List <Libro> libros = null;
		
		try {
			
			libros = libroService.findAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		
		return mav;
	}
	
	
}
