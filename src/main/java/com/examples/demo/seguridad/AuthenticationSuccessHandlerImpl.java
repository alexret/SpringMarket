package com.examples.demo.seguridad;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.entidades.User;
import com.example.demo.servicios.UserServicio;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private UserServicio userServicio;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = request.getSession();
		User authUser = userServicio.findByUsername(userDetails.getUsername());
		session.setAttribute("username", authUser.getNombre());
		session.setAttribute("idUsuario", authUser.getIdUsuarios());
//		handle(request, response, authentication);
//		clearAuthenticationAttributes(request);
		
		
		//FALTA CREAR LAS TABLAS DE ROLES Y ADAPTAR ESTE CÓDIGO
		boolean isProfesor = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROL_PROFESOR")) {
				isProfesor = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
				isAdmin = true;
				break;
			}
		}
		String targetUrl;
		if (isProfesor) {
			targetUrl = "/profesor/myprofile";
		} else if (isAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);

	}

}
